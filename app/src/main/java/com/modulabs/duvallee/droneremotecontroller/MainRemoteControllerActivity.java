package com.modulabs.duvallee.droneremotecontroller;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.res.Configuration;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.content.Intent;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBar;

import android.content.pm.ActivityInfo;
import android.view.Window;
import android.view.WindowManager;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MainRemoteControllerActivity extends AppCompatActivity
{

    public static final String TAG = "DroneRemoteControl";

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Fragment fr;
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        // Handle item selection
        switch (item.getItemId())
        {
            case R.id.main_rc_control :
                fr = new fragmentB();
                fragmentTransaction.replace(R.id.fragment_main_frame, fr);
                fragmentTransaction.commit();
                break;
            case R.id.joystick_rc_control :
                fr = new fragmentC();
                fragmentTransaction.replace(R.id.fragment_main_frame, fr);
                fragmentTransaction.commit();
                break;
            case R.id.throttle_rc_control :
                break;
            case R.id.roll_rc_control :
                break;
            case R.id.pitch_rc_control :
                break;
            case R.id.yaw_rc_control :
                break;
            default :
                break;
        }
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        // called before setContentView()
        // start : for full screen
        // title bar : battery, rssi of lte ...
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // or
//        setTheme(android.R.style.Theme_NoTitleBar_Fullscreen);
        // end : for full screen

        setContentView(R.layout.activity_main);

        // called after setContentView()
        ActionBar actionbar = getSupportActionBar();
        actionbar.hide();

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.fragment_main_frame, new MainScreenFragment());
        fragmentTransaction.commit();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);

//        delayedHide(100);
    }

    @Override
    public void onStart()
    {
        super.onStart();
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }

    @Override
    protected void onStop()
    {
        Log.d(TAG, "onStop");
        super.onStop();
    }

    @Override
    protected void onPause()
    {
        Log.d(TAG, "onPause");
        super.onPause();
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }
    @Override
    public void onResume()
    {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
    }
}
