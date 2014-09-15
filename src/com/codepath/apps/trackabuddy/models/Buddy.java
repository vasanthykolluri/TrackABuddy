package com.codepath.apps.trackabuddy.models;

import java.util.Date;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Buddy")
public class Buddy extends ParseObject {
	// subclass should have a public default constructor
	public Buddy() {
		super();
	}

	public Buddy(String userId, String buddyId, String buddyScreenName,
			String imgUrl) {
		put("userId", userId);
		put("buddyId", buddyId);
		put("buddyScreenName", buddyScreenName);
		put("imgUrl", imgUrl);
	}

	public String getObjectId() {
		return getString("objectId");
	}

	public String getUserId() {
		return getString("userId");
	}

	public String getBuddyId() {
		return getString("buddyId");
	}

	public String getBuddyScreenName() {
		return getString("buddyScreenName");
	}

	public String getImgUrl() {
		return getString("imgUrl");
	}

	public double getLatitude() {
		return getDouble("latitude");
	}
	
	public double getLongitude() {
		return getDouble("longitude");
	}
	
	public String getCity() {
		return getString("city");
	}

	public String getCountry() {
		return getString("country");
	}
	
	public void setDistance(float distance){
		put("distance", distance);
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
