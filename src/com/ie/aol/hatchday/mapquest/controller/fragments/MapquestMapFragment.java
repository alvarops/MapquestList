/**
 * 
 */
package com.ie.aol.hatchday.mapquest.controller.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ie.aol.hatchday.mapquest.R;
import com.mapquest.android.maps.AnnotationView;
import com.mapquest.android.maps.DefaultItemizedOverlay;
import com.mapquest.android.maps.GeoPoint;
import com.mapquest.android.maps.ItemizedOverlay;
import com.mapquest.android.maps.MapFragment;
import com.mapquest.android.maps.MapView;
import com.mapquest.android.maps.MyLocationOverlay;
import com.mapquest.android.maps.OverlayItem;

/**
 * @author Alvaro
 * 
 */
public class MapquestMapFragment extends MapFragment {

	private static final String TAG = MapquestMapFragment.class.getSimpleName();

	private MapView map;
	private AnnotationView annotation;
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

		View view = (View) inflater.inflate(R.layout.map_detail, container,
				true);

		// Inflate the layout for this fragment
		map = (MapView) view.findViewById(R.id.map);

		// initialize the annotation to be shown later
		annotation = new AnnotationView(map);

		// set the zoom level, center point and enable the default zoom controls
		map.getController().setZoom(9);
		map.getController().setCenter(new GeoPoint(53.344262, -6.308835));
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

	// set up a MyLocationOverlay and execute the runnable once we have a
	// location fix
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

	// add an itemized overlay to map
	private void addPoiOverlay() {
		// use a custom POI marker by referencing the bitmap file directly,
		// using the filename as the resource ID

		Drawable icon = getResources().getDrawable(R.drawable.location_marker);
		final DefaultItemizedOverlay poiOverlay = new DefaultItemizedOverlay(
				icon);
		// set GeoPoints and title/snippet to be used in the annotation view
		OverlayItem poi1 = new OverlayItem(
				new GeoPoint(39.739983, -104.984727), "Denver, Colorado",
				"MapQuest Headquarters");
		poiOverlay.addItem(poi1);
		OverlayItem poi2 = new OverlayItem(
				new GeoPoint(37.441903, -122.141895), "Palo Alto, California",
				"AOL Offices");
		poiOverlay.addItem(poi2);
		// add a tap listener for the POI overlay
		poiOverlay.setTapListener(new ItemizedOverlay.OverlayTapListener() {
			@Override
			public void onTap(GeoPoint pt, MapView mapView) {
				// when tapped, show the annotation for the overlayItem
				int lastTouchedIndex = poiOverlay.getLastFocusedIndex();
				if (lastTouchedIndex > -1) {
					OverlayItem tapped = poiOverlay.getItem(lastTouchedIndex);
					annotation.showAnnotationView(tapped);
				}
			}
		});
		map.getOverlays().add(poiOverlay);
	}
}
