package org.codexplosion.rs3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import org.codexplosion.rs3.model.Item;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FeedItemAdapter extends ArrayAdapter<Item> {

    private static int widthWordLimit = 10;
    private List<Item> objects;

    public FeedItemAdapter(Context context, int textViewResourceId,
                           List<Item> objects) {
        super(context, textViewResourceId, objects);
        this.objects = objects;
    }

    @Override
    public View getView(int position, View covertView, ViewGroup parent) {
        Collections.sort(objects, new Comparator<Object>() {
            @Override
            public int compare(Object o, Object o2) {
             /*   Date firstDate = ((Item) o).getDate();
                Date secondDate = ((Item) o2).getDate();



                int dCmp = firstDate.compareTo(secondDate);
                if(dCmp != 0) {
                    return dCmp;
                } else {
                    Boolean a = ((Item) o).isRead();
                    Boolean b = ((Item) o2).isRead();
                    return a.compareTo(b);
                }
               */
                Boolean a = ((Item) o).isRead();
                Boolean b = ((Item) o2).isRead();
                if(a != null) {
                	return a.compareTo(b);
                } else {
                	return 0;
                }
            }
        });

        View view = covertView;
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.feed_item_list, null);
        }

        Item item = getItem(position);
        TextView title = (TextView) view.findViewById(R.id.feedItemTitle);
        title.setText(item.getTitle());
        TextView desc = (TextView) view.findViewById(R.id.feedItemDesc);
        String content;
        if (item.getContent().split(" ").length > widthWordLimit) {
            String[] words = item.getContent().split(" ");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < widthWordLimit; i++) {
                sb.append(words[i] + " ");
            }
            content = sb.toString() + "...";
        } else {
            content = item.getContent();
        }
        desc.setText(content);
        if (!item.isRead()) {
            view.setBackgroundColor(getContext().getResources().getColor(R.color.white));
        } else {
            view.setBackgroundColor(getContext().getResources().getColor(R.color.unreadColour));
        }
        return view;
    }

    public List<Item> getObjects() {
        return objects;
    }
}
