package org.codexplosion.rs3;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

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
            Feed feed = (Feed) savedInstanceState.get(FeedFragment.FEED);
            if (feed != null) {
                setItems(feed.getItems());
            }
        }
        setListAdapter(adapter);
        return view;
    }

    public void updateItems(Feed feed) {
        setItems(feed.getItems());
        adapter.notifyDataSetChanged();
    }

    public void setItems(List<Item> items) {
        this.items.clear();
        this.items.addAll(items);
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
}
