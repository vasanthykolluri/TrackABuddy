package com.codepath.apps.trackabuddy;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ShowTrackReqResp extends Activity implements OnClickListener {

	TextView tvTrackReqResponse;
	Button btnOK;
	String buddyName;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		Boolean response = getIntent().getBooleanExtra("response", false);
		buddyName = getIntent().getStringExtra("buddyName");

		setContentView(R.layout.popup_trackreqresp);
		tvTrackReqResponse = (TextView) findViewById(R.id.tvTrackReqResp);
		btnOK = (Button) findViewById(R.id.btnOK);
		if (response == true) {
			tvTrackReqResponse.setText("Yayy! " + buddyName + " accepted your track request");
		} else {
			tvTrackReqResponse.setText("Oops! " + buddyName + " declined your track request");
		}
	}

	@Override
	public void onClick(View v) {
		finish();
	}

}