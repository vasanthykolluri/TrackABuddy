package com.codepath.apps.trackabuddy.networking;

import java.util.Date;

import android.content.Context;
import android.util.Log;

import com.codepath.apps.trackabuddy.models.Buddy;
import com.parse.CountCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
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
			final String buddyScreenName, final String imgUrl, final Boolean trackingNow) {
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

	public static ParseClient getInstance(Class<ParseClient> class1,
			Context context) {
		// TODO Auto-generated method stub
		return null;
	}

}
