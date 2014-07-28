package com.codepath.apps.trackabuddy.models;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class TrackReqResp implements Serializable {

	private static final long serialVersionUID = -6132969116774102360L;

	private String senderId;
	private String senderName;
	private String senderImgUrl;
	private String receiverId;
	private String receiverName;
	private Boolean response;

	public TrackReqResp() {
		// TODO Auto-generated constructor stub
	}

	public TrackReqResp(String senderId, String senderName, String senderImgUrl,
			String receiverId, Boolean response) {
		this.senderId = senderId;
		this.senderName = senderName;
		this.senderImgUrl = senderImgUrl;
		this.receiverId = receiverId;
		this.response = response;
	}

	public String getSenderId() {
		return senderId;
	}

	public String getSenderName() {
		return senderName;
	}

	public String getSenderImgUrl() {
		return senderImgUrl;
	}
	
	public String getReceiverId() {
		return receiverId;
	}

	public Boolean getResponse() {
		return response;
	}

	public static TrackReqResp fromJson(JSONObject jsonObject) {
		TrackReqResp trackReqResp = new TrackReqResp();

		try {
			trackReqResp.senderId = jsonObject.getString("senderId");
			trackReqResp.senderName = jsonObject.getString("senderName");
			trackReqResp.senderImgUrl = jsonObject.getString("senderImgUrl");
			trackReqResp.receiverId = jsonObject.getString("receiverId");
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
			jsonObject.put("senderImgUrl", trackReqResp.senderImgUrl);
			jsonObject.put("receiverId", trackReqResp.receiverId);
			jsonObject.put("response", trackReqResp.response);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}

		return jsonObject;
	}
}
