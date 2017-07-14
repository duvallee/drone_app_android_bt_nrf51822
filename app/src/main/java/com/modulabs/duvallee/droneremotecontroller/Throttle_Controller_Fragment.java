package com.modulabs.duvallee.droneremotecontroller;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

/**
 * Created by duval on 2017-07-03.
 */

public class Throttle_Controller_Fragment extends Fragment implements View.OnClickListener
{
    private MainRemoteControllerActivity mParrent;

    private RangeSeekBar<Integer> m_throttle_seekbar_test = null;

    // ****************************************************************************************** //
    //
    // constructor
    //
    //
    // ****************************************************************************************** //
    public Throttle_Controller_Fragment(MainRemoteControllerActivity p)
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

    // ****************************************************************************************** //
    //
    // public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    //
    //
    // ****************************************************************************************** //
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        LinearLayout linearlayout = new LinearLayout(getActivity());
        View view = inflater.inflate(R.layout.throttle_fragment, container, false);

        // -----------------------------------------------------------------------------------------
        ImageButton button = (ImageButton) view.findViewById(R.id.backButton);
        button.setOnClickListener(this);

        // -----------------------------------------------------------------------------------------
        Switch arming_switch = (Switch) view.findViewById(R.id.armingSwitchButton);
        arming_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    buttonView.setText("DISARM");
                }
                else
                {
                    buttonView.setText("ARM");
                }
                DroneRemoteControllerProtocol droneProtocol = mParrent.getProtocol();
                UartService uartservice = mParrent.getUartService();

                if (uartservice == null)
                {
                    Toast.makeText(mParrent, "Not connected the Drone BT Transmitter", Toast.LENGTH_LONG).show();
                    mParrent.switch_view(mParrent.VIEW_MAIN_MENU_SCREEN_INDEX);
                    return;
                }

                if (isChecked)
                {
                    droneProtocol.set_gear_value(droneProtocol.get_gear_max_value());
                }
                else
                {
                    droneProtocol.set_gear_value(droneProtocol.get_gear_min_value());
                }

                if (droneProtocol.Send_Channel_Message(uartservice) < 0)
                {
                    Toast.makeText(mParrent, "Busy state !!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // -----------------------------------------------------------------------------------------
        //throttle
        m_throttle_seekbar_test = new RangeSeekBar<Integer>(mParrent, true, true);
        m_throttle_seekbar_test.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>()
        {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue, Integer Value)
            {
                DroneRemoteControllerProtocol droneProtocol = mParrent.getProtocol();
                UartService uartservice = mParrent.getUartService();

                if (uartservice == null)
                {
                    Toast.makeText(mParrent, "Not connected the Drone BT Transmitter", Toast.LENGTH_LONG).show();
                    mParrent.switch_view(mParrent.VIEW_MAIN_MENU_SCREEN_INDEX);
                    return;
                }
                droneProtocol.set_throttle_value(Value);
                if (droneProtocol.Send_Channel_Message(uartservice) < 0)
                {
                    Toast.makeText(mParrent, "Busy state !!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // layout of throttle
        LinearLayout throttle_Layout = (LinearLayout) view.findViewById(R.id.test_throttle_linear_layout);
        throttle_Layout.addView(m_throttle_seekbar_test);

        DroneRemoteControllerProtocol droneProtocol = mParrent.getProtocol();

        int throttle_min = droneProtocol.get_throttle_min_value();
        int throttle_value = droneProtocol.get_throttle_value();
        int throttle_max = droneProtocol.get_throttle_max_value();

        m_throttle_seekbar_test.setRangeValues(throttle_min, throttle_max);
        m_throttle_seekbar_test.setValues(throttle_value);

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
        switch (v.getId())
        {
            case R.id.backButton :
                mParrent.switch_view(mParrent.VIEW_MAIN_MENU_SCREEN_INDEX);
                break;
        }
    }
}
