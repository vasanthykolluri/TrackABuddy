package com.codepath.apps.trackabuddy.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.codepath.apps.trackabuddy.models.Buddy;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import android.R;
import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class TrackaBuddyService extends Service{
	private static final String TAG = "BroadcastService";
	public static final String BROADCAST_ACTION = "com.websmithing.broadcasttest.displayevent";
	private final Handler handler = new Handler();
	private ArrayList<String> userIdList = new ArrayList<String>();
	Intent localIntent;
	int counter = 0;
	String userId= "";
	LatLng userLocation;
	
	@Override
	public void onCreate() {
		super.onCreate();	
		localIntent = new Intent(BROADCAST_ACTION);
	}
	
    @Override
    public void onStart(Intent intent, int startId) {
        handler.removeCallbacks(sendUpdatesToUI);
      // bundle = intent.getExtras();
        userId = intent.getStringExtra("userId");
        Toast.makeText(getApplicationContext(), "userId Service: " + userId, Toast.LENGTH_SHORT).show();
        handler.postDelayed(sendUpdatesToUI, 1000); // 1 second
      //  return START_STICKY;
    }

    private Runnable sendUpdatesToUI = new Runnable() {
    	public void run() {
    		getLocationFromDatabase();
    		    		
    	    handler.postDelayed(this, 10000); // 10 seconds
    	}

		private void getLocationFromDatabase() {
			Toast.makeText(getApplicationContext(), "insideGetLocationService", Toast.LENGTH_SHORT).show();
			ParseQuery<Buddy> query = ParseQuery.getQuery(Buddy.class);
			query.findInBackground(new FindCallback<Buddy>() {

				@Override
				public void done(List<Buddy> buddies, ParseException e) {
					if (e == null) {
						for(Buddy buddy: buddies){
							
							Toast.makeText(getApplicationContext(), "Found match " + buddy.getUserId() + " " + buddy.getLongitude() + "  " + buddy.getLatitude() , Toast.LENGTH_SHORT).show();
							Log.d("debug","buddies : " + buddy);
							if(buddy.getUserId()!=null && buddy.getUserId().equalsIgnoreCase(userId))
							{
								Log.d("debug","Found match : " + buddy);

								Toast.makeText(getApplicationContext(), "Found match " + buddy.getUserId() + " " + buddy.getLongitude() + "  " + buddy.getLatitude() , Toast.LENGTH_SHORT).show();
								userLocation = new LatLng(buddy.getLatitude(), buddy.getLongitude());
										break;						
							}
						}
						DisplayLoggingInfo();
					}
					else {
						Toast.makeText(getApplicationContext(), e + " inside exception", Toast.LENGTH_SHORT).show();

					}
				}

				
			});
			
		}
    };    
    
    private void DisplayLoggingInfo() {
    	Log.d(TAG, "entered DisplayLoggingInfo");
    	
    	localIntent.putExtra("latitude", userLocation.latitude);
    	localIntent.putExtra("longitude", userLocation.longitude);
    	sendBroadcast(localIntent);
    }
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onDestroy() {		
        handler.removeCallbacks(sendUpdatesToUI);		
		super.onDestroy();
		Toast.makeText(this, "Service Stopped", Toast.LENGTH_SHORT).show();
	}		
}
