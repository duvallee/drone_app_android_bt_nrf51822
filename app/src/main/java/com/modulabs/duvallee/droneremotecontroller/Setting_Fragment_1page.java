package com.modulabs.duvallee.droneremotecontroller;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * Created by duval on 2017-07-03.
 */

public class Setting_Fragment_1page extends Fragment implements View.OnClickListener
{
    private MainRemoteControllerActivity mParrent;

    // ------------------------------------------------------------------------------------------ //
    RangeSeekBar<Integer> throttle_value = null;
    RangeSeekBar<Integer> throttle_min_max = null;

    RangeSeekBar<Integer> yaw_value = null;
    RangeSeekBar<Integer> yaw_min_max = null;

    RangeSeekBar<Integer> pitch_value = null;
    RangeSeekBar<Integer> pitch_min_max = null;


    // ****************************************************************************************** //
    //
    // constructor
    //
    //
    // ****************************************************************************************** //
    public Setting_Fragment_1page(MainRemoteControllerActivity p)
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
    }

//    // channel id
//    public final int SPEKTRUM_CHANNEL_ROLL = 0;
//    public final int SPEKTRUM_CHANNEL_PITCH = 1;
//    public final int SPEKTRUM_CHANNEL_YAW = 2;
//    public final int SPEKTRUM_CHANNEL_THROTTLE = 3;
//    public final int SPEKTRUM_CHANNEL_GEAR = 4;
//    public final int SPEKTRUM_CHANNEL_AUX_1 = 5;
//    public final int SPEKTRUM_CHANNEL_AUX_2 = 6;
//    public final int SPEKTRUM_CHANNEL_AUX_3 = 7;
//    public final int SPEKTRUM_CHANNEL_AUX_4 = 8;
//    public final int SPEKTRUM_CHANNEL_AUX_5 = 9;
//    public final int SPEKTRUM_CHANNEL_AUX_6 = 10;
//    public final int SPEKTRUM_CHANNEL_AUX_7 = 11;
//    public final int SPEKTRUM_MAX_CHANNEL = 12;

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        LinearLayout linearlayout = new LinearLayout(getActivity());
        View view = inflater.inflate(R.layout.setting_fragment_1page, container, false);

        ImageButton backbutton = (ImageButton) view.findViewById(R.id.backButton);
        backbutton.setOnClickListener(this);

        ImageButton nextbutton = (ImageButton) view.findViewById(R.id.NextButton);
        nextbutton.setOnClickListener(this);

        // -----------------------------------------------------------------------------------------
        DroneRemoteControllerProtocol droneProtocol = mParrent.getProtocol();

        RangeSeekBar<Integer> throttle_value = new RangeSeekBar<Integer>(mParrent, false);;
        RangeSeekBar<Integer> throttle_min_max = new RangeSeekBar<Integer>(mParrent, true);;
        throttle_min_max.setRangeValues(0, 100);

        RangeSeekBar<Integer> yaw_value =  new RangeSeekBar<Integer>(mParrent, false);;
        RangeSeekBar<Integer> yaw_min_max =  new RangeSeekBar<Integer>(mParrent, true);;
        yaw_min_max.setRangeValues(0, 100);

        RangeSeekBar<Integer> pitch_value =  new RangeSeekBar<Integer>(mParrent, false);;
        RangeSeekBar<Integer> pitch_min_max = new RangeSeekBar<Integer>(mParrent, true);;
        pitch_min_max.setRangeValues(0, 100);

        // -----------------------------------------------------------------------------------------
        LinearLayout throttle_Layout = (LinearLayout) view.findViewById(R.id.seetings_throttle_linear_layout);
        throttle_Layout.addView(throttle_min_max);
        throttle_Layout.addView(throttle_value);

        // -----------------------------------------------------------------------------------------
        LinearLayout yaw_Layout = (LinearLayout) view.findViewById(R.id.seetings_Yaw_linear_layout);
        yaw_Layout.addView(yaw_min_max);
        yaw_Layout.addView(yaw_value);

        // -----------------------------------------------------------------------------------------
        LinearLayout pitch_Layout = (LinearLayout) view.findViewById(R.id.seetings_Pitch_linear_layout);
        pitch_Layout.addView(pitch_min_max);
        pitch_Layout.addView(pitch_value);

        // -----------------------------------------------------------------------------------------
        linearlayout.addView(view);
        return linearlayout;
    }

    public void onClick(final View v)
    {
        switch (v.getId())
        {
            case R.id.backButton :
                mParrent.switch_view(mParrent.VIEW_MAIN_MENU_SCREEN_INDEX);
//                Toast.makeText(mParrent, "button click", Toast.LENGTH_SHORT).show();
                break;
            case R.id.NextButton :
                Toast.makeText(mParrent, "button click", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
