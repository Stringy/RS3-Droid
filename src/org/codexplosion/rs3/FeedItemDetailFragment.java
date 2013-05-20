package org.codexplosion.rs3;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import org.codexplosion.rs3.model.Feed;
import org.codexplosion.rs3.model.Item;

import java.util.ArrayList;
import java.util.List;

public class FeedItemDetailFragment extends ListFragment {

    private List<Item> items = new ArrayList<Item>();
    private Feed feed;
    private FeedItemAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed_item_detail,
                container, false);
        Bundle bundle = getActivity().getIntent().getExtras();
        if (bundle != null) {
            feed = (Feed) bundle.getSerializable(FeedFragment.FEED);
            if (feed != null) {
                this.items = feed.getItems();
            }
        }
        adapter = new FeedItemAdapter(getActivity()
                .getApplicationContext(), R.layout.feed_item_list, items);
        setListAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
    }

    public void updateFeed() {
        feed.setItems(items);
        adapter.notifyDataSetChanged();
    }

    public void setItems(Feed feed) {
        this.items.clear();
        this.items.addAll(feed.getItems());
        this.feed = feed;
    }

    @Override
    public void onStart() {
        super.onStart();
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(),
                        WebViewActivity.class);
                Bundle bundle = new Bundle();
                Item item = (Item) getListView().getItemAtPosition(i);
                item.setRead(true);
                adapter.notifyDataSetChanged();
                bundle.putSerializable("URL", item.getLink());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    public Feed getFeed() {
        return this.feed;
    }
}
