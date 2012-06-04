/**
 * 
 */
package com.ie.alvaropereda.fragmentedMap.controller.tasks;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Data;
import android.util.Log;

import com.ie.alvaropereda.fragmentedMap.controller.fragments.ContactAdapter;
import com.ie.alvaropereda.fragmentedMap.model.ContactItem;

/**
 * @author Alvaro
 * 
 */
public class ListContactTask extends AsyncTask<Void, Void, List<ContactItem>> {
	private static final String TAG = ListContactTask.class.getSimpleName();
	private Activity activity;
	private ContactAdapter adapter;

	public ListContactTask(Activity activity, ContactAdapter adapter) {
		this.activity = activity;
		this.adapter = adapter;
		
		if (adapter.getPairList() == null) {
			adapter.setPairList(new LinkedList<ContactItem>());
		} else {
			adapter.getPairList().clear();
		}
	}

	@Override
	protected List<ContactItem> doInBackground(Void... params) {
		return getContacts(activity);
	}
	
	public List<ContactItem> getContacts(Context activity){
		Cursor c = activity.getContentResolver().query(
				Data.CONTENT_URI,
				new String[] { Data._ID, Data.DISPLAY_NAME, Phone.NUMBER,
						Data.CONTACT_ID, Phone.TYPE, Phone.LABEL },
				Data.MIMETYPE + "='" + ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE + "'", null,
				Data.DISPLAY_NAME);

		int count = c.getCount();
		c.moveToFirst();
		String[] columnNames = c.getColumnNames();
		int displayNameColIndex = c.getColumnIndex("display_name");
		int idColIndex = c.getColumnIndex("_id");
		int col2Index = c.getColumnIndex(columnNames[2]);
		int col3Index = c.getColumnIndex(columnNames[3]);
		int col4Index = c.getColumnIndex(columnNames[4]);

		List<ContactItem> contactItemList = adapter.getPairList();
		
		for (int i = 0; i < count; i++) {
			String displayName = c.getString(displayNameColIndex);
			String address = c.getString(col2Index);
			int contactId = c.getInt(col3Index);
			String phoneType = c.getString(col4Index);
			
			Log.d(TAG, displayName);
			
			long _id = c.getLong(idColIndex);
			ContactItem contactItem = new ContactItem();
			contactItem.mId = _id;
			contactItem.mContactId = contactId;
			contactItem.mDisplayName = displayName;
			contactItem.mAddress = address;
			contactItemList.add(contactItem);
			boolean b2 = c.moveToNext();
		}
		c.close();
		return contactItemList;
	}

	@Override
	protected void onPostExecute(List<ContactItem> result) {
		Log.d(TAG, "OnPostExecute");
		adapter.notifyDataSetChanged();
	}
}