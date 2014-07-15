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

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		Boolean response = getIntent().getBooleanExtra("response", false);

		Toast.makeText(this, "PopUp Resp" + response, Toast.LENGTH_SHORT)
				.show();
		setContentView(R.layout.popup_track_reqresponse);
		tvTrackReqResponse = (TextView) findViewById(R.id.tvTrackReqResp);
		btnOK = (Button) findViewById(R.id.btnOK);
		if (response == true) {
			tvTrackReqResponse
					.setText("Yayy!!! Your invite has been accepted.");
		} else {
			tvTrackReqResponse
					.setText("Oops!!! Your invite has been declined.");
		}
	}

	@Override
	public void onClick(View v) {
		finish();
	}

}