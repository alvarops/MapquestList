/**
 * 
 */
package com.ie.alvaropereda.fragmentedMap.controller.fragments;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.ie.alvaropereda.fragmentedMap.R;
import com.ie.alvaropereda.fragmentedMap.controller.tasks.ListContactTask;
import com.ie.alvaropereda.fragmentedMap.model.ContactItem;

/**
 * @author Alvaro
 * 
 */
public class ContactListFragment extends ListFragment {

	private static final String TAG = ContactListFragment.class.getName();
	
	private OnListItemSelectedListener listItemSelectedListener;
	private LinkedList<ContactItem> contactItemList = new LinkedList<ContactItem>();
	public boolean taskRun = false;
	long currentID = 0;
	long currentContactID = 0;
	private ContactAdapter mAdapter;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.ListFragment#onActivityCreated(android.os.Bundle)
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		if (!taskRun) {
			FragmentTransaction ft = getFragmentManager().beginTransaction();
	        ListContactTask task= new ListContactTask(getActivity(),ft);
	        task.execute();
		}
		taskRun = true;
		listItemSelectedListener = (OnListItemSelectedListener) getActivity();
		
		mAdapter = new ContactAdapter(getActivity(), R.layout.list_item,
				R.id.key, contactItemList);
		mAdapter.setLayout(R.layout.list_item);
		setListAdapter(mAdapter);
		//getListView().invalidate();
	}

	public void setDataList(List<ContactItem> list) {
		Activity act = getActivity();
		this.contactItemList = (LinkedList<ContactItem>) list;
		if (act != null) {
			mAdapter = new ContactAdapter(act, R.layout.list_item, R.id.key,
					list);
			mAdapter.setLayout(R.layout.list_item);
			setListAdapter(mAdapter);
			//getListView().invalidate();
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
