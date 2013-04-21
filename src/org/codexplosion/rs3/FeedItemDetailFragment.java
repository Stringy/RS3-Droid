package org.codexplosion.rs3;

import java.util.ArrayList;
import java.util.List;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FeedItemDetailFragment extends ListFragment {

	List<Item> items = new ArrayList<Item>();
	FeedItemAdapter adapter;
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_feed_item_detail,
				container, false);
		adapter = new FeedItemAdapter(getActivity()
				.getApplicationContext(), R.layout.feed_item_list, items);
		if (savedInstanceState != null) {
			Feed feed = (Feed) savedInstanceState.get("feed");
			if (feed != null) {
				setItems(feed.items);
			}
		}
		setListAdapter(adapter);
		return view;
	}

	public void updateItems(Feed feed) {
		setItems(feed.items);
		adapter.notifyDataSetChanged();
	}
	
	public void setItems(List<Item> items) {
		this.items.clear();
		this.items.addAll(items);
	}
}