package com.modulabs.duvallee.droneremotecontroller;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by duval on 2017-07-03.
 */

public class MainMenuFragment extends Fragment
{
    private MainMenuView mMainView = null;

    // ****************************************************************************************** //
    //
    // public MainMenuView getMainView()
    //
    // ****************************************************************************************** //
    public MainMenuView getMainView()
    {
        return mMainView;
    }

    // ****************************************************************************************** //
    //
    // public MainMenuFragment()
    //
    // ****************************************************************************************** //
    public MainMenuFragment()
    {
        // Required empty public constructor
    }

    // ****************************************************************************************** //
    //
    // void onCreate(Bundle savedInstanceState)
    //
    // ****************************************************************************************** //
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mMainView = new MainMenuView(getActivity());

    }

    // ****************************************************************************************** //
    //
    // View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    //
    // ****************************************************************************************** //
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        // -----------------------------------------------------------------------------------------
        // case 4
        RelativeLayout relativelayout = new RelativeLayout(getActivity());
        relativelayout.addView(mMainView);

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
