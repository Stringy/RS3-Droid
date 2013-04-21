package org.codexplosion.rs3;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class FeedAdapter extends ArrayAdapter<Feed> {

	public FeedAdapter(Context context, int resource, int textViewResourceId,
			List<Feed> objects) {
		super(context, resource, textViewResourceId, objects);
	}
	
	public FeedAdapter(Context context, int resource, List<Feed> objects) {
		super(context, resource, objects);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		
		if (view == null) {
			view = LayoutInflater.from(getContext()).inflate(R.layout.feed_list_layout, null);
		}
		Feed feed = getItem(position);
		if(feed != null) {
			if (feed.selected()) {
				view.setBackgroundColor(getContext().getResources().getColor(R.color.light_gray));
			} else {
				view.setBackgroundColor(getContext().getResources().getColor(R.color.white));	
			}
			TextView title = (TextView) view.findViewById(R.id.feedTitle);
			title.setText(feed.title);
			TextView desc = (TextView) view.findViewById(R.id.feedDesc);
			desc.setText(feed.Description);
		}
		return view;
	}
}
