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
    private MainRemoteControllerActivity mParrent = null;
    private Sensor_Controller_View mSensorControllerView = null;

    // ****************************************************************************************** //
    //
    // constructor
    //
    //
    // ****************************************************************************************** //
    public Sensor_Controller_Fragment(MainRemoteControllerActivity p)
    {
        // Required empty public constructor }
        mParrent = p;
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
        mSensorControllerView = new Sensor_Controller_View(mParrent);
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
//        DroneRemoteControllerProtocol droneProtocol = mParrent.getProtocol();
//        UartService uartservice = mParrent.getUartService();

        switch (v.getId())
        {
            case R.id.backButton :
                mParrent.switch_view(mParrent.VIEW_MAIN_MENU_SCREEN_INDEX);
                break;

            case R.id.testButton :
                break;
        }
    }

}
