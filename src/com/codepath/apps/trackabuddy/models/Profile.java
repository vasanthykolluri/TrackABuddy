package com.codepath.apps.trackabuddy.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Profile")
public class Profile extends ParseObject {
	// subclass should have a public default constructor
	public Profile() {
		super();
	}

	public Profile(String userId, String screenName, String phone) {
		put("userId", userId);
		put("screenName", screenName);
		put("phone", phone);
	}

	public String getUserId() {
		return getString("userId");
	}

	public String getScreenName() {
		return getString("screenName");
	}

	public String getImgUrl() {
		return getString("imgUrl");
	}

	public String getPhone() {
		return getString("phone");
	}
}
