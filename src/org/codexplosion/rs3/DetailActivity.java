package org.codexplosion.rs3;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import org.codexplosion.rs3.model.Feed;
import org.codexplosion.rs3.model.Item;

public class DetailActivity extends Activity {

    private Feed feed = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        FeedItemDetailFragment frag = (FeedItemDetailFragment) getFragmentManager().findFragmentById(R.id.feedDetailFrag);
        final ListView listView = frag.getListView();
        listView.setBackgroundColor(getResources().getColor(R.color.white));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(DetailActivity.this, WebViewActivity.class);
                Item item = (Item) listView.getItemAtPosition(i);
                intent.putExtra("URL", item.getLink());
                startActivity(intent);
            }
        });

        ActionBar ab = getActionBar();
        ab.setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bar_gradient));
        ab.setCustomView(R.layout.rs3_action_bar);
        ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM
                | ActionBar.DISPLAY_SHOW_HOME);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Log.d("debug", "updating fragment");
            feed = (Feed) bundle.get(FeedFragment.FEED);
            TextView actionTitle = (TextView) findViewById(R.id.actionBarTitle);
            actionTitle.setText(feed.getTitle());
            frag.updateItems(feed);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        ImageView image = (ImageView) findViewById(R.id.unreadImageView);
        TextView text = (TextView) findViewById(R.id.unreadTextView);
        if(feed != null) {
            if(feed.containsUnread()) {
                int unread = feed.getUnreadCount();
                text.setText(String.valueOf(unread));
                image.setImageDrawable(getResources().getDrawable(R.drawable.unread));
            } else {
                image.setImageDrawable(getResources().getDrawable(R.drawable.no_unread));
                text.setText(getString(R.string.zero));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_detail, menu);
        return true;
    }

}
