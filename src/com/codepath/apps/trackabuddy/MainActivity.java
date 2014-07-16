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

import android.view.Menu;
import android.view.MenuItem;

import android.view.View;

import android.widget.Toast;


import com.codepath.apps.trackabuddy.models.BuddyLocation;
import com.parse.ParseException;

import com.codepath.apps.trackabuddy.fragments.BuddyListFragment;
import com.codepath.apps.trackabuddy.fragments.BuddyMapFragment;
import com.codepath.apps.trackabuddy.listeners.FragmentTabListener;
import com.codepath.apps.trackabuddy.R;

import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.SendCallback;

public class MainActivity extends FragmentActivity {
	
	BuddyListFragment buddyListFragment;
	BuddyMapFragment buddyMapFragment;

	private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			Toast.makeText(getApplicationContext(), "onReceive invoked!",
					Toast.LENGTH_LONG).show();
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		setupTabs();
	}
	
	private void setupTabs() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(true);

		Tab tab1 = actionBar
			.newTab()
			.setText("Map")
			.setIcon(R.drawable.ic_map)
			.setTag("BuddyMapFragment")
			.setTabListener(
				new FragmentTabListener<BuddyMapFragment>(R.id.flContainer, this, "first",
								BuddyMapFragment.class));

		actionBar.addTab(tab1);
		actionBar.selectTab(tab1);

		Tab tab2 = actionBar
			.newTab()
			.setText("Buddy List")
			.setIcon(R.drawable.ic_buddy)
			.setTag("BuddyListFragment")
			.setTabListener(
			    new FragmentTabListener<BuddyListFragment>(R.id.flContainer, this, "second",
			    		BuddyListFragment.class));

		actionBar.addTab(tab2);
	}
	

	@Override
	public void onPause() {
		super.onPause();

		LocalBroadcastManager.getInstance(this).unregisterReceiver(
				mBroadcastReceiver);
	}

	@Override
	public void onResume() {
		super.onResume();

		LocalBroadcastManager.getInstance(this).registerReceiver(
				mBroadcastReceiver,
				new IntentFilter(MyCustomReceiver.intentActionTrackReq));
	}

	public void onTrackClick(View v) {
		if (v.getId() == R.id.btnTrackAkash) {
			sendTrackingRequest("akash");
		} else if (v.getId() == R.id.btnTrackSadhana) {
			sendTrackingRequest("sadhana");
		} else if (v.getId() == R.id.btnTrackVasanthy) {
			sendTrackingRequest("vasanthy");
		}
	}

	private void sendTrackingRequest(String tgtUserName) {

		JSONObject obj;
		try {
			obj = new JSONObject();
			obj.put("alert", "Hello " + tgtUserName + "," + TrackABuddyApp.userName + " here...");
			obj.put("action", MyCustomReceiver.intentActionTrackReq);
			BuddyLocation senderLocation = new BuddyLocation(
					TrackABuddyApp.userName, "example.com", "San Jose");
			obj.put("senderLocation", BuddyLocation.toJson(senderLocation));
			obj.put("message", "Let's track each other. What say???");

			ParsePush push = new ParsePush();
			ParseQuery query = ParseInstallation.getQuery();

			// Push the notification to Android users
			query.whereEqualTo("deviceType", "android");
			// Push the notification to a specific user
			//query.whereEqualTo("username", tgtUserName);
			push.setQuery(query);
			push.setChannel(tgtUserName);
			push.setData(obj);
			push.sendInBackground(new SendCallback() {

				@Override
				public void done(ParseException arg0) {
					// Toast.makeText(getApplicationContext(),
					// "Done with sending", Toast.LENGTH_LONG).show();
				}

			});
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.contacts_settings, menu);
        return true;
    }

    public void onClickContacts(MenuItem menuItem){
        Intent i = new Intent(this,ContactActivity.class);
        startActivity(i);
    }
}
