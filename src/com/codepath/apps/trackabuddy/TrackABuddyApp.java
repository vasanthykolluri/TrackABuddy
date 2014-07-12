package com.codepath.apps.trackabuddy;

import android.content.Context;

import com.codepath.apps.trackabuddy.models.Buddy;
import com.codepath.apps.trackabuddy.models.Profile;
import com.codepath.apps.trackabuddy.models.Settings;
import com.codepath.apps.trackabuddy.networking.RestClient;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.PushService;

/*
 * This is the Android application itself and is used to configure various settings
 * including the image cache in memory and on disk. This also adds a singleton
 * for accessing the relevant rest client.
 * 
 *     RestClient client = TrackABuddyApp.getRestClient();
 *     // use client to send requests to API
 *     
 */
public class TrackABuddyApp extends com.activeandroid.app.Application {
	private static Context context;

	@Override
	public void onCreate() {
		super.onCreate();
		TrackABuddyApp.context = this;

		// Create global configuration and initialize ImageLoader with this
		// configuration
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
				.cacheInMemory().cacheOnDisc().build();
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				getApplicationContext()).defaultDisplayImageOptions(
				defaultOptions).build();
		ImageLoader.getInstance().init(config);
		
		// Register app with Parse
		registerParse();
	}

	private void registerParse() {
		// Register your parse models
		ParseObject.registerSubclass(Buddy.class);
		ParseObject.registerSubclass(Profile.class);
		ParseObject.registerSubclass(Settings.class);


		// Add your initialization code here
		Parse.initialize(this, "0x2akUUbhpwPM2eiE3rvXeFi7kGapSQufzBTluHk",
				"Fj3d6aN04KvRIVZjPGsJmMgNgIIWKzYjrzChsK02");

		PushService.setDefaultPushCallback(this, MainActivity.class);
		ParseInstallation.getCurrentInstallation().saveInBackground();
	}

	public static RestClient getRestClient() {
		return (RestClient) RestClient.getInstance(RestClient.class,
				TrackABuddyApp.context);
	}
}