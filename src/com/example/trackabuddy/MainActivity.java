package com.example.trackabuddy;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Buddy item = new Buddy("dummy", "dummy");
		item.saveInBackground();

		// Specify which class to query
		ParseQuery<Buddy> query = ParseQuery.getQuery(Buddy.class);
		query.findInBackground(new FindCallback<Buddy>() {

			@Override
			public void done(List<Buddy> items, ParseException arg1) {
				// TODO Auto-generated method stub
				Buddy item = items.get(0);
				Toast.makeText(MainActivity.this, item.getName(),
						Toast.LENGTH_SHORT).show();
			}
		});
	}
}
