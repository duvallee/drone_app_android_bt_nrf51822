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
    private RangeSeekBar<Integer> m_throttle_seekbar_value = null;
    private RangeSeekBar<Integer> m_throttle_seekbar_min_max = null;
    private int m_throttle_min = 0;
    private int m_throttle_value = 0;
    private int m_throttle_max = 0;

    private RangeSeekBar<Integer> m_yaw_seekbar_value = null;
    private RangeSeekBar<Integer> m_yaw_seekbar_min_max = null;
    private int m_yaw_min = 0;
    private int m_yaw_value = 0;
    private int m_yaw_max = 0;


    private RangeSeekBar<Integer> m_pitch_seekbar_value = null;
    private RangeSeekBar<Integer> m_pitch_seekbar_min_max = null;
    private int m_pitch_min = 0;
    private int m_pitch_value = 0;
    private int m_pitch_max = 0;

    // ****************************************************************************************** //
    //
    // constructor
    //
    //
    // ****************************************************************************************** //
    public Setting_Fragment_1page()
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
        View view = inflater.inflate(R.layout.setting_fragment_1page, container, false);

        ImageButton backbutton = (ImageButton) view.findViewById(R.id.backButton);
        backbutton.setOnClickListener(this);

        ImageButton nextbutton = (ImageButton) view.findViewById(R.id.NextButton);
        nextbutton.setOnClickListener(this);

        MainRemoteControllerActivity main_activity = (MainRemoteControllerActivity) getActivity();
        if (main_activity == null)
        {
            // error
            return null;
        }
        // -----------------------------------------------------------------------------------------
        DroneRemoteControllerProtocol droneProtocol = main_activity.getProtocol();

        // -----------------------------------------------------------------------------------------
        // value of throttle
        m_throttle_seekbar_value = new RangeSeekBar<Integer>(main_activity, true, true);
        m_throttle_seekbar_value.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>()
        {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue, Integer Value)
            {
                if (m_throttle_min > Value)
                {
                    m_throttle_value = m_throttle_min;
                    m_throttle_seekbar_value.setValues(m_throttle_value);
                }
                else if (m_throttle_max < Value)
                {
                    m_throttle_value = m_throttle_max;
                    m_throttle_seekbar_value.setValues(m_throttle_value);
                }
                else
                {
                    m_throttle_value = Value;
                }
            }
        });

        // -----------------------------------------------------------------------------------------
        // range of throttle
        m_throttle_seekbar_min_max = new RangeSeekBar<Integer>(main_activity, false, true);
        m_throttle_seekbar_min_max.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>()
        {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue, Integer Value)
            {
                m_throttle_min = minValue;
                m_throttle_max = maxValue;

                if (m_throttle_min > m_throttle_value)
                {
                    m_throttle_value = m_throttle_min;
                    m_throttle_seekbar_value.setValues(m_throttle_value);
                }
                else if (m_throttle_max < m_throttle_value)
                {
                    m_throttle_value = m_throttle_max;
                    m_throttle_seekbar_value.setValues(m_throttle_value);
                }
            }
        });
        m_throttle_min = droneProtocol.get_throttle_min_value();
        m_throttle_value = droneProtocol.get_throttle_value();
        m_throttle_max = droneProtocol.get_throttle_max_value();

        m_throttle_seekbar_min_max.setRangeValues(m_throttle_min, m_throttle_max);
        m_throttle_seekbar_value.setRangeValues(m_throttle_min, m_throttle_max);
        m_throttle_seekbar_value.setValues(m_throttle_value);

        // layout of throttle
        LinearLayout throttle_Layout = (LinearLayout) view.findViewById(R.id.seetings_throttle_linear_layout);
        throttle_Layout.addView(m_throttle_seekbar_value);
        throttle_Layout.addView(m_throttle_seekbar_min_max);

        // -----------------------------------------------------------------------------------------
        // value of yaw
        m_yaw_seekbar_value =  new RangeSeekBar<Integer>(main_activity, true, true);
        m_yaw_seekbar_value.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>()
        {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue, Integer Value)
            {
                if (m_yaw_min > Value)
                {
                    m_yaw_value = m_yaw_min;
                    m_yaw_seekbar_value.setValues(m_yaw_value);
                }
                else if (m_yaw_max < Value)
                {
                    m_yaw_value = m_yaw_max;
                    m_yaw_seekbar_value.setValues(m_yaw_value);
                }
                else
                {
                    m_yaw_value = Value;
                }
//                Toast.makeText(mParrent, minValue + "-" + maxValue, Toast.LENGTH_LONG).show();
            }
        });

        // range of yaw
        m_yaw_seekbar_min_max =  new RangeSeekBar<Integer>(main_activity, false, true);
        m_yaw_seekbar_min_max.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>()
        {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue, Integer Value)
            {
                m_yaw_min = minValue;
                m_yaw_max = maxValue;

                if (m_yaw_min > m_yaw_value)
                {
                    m_yaw_value = m_yaw_min;
                    m_yaw_seekbar_value.setValues(m_yaw_value);
                }
                else if (m_yaw_max < m_yaw_value)
                {
                    m_yaw_value = m_yaw_max;
                    m_yaw_seekbar_value.setValues(m_yaw_value);
                }
            }
        });

        m_yaw_min = droneProtocol.get_yaw_min_value();
        m_yaw_value = droneProtocol.get_yaw_value();
        m_yaw_max = droneProtocol.get_yaw_max_value();

        m_yaw_seekbar_min_max.setRangeValues(m_yaw_min, m_yaw_max);
        m_yaw_seekbar_value.setRangeValues(m_yaw_min, m_yaw_max);
        m_yaw_seekbar_value.setValues(m_yaw_value);

        // layout of yaw
        LinearLayout yaw_Layout = (LinearLayout) view.findViewById(R.id.seetings_Yaw_linear_layout);
        yaw_Layout.addView(m_yaw_seekbar_value);
        yaw_Layout.addView(m_yaw_seekbar_min_max);


        // -----------------------------------------------------------------------------------------
        // value of pitch
        m_pitch_seekbar_value =  new RangeSeekBar<Integer>(main_activity, true, true);
        m_pitch_seekbar_value.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>()
        {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue, Integer Value)
            {
                //Now you have the minValue and maxValue of your RangeSeekbar
                if (m_pitch_min > Value)
                {
                    m_pitch_value = m_pitch_min;
                    m_pitch_seekbar_value.setValues(m_pitch_value);
                }
                else if (m_pitch_max < Value)
                {
                    m_pitch_value = m_pitch_max;
                    m_pitch_seekbar_value.setValues(m_pitch_value);
                }
                else
                {
                    m_pitch_value = Value;
                }
//                Toast.makeText(mParrent, minValue + "-" + maxValue, Toast.LENGTH_LONG).show();
            }
        });

        // range of pitch
        m_pitch_seekbar_min_max = new RangeSeekBar<Integer>(main_activity, false, true);
        m_pitch_seekbar_min_max.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>()
        {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue, Integer Value)
            {
                m_pitch_min = minValue;
                m_pitch_max = maxValue;

                if (m_pitch_min > m_pitch_value)
                {
                    m_pitch_value = m_pitch_min;
                    m_pitch_seekbar_value.setValues(m_pitch_value);
                }
                else if (m_pitch_max < m_pitch_value)
                {
                    m_pitch_value = m_pitch_max;
                    m_pitch_seekbar_value.setValues(m_pitch_value);
                }
            }
        });

        m_pitch_min = droneProtocol.get_pitch_min_value();
        m_pitch_value = droneProtocol.get_pitch_value();
        m_pitch_max = droneProtocol.get_pitch_max_value();

        m_pitch_seekbar_min_max.setRangeValues(m_pitch_min, m_pitch_max);
        m_pitch_seekbar_value.setRangeValues(m_pitch_min, m_pitch_max);
        m_pitch_seekbar_value.setValues(m_pitch_value);

        // layout of Pitch
        LinearLayout pitch_Layout = (LinearLayout) view.findViewById(R.id.seetings_Pitch_linear_layout);
        pitch_Layout.addView(m_pitch_seekbar_value);
        pitch_Layout.addView(m_pitch_seekbar_min_max);

        // -----------------------------------------------------------------------------------------
        linearlayout.addView(view);
        return linearlayout;
    }

    // ****************************************************************************************** //
    //
    // public void onClick(final View v)
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
        switch (v.getId())
        {
            case R.id.backButton :
                main_activity.switch_view(main_activity.VIEW_MAIN_MENU_SCREEN_INDEX);
                break;
            case R.id.NextButton :
                Toast.makeText(main_activity, "button click", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    // ------------------------------------------------------------------------------------------ //
    // Lifeccle for the Fragment
    //
    // Fragment is added ...
    // onAttach()
    // onCreate()
    // onCreateView()
    // onStart()
    // onResume()
    //
    //  Fragment is active ...
    //
    // onPause()
    // onStop()
    // onDestroyView()
    // onDestory()
    // onDetached()
    // Fragment is destroyed ...
    //
    // ------------------------------------------------------------------------------------------ //

    // ****************************************************************************************** //
    //
    // public void onStart()
    //
    // ****************************************************************************************** //
    @Override
    public void onStart()
    {
        super.onStart();
    }

    // ****************************************************************************************** //
    //
    // public void onResume()
    //
    // ****************************************************************************************** //
    @Override
    public void onResume()
    {
        super.onResume();
    }

    // ****************************************************************************************** //
    //
    // public void onPause()
    //
    // ****************************************************************************************** //
    @Override
    public void onPause()
    {
        super.onResume();
    }

    // ****************************************************************************************** //
    //
    // public void onStop()
    //
    // ****************************************************************************************** //
    @Override
    public void onStop()
    {
        super.onStop();
    }

}
