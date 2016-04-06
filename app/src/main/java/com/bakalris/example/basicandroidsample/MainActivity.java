package com.bakalris.example.basicandroidsample;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Mat;

public class MainActivity extends AppCompatActivity  implements CameraBridgeViewBase.CvCameraViewListener2{
    private static final String TAG = "MainActivity";
    public int mInt = 0;



    /* must functions for opencv
    starte
     */
    private View mCameraLayout;
    private View mProgressLayout;
    private CameraBridgeViewBase mOpenCvCameraView;

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS:
                {
                    Log.i(TAG, "OpenCV loaded successfully");
                    mOpenCvCameraView.enableView();
                } break;
                default:
                {
                    super.onManagerConnected(status);
                } break;
            }
        }
    };


    @Override
    public void onPause()
    {
        super.onPause();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        if (!OpenCVLoader.initDebug()) {
            Log.d(TAG, "Internal OpenCV library not found. Using OpenCV Manager for initialization");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, this, mLoaderCallback);
        } else {
            Log.d(TAG, "OpenCV library found inside package. Using it!");
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    /* must functions for opencv
    ende
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        /* init layouts for opencv
                START
         */
        mOpenCvCameraView = (CameraBridgeViewBase) findViewById(R.id.opencv_camera_view);
        mCameraLayout = findViewById(R.id.camera_layout);
        mProgressLayout = findViewById(R.id.camera_progress);

        mOpenCvCameraView.setVisibility(SurfaceView.VISIBLE);

        mOpenCvCameraView.setCvCameraViewListener(this);


        /* init layouts for opencv
                END
         */

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
                showAllKofolas();
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

    private void showAllKofolas() {
        Intent intent = new Intent(this, DbViewActivity.class);
        startActivity(intent);
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


    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mCameraLayout.setVisibility(show ? View.GONE : View.VISIBLE);
            mCameraLayout.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mCameraLayout.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressLayout.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressLayout.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressLayout.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressLayout.setVisibility(show ? View.VISIBLE : View.GONE);
            mCameraLayout.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public void onCameraViewStarted(int width, int height) {

    }

    @Override
    public void onCameraViewStopped() {

    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {

        return inputFrame.rgba();
    }
}
