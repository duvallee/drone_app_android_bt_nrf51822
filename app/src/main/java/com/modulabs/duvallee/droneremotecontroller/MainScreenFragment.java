package com.modulabs.duvallee.droneremotecontroller;

import android.app.Fragment;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import android.support.constraint.ConstraintLayout;
import android.widget.RelativeLayout;


/**
 * Created by duval on 2017-06-26.
 */

public class MainScreenFragment extends Fragment
{
    private MainScreenView view;
    public MainScreenFragment()
    {
        // Required empty public constructor }
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        view = new MainScreenView(getActivity());

    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        // -----------------------------------------------------------------------------------------
        // case 4
        RelativeLayout relativelayout = new RelativeLayout(getActivity());

        relativelayout.addView(view);

//        int width = relativelayout.

        return (relativelayout);

        // -----------------------------------------------------------------------------------------
        // case 3
//        ConstraintLayout constraint_layout = new ConstraintLayout(getActivity());
//        constraint_layout.LayoutParams()
//                ConstraintLayout.LayoutParams (Context c,
//                AttributeSet attrs)
        // -----------------------------------------------------------------------------------------
        // case 2
        // LinearLayout layout = new LinearLayout(getActivity());
        // Button btn = new Button(getActivity());
        // btn.setText("test");
        // layout.addView(btn, 150, 200);
        // layout.addView(view, 500, 500);
        // return (layout);

        // -----------------------------------------------------------------------------------------
        // case 1
        // return (view);
    }
}
