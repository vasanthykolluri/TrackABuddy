package com.codepath.apps.trackabuddy.models;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class TrackReqResp implements Serializable {

	private static final long serialVersionUID = -6132969116774102360L;

	private String senderId;
	private String senderName;
	private String receiverId;
	private String receiverName;
	private Boolean response;

	public TrackReqResp() {
		// TODO Auto-generated constructor stub
	}

	public TrackReqResp(String senderId, String senderName,
			String receiverId, String receiverName, Boolean response) {
		this.senderId = senderId;
		this.senderName = senderName;
		this.receiverId = receiverId;
		this.receiverName = receiverName;
		this.response = response;
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

	public Boolean getResponse() {
		return response;
	}

	public static TrackReqResp fromJson(JSONObject jsonObject) {
		TrackReqResp trackReqResp = new TrackReqResp();

		try {
			trackReqResp.senderId = jsonObject.getString("senderId");
			trackReqResp.senderName = jsonObject.getString("senderName");
			trackReqResp.receiverId = jsonObject.getString("receiverId");
			trackReqResp.receiverName = jsonObject.getString("receiverName");
			trackReqResp.response = jsonObject.getBoolean("response");
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}

		return trackReqResp;
	}

	public static JSONObject toJson(TrackReqResp trackReqResp) {
		JSONObject jsonObject = new JSONObject();

		try {
			jsonObject.put("senderId", trackReqResp.senderId);
			jsonObject.put("senderName", trackReqResp.senderName);
			jsonObject.put("receiverId", trackReqResp.receiverId);
			jsonObject.put("receiverName", trackReqResp.receiverName);
			jsonObject.put("response", trackReqResp.response);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}

		return jsonObject;
	}
}
