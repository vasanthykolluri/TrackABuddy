package com.codepath.apps.trackabuddy.networking;

import java.sql.Date;

import android.widget.Toast;

import com.codepath.apps.trackabuddy.models.Buddy;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

public class ParseClient {
	public void getBuddyObject(String buddyId) {
		// Specify which class to query
		ParseQuery<Buddy> query = ParseQuery.getQuery(Buddy.class);
		// Specify the object id
		query.getInBackground(buddyId, new GetCallback<Buddy>() {
		  public void done(Buddy item, ParseException e) {
		    if (e == null) {
		      // Access data using the `get` methods for the object
//		      String body = item.getBody();
//		      // Access special values that are built-in to each object
//		      String objectId = item.getObjectId();
//		      Date updatedAt = item.getUpdatedAt();
//		      Date createdAt = item.getCreatedAt();
//		      // Do whatever you want with the data...
//		      Toast.makeText(TodoItemsActivity.this, body, Toast.LENGTH_SHORT).show();
		    } else {
		      // something went wrong
		    }
		  }
		});
	}

}
