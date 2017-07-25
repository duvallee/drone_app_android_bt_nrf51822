package com.modulabs.duvallee.droneremotecontroller;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by duval on 2017-07-25.
 */

public class Joystick_Controller_View extends View
{
    // for Main Activity
    private MainRemoteControllerActivity mActivity;

    // ****************************************************************************************** //
    //
    // constructor
    //
    // ****************************************************************************************** //
    public Joystick_Controller_View(Context context)
    {
        super(context);
        mActivity = (MainRemoteControllerActivity) context;
    }

    // ****************************************************************************************** //
    //
    // constructor
    //
    // ****************************************************************************************** //
    public Joystick_Controller_View(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        mActivity = (MainRemoteControllerActivity) context;
    }

    // ****************************************************************************************** //
    //
    // constructor
    //
    // ****************************************************************************************** //
    public Joystick_Controller_View(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        mActivity = (MainRemoteControllerActivity) context;
    }


}
