package com.codepath.apps.trackabuddy.networking;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.codepath.apps.trackabuddy.R;
import com.codepath.apps.trackabuddy.TrackABuddyApp;
import com.codepath.apps.trackabuddy.models.Buddy;
import com.codepath.apps.trackabuddy.models.Profile;
import com.parse.CountCallback;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;

public class ParseClient {
	public void getBuddy(String buddyId) {
		// Specify which class to query
		ParseQuery<Buddy> query = ParseQuery.getQuery(Buddy.class);
		// Specify the object id
		query.getInBackground(buddyId, new GetCallback<Buddy>() {
			public void done(Buddy item, ParseException e) {
				if (e == null) {
					// Access data using the `get` methods for the object
					String name = item.getBuddyScreenName();
					String imgUrl = item.getImgUrl();
					String city = item.getCity();
					Long distance = item.getDistance();

					// Access special values that are built-in to each object
					String objectId = item.getObjectId();
					Date createdAt = item.getCreatedAt();
					Date updatedAt = item.getUpdatedAt();

					// Do whatever you want with the data...
					// Toast.makeText(MainActivity.this, name + " " + city + " "
					// + distance,
					// Toast.LENGTH_SHORT).show();
				} else {
					Log.d("item", "Error: " + e.getMessage());
				}
			}
		});
	}

	public void addBuddy(final String userId, final String buddyId,
			final String buddyScreenName, final String imgUrl,
			final Boolean trackingNow) {
		ParseQuery<Buddy> count_query = ParseQuery.getQuery("Buddy");
		count_query.countInBackground(new CountCallback() {
			public void done(int count, ParseException e) {
				if (e == null) {
					if (count == 0) {
						Buddy buddy = new Buddy(userId, buddyId,
								buddyScreenName, imgUrl, trackingNow);
						buddy.saveInBackground();
					} else {
						Log.d("error",
								"Inserting a duplicate entry to Buddy table");
					}
				} else {
					Log.d("error", "Error: " + e.getMessage());
				}
			}
		});
	}

	public void updateProfileImage(byte[] image) {

		// Upload the image into Parse Cloud
		final ParseFile parseFile = new ParseFile(TrackABuddyApp.userId + "_profile_img.png", image);
		parseFile.saveInBackground();

		// Save imageUrl in the user's profile
		ParseQuery<Profile> query = new ParseQuery<Profile>("Profile");
		query.whereEqualTo("userId", TrackABuddyApp.userId);
		query.findInBackground(new FindCallback<Profile>() {

			@Override
			public void done(List<Profile> profileObjs, ParseException e) {
				if (e == null) {
					if (profileObjs.size() == 1) {
						profileObjs.get(0).put("imgUrl", parseFile.getUrl());
						profileObjs.get(0).saveInBackground();

					} else {
						Log.d("error",
								"Error: Multiple profiles exist for userId:"
										+ TrackABuddyApp.userId);
					}
				} else {
					e.printStackTrace();
				}
			}
		});
	}

}
