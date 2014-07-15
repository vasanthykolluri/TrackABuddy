package com.codepath.apps.trackabuddy;

import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.codepath.apps.trackabuddy.models.Buddy;
import com.codepath.apps.trackabuddy.models.BuddyLocation;
import com.codepath.apps.trackabuddy.networking.ParseClient;

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
					// Listen on a specific channel
					if (channel.equals(TrackABuddyApp.userName)) {
						JSONObject json = new JSONObject(intent.getExtras()
								.getString("com.parse.Data"));

						Log.d(TAG, "got action " + action + " on channel "
								+ channel);
						String sender = json.getString("sender");
						String message = json.getString("message");

						// Handle push notification by invoking activity
						// directly
						Intent pupInt = new Intent(context,
								HandleTrackReqActivity.class);
						pupInt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						pupInt.putExtra("sender", sender);
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
					// Listen on a specific channel
					if (channel.equals(TrackABuddyApp.userName)) {

						boolean acceptFlag = json.getBoolean("acceptFlag");

						// Handle push notification by invoking activity
						// directly

						if (acceptFlag == true) {
							BuddyLocation buddyLocation = BuddyLocation
									.fromJson(json
											.getJSONObject("buddyLocation"));
							parseClient.addBuddy(buddyLocation.getName(),
									buddyLocation.getImgUrl(),
									buddyLocation.getCity());
							Toast.makeText(context.getApplicationContext(),
									"Track Req Response - ACCEPTED",
									Toast.LENGTH_LONG).show();
							// Intent pupRespInt = new Intent(context,
							// ShowPopUpResponse.class);
							// pupRespInt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							// pupRespInt.putExtra("response",
							// response);
							// context.getApplicationContext().startActivity(
							// pupRespInt);
						} else {
							Toast.makeText(context.getApplicationContext(),
									"Track Req Response - DECLINED",
									Toast.LENGTH_LONG).show();
						}

						Iterator<String> itr = json.keys();
						// while (itr.hasNext()) {
						// String key = (String) itr.next();
						// if (key.equals("acceptFlag")) {
						// // Handle push notification by invoking activity
						// // directly
						//
						// boolean acceptFlag = json
						// .getBoolean("acceptFlag");
						// if (acceptFlag == true) {
						// parseClient.addBuddy("newName", "newUrl",
						// "newCity", (long) 10);
						// Toast.makeText(
						// context.getApplicationContext(),
						// "Track Req Response - ACCEPTED",
						// Toast.LENGTH_LONG).show();
						// // Intent pupRespInt = new Intent(context,
						// // ShowPopUpResponse.class);
						// //
						// pupRespInt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						// // pupRespInt.putExtra("response",
						// // response);
						// // context.getApplicationContext().startActivity(
						// // pupRespInt);
						// } else {
						// Toast.makeText(
						// context.getApplicationContext(),
						// "Track Req Response - DECLINED",
						// Toast.LENGTH_LONG).show();
						// }
						// }
						// Log.d(TAG,
						// "..." + key + " => " + json.getString(key));
						// }
					}
				}
			}
		} catch (JSONException e) {
			Log.d(TAG, "JSONException: " + e.getMessage());
		}
	}
}