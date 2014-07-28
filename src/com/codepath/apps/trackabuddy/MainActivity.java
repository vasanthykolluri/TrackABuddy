package com.codepath.apps.trackabuddy;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.codepath.apps.trackabuddy.fragments.BuddyListFragment;
import com.codepath.apps.trackabuddy.fragments.BuddyMapFragment;
import com.codepath.apps.trackabuddy.listeners.FragmentTabListener;
import com.codepath.apps.trackabuddy.networking.MyCustomReceiver;
import com.codepath.apps.trackabuddy.networking.MyCustomSender;

public class MainActivity extends FragmentActivity {

	BuddyListFragment buddyListFragment;
	BuddyMapFragment buddyMapFragment;

	private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			Toast.makeText(getApplicationContext(), "onReceive invoked!",
					Toast.LENGTH_LONG).show();
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		setupTabs();
	}

	private void setupTabs() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(true);

		Tab tab1 = actionBar
				.newTab()
				.setText("Map")
				.setIcon(R.drawable.ic_map)
				.setTag("BuddyMapFragment")
				.setTabListener(
						new FragmentTabListener<BuddyMapFragment>(
								R.id.flContainer, this, "first",
								BuddyMapFragment.class));

		actionBar.addTab(tab1);
		actionBar.selectTab(tab1);

		Tab tab2 = actionBar
				.newTab()
				.setText("Buddy List")
				.setIcon(R.drawable.ic_buddy)
				.setTag("BuddyListFragment")
				.setTabListener(
						new FragmentTabListener<BuddyListFragment>(
								R.id.flContainer, this, "second",
								BuddyListFragment.class));
		actionBar.addTab(tab2);
	}

	@Override
	public void onPause() {
		super.onPause();

		LocalBroadcastManager.getInstance(this).unregisterReceiver(
				mBroadcastReceiver);
	}

	@Override
	public void onResume() {
		super.onResume();

		LocalBroadcastManager.getInstance(this).registerReceiver(
				mBroadcastReceiver,
				new IntentFilter(MyCustomReceiver.intentActionTrackReq));
	}

	public void onTrackClick(View v) {
		if (v.getId() == R.id.btnTrackAkash) {
			MyCustomSender.sendTrackReq("vasanthykolluri", "Vasanthy", "akashagarwal");
		} else if (v.getId() == R.id.btnTrackSadhana) {
			MyCustomSender.sendTrackReq("vasanthykolluri", "Vasanthy", "sadhanasahas");
		} else if (v.getId() == R.id.btnTrackVasanthy) {
			MyCustomSender.sendTrackReq("vasanthykolluri", "Vasanthy", "vasanthykolluri");
		}
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.contacts_settings, menu);
		return true;
	}

	public void onClickContacts(MenuItem menuItem) {
		Intent i = new Intent(this, ContactActivity.class);
		startActivity(i);
	}
}
