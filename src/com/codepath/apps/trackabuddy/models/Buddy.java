package com.codepath.apps.trackabuddy.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Buddy")
public class Buddy extends ParseObject {
	//subclass should have a public default constructor
	public Buddy() {
		super();
	}
	
	public Buddy(String name, String imgUrl, String city, Long distance) {
		put("name", name);
		put("imgUrl", imgUrl);
		//put("location", location);
		put("city", city);
		put("distance", "distance");
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
	
	public String getDistance() {
		return getString("distance");
	}
}
