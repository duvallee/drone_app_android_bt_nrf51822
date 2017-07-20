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

public class Roll_Controller_Fragment extends Fragment implements View.OnClickListener
{
    private MainRemoteControllerActivity mParrent;

    private RangeSeekBar<Integer> m_roll_seekbar_test = null;
    private RangeSeekBar<Integer> m_aux1_seekbar_test = null;
    private RangeSeekBar<Integer> m_aux2_seekbar_test = null;
    private RangeSeekBar<Integer> m_aux3_seekbar_test = null;

    // ****************************************************************************************** //
    //
    // constructor
    //
    //
    // ****************************************************************************************** //
    public Roll_Controller_Fragment(MainRemoteControllerActivity p)
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
        View view = inflater.inflate(R.layout.roll_fragment, container, false);

        ImageButton button = (ImageButton) view.findViewById(R.id.backButton);
        button.setOnClickListener(this);

        ImageButton default_button = (ImageButton) view.findViewById(R.id.defaultButton);
        default_button.setOnClickListener(this);

        // -----------------------------------------------------------------------------------------
        // aux1
        m_aux1_seekbar_test = new RangeSeekBar<Integer>(mParrent, true, true);

        m_aux1_seekbar_test.setValueLabel("aux1");

        m_aux1_seekbar_test.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>()
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
                droneProtocol.set_aux_1_value(Value);

                if (droneProtocol.Send_Channel_Message(uartservice) < 0)
                {
                    Toast.makeText(mParrent, "Busy state !!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // layout of aux1
        LinearLayout aux1_Layout = (LinearLayout) view.findViewById(R.id.test_aux1_linear_layout);
        aux1_Layout.addView(m_aux1_seekbar_test);

        DroneRemoteControllerProtocol droneProtocol = mParrent.getProtocol();

        int aux1_min = droneProtocol.get_aux_1_min_value();
        int aux1_value = droneProtocol.get_aux_1_value();
        int aux1_max = droneProtocol.get_aux_1_max_value();

        m_aux1_seekbar_test.setRangeValues(aux1_min, aux1_max);
        m_aux1_seekbar_test.setValues(aux1_value);
        // -----------------------------------------------------------------------------------------

        // -----------------------------------------------------------------------------------------
        // aux2
        m_aux2_seekbar_test = new RangeSeekBar<Integer>(mParrent, true, true);

        m_aux2_seekbar_test.setValueLabel("aux2");

        m_aux2_seekbar_test.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>()
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
                droneProtocol.set_aux_2_value(Value);

                if (droneProtocol.Send_Channel_Message(uartservice) < 0)
                {
                    Toast.makeText(mParrent, "Busy state !!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // layout of aux2
        LinearLayout aux2_Layout = (LinearLayout) view.findViewById(R.id.test_aux2_linear_layout);
        aux2_Layout.addView(m_aux2_seekbar_test);

        int aux2_min = droneProtocol.get_aux_2_min_value();
        int aux2_value = droneProtocol.get_aux_2_value();
        int aux2_max = droneProtocol.get_aux_2_max_value();

        m_aux2_seekbar_test.setRangeValues(aux2_min, aux2_max);
        m_aux2_seekbar_test.setValues(aux2_value);
        // -----------------------------------------------------------------------------------------

        // -----------------------------------------------------------------------------------------
        // aux3
        m_aux3_seekbar_test = new RangeSeekBar<Integer>(mParrent, true, true);

        m_aux3_seekbar_test.setValueLabel("aux3");

        m_aux3_seekbar_test.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>()
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
                droneProtocol.set_aux_3_value(Value);

                if (droneProtocol.Send_Channel_Message(uartservice) < 0)
                {
                    Toast.makeText(mParrent, "Busy state !!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // layout of aux3
        LinearLayout aux3_Layout = (LinearLayout) view.findViewById(R.id.test_aux3_linear_layout);
        aux3_Layout.addView(m_aux3_seekbar_test);

        int aux3_min = droneProtocol.get_aux_3_min_value();
        int aux3_value = droneProtocol.get_aux_3_value();
        int aux3_max = droneProtocol.get_aux_3_max_value();

        m_aux3_seekbar_test.setRangeValues(aux3_min, aux3_max);
        m_aux3_seekbar_test.setValues(aux3_value);
        // -----------------------------------------------------------------------------------------


        // -----------------------------------------------------------------------------------------
        // roll
        m_roll_seekbar_test = new RangeSeekBar<Integer>(mParrent, true, true);

        m_roll_seekbar_test.setValueLabel("roll");

        m_roll_seekbar_test.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>()
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
                droneProtocol.set_roll_value(Value);

                if (droneProtocol.Send_Channel_Message(uartservice) < 0)
                {
                    Toast.makeText(mParrent, "Busy state !!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // layout of roll
        LinearLayout roll_Layout = (LinearLayout) view.findViewById(R.id.test_roll_linear_layout);
        roll_Layout.addView(m_roll_seekbar_test);

        int roll_min = droneProtocol.get_roll_min_value();
        int roll_value = droneProtocol.get_roll_value();
        int roll_max = droneProtocol.get_roll_max_value();

        m_roll_seekbar_test.setRangeValues(roll_min, roll_max);
        m_roll_seekbar_test.setValues(roll_value);
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
        DroneRemoteControllerProtocol droneProtocol = mParrent.getProtocol();
        UartService uartservice = mParrent.getUartService();

        switch (v.getId())
        {
            case R.id.backButton :
                mParrent.switch_view(mParrent.VIEW_MAIN_MENU_SCREEN_INDEX);
                break;

            case R.id.defaultButton :
                if (uartservice == null)
                {
                    Toast.makeText(mParrent, "Not connected the Drone BT Transmitter", Toast.LENGTH_LONG).show();
                    mParrent.switch_view(mParrent.VIEW_MAIN_MENU_SCREEN_INDEX);
                    return;
                }

                droneProtocol.set_roll_value(512);
                if (droneProtocol.Send_Channel_Message(uartservice) < 0)
                {
                    Toast.makeText(mParrent, "Busy state !!!", Toast.LENGTH_SHORT).show();
                }

                m_roll_seekbar_test.setValues(512);
                break;
        }
    }
}
