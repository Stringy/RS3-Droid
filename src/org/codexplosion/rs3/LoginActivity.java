package org.codexplosion.rs3;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import org.codexplosion.rs3.model.User;

public class LoginActivity extends Activity {

    private Client client = Client.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        Button loginButton = (Button) findViewById(R.id.loginButton);
        final EditText password = (EditText) findViewById(R.id.passwordText);
        final EditText email = (EditText) findViewById(R.id.emailText);

        final TextView uLogin = (TextView) findViewById(R.id.login_unsuccessful);
        final TextView userPass = (TextView) findViewById(R.id.user_or_pass);
        final ProgressBar progress = (ProgressBar) findViewById(R.id.progressBar);
        uLogin.setVisibility(View.GONE);
        userPass.setVisibility(View.GONE);
        progress.setVisibility(View.GONE);
        progress.setIndeterminate(true);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        //        Intent intent = new Intent(LoginActivity.this, FeedsActivity.class);
        //        startActivity(intent);
                progress.setVisibility(View.VISIBLE);
                new AsyncTask<Void, Void, Void>() {

                    private User user;

                    @Override
                    protected Void doInBackground(Void... voids) {
                        user = client.login(email.getText().toString(),
                                password.getText().toString());
                        return null;
                    }

                    @Override
                    protected void onPostExecute(final Void result){
                        if(user == null) {
                            uLogin.setVisibility(View.VISIBLE);
                            userPass.setVisibility(View.VISIBLE);
                            progress.setVisibility(View.GONE);
                            Intent intent = new Intent(LoginActivity.this, FeedsActivity.class);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(LoginActivity.this, FeedsActivity.class);
                            startActivity(intent);
                        }
                    }
                }.execute();
            }
        });
        getActionBar().hide();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
