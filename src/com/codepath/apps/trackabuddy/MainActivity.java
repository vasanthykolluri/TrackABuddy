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
import android.view.View;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.SendCallback;

public class MainActivity extends FragmentActivity {

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
			obj.put("customdata", "Let's track each other. What say???");

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
}
