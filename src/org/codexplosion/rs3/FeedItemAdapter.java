package org.codexplosion.rs3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class FeedItemAdapter extends ArrayAdapter<Item> {

    private static int widthWordLimit = 10;

    public FeedItemAdapter(Context context, int textViewResourceId,
                           List<Item> objects) {
        super(context, textViewResourceId, objects);
    }

    @Override
    public View getView(int position, View covertView, ViewGroup parent) {
        View view = covertView;
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.feed_item_list, null);
        }

        Item item = getItem(position);
        TextView title = (TextView) view.findViewById(R.id.feedItemTitle);
        title.setText(item.title);
        TextView desc = (TextView) view.findViewById(R.id.feedItemDesc);
        String content;
        if (item.content.split(" ").length > widthWordLimit) {
            String[] words = item.content.split(" ");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < widthWordLimit; i++) {
                sb.append(words[i] + " ");
            }
            content = sb.toString() + "...";
        } else {
            content = item.content;
        }
        desc.setText(content);
        if (!item.read) {
            view.setBackgroundColor(getContext().getResources().getColor(R.color.unreadColour));
        }
        return view;
    }
}
