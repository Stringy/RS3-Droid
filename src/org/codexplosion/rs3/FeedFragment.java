package org.codexplosion.rs3;

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

import java.util.ArrayList;
import java.util.List;

public class FeedFragment extends ListFragment {

    private List<Feed> feeds = new ArrayList<Feed>();
    private FeedAdapter adapter;

    public static String FEED = "feed";

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

        items.add(new Item("MPs challenge Google over UK tax",
                "MPs challenge Google over reporting of income for UK tax, " +
                        "but the internet giant says there are no transactions executed in Britain.",
                "http://www.bbc.co.uk/news/business-22551401#sa-ns_mchannel=rss&ns_source=PublicRSS20-sa",
                null, "", false));

        feeds.add(new Feed(
                "BBC News",
                "News from around the world",
                "http://newsrss.bbc.co.uk/rss/newsonline_uk_edition/uk/rss.xml",
                items));

        return feeds;
    }

    @Override
    public void onStart() {
        super.onStart();
        getListView().setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                for (Feed feed : feeds) {
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
                    bundle.putSerializable(FEED, (Feed) getListView()
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
                        args.putSerializable(FEED, getListView()
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
