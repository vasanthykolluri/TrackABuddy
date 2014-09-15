package com.codepath.apps.trackabuddy.fragments;

import java.text.DecimalFormat;
import java.util.List;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.codepath.apps.trackabuddy.R;
import com.codepath.apps.trackabuddy.models.Buddy;
import com.codepath.apps.trackabuddy.service.TrackaBuddyService;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.ErrorDialogFragment;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

public class BuddyMapFragment extends Fragment implements
GooglePlayServicesClient.ConnectionCallbacks,
GooglePlayServicesClient.OnConnectionFailedListener,
OnMarkerClickListener, com.google.android.gms.location.LocationListener{

	
	private SupportMapFragment mapFragment;
	private GoogleMap map;
	private LocationClient mLocationClient;
	LatLngBounds.Builder bc;
	private Marker trackingMarker; 
	Intent intent;
	LocationRequest mLocationRequest;
	
	private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
	private static final int MILLISECONDS_PER_SECOND = 1000;
    // Update frequency in seconds
    public static final int UPDATE_INTERVAL_IN_SECONDS = 5;
    // Update frequency in milliseconds
    private static final long UPDATE_INTERVAL =
            MILLISECONDS_PER_SECOND * UPDATE_INTERVAL_IN_SECONDS;
    // The fastest update frequency, in seconds
    private static final int FASTEST_INTERVAL_IN_SECONDS = 1;
    // A fast frequency ceiling in milliseconds
    private static final long FASTEST_INTERVAL =
            MILLISECONDS_PER_SECOND * FASTEST_INTERVAL_IN_SECONDS;
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater
				.inflate(R.layout.fragment_buddymap, container, false);
		mLocationClient = new LocationClient(getActivity(), this, this);
		mLocationRequest = LocationRequest.create();
		// Use high accuracy
        mLocationRequest.setPriority(
                LocationRequest.PRIORITY_HIGH_ACCURACY);
        // Set the update interval to 5 seconds
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        // Set the fastest update interval to 1 second
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        mapFragment = (SupportMapFragment) getFragmentManager().findFragmentById(R.id.map);
        if(mapFragment!=null){
        	map = mapFragment.getMap();
        	if(map!=null){
        		//Toast.makeText(getActivity(), "Map Fragment was loaded properly!", Toast.LENGTH_SHORT).show();
				map.setMyLocationEnabled(true);
				CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(37.423, -122.091), 15);
			 map.animateCamera(cameraUpdate);	
//			 addUserLocation("akashagarwal", 37.423, -122.091);
//				bc = new LatLngBounds.Builder();
//				getLocationFromDB();
//			//	map.addMarker(new MarkerOptions().position(new LatLng(37.423, -122.091)).title("Akash").visible(true));
				if(isGooglePlayServicesAvailable()){
					mLocationClient.connect();
				  //  mLocationClient.requestLocationUpdates(request, listener);
				}
			} else {
				Toast.makeText(getActivity(), "Error - Map was null!!", Toast.LENGTH_SHORT).show();
			}
        	
        	}else {
    			Toast.makeText(getActivity(), "Error - Map Fragment was null!!", Toast.LENGTH_SHORT).show();

        	}        
		return v;
		
	}
	
	private boolean isGooglePlayServicesAvailable() {
		// Check that Google Play services is available
		int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity());
		// If Google Play services is available
		if (ConnectionResult.SUCCESS == resultCode) {
			// In debug mode, log the status
			Log.d("Location Updates", "Google Play services is available.");
			return true;
		} else {
			// Get the error dialog from Google Play services
			Dialog errorDialog = GooglePlayServicesUtil.getErrorDialog(resultCode, getActivity(),
					CONNECTION_FAILURE_RESOLUTION_REQUEST);

			// If Google Play services can provide an error dialog
			if (errorDialog != null) {
				// Create a new DialogFragment for the error dialog
				ErrorDialogFragment errorFragment = new ErrorDialogFragment();
//				errorFragment.setDialog(errorDialog);
//				errorFragment.show(getFragmentManager(), "Location Updates");
			}

			return false;
		}
	}


	@Override
    public void onDestroyView() {
        super.onDestroyView();
        SupportMapFragment f = (SupportMapFragment) getFragmentManager().findFragmentById(R.id.map);
        if (f != null) 
            getFragmentManager().beginTransaction().remove(f).commitAllowingStateLoss();
        super.onDestroy();
    }

	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onConnected(Bundle arg0) {
		Location location = mLocationClient.getLastLocation();
		Toast.makeText(getActivity(), "Location " + location, Toast.LENGTH_SHORT).show();
		if (location != null) { 
			Toast.makeText(getActivity(), "GPS location was found!", Toast.LENGTH_SHORT).show();
			LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
			CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 15);
			map.animateCamera(cameraUpdate);
			addUserLocation("akashagarwal", location.getLatitude(), location.getLongitude());
			bc = new LatLngBounds.Builder();
			getLocationFromDB();
			//map.addPolyline(new PolylineOptions().geodesic(false).add(latLng, new LatLng(37.423, -122.091)));
			
			mLocationClient.requestLocationUpdates(mLocationRequest, this);
			
		} else {
			//Toast.makeText(getActivity(), "Current location was null, enable GPS on emulator!", Toast.LENGTH_SHORT).show();
		}
		
	}

	@Override
	public void onDisconnected() {
		// TODO Auto-generated method stub
		
	}
	
	private void addMarkeronMap(Buddy buddy) {
		if(map!=null){
			//Toast.makeText(getActivity(), "map not null - " + buddy.getLongitude() + " lat - " + buddy.getLatitude() + " name - " + buddy.getUserId(), Toast.LENGTH_SHORT).show();
			DecimalFormat df = new DecimalFormat();
			df.setMaximumFractionDigits(2);
			
		map.addMarker(new MarkerOptions()
        .position(new LatLng(buddy.getLatitude(), buddy.getLongitude()))
        
        .title(buddy.getUserId()).visible(true).snippet("Distance" + df.format(buddy.getDistance()/(1000.0 * 1.60934)) +  "m \nCity: " + buddy.getCity() + "START TRACKING")).showInfoWindow();
		map.setOnMarkerClickListener(this);
		
		}
		
		
	}
	
	public void getLocationFromDB(){
		
		ParseQuery<Buddy> query = ParseQuery.getQuery(Buddy.class);
		query.findInBackground(new FindCallback<Buddy>() {

			@Override
			public void done(List<Buddy> buddies, ParseException e) {
				if (e == null) {
					for(Buddy buddy: buddies){
						
						
						if(buddy.getUserId()!=null && buddy.getBuddyId()!=null && !buddy.getUserId().equals("akashagarwal") && buddy.getBuddyId().equals("akashagarwal"))
						{
							//Toast.makeText(getActivity(), "Found match " + buddy.getBuddyScreenName() + " " + buddy.getLongitude() + "  " + buddy.getLatitude() , Toast.LENGTH_SHORT).show();
							LatLng latLng = new LatLng(buddy.getLatitude(), buddy.getLongitude());
							bc.include(latLng);
							LatLng ownLocation = new LatLng(37.422, -122.086);
						//	Location ownLocation = new Loca
							float[] results = new float[3];
							Location.distanceBetween(ownLocation.latitude, ownLocation.longitude, buddy.getLatitude(), buddy.getLongitude(), results);
							buddy.setDistance(results[0]);
							addMarkeronMap(buddy);
							
						}
					}
					map.moveCamera(CameraUpdateFactory.newLatLngBounds(bc.build(), 50));
				}
				else {
					Toast.makeText(getActivity(), e + " inside exception", Toast.LENGTH_SHORT).show();

				}
			}

			
		});
	}
	
	public void addUserLocation(final String userId, final double latitude, final double longitude) {
		// Specify which class to query
				ParseQuery<Buddy> query = ParseQuery.getQuery(Buddy.class);
				//Toast.makeText(getActivity(), "inside add buddy location", Toast.LENGTH_SHORT).show();

				query.findInBackground(new FindCallback<Buddy>() {

					@Override
					public void done(List<Buddy> buddyList, ParseException e) {
					//	Toast.makeText(getActivity(), "Done", Toast.LENGTH_SHORT).show();
						if (e == null) {
							for(Buddy buddy: buddyList){
								if(buddy.getUserId()!=null && buddy.getUserId().equals(userId)){
									buddy.put("longitude", longitude);
									buddy.put("latitude", latitude);
									buddy.saveInBackground();
									
								}
							}
						} else {
							e.printStackTrace();

						}
						
					}

				});
	}

	@Override
	public boolean onMarkerClick(Marker marker) {
		trackingMarker= marker;
		if(marker.getSnippet().contains("START")){
		Toast.makeText(getActivity(), "marker : " + marker.getSnippet(), Toast.LENGTH_SHORT).show();
		 intent = new Intent(getActivity(), TrackaBuddyService.class);
		intent.putExtra("userId", marker.getTitle());
		getActivity().startService(intent);
		getActivity().registerReceiver(broadcastReceiver, new IntentFilter(TrackaBuddyService.BROADCAST_ACTION));
		}
		else if(marker.getSnippet().contains("STOP")){
			getActivity().unregisterReceiver(broadcastReceiver);
			getActivity().stopService(intent);
			trackingMarker.setSnippet(trackingMarker.getSnippet().replace( "STOP", "START"));
       	 	trackingMarker.showInfoWindow();
		}
		return false;
	}

	private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

        	 LatLng updatedLocation = new LatLng(intent.getDoubleExtra("latitude",  0.0), intent.getDoubleExtra("longitude", 0.0));
        	 trackingMarker.setPosition(updatedLocation);
     		Toast.makeText(getActivity(), "trackingMarker. " + trackingMarker.getSnippet().replace("START", "STOP"), Toast.LENGTH_SHORT).show();

        	 trackingMarker.setSnippet(trackingMarker.getSnippet().replace("START", "STOP"));
        	 trackingMarker.showInfoWindow();
        }
    };
	@Override
	public void onLocationChanged(Location location) {
		// Report to the UI that the location was updated
        String msg = "Updated Location: " +
                Double.toString(location.getLatitude()) + "," +
                Double.toString(location.getLongitude());
        addUserLocation("akashagarwal", location.getLatitude(), location.getLongitude());
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
		
	}

}