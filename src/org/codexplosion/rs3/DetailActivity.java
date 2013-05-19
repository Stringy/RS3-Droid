package org.codexplosion.rs3;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class DetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        FeedItemDetailFragment frag = (FeedItemDetailFragment) getFragmentManager().findFragmentById(R.id.feedDetailFrag);
        final ListView listView = frag.getListView();
        listView.setBackgroundColor(getResources().getColor(R.color.white));
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Log.d("debug", "updating fragment");
            Feed feed = (Feed) bundle.get(FeedFragment.FEED);
            setTitle(feed.title);
            frag.updateItems(feed);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(DetailActivity.this, WebViewActivity.class);
                Item item = (Item) listView.getItemAtPosition(i);
                intent.putExtra("URL", item.link);
                startActivity(intent);
            }
        });

        ActionBar ab = getActionBar();
        ab.setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bar_gradient));
        ab.setCustomView(R.layout.rs3_action_bar);
        ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM
                | ActionBar.DISPLAY_SHOW_HOME);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_detail, menu);
        return true;
    }

}
