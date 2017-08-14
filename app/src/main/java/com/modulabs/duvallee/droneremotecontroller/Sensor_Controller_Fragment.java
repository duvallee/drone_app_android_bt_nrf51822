package com.modulabs.duvallee.droneremotecontroller;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * Created by duval on 2017-07-21.
 */

public class Sensor_Controller_Fragment extends Fragment
{
    private Sensor_Controller_View mSensorControllerView = null;

    // ****************************************************************************************** //
    //
    // constructor
    //
    //
    // ****************************************************************************************** //
    public Sensor_Controller_Fragment()
    {
        // Required empty public constructor
    }

    // ****************************************************************************************** //
    //
    // void onCreate(Bundle savedInstanceState)
    //
    //
    // ****************************************************************************************** //
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        MainRemoteControllerActivity main_activity = (MainRemoteControllerActivity) getActivity();
        if (main_activity == null)
        {
            // error
            return;
        }
        mSensorControllerView = new Sensor_Controller_View(main_activity);
    }

    // ****************************************************************************************** //
    //
    // public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    //
    //
    // ****************************************************************************************** //
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
//        LinearLayout linearlayout = new LinearLayout(getActivity());
//        View view = inflater.inflate(R.layout.roll_fragment, container, false);
//        ImageButton button = (ImageButton) view.findViewById(R.id.backButton);
//        button.setOnClickListener(this);
//        linearlayout.addView(view);
//        return linearlayout;

        RelativeLayout relativelayout = new RelativeLayout(getActivity());
        relativelayout.addView(mSensorControllerView);

        return (relativelayout);
    }

    // ****************************************************************************************** //
    //
    // void onClick(final View v)
    //
    //
    // ****************************************************************************************** //
    public void onClick(final View v)
    {
        MainRemoteControllerActivity main_activity = (MainRemoteControllerActivity) getActivity();
        if (main_activity == null)
        {
            // error
            return;
        }

//        DroneRemoteControllerProtocol droneProtocol = mParrent.getProtocol();
//        UartService uartservice = mParrent.getUartService();

        switch (v.getId())
        {
            case R.id.backButton :
                main_activity.switch_view(main_activity.VIEW_MAIN_MENU_SCREEN_INDEX);
                break;

            case R.id.testButton :
                break;
        }
    }

}
