package com.modulabs.duvallee.droneremotecontroller;

import android.app.Fragment;
import android.os.Bundle;

/**
 * Created by duval on 2017-07-03.
 */

public class Yaw_Controller_Fragment extends Fragment
{
    private MainRemoteControllerActivity parrent;
    public Yaw_Controller_Fragment(MainRemoteControllerActivity p)
    {
        // Required empty public constructor }
        parrent = p;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }
}
