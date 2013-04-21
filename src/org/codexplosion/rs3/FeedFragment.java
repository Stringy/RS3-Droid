package org.codexplosion.rs3;

import java.util.ArrayList;
import java.util.List;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class FeedFragment extends ListFragment {

	private List<Feed> feeds = new ArrayList<Feed>();
	private FeedAdapter adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_feeds, container, false);
		feeds = getFeeds();
		adapter = new FeedAdapter(getActivity()
				.getApplicationContext(), R.layout.feed_list_layout, feeds);
		setListAdapter(adapter);
		return view;
	}

	private List<Feed> getFeeds() {
		List<Feed> feeds = new ArrayList<Feed>();
		List<Item> items = new ArrayList<Item>();

		items.add(new Item("Feed 1 Item 1", "Item Content", "Item Link", null,
				"", false));
		items.add(new Item("Feed 1 Item 2", "Item Content", "Item Link", null,
				"", false));
		items.add(new Item("Feed 1 Item 3", "Item Content", "Item Link", null,
				"", false));
		items.add(new Item("Feed 1 Item 4", "Item Content", "Item Link", null,
				"", false));

		feeds.add(new Feed("Feed One", "Feed Description", "Feed Link", items));

		items = new ArrayList<Item>();
		items.add(new Item("Feed 2 Item 1", "Item Content", "Item Link", null,
				"", false));
		items.add(new Item("Feed 2 Item 2", "Item Content", "Item Link", null,
				"", false));
		items.add(new Item("Feed 2 Item 3", "Item Content", "Item Link", null,
				"", false));
		items.add(new Item("Feed 2 Item 4", "Item Content", "Item Link", null,
				"", false));

		feeds.add(new Feed("Feed Two", "Feed Description", "Feed Link", items));
		
		return feeds;
	}

	@Override
	public void onStart() {
		super.onStart();
		getListView().setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Log.d("debug", "INSIDE CLICK LISTENER YO");
				for(Feed feed : feeds) {
					feed.deselect();
				}
				((Feed) getListView().getItemAtPosition(arg2)).select();
				
				adapter.notifyDataSetChanged();
				FeedItemDetailFragment detail = (FeedItemDetailFragment) getFragmentManager()
						.findFragmentById(R.id.feedDetailFrag);
				if (detail == null || !detail.isInLayout()) {
					Intent intent = new Intent(getActivity(),
							DetailActivity.class);
					Bundle bundle = new Bundle();
					bundle.putSerializable("feed", (Feed) getListView()
							.getItemAtPosition(arg2));
					intent.putExtras(bundle);
					startActivity(intent);
					return;
				} else {

					FeedItemDetailFragment frag = (FeedItemDetailFragment) getFragmentManager()
							.findFragmentById(R.id.feedDetailFrag);
					if (frag != null) {
						Log.d("debug", "Attempting to update current fragment");
						frag.updateItems((Feed) getListView()
								.getItemAtPosition(arg2));
					} else {
						FeedItemDetailFragment newFrag = new FeedItemDetailFragment();
						Bundle args = new Bundle();
						args.putSerializable("feed", getListView()
								.getItemIdAtPosition(arg2));
						newFrag.setArguments(args);

						FragmentTransaction transaction = getFragmentManager()
								.beginTransaction();

						transaction.replace(R.id.feedDetailFrag, newFrag);
						transaction.addToBackStack(null);

						// Commit the transaction
						transaction.commit();
					}
				}
			}
		});
	}
}
