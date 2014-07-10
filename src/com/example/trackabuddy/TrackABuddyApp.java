package com.example.trackabuddy;

import android.app.Application;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.PushService;

public class TrackABuddyApp extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		Toast.makeText(this, "onAppCreate", Toast.LENGTH_LONG).show();
		// Register your parse models
		ParseObject.registerSubclass(Buddy.class);
		ParseObject.registerSubclass(Profile.class);

		// Add your initialization code here
		Parse.initialize(this, "0x2akUUbhpwPM2eiE3rvXeFi7kGapSQufzBTluHk",
				"Fj3d6aN04KvRIVZjPGsJmMgNgIIWKzYjrzChsK02");

		PushService.setDefaultPushCallback(this, MainActivity.class);
		ParseInstallation.getCurrentInstallation().saveInBackground();

	}

}
