package com.codepath.apps.trackabuddy;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ShowPopUpResponse extends Activity implements OnClickListener {

	TextView tvTrackReqResponse;
	Button btnOK;
	String buddyName;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		Boolean acceptFlag = getIntent().getBooleanExtra("acceptFlag", false);
		buddyName = getIntent().getStringExtra("buddyName");

		setContentView(R.layout.popup_track_reqresponse);
		tvTrackReqResponse = (TextView) findViewById(R.id.tvTrackReqResp);
		btnOK = (Button) findViewById(R.id.btnOK);
		if (acceptFlag == true) {
			tvTrackReqResponse
					.setText("Yayy!!! Your invite is accepted by " + buddyName);
		} else {
			tvTrackReqResponse
					.setText("Oops!!! Your invite is declined by " + buddyName);
		}
	}

	@Override
	public void onClick(View v) {
		finish();
	}

}