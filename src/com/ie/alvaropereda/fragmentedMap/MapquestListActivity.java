package com.ie.alvaropereda.fragmentedMap;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.ie.alvaropereda.fragmentedMap.controller.fragments.ContactListFragment;
import com.ie.alvaropereda.fragmentedMap.controller.fragments.MapquestMapFragment;
import com.ie.alvaropereda.fragmentedMap.model.ContactItem;
import com.mapquest.android.maps.MapFragmentActivity;

public class MapquestListActivity extends MapFragmentActivity implements ContactListFragment.OnListItemSelectedListener{

	private static final String TAG = MapquestListActivity.class.getName();
	
	private ContactListFragment contactListFragment;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "OnCreate");
		setContentView(R.layout.main);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void onListItemSelected(ContactItem contactItem) {
		
		// Load the Fragment in an activity if its not present,
		// otherwise just update the fragment.
		MapquestMapFragment detailView = (MapquestMapFragment) getSupportFragmentManager()
			.findFragmentById(R.id.mapquest_map_fragment);
		 
		if (detailView == null || !detailView.isInLayout()) {
			Intent showContent = new Intent(getApplicationContext(),
					MapquestMapActivity.class);
			//showContent.setData(Uri.parse(comicID));
			startActivity(showContent);
		} else {
			//detailView.updateComic(comicID);
		}
	}
	
	public void addContactListFragment(FragmentTransaction ft, List<ContactItem> result) {
		contactListFragment = (ContactListFragment) getSupportFragmentManager().findFragmentByTag("ContactList");
		
		if (contactListFragment == null) {
			contactListFragment = new ContactListFragment();
		}
		ft.replace(R.id.contact_list_fragment, contactListFragment);
		ft.commit();
		contactListFragment.setDataList(result);
		contactListFragment.taskRun = true;
	}
}