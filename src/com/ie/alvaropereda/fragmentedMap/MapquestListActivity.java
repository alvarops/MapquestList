package com.ie.alvaropereda.fragmentedMap;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.ie.alvaropereda.fragmentedMap.controller.fragments.ContactListFragment;
import com.ie.alvaropereda.fragmentedMap.model.ContactItem;

public class MapquestListActivity extends FragmentActivity {

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

	public void addContactListFragment(FragmentTransaction ft, List<ContactItem> result) {
		contactListFragment = (ContactListFragment) getSupportFragmentManager().findFragmentByTag("ContactList");
		
		if (contactListFragment == null) {
			contactListFragment = new ContactListFragment();
		}
		ft.replace(R.id.listFragment, contactListFragment);
		ft.commit();
		contactListFragment.setDataList(result);
		contactListFragment.taskRun = true;
	}
}