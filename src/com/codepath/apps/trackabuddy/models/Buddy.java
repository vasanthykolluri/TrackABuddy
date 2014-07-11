package com.codepath.apps.trackabuddy.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Buddy")
public class Buddy extends ParseObject {
	//subclass should have a public default constructor
	public Buddy() {
		super();
	}
	
	public Buddy(String name, String imgUrl) {
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
