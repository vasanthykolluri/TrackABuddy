package com.codepath.apps.trackabuddy.fragments;

import android.app.Fragment;
import android.widget.ListView;

import com.codepath.apps.trackabuddy.ProfileArrayAdapter;
import com.codepath.apps.trackabuddy.models.Profile;
import com.codepath.apps.trackabuddy.networking.ParseClient;


import java.util.ArrayList;

/**
 * Created by sadhanas on 7/14/14.
 */
public class ContactListFragment extends Fragment{
    private ArrayList<Profile> profiles;
    private ProfileArrayAdapter aProfileAdapter;
    private ListView lvProfile;
    protected ParseClient parseClient;
}
