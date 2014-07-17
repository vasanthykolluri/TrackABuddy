package com.codepath.apps.trackabuddy;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.codepath.apps.trackabuddy.models.BuddyLocation;

public class MyCustomReceiver extends BroadcastReceiver {
	private static final String TAG = "MyCustomReceiver";

	public static final String intentActionTrackReq = "TRACK_REQ";
	public static final String intentActionTrackReqResp = "TRACK_REQ_RESP";

	@Override
	public void onReceive(Context context, Intent intent) {
		//Toast.makeText(context.getApplicationContext(), "MyCustomReceiver - onReceive", Toast.LENGTH_LONG).show();
		try {
			if (intent == null) {
				Log.d(TAG, "Receiver intent null");
			} else {
				String action = intent.getAction();
				Log.d(TAG, "got action " + action);
				if (action.equals(intentActionTrackReq)) {
					String channel = intent.getExtras().getString(
							"com.parse.Channel");
					// Filter on user's channel
					if (channel.equals(TrackABuddyApp.userName)) {
						JSONObject json = new JSONObject(intent.getExtras()
								.getString("com.parse.Data"));

						Log.d(TAG, "got action " + action + " on channel "
								+ channel);
						BuddyLocation senderLocation = BuddyLocation
								.fromJson(json.getJSONObject("senderLocation"));
						String message = json.getString("message");

						// Handle push notification by invoking activity
						// directly
						Intent pupInt = new Intent(context,
								HandleTrackReqActivity.class);
						pupInt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						pupInt.putExtra("senderLocation", senderLocation);
						pupInt.putExtra("message", message);
						context.getApplicationContext().startActivity(pupInt);

						// Handle push notification by sending a local
						// broadcast
						// to which the activity
						// subscribes to
						// LocalBroadcastManager.getInstance(context)
						// .sendBroadcast(
						// new Intent(intentActionTrackReq));

					}
				} else if (action.equals(intentActionTrackReqResp)) {
					String channel = intent.getExtras().getString(
							"com.parse.Channel");
					JSONObject json = new JSONObject(intent.getExtras()
							.getString("com.parse.Data"));

					Log.d(TAG, "got action " + action + " on channel "
							+ channel);
					// Filter on user's channel
					if (channel.equals(TrackABuddyApp.userName)) {

						boolean acceptFlag = json.getBoolean("acceptFlag");
						BuddyLocation buddyLocation = BuddyLocation
								.fromJson(json.getJSONObject("buddyLocation"));

						// Handle push notification by invoking activity
						// directly

						if (acceptFlag == true) {
							// Add buddy to db
							TrackABuddyApp.parseClient.addBuddy(
									buddyLocation.getName(),
									buddyLocation.getImgUrl(),
									buddyLocation.getCity());
						}

						Intent pupRespInt = new Intent(context,
								ShowPopUpResponse.class);
						pupRespInt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						pupRespInt.putExtra("acceptFlag", acceptFlag);
						pupRespInt.putExtra("buddyName",
								buddyLocation.getName());
						context.getApplicationContext().startActivity(
								pupRespInt);
					}
				}
			}
		} catch (JSONException e) {
			Log.d(TAG, "JSONException: " + e.getMessage());
		}
	}
}