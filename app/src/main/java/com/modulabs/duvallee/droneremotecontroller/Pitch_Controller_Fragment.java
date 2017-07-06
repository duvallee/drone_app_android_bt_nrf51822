package com.modulabs.duvallee.droneremotecontroller;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

/**
 * Created by duval on 2017-07-03.
 */

public class Pitch_Controller_Fragment extends Fragment implements View.OnClickListener
{
    private MainRemoteControllerActivity mParrent;

    // ****************************************************************************************** //
    //
    // constructor
    //
    //
    // ****************************************************************************************** //
    public Pitch_Controller_Fragment(MainRemoteControllerActivity p)
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
        View view = inflater.inflate(R.layout.under_construction_fragment, container, false);

        ImageButton button = (ImageButton) view.findViewById(R.id.backButton);
        button.setOnClickListener(this);

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
