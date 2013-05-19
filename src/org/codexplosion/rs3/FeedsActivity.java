package org.codexplosion.rs3;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;

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
}