/**
 * 
 */
package com.ie.alvaropereda.fragmentedMap.controller.fragments;

import android.os.Bundle;
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

	/**
	 * @see android.app.Fragment#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		
	}

	/**
	 * @see android.app.Fragment#onCreateView(android.view.LayoutInflater,
	 *      android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		// Inflate the layout for this fragment
		MapView map = (MapView) inflater
				.inflate(R.layout.map, container, false);

		// set the zoom level, center point and enable the default zoom controls
		map.getController().setZoom(9);
		map.getController().setCenter(new GeoPoint(38.892155, -77.036195));
		map.setBuiltInZoomControls(true);

		return map;
	}

	/**
	 * @see android.app.Fragment#onPause()
	 */
	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
