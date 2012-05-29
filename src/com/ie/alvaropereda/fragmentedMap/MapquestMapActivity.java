package com.ie.alvaropereda.fragmentedMap;

import android.content.res.Configuration;
import android.os.Bundle;

import com.ie.alvaropereda.fragmentedMap.controller.fragments.MapquestMapFragment;
import com.mapquest.android.maps.MapActivity;

public class MapquestMapActivity extends MapActivity {
	/**
	 * @see android.app.Activity#onCreate(Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE) {
            // If the screen is now in landscape mode, we can show the
            // dialog in-line with the list so we don't need this activity.
            finish();
            return;
        }
		
		if (savedInstanceState == null) {
            // During initial setup, plug in the details fragment.
            MapquestMapFragment details = new MapquestMapFragment();
            details.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(android.R.id.content, details).commit();
        }
	}

	@Override
	public boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}
