package com.bakalris.example.basicandroidsample;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public int mInt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        final TextView textView = (TextView) findViewById(R.id.hello);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                int SLEEP_INTERVAL_MS = 5000;
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        fab.setVisibility(View.VISIBLE);
                    }
                }, SLEEP_INTERVAL_MS);

                fab.setVisibility(View.GONE);
                Handler handler2 = new Handler();
                handler2.post(new Runnable() {
                    public void run() {
                        textView.append("\n wof wof");
                    }
                });

            }
        });
        final Activity a = this;
        FloatingActionButton fabLogin = (FloatingActionButton) findViewById(R.id.fabLogin);
        fabLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(a, LoginActivity.class);
                //intent.putExtra(.BUNDLE_DATA, mItemResult.getUser_login());
                startActivity(intent);
            }
        });



        String appName = getResources().getString(R.string.app_name);
        mInt = getResources().getInteger(R.integer.vyska);


        textView.setText(textView.getText() + " " + mInt);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(App.getContext());
        prefs.edit().putInt("somethingSmall", mInt).commit();
        int readPrefsVal = prefs.getInt("somethingSmall", 0);


        textView.setText(textView.getText() + "\n Prefs Val : " + readPrefsVal);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
