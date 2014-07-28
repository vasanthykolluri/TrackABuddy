package com.codepath.apps.trackabuddy.models;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class TrackReq implements Serializable {

	private static final long serialVersionUID = -9151341478106895032L;

	private String senderId;
	private String senderName;
	private String receiverId;
	private String receiverName;

	public TrackReq() {
		// TODO Auto-generated constructor stub
	}

	public TrackReq(String senderId, String senderName, String receiverId,
			String receiverName) {
		this.senderId = senderId;
		this.senderName = senderName;
		this.receiverId = receiverId;
		this.receiverName = receiverName;
	}

	public String getSenderId() {
		return senderId;
	}

	public String getSenderName() {
		return senderName;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public static TrackReq fromJson(JSONObject jsonObject) {
		TrackReq trackReq = new TrackReq();

		try {
			trackReq.senderId = jsonObject.getString("senderId");
			trackReq.senderName = jsonObject.getString("senderName");
			trackReq.receiverId = jsonObject.getString("receiverId");
			trackReq.receiverName = jsonObject.getString("receiverName");
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		return trackReq;
	}

	public static JSONObject toJson(TrackReq trackReq) {
		JSONObject jsonObject = new JSONObject();

		try {
			jsonObject.put("senderId", trackReq.senderId);
			jsonObject.put("senderName", trackReq.senderName);
			jsonObject.put("receiverId", trackReq.receiverId);
			jsonObject.put("receiverName", trackReq.receiverName);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}

		return jsonObject;
	}
}
