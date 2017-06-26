package com.modulabs.duvallee.droneremotecontroller;

import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;


/**
 * Created by duval on 2017-06-26.
 */

public class fragmentC extends Fragment
{
    public fragmentC() {
        // Required empty public constructor }
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_c, container, false);
    }
}
