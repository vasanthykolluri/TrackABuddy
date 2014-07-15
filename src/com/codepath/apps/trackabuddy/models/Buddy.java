package com.codepath.apps.trackabuddy.models;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import com.parse.ParseClassName;
import com.parse.ParseFacebookUtils.Permissions.User;
import com.parse.ParseObject;

@ParseClassName("Buddy")
public class Buddy extends ParseObject {
	//subclass should have a public default constructor
	public Buddy() {
		super();
	}
	
	public Buddy(String name, String imgUrl, String city) {
		put("name", name);
		put("imgUrl", imgUrl);
		//put("location", location);
		put("city", city);
	}
	
	public Buddy(String name, String imgUrl, String city, Long distance) {
		put("name", name);
		put("imgUrl", imgUrl);
		put("city", city);
		put("distance", distance);
	}
	
	public String getId() {
		return getString("objectId");
	}
	
	public String getName() {
		return getString("name");
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
