package com.ie.alvaropereda.fragmentedMap;

import android.content.Intent;
import android.os.Bundle;

import com.ie.alvaropereda.fragmentedMap.controller.fragments.MapquestMapFragment;
import com.mapquest.android.maps.MapFragmentActivity;

public class MapquestMapActivity extends MapFragmentActivity {
	/**
	 * @see android.app.Activity#onCreate(Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.map_fragment);
        
        Intent launchingIntent = getIntent();
        String content = launchingIntent.getData().toString();
     
        MapquestMapFragment detailView = (MapquestMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapquest_map_fragment);
        
	}

	@Override
	public boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}
