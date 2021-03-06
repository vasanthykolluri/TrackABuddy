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
			String imgUrl, Boolean trackingNow) {
		put("userId", userId);
		put("buddyId", buddyId);
		put("buddyScreenName", buddyScreenName);
		put("imgUrl", imgUrl);
		put("trackingNow", trackingNow);
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

	public Boolean getTrackingNow() {
		return getBoolean("trackingNow");
	}
	
	public Long getLatitude() {
		return getLong("latitude");
	}
	
	public Long getLongitude() {
		return getLong("longitude");
	}
	
	public String getCity() {
		return getString("city");
	}

	public String getCountry() {
		return getString("country");
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
