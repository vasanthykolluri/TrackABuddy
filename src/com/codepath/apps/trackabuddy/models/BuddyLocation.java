package com.codepath.apps.trackabuddy.models;

import org.json.JSONException;
import org.json.JSONObject;

import com.parse.ParseFacebookUtils.Permissions.User;

public class BuddyLocation {

	private String name;
	private String imgUrl;
	private String city;
	
	public BuddyLocation() {
		// TODO Auto-generated constructor stub
	}
	
	public BuddyLocation(String name, String imgUrl, String city) {
		this.name = name;
		this.imgUrl = imgUrl;
		this.city = city;
	}
	
	public String getName() {
		return name;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public String getCity() {
		return city;
	}
	
	public static BuddyLocation fromJson(JSONObject jsonObject) {
		BuddyLocation buddyLocation = new BuddyLocation();

		try {
			buddyLocation.name = jsonObject.getString("name");
			buddyLocation.imgUrl = jsonObject.getString("imgUrl");
			buddyLocation.city = jsonObject.getString("city");
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}

		return buddyLocation;
	}
	
	public static JSONObject toJson(BuddyLocation buddyLocation) {
		JSONObject jsonObject = new JSONObject();

		try {
			jsonObject.put("name", buddyLocation.name);
			jsonObject.put("imgUrl", buddyLocation.imgUrl);
			jsonObject.put("city", buddyLocation.city);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}

		return jsonObject;
	}
}
