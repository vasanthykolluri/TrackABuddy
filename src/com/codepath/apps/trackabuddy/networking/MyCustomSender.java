package com.codepath.apps.trackabuddy.networking;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.codepath.apps.trackabuddy.TrackABuddyApp;
import com.codepath.apps.trackabuddy.models.TrackReq;
import com.codepath.apps.trackabuddy.models.TrackReqResp;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.SendCallback;

public class MyCustomSender {
	private static final String TAG = "MyCustomSender";

	public static void sendTrackReq(String senderId, String senderName,
			String receiverId) {

		JSONObject obj;
		try {
			obj = new JSONObject();

			obj.put("action", MyCustomReceiver.intentActionTrackReq);
			obj.put("alert", "Hello there! " + senderName
					+ " here...");
			TrackReq trackReq = new TrackReq(senderId, senderName, "dummyImgUrl", receiverId);
			obj.put("trackReq", TrackReq.toJson(trackReq));

			ParsePush push = new ParsePush();
			ParseQuery query = ParseInstallation.getQuery();

			// Push the notification to Android users
			query.whereEqualTo("deviceType", "android");
			push.setQuery(query);

			// Push the notification to a specific user's channel
			push.setChannel(MyUtils.getChannelName(receiverId));
			push.setData(obj);
			push.sendInBackground(new SendCallback() {

				@Override
				public void done(ParseException arg0) {
					// Toast.makeText(C(),
					// "Sent GROUP_ADD_REQ", Toast.LENGTH_LONG).show();
				}

			});
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public static void sendTrackReqResp(TrackReq trackReq,
			boolean response) {

		JSONObject obj;
		try {
			obj = new JSONObject();
			obj.put("action", MyCustomReceiver.intentActionTrackReqResp);
			TrackReqResp trackReqResp = new TrackReqResp(
					TrackABuddyApp.userId, TrackABuddyApp.userName, "dummyImgUrl",
					trackReq.getSenderId(),
					response);
			obj.put("alert",
					"TrackABuddy! Response from " + trackReqResp.getSenderName());
			obj.put("trackReqResp", TrackReqResp.toJson(trackReqResp));

			Log.d(TAG, "Resp Sender " + trackReqResp.getSenderId() + " "
					+ trackReqResp.getSenderName());
			Log.d(TAG, "Resp Rcvr " + trackReqResp.getReceiverId());

			ParsePush push = new ParsePush();
			ParseQuery query = ParseInstallation.getQuery();
			query.whereEqualTo("deviceType", "android");
			push.setQuery(query);
			
			// Send response on sender's channel
			push.setChannel(MyUtils.getChannelName(trackReqResp
					.getReceiverId()));
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
