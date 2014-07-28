package com.codepath.apps.trackabuddy.fragments;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.codepath.apps.trackabuddy.BuddyArrayAdapter;
import com.codepath.apps.trackabuddy.EndlessScrollListener;
import com.codepath.apps.trackabuddy.R;
import com.codepath.apps.trackabuddy.models.Buddy;
import com.codepath.apps.trackabuddy.networking.ParseClient;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

public class BuddyListFragment extends Fragment {
	private ArrayList<Buddy> buddies;
	private BuddyArrayAdapter aBuddies;
	private ListView lvBuddies;
	protected ParseClient parseClient;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		buddies = new ArrayList<Buddy>();
		aBuddies = new BuddyArrayAdapter(getActivity(), buddies);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout
		View v = inflater
				.inflate(R.layout.fragment_buddylist, container, false);

		lvBuddies = (ListView) v.findViewById(R.id.lvBuddies);
		lvBuddies.setAdapter(aBuddies);

		lvBuddies.setOnScrollListener(new EndlessScrollListener() {
			@Override
			public void onLoadMore(int page, int totalItemsCount) {
				String lastName = buddies.get(totalItemsCount - 1).getBuddyScreenName();
				customLoadMoreDataFromApi(lastName);
			}
		});

		populateBuddyList();
		Toast.makeText(getActivity(), "Returning view", Toast.LENGTH_SHORT).show();

		// Return the layout view
		return v;
	}

	public void populateBuddyList() {
		// Specify which class to query
		ParseQuery<Buddy> query = ParseQuery.getQuery(Buddy.class);

		query.findInBackground(new FindCallback<Buddy>() {

			@Override
			public void done(List<Buddy> buddyList, ParseException e) {
				Toast.makeText(getActivity(), "Done", Toast.LENGTH_SHORT).show();
				if (e == null) {
					aBuddies.clear();
					aBuddies.addAll(buddyList);
				} else {
					e.printStackTrace();

				}
			}

		});

	}

	public void customLoadMoreDataFromApi(String lastName) {

	}
}
