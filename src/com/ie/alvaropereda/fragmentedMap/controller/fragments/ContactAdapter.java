package com.ie.alvaropereda.fragmentedMap.controller.fragments;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ie.alvaropereda.fragmentedMap.R;
import com.ie.alvaropereda.fragmentedMap.model.ContactItem;

class ContactAdapter extends ArrayAdapter<ContactItem> {
	private static final String TAG = ContactAdapter.class.getName();
	private LayoutInflater inflator = null;
	List<ContactItem> pairList = null;
	private int layout;

	public ContactAdapter(Context context, int resource,
			int textViewResourceId, List<ContactItem> objects) {
		super(context, textViewResourceId, objects);
		this.pairList = objects;
		this.inflator = (LayoutInflater)context.getSystemService
			      (Context.LAYOUT_INFLATER_SERVICE);
	}

	public void setInflater(LayoutInflater mInflater) {
		this.inflator = mInflater;
	}

	public void setLayout(int layout) {
		this.layout = layout;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		try {
			if (convertView == null) {
				convertView = this.inflator.inflate(layout, null);
				holder = new ViewHolder();
				holder.key = (TextView) convertView.findViewById(R.id.key);
				holder.value = (TextView) convertView.findViewById(R.id.value);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			ContactItem pair = (ContactItem) getItem(position);
			String key = pair.mDisplayName;
			String value = pair.mPhone;

			holder.key.setText(key);
			holder.value.setText(value);
		} catch (Exception e) {
			Log.e(TAG, e.toString(), e);
		}
		return convertView;
	}

	static class ViewHolder {
		TextView key;
		TextView value;
	}
}