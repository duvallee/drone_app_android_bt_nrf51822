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
    private MainRemoteControllerActivity parrent;
    private MainMenuView view;
    public MainMenuFragment(MainRemoteControllerActivity p)
    {
        // Required empty public constructor }
        parrent = p;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        view = new MainMenuView(getActivity());

    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        // -----------------------------------------------------------------------------------------
        // case 4
        RelativeLayout relativelayout = new RelativeLayout(getActivity());
        relativelayout.addView(view);

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
