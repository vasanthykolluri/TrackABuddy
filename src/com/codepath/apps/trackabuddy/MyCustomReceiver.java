package com.codepath.apps.trackabuddy;

import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import com.codepath.apps.trackabuddy.networking.ParseClient;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

public class MyCustomReceiver extends BroadcastReceiver {
	private static final String TAG = "MyCustomReceiver";

	public static final String intentActionTrackReq = "TRACK_REQ";
	public static final String intentActionTrackReqResp = "TRACK_REQ_RESP";
	
	private ParseClient parseClient = new ParseClient();

	@Override
	public void onReceive(Context context, Intent intent) {
		try {
			if (intent == null) {
				Log.d(TAG, "Receiver intent null");
			} else {
				String action = intent.getAction();
				Log.d(TAG, "got action " + action);
				if (action.equals(intentActionTrackReq)) {
					String channel = intent.getExtras().getString(
							"com.parse.Channel");
					JSONObject json = new JSONObject(intent.getExtras()
							.getString("com.parse.Data"));

					Log.d(TAG, "got action " + action + " on channel "
							+ channel + " with:");
					Iterator<String> itr = json.keys();
					while (itr.hasNext()) {
						String key = (String) itr.next();
						if (key.equals("customdata")) {
							// Handle push notification by invoking activity
							// directly
							Intent pupInt = new Intent(context, ShowPopUp.class);
							pupInt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							pupInt.putExtra("customdata", json.getString(key));
							context.getApplicationContext().startActivity(
									pupInt);

							// Handle push notification by sending a local
							// broadcast
							// to which the activity
							// subscribes to
//							LocalBroadcastManager.getInstance(context)
//									.sendBroadcast(
//											new Intent(intentActionTrackReq));
						}
						Log.d(TAG, "..." + key + " => " + json.getString(key));
					}
				} else if (action.equals(intentActionTrackReqResp)) {
					String channel = intent.getExtras().getString(
							"com.parse.Channel");
					JSONObject json = new JSONObject(intent.getExtras()
							.getString("com.parse.Data"));

					Log.d(TAG, "got action " + action + " on channel "
							+ channel + " with:");
					Iterator<String> itr = json.keys();
					while (itr.hasNext()) {
						String key = (String) itr.next();
						if (key.equals("customdata")) {
							// Handle push notification by invoking activity
							// directly
							
							boolean response = json.getBoolean("customdata");
							if (response == true) {
								parseClient.addBuddy("newName", "newUrl", "newCity", (long) 10);
								Toast.makeText(context.getApplicationContext(),
										"Track Req Response - ACCEPTED", Toast.LENGTH_LONG)
										.show();
							} else {
								Toast.makeText(context.getApplicationContext(),
										"Track Req Response - DECLINED", Toast.LENGTH_LONG)
										.show();
							}
						}
						Log.d(TAG, "..." + key + " => " + json.getString(key));
					}
				}
			}
		} catch (JSONException e) {
			Log.d(TAG, "JSONException: " + e.getMessage());
		}
	}
}