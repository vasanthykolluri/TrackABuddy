package com.codepath.apps.trackabuddy;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

import com.codepath.apps.trackabuddy.fragments.BuddyListFragment;
import com.codepath.apps.trackabuddy.fragments.BuddyMapFragment;
import com.codepath.apps.trackabuddy.listeners.FragmentTabListener;
import com.example.trackabuddy.R;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.ParseQuery;

public class MainActivity extends FragmentActivity {
	
	BuddyListFragment buddyListFragment;
	BuddyMapFragment buddyMapFragment;

	private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {        	
        	Toast.makeText(getApplicationContext(), "onReceive invoked!", Toast.LENGTH_LONG).show();
        }
    };
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setupTabs();
		sendSamplePush();
	}
	
	private void setupTabs() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(true);

		Tab tab1 = actionBar
			.newTab()
			.setText("Map")
			.setIcon(R.drawable.ic_launcher)
			.setTag("BuddyMapFragment")
			.setTabListener(
				new FragmentTabListener<BuddyMapFragment>(R.id.flContainer, this, "first",
								BuddyMapFragment.class));

		actionBar.addTab(tab1);
		actionBar.selectTab(tab1);

		Tab tab2 = actionBar
			.newTab()
			.setText("Buddy List")
			.setIcon(R.drawable.ic_launcher)
			.setTag("BuddyListFragment")
			.setTabListener(
			    new FragmentTabListener<BuddyListFragment>(R.id.flContainer, this, "second",
			    		BuddyListFragment.class));

		actionBar.addTab(tab2);
	}
	
	@Override
    public void onPause() {
        super.onPause();

        LocalBroadcastManager.getInstance(this).unregisterReceiver(mBroadcastReceiver);
    }
    
	@Override
    public void onResume() {
        super.onResume();
        
        LocalBroadcastManager.getInstance(this).registerReceiver(mBroadcastReceiver, new IntentFilter(MyCustomReceiver.intentAction));
    }
	
	public void sendSamplePush() {

		JSONObject obj;
		try {
			obj = new JSONObject();
			obj.put("alert", "hello!");
			obj.put("action", MyCustomReceiver.intentAction);
			obj.put("customdata","My message");

			ParsePush push = new ParsePush();
			ParseQuery query = ParseInstallation.getQuery();

			// Push the notification to Android users
			query.whereEqualTo("deviceType", "android");
			push.setQuery(query);
			push.setData(obj);
			push.sendInBackground(); 
		} catch (JSONException e) {

			e.printStackTrace();
		}
	}
}
