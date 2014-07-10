package com.example.trackabuddy;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Settings")
public class Settings extends ParseObject {
	//subclass should have a public default constructor
	public Settings() {
		super();
	}
	
	public Settings(Integer distanceUnits, Boolean iconEnable, String buddyIconUrl, String profileIconUrl) {
		put("distanceUnits", distanceUnits);
		put("iconEnable", iconEnable);
		put("buddyIconUrl", buddyIconUrl);
		put("profileIconUrl", profileIconUrl);
	}
	
	public Integer getDistanceUnits() {
		return getInt("distanceUnits");
	}	
	
	public Boolean getIconEnable() {
		return getBoolean("iconEnable");
	}	
	
	public String getBuddyIconUrl() {
		return getString("buddyIconUrl");
	}	
	
	public String getProfileIconUrl() {
		return getString("profileIconUrl");
	}	

}
