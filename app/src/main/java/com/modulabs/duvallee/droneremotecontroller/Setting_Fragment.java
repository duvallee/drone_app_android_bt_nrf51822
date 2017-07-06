package com.modulabs.duvallee.droneremotecontroller;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by duval on 2017-07-03.
 */

public class Setting_Fragment extends Fragment implements View.OnClickListener
{
    private MainRemoteControllerActivity mParrent;

    // ****************************************************************************************** //
    //
    // constructor
    //
    //
    // ****************************************************************************************** //
    public Setting_Fragment(MainRemoteControllerActivity p)
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

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        LinearLayout linearlayout = new LinearLayout(getActivity());
        View view = inflater.inflate(R.layout.setting_fragment, container, false);

        ImageButton button = (ImageButton) view.findViewById(R.id.backButton);
        button.setOnClickListener(this);

        RangeSeekBar<Integer> seekBar = new RangeSeekBar<Integer>(mParrent);
        seekBar.setRangeValues(0, 100);

        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.seetings_main_linear_layout);
        relativeLayout.addView(seekBar);

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
        }
    }
}
