package org.codexplosion.rs3;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

public class DetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		FeedItemDetailFragment frag = (FeedItemDetailFragment) getFragmentManager().findFragmentById(R.id.feedDetailFrag);
		ListView listView = frag.getListView();
		listView.setBackgroundColor(getResources().getColor(R.color.white));
		Bundle bundle = getIntent().getExtras();
		if(bundle != null) {
			Log.d("debug", "updating fragment");
			Feed feed = (Feed) bundle.get("feed");
			frag.updateItems(feed);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_detail, menu);
		return true;
	}

}
