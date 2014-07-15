package com.codepath.apps.trackabuddy.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Profile")
public class Profile extends ParseObject {
	// subclass should have a public default constructor
	public Profile() {
		super();
	}

	public Profile(String name, String imgUrl, String phone) {
		put("name", name);
		put("imgUrl", imgUrl);
        put("phone", phone );
	}

	public String getName() {
		return getString("name");
	}

	public String getImgUrl() {
		return getString("imgUrl");
	}

    public String getPhone() { return getString("phone");}
}
