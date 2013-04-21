package org.codexplosion.rs3;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class FeedItemAdapter extends ArrayAdapter<Item> {

	public FeedItemAdapter(Context context, int textViewResourceId,
			List<Item> objects) {
		super(context, textViewResourceId, objects);
	}

	@Override
	public View getView(int position, View covertView, ViewGroup parent) {
		View view = covertView;
		if(view == null) {
			view = LayoutInflater.from(getContext()).inflate(R.layout.feed_item_list, null);
		}
		
		Item item = getItem(position);
		TextView title = (TextView) view.findViewById(R.id.feedItemTitle);
		title.setText(item.title);
		TextView desc = (TextView) view.findViewById(R.id.feedItemDesc);
		desc.setText(item.content);
		return view;
	}
}
