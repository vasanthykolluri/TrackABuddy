package com.codepath.apps.trackabuddy;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.codepath.apps.trackabuddy.R;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.SendCallback;

public class ShowPopUp extends Activity implements OnClickListener {

	Button accept;
	Button decline;

	boolean click = true;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle(getIntent().getStringExtra("customdata"));
		setContentView(R.layout.popupdialog);
		accept = (Button) findViewById(R.id.btnAccept);
		accept.setOnClickListener(this);
		decline = (Button) findViewById(R.id.btnDecline);
		decline.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.btnAccept) {
			sendTrackingRequestResponse(true);
		} else if (v.getId() == R.id.btnAccept) {
			sendTrackingRequestResponse(false);
		}
		finish();
	}

	private void sendTrackingRequestResponse(boolean response) {

		JSONObject obj;
		try {
			obj = new JSONObject();
			obj.put("alert", "Hello Buddy Response!");
			obj.put("action", MyCustomReceiver.intentActionTrackReqResp);
			obj.put("customdata", response);

			ParsePush push = new ParsePush();
			ParseQuery query = ParseInstallation.getQuery();

			// Push the notification to Android users
			query.whereEqualTo("deviceType", "android");
			// query.whereEqualTo("device_id", "1234567890");

			push.setQuery(query);
			push.setData(obj);
			push.sendInBackground(new SendCallback() {

				@Override
				public void done(ParseException arg0) {
					// Toast.makeText(getApplicationContext(),
					// "Done with sending", Toast.LENGTH_LONG).show();
				}

			});
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}