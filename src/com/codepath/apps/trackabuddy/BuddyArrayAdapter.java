package com.codepath.apps.trackabuddy;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.trackabuddy.models.Buddy;
import com.example.trackabuddy.R;
import com.nostra13.universalimageloader.core.ImageLoader;

public class BuddyArrayAdapter extends ArrayAdapter<Buddy> {

	public BuddyArrayAdapter(Context context, List<Buddy> buddies) {
		super(context, 0, buddies);

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// Get the data item for this position
		Buddy buddy = getItem(position);

		// Find or inflate the template
		View v;
		if (convertView == null) {
			LayoutInflater inflator = LayoutInflater.from(getContext());
			v = inflator.inflate(R.layout.buddy_item, parent, false);
		} else {
			v = convertView;
		}

		// Find the views within template
		ImageView ivProfileImage = (ImageView) v
				.findViewById(R.id.ivProfileImage);
		TextView tvBuddyName = (TextView) v.findViewById(R.id.tvBuddyName);
		TextView tvCity = (TextView) v.findViewById(R.id.tvCity);
		TextView tvDistance = (TextView) v
				.findViewById(R.id.tvDistance);

		ivProfileImage.setImageResource(android.R.color.transparent);
		ImageLoader imageLoader = ImageLoader.getInstance();

		// Populate the data into the template view using the data object
		imageLoader.displayImage(buddy.getImgUrl(),
				ivProfileImage);
		tvBuddyName.setText(buddy.getName());
		tvCity.setText(buddy.getCity());
		tvDistance.setText(buddy.getDistance().toString());

		// Save the buddyname in the imageView tag

		ivProfileImage.setTag(buddy.getName());
		ivProfileImage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
//				Intent profileIntent = new Intent(getContext(),
//						ProfileActivity.class);
//				profileIntent.putExtra("userId", (long) v.getTag());
//				getContext().startActivity(profileIntent);
			}
		});

		// Return the completed view to render on screen
		return v;
	}
}
