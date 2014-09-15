package com.codepath.apps.trackabuddy.models;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class LocationUpdate implements Serializable {

	private static final long serialVersionUID = 6504849876860316528L;

	private String senderId;
	private String receiverId;
	private double latitude;
	private double longitude;

	public LocationUpdate() {
		// TODO Auto-generated constructor stub
	}

	public LocationUpdate(String senderId, String receiverId, double latitude,
			double longitude) {
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getSenderId() {
		return senderId;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public static LocationUpdate fromJson(JSONObject jsonObject) {
		LocationUpdate locationUpdate = new LocationUpdate();

		try {
			locationUpdate.senderId = jsonObject.getString("senderId");
			locationUpdate.receiverId = jsonObject.getString("receiverId");
			locationUpdate.latitude = jsonObject.getDouble("latitude");
			locationUpdate.longitude = jsonObject.getDouble("longitude");
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}

		return locationUpdate;
	}

	public static JSONObject toJson(LocationUpdate locationUpdate) {
		JSONObject jsonObject = new JSONObject();

		try {
			jsonObject.put("senderId", locationUpdate.senderId);
			jsonObject.put("receiverId", locationUpdate.receiverId);
			jsonObject.put("latitude", locationUpdate.latitude);
			jsonObject.put("longitude", locationUpdate.longitude);

		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}

		return jsonObject;
	}
}
