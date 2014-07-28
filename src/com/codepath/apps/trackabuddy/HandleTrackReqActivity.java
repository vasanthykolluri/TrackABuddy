package com.codepath.apps.trackabuddy;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.codepath.apps.trackabuddy.models.TrackReq;
import com.codepath.apps.trackabuddy.networking.MyCustomSender;

public class HandleTrackReqActivity extends Activity implements OnClickListener {

	Button accept;
	Button decline;

	boolean click = true;

	private TrackReq trackReq;
	private TextView tvTrackReq;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.popup_trackreq);
		tvTrackReq = (TextView) findViewById(R.id.tvTrackReq);

		trackReq = (TrackReq) getIntent().getSerializableExtra("trackReq");
		String message = trackReq.getSenderName()
				+ " wants to track your location!";

		setTitle("TrackABuddy!");
		tvTrackReq.setText(message);

		accept = (Button) findViewById(R.id.btnAccept);
		accept.setOnClickListener(this);
		decline = (Button) findViewById(R.id.btnDecline);
		decline.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.btnAccept) {
			MyCustomSender.sendTrackReqResp(trackReq, true);

			// Add buddy to Parse db
			TrackABuddyApp.getParseClient().addBuddy(TrackABuddyApp.userId,
					trackReq.getSenderId(), trackReq.getSenderName(),
					"dummyImgUrl");
		} else if (v.getId() == R.id.btnDecline) {
			MyCustomSender.sendTrackReqResp(trackReq, false);
		}
		finish();
	}
}