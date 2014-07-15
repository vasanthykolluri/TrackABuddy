package com.codepath.apps.trackabuddy;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;
import android.view.Menu;
import android.widget.Toast;

import com.example.trackabuddy.R;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.ParseQuery;

public class MainActivity extends FragmentActivity {

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
		sendSamplePush();
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

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.contacts_settings, menu);
        return true;
    }
}
