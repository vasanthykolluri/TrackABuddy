package com.codepath.apps.trackabuddy.models;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class ProfileUpdate implements Serializable {

	private static final long serialVersionUID = -3381564301440246907L;
	
	private String senderId;
	private String receiverId;
	private String senderName;
	private String senderImgUrl;

	public ProfileUpdate() {
		// TODO Auto-generated constructor stub
	}

	public ProfileUpdate(String senderId, String receiverId, String senderName,
			String senderImgUrl) {
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.senderName = senderName;
		this.senderImgUrl = senderImgUrl;
	}

	public String getSenderId() {
		return senderId;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public String getSenderName() {
		return senderName;
	}

	public String getSenderUrl() {
		return senderImgUrl;
	}

	public static ProfileUpdate fromJson(JSONObject jsonObject) {
		ProfileUpdate profileUpdate = new ProfileUpdate();

		try {
			profileUpdate.senderId = jsonObject.getString("senderId");
			profileUpdate.receiverId = jsonObject.getString("receiverId");
			profileUpdate.senderName = jsonObject.getString("senderName");
			profileUpdate.senderImgUrl = jsonObject.getString("senderImgUrl");
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}

		return profileUpdate;
	}

	public static JSONObject toJson(ProfileUpdate profileUpdate) {
		JSONObject jsonObject = new JSONObject();

		try {
			jsonObject.put("senderId", profileUpdate.senderId);
			jsonObject.put("receiverId", profileUpdate.receiverId);
			jsonObject.put("senderName", profileUpdate.senderName);
			jsonObject.put("senderImgUrl", profileUpdate.senderImgUrl);

		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}

		return jsonObject;
	}
}
