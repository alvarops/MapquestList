/**
 * 
 */
package com.ie.aol.hatchday.mapquest.controller.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ie.aol.hatchday.mapquest.R;
import com.mapquest.android.maps.GeoPoint;
import com.mapquest.android.maps.MapFragment;
import com.mapquest.android.maps.MapView;
import com.mapquest.android.maps.MyLocationOverlay;

/**
 * @author Alvaro
 * 
 */
public class MapquestMapFragment extends MapFragment {

	private static final String TAG = MapquestMapFragment.class.getSimpleName();
	
	private MapView map;

	private MyLocationOverlay myLocationOverlay;
	
	public MapquestMapFragment() {
		super();
	}
	
	/**
	 * @see android.app.Fragment#onCreateView(android.view.LayoutInflater,
	 *      android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		Log.d(TAG, "onCreateView");
		
		View view = (View) inflater.inflate(R.layout.map_detail,
				container, true);

		// Inflate the layout for this fragment
		map = (MapView) view.findViewById(R.id.map);

		// set the zoom level, center point and enable the default zoom controls
		map.getController().setZoom(9);
		//map.getController().setCenter(new GeoPoint(38.892155, -77.036195));
		map.setBuiltInZoomControls(true);
		
		setupMyLocation();
		return view;
	}

	 // enable features of the overlay 
    @Override
	public void onResume() {
      myLocationOverlay.enableMyLocation();
      myLocationOverlay.enableCompass();
      super.onResume();
    }

    // disable features of the overlay when in the background 
    @Override
	public void onPause() {
      super.onPause();
      myLocationOverlay.disableCompass();
      myLocationOverlay.disableMyLocation();
    }

	
	
	 // set up a MyLocationOverlay and execute the runnable once we have a location fix 
    private void setupMyLocation() {
      this.myLocationOverlay = new MyLocationOverlay(this.getActivity(), map);
      myLocationOverlay.enableMyLocation();
      myLocationOverlay.runOnFirstFix(new Runnable() {
        @Override
        public void run() {
          GeoPoint currentLocation = myLocationOverlay.getMyLocation();
          map.getController().animateTo(currentLocation);
          map.getController().setZoom(14);
          map.getOverlays().add(myLocationOverlay);
          map.getController().setCenter(currentLocation);
          myLocationOverlay.setFollowing(true);
        }
      });
    }

}
