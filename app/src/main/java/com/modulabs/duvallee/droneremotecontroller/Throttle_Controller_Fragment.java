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

public class Throttle_Controller_Fragment extends Fragment implements View.OnClickListener
{
    private MainRemoteControllerActivity mParrent;

    private RangeSeekBar<Integer> m_throttle_seekbar_test = null;
    DroneRemoteControllerProtocol m_droneProtocol = null;

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

        ImageButton button = (ImageButton) view.findViewById(R.id.backButton);
        button.setOnClickListener(this);

        // -----------------------------------------------------------------------------------------
        m_droneProtocol = mParrent.getProtocol();

        // -----------------------------------------------------------------------------------------
        //throttle
        m_throttle_seekbar_test = new RangeSeekBar<Integer>(mParrent, true, true);
        m_throttle_seekbar_test.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>()
        {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue, Integer Value)
            {
                m_droneProtocol.set_throttle_value(Value);
                // ---------------------------------------------------------------------------------
                // start : for test code
//                if (mDroneTransmitterBtService != null)
//                {
//                    if (mDroneTransmitterBtService.getStatus() == STATE_CONNECTED)
//                    {
//                        byte[] Data = mDroneRemoteControllerProtocol.getProtocolData();
//                        mDroneTransmitterBtService.writeRXCharacteristic(Data);
//                    }
//                    else
//                    {
//                        showMessage("not connected !!!");
//                    }
//                }
//                else
//                {
//                    showMessage("not initialize !!!");
//                }
                // end : for test code
                // ---------------------------------------------------------------------------------

                Toast.makeText(mParrent, minValue + "-" + maxValue, Toast.LENGTH_LONG).show();
            }
        });

        // layout of throttle
        LinearLayout throttle_Layout = (LinearLayout) view.findViewById(R.id.test_throttle_linear_layout);
        throttle_Layout.addView(m_throttle_seekbar_test);

        int throttle_min = m_droneProtocol.get_throttle_min_value();
        int throttle_value = m_droneProtocol.get_throttle_value();
        int throttle_max = m_droneProtocol.get_throttle_max_value();

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
