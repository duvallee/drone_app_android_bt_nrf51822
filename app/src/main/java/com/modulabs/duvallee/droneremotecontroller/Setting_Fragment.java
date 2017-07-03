package com.modulabs.duvallee.droneremotecontroller;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by duval on 2017-07-03.
 */

public class Setting_Fragment extends Fragment
{
    private MainRemoteControllerActivity parrent;
    public Setting_Fragment(MainRemoteControllerActivity p)
    {
        // Required empty public constructor }
        parrent = p;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.setting_fragment, container, false);
    }
}
