/**
 * 
 */
package com.ie.aol.hatchday.mapquest.controller.fragments;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.ie.aol.hatchday.mapquest.R;
import com.ie.aol.hatchday.mapquest.controller.tasks.ListContactTask;
import com.ie.aol.hatchday.mapquest.model.ContactItem;

/**
 * @author Alvaro
 * 
 */
public class ContactListFragment extends ListFragment {

	private static final String TAG = ContactListFragment.class.getSimpleName();
	
	private OnListItemSelectedListener listItemSelectedListener;
	private LinkedList<ContactItem> contactItemList = new LinkedList<ContactItem>();
	public boolean taskRun = false;
	long currentID = 0;
	long currentContactID = 0;
	private ContactAdapter mAdapter;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    Log.d(TAG, "onCreate");
		
	    this.contactItemList = new LinkedList<ContactItem>();//getContacts();
		
	    mAdapter = new ContactAdapter(getActivity(), R.layout.list_item,
				R.id.key, contactItemList);
		setListAdapter(mAdapter);
		
		if (!taskRun) {
			new ListContactTask(getActivity(), mAdapter).execute();
		}
		
		taskRun = true;
	    listItemSelectedListener = (OnListItemSelectedListener) getActivity();
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.ListFragment#onActivityCreated(android.os.Bundle)
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		Log.d(TAG, "onActivityCreated");
		
	}

	public void setDataList(List<ContactItem> list) {
		Activity act = getActivity();
		this.contactItemList = (LinkedList<ContactItem>) list;
		if (act != null) {
			mAdapter = new ContactAdapter(act, R.layout.list_item, R.id.key,
					list);
			mAdapter.setLayout(R.layout.list_item);
			mAdapter.notifyDataSetChanged();
			setListAdapter(mAdapter);
		}
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		if (this.contactItemList != null && !this.contactItemList.isEmpty() && this.contactItemList.size() >= position)
		listItemSelectedListener.onListItemSelected(this.contactItemList.get(position));
	}
	
	public interface OnListItemSelectedListener {
	    public void onListItemSelected(ContactItem contactItem);
	}
	
}
