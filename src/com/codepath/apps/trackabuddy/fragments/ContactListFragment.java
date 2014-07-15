package com.codepath.apps.trackabuddy.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.codepath.apps.trackabuddy.BuddyArrayAdapter;
import com.codepath.apps.trackabuddy.EndlessScrollListener;
import com.codepath.apps.trackabuddy.ProfileArrayAdapter;
import com.codepath.apps.trackabuddy.models.Buddy;
import com.codepath.apps.trackabuddy.models.Profile;
import com.codepath.apps.trackabuddy.networking.ParseClient;
import com.example.trackabuddy.R;


import java.util.ArrayList;

/**
 * Created by sadhanas on 7/14/14.
 */
public class ContactListFragment extends Fragment{
    private ArrayList<Profile> profiles;
    private ProfileArrayAdapter aProfile;
    private ListView lvProfile;
    protected ParseClient parseClient;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profiles = new ArrayList<Profile>();
        aProfile = new ProfileArrayAdapter(getActivity(), profiles);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout
        View v = inflater
                .inflate(R.layout.fragment_contactlist, container, false);
        lvProfile = (ListView) v.findViewById(R.id.lvContact);
        lvProfile.setAdapter(aProfile);

//        lvProfile.setOnScrollListener(new EndlessScrollListener() {
//            @Override
//            public void onLoadMore(int page, int totalItemsCount) {
//                String lastName = buddies.get(totalItemsCount - 1).getName();
//                customLoadMoreDataFromApi(lastName);
//            }
//        });

        //populateBuddyList();

        // Return the layout view
        return v;
    }

}
