package org.codexplosion.rs3;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import org.codexplosion.rs3.model.Feed;

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
        view.setBackgroundColor(getContext().getResources().getColor(R.color.white));
        if(feed != null) {
            ImageView image = (ImageView) view.findViewById(R.id.unreadImageView);
            TextView numUnread = (TextView) view.findViewById(R.id.unreadTextView);

            if(feed.containsUnread()) {
                image.setImageDrawable(view.getResources().getDrawable(R.drawable.unread));
                numUnread.setText(String.valueOf(feed.getUnreadCount()));
            } else {
                image.setImageDrawable(view.getResources().getDrawable(R.drawable.no_unread));
                numUnread.setText(view.getResources().getString(R.string.zero));
            }

            TextView title = (TextView) view.findViewById(R.id.feedTitle);
            title.setText(feed.getTitle());
            TextView desc = (TextView) view.findViewById(R.id.feedDesc);
            desc.setText(feed.getDescription());
        }
        return view;
    }
}