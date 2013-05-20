package org.codexplosion.rs3;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import org.codexplosion.rs3.model.Feed;

import java.util.List;

/**
 * Created by giles on 16/05/13.
 */
public class FeedsActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar ab = getActionBar();
        ab.setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bar_gradient));
        ab.setCustomView(R.layout.rs3_action_bar);
        ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM
                | ActionBar.DISPLAY_SHOW_HOME);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    @Override
    public void onResume() {
    	super.onResume();
    	FeedFragment frag = (FeedFragment) getFragmentManager().findFragmentById(R.id.feedItemFrag);
    	List<Feed> list;
        list = frag.getFeeds();
        int numUnread = 0;
        for(Feed f : list) {
            numUnread += f.getUnreadCount();
        }
        ImageView image = (ImageView) findViewById(R.id.unreadImageView);
        TextView text = (TextView) findViewById(R.id.unreadTextView);
        if(numUnread == 0) {
            image.setImageDrawable(getResources().getDrawable(R.drawable.no_unread));
            text.setText(String.valueOf(numUnread));
        } else {
            image.setImageDrawable(getResources().getDrawable(R.drawable.unread));
            text.setText(String.valueOf(numUnread));
        }
    }
}