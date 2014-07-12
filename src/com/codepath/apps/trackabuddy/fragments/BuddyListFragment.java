package com.codepath.apps.trackabuddy.fragments;

import java.util.ArrayList;

import org.json.JSONArray;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.codepath.apps.trackabuddy.BuddyArrayAdapter;
import com.codepath.apps.trackabuddy.EndlessScrollListener;
import com.codepath.apps.trackabuddy.TrackABuddyApp;
import com.codepath.apps.trackabuddy.models.Buddy;
import com.codepath.apps.trackabuddy.networking.GoogleMapClient;
import com.example.trackabuddy.R;
import com.loopj.android.http.JsonHttpResponseHandler;

public class BuddyListFragment extends Fragment {
	private ArrayList<Buddy> buddies;
	private BuddyArrayAdapter aBuddies;
	private ListView lvBuddies;
	protected GoogleMapClient client;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		buddies = new ArrayList<Buddy>();
		aBuddies = new BuddyArrayAdapter(getActivity(), buddies);
		//client = TrackABuddyApp.getRestClient();
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
				String lastName = buddies.get(totalItemsCount - 1).getName();
				customLoadMoreDataFromApi(lastName);
			}
		});

		// Return the layout view
		return v;
	}

	public void clearAll() {
		aBuddies.clear();
	}

	public void addAll(ArrayList<Buddy> buddies) {
		aBuddies.addAll(buddies);
	}

	public void populateBuddyList() {

	}

	public void customLoadMoreDataFromApi(String lastName) {

	}
}
