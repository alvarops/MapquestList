/**
 * 
 */
package com.ie.alvaropereda.fragmentedMap.controller.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ie.alvaropereda.fragmentedMap.R;
import com.mapquest.android.maps.GeoPoint;
import com.mapquest.android.maps.MapFragment;
import com.mapquest.android.maps.MapView;

/**
 * @author Alvaro
 * 
 */
public class MapquestMapFragment extends MapFragment {

	private static final String TAG = MapquestMapFragment.class.getName();
	
	private MapView map;
	
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
		map.getController().setCenter(new GeoPoint(38.892155, -77.036195));
		map.setBuiltInZoomControls(true);
		return view;
	}

	/**
	 * @see android.app.Fragment#onPause()
	 */
	@Override
	public void onPause() {
		super.onPause();
	}

}
