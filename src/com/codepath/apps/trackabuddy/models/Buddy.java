package com.codepath.apps.trackabuddy.models;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import com.parse.ParseClassName;
import com.parse.ParseFacebookUtils.Permissions.User;
import com.parse.ParseObject;

@ParseClassName("Buddy")
public class Buddy extends ParseObject {
	// subclass should have a public default constructor
	public Buddy() {
		super();
	}

	public Buddy(String userId, String buddyId, String buddyScreenName,
			String imgUrl, String city) {
		put("userId", userId);
		put("buddyId", buddyId);
		put("buddyScreenName", buddyScreenName);
		put("imgUrl", imgUrl);
		// put("location", location);
		put("city", city);
	}

	public Buddy(String userId, String buddyId, String buddyScreenName,
			String imgUrl, String city, Long distance) {
		put("userId", userId);
		put("buddyId", buddyId);
		put("buddyScreenName", buddyScreenName);
		put("imgUrl", imgUrl);
		// put("location", location);
		put("city", city);
		put("distance", distance);
	}

	public String getObjectId() {
		return getString("objectId");
	}

	public String getUserId() {
		return getString("userId");
	}

	public String getBuddyUserId() {
		return getString("buddyUserId");
	}

	public String getBuddyScreenName() {
		return getString("buddyScreenName");
	}

	public String getImgUrl() {
		return getString("imgUrl");
	}

	public String getCity() {
		return getString("city");
	}

	public Long getDistance() {
		return getLong("distance");
	}

	public Date getCreatedAt() {
		// TODO Auto-generated method stub
		return getDate("createdAt");
	}

	public Date getUpdatedAt() {
		// TODO Auto-generated method stub
		return getDate("updatedAt");
	}
}
