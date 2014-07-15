package com.codepath.apps.trackabuddy;
import com.codepath.apps.trackabuddy.models.Buddy;
import com.codepath.apps.trackabuddy.models.Profile;
import com.example.trackabuddy.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sadhanas on 7/14/14.
 */
public class ProfileArrayAdapter extends ArrayAdapter<Profile>{

    public ProfileArrayAdapter(Context context, List<Profile> profiles) {
        super(context, 0, profiles);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Profile profile = getItem(position);

        // Find or inflate the template
        View v;
        if (convertView == null) {
            LayoutInflater inflator = LayoutInflater.from(getContext());
            v = inflator.inflate(R.layout.profile_item, parent, false);
        } else {
            v = convertView;
        }

        // Find the views within template
        ImageView ivProfileImage = (ImageView) v
                .findViewById(R.id.ivProfileImg);
        TextView tvName = (TextView) v.findViewById(R.id.tvName);
        TextView tvPhone = (TextView) v.findViewById(R.id.tvPhone);


        ivProfileImage.setImageResource(android.R.color.transparent);

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(profile.getImgUrl(),ivProfileImage);

        // Populate the data into the template view using the data object
        //imageLoader.displayImage(buddy.getImgUrl(),
        //	ivProfileImage);
        tvName.setText(profile.getName());
        tvPhone.setText(profile.getPhone());


        // Save the buddyname in the imageView tag


        ivProfileImage.setOnClickListener(new View.OnClickListener() {

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
