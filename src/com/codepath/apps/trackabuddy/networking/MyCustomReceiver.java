package com.codepath.apps.trackabuddy.networking;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.codepath.apps.trackabuddy.HandleTrackReqActivity;
import com.codepath.apps.trackabuddy.ShowTrackReqResp;
import com.codepath.apps.trackabuddy.TrackABuddyApp;
import com.codepath.apps.trackabuddy.models.TrackReq;
import com.codepath.apps.trackabuddy.models.TrackReqResp;

public class MyCustomReceiver extends BroadcastReceiver {
	private static final String TAG = "MyCustomReceiver";

	public static final String intentActionTrackReq = "TRACK_REQ";
	public static final String intentActionTrackReqResp = "TRACK_REQ_RESP";

	@Override
	public void onReceive(Context context, Intent intent) {
		// Toast.makeText(context.getApplicationContext(),
		// "MyCustomReceiver - onReceive", Toast.LENGTH_LONG).show();
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
					if (channel.equals(MyUtils
							.getChannelName(TrackABuddyApp.userId))) {
						JSONObject json = new JSONObject(intent.getExtras()
								.getString("com.parse.Data"));

						Log.d(TAG, "got action " + action + " on channel "
								+ channel);
						TrackReq trackReq = TrackReq.fromJson(json
								.getJSONObject("trackReq"));

						// Handle push notification by invoking activity
						// directly
						Intent pupInt = new Intent(context,
								HandleTrackReqActivity.class);
						pupInt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						pupInt.putExtra("trackReq", trackReq);
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
					if (channel.equals(MyUtils
							.getChannelName(TrackABuddyApp.userId))) {

						TrackReqResp trackReqResp = TrackReqResp.fromJson(json
								.getJSONObject("trackReqResp"));

						// Handle push notification by invoking activity
						// directly

						// if (acceptFlag == true) {
						// // Add buddy to db
						// TrackABuddyApp.parseClient.addBuddy(
						// buddyLocation.getName(),
						// buddyLocation.getImgUrl(),
						// buddyLocation.getCity());
						// }

						Intent pupRespInt = new Intent(context,
								ShowTrackReqResp.class);
						pupRespInt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						pupRespInt.putExtra("response",
								trackReqResp.getResponse());
						pupRespInt.putExtra("buddyName",
								trackReqResp.getSenderName());
						context.getApplicationContext().startActivity(
								pupRespInt);

						if (trackReqResp.getResponse() == true) {
							TrackABuddyApp.getParseClient().addBuddy(
									trackReqResp.getReceiverId(),
									trackReqResp.getSenderId(),
									trackReqResp.getSenderName(),
									trackReqResp.getSenderImgUrl(), true);
						}
					}
				}
			}
		} catch (JSONException e) {
			Log.d(TAG, "JSONException: " + e.getMessage());
		}
	}
}