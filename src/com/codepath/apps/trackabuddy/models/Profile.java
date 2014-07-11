package com.codepath.apps.trackabuddy.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Profile")
public class Profile extends ParseObject {
	// subclass should have a public default constructor
	public Profile() {
		super();
	}

	public Profile(String name, String imgUrl) {
		put("name", name);
		put("imgUrl", imgUrl);
	}

	public String getName() {
		return getString("name");
	}

	public String getImgUrl() {
		return getString("imgUrl");
	}
}
