package com.modulabs.duvallee.droneremotecontroller;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by duval on 2017-07-03.
 */

public class Yaw_Controller_Fragment extends Fragment implements View.OnClickListener
{
    private MainRemoteControllerActivity mParrent;

    private RangeSeekBar<Integer> m_yaw_seekbar_test = null;

    // ****************************************************************************************** //
    //
    // constructor
    //
    //
    // ****************************************************************************************** //
    public Yaw_Controller_Fragment()
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
    }

    // ****************************************************************************************** //
    //
    // public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    //
    //
    // ****************************************************************************************** //
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        LinearLayout linearlayout = new LinearLayout(getActivity());
        View view = inflater.inflate(R.layout.yaw_fragment, container, false);

        ImageButton button = (ImageButton) view.findViewById(R.id.backButton);
        button.setOnClickListener(this);

        ImageButton default_button = (ImageButton) view.findViewById(R.id.defaultButton);
        default_button.setOnClickListener(this);

        MainRemoteControllerActivity main_activity = (MainRemoteControllerActivity) getActivity();
        if (main_activity == null)
        {
            // error
            return null;
        }

        // -----------------------------------------------------------------------------------------
        // yaw
        m_yaw_seekbar_test = new RangeSeekBar<Integer>(main_activity, true, true);
        m_yaw_seekbar_test.setValueLabel("yaw");

        m_yaw_seekbar_test.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>()
        {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue, Integer Value)
            {
                MainRemoteControllerActivity main_activity = (MainRemoteControllerActivity) getActivity();
                if (main_activity == null)
                {
                    // error
                    return;
                }
                DroneRemoteControllerProtocol droneProtocol = main_activity.getProtocol();
                UartService uartservice = main_activity.getUartService();

                if (uartservice == null)
                {
                    Toast.makeText(main_activity, "Not connected the Drone BT Transmitter", Toast.LENGTH_LONG).show();
                    main_activity.switch_view(main_activity.VIEW_MAIN_MENU_SCREEN_INDEX);
                    return;
                }
                droneProtocol.set_yaw_value(Value);

                if (droneProtocol.Send_Channel_Message() < 0)
                {
                    Toast.makeText(main_activity, "Busy state !!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // layout of yaw
        LinearLayout yaw_Layout = (LinearLayout) view.findViewById(R.id.test_yaw_linear_layout);
        yaw_Layout.addView(m_yaw_seekbar_test);

        DroneRemoteControllerProtocol droneProtocol = main_activity.getProtocol();

        int yaw_min = droneProtocol.get_channel_min_value();
        int yaw_value = droneProtocol.get_channel_default_value();
        int yaw_max = droneProtocol.get_channel_max_value();

        m_yaw_seekbar_test.setRangeValues(yaw_min, yaw_max);
        m_yaw_seekbar_test.setValues(yaw_value);

        // -----------------------------------------------------------------------------------------

        linearlayout.addView(view);

        return linearlayout;
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
        DroneRemoteControllerProtocol droneProtocol = main_activity.getProtocol();
        UartService uartservice = main_activity.getUartService();

        switch (v.getId())
        {
            case R.id.backButton :
                main_activity.switch_view(main_activity.VIEW_MAIN_MENU_SCREEN_INDEX);
                break;
            case R.id.defaultButton :
                if (uartservice == null)
                {
                    Toast.makeText(main_activity, "Not connected the Drone BT Transmitter", Toast.LENGTH_LONG).show();
                    main_activity.switch_view(main_activity.VIEW_MAIN_MENU_SCREEN_INDEX);
                    return;
                }

                droneProtocol.set_yaw_value(512);
                if (droneProtocol.Send_Channel_Message() < 0)
                {
                    Toast.makeText(main_activity, "Busy state !!!", Toast.LENGTH_SHORT).show();
                }

                m_yaw_seekbar_test.setValues(512);
                break;
        }
    }
}
