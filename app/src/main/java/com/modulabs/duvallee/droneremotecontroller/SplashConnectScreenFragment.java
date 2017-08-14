package com.modulabs.duvallee.droneremotecontroller;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by duval on 2017-07-03.
 */

public class SplashConnectScreenFragment extends Fragment
{
    private SplashConnectScreenView splash_view;

    // ****************************************************************************************** //
    //
    // public SplashConnectScreenFragment()
    //
    //
    // ****************************************************************************************** //
    public SplashConnectScreenFragment()
    {
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // use parrent instead of getActivity

        MainRemoteControllerActivity main_activity = (MainRemoteControllerActivity) getActivity();
        if (main_activity == null)
        {
            // error
            return;
        }

        splash_view = new SplashConnectScreenView(main_activity);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // -----------------------------------------------------------------------------------------
        // case 4
//        RelativeLayout relativelayout = new RelativeLayout(parrent);
//        relativelayout.addView(splash_view);

//        int width = relativelayout.

//        return (relativelayout);

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
        return (splash_view);
    }
}
