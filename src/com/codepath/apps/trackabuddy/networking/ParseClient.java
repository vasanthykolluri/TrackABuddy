package com.codepath.apps.trackabuddy.networking;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.codepath.apps.trackabuddy.models.Buddy;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
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

	public void addBuddy(String name, String imgUrl, String city) {
		// Specify which class to query
		ParseQuery<Buddy> query = ParseQuery.getQuery(Buddy.class);
		Buddy buddy = new Buddy("dummyUserId", "DummyBuddyId", name, imgUrl, city);
		buddy.saveInBackground();
	}

	public static ParseClient getInstance(Class<ParseClient> class1,
			Context context) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
