package com.modulabs.duvallee.droneremotecontroller;

import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.LinearLayout;


/**
 * Created by duval on 2017-06-26.
 */

public class MainScreenFragment extends Fragment
{
    private LinearLayout layout;
    public MainScreenFragment()
    {
        // Required empty public constructor }
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
//        layout = new LinearLayout(getActivity());
//        layout.addView(new MainScreenView());
//        view = new MainScreenView(getActivity());
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return (new MainScreenView(getActivity()));
//        return inflater.inflate(R.layout.fragment_mainscreen, container, false);
    }
}
