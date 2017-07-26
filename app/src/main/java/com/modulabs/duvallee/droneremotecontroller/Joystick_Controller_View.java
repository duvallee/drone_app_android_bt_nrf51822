package com.modulabs.duvallee.droneremotecontroller;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
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

    // ****************************************************************************************** //
    //
    // int MeasuredWidth(int measureSpec)
    //
    // ****************************************************************************************** //
    private int MeasuredWidth(int measureSpec)
    {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        // calculate width of view ...
        if (specMode == MeasureSpec.AT_MOST)
        {

        }
        else if (specMode == MeasureSpec.EXACTLY)
        {

        }
        return specSize;
    }

    // ****************************************************************************************** //
    //
    // int MeasuredHeight(int measureSpec)
    //
    // ****************************************************************************************** //
    private int MeasuredHeight(int measureSpec)
    {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        // calculate height of view ...
        if (specMode == MeasureSpec.AT_MOST)
        {

        }
        else if (specMode == MeasureSpec.EXACTLY)
        {

        }
        return specSize;
    }

    // ****************************************************************************************** //
    //
    // void onMeasure(int wMeasureSpec, int hHeasureSpec)
    //
    // ****************************************************************************************** //
    @Override
    protected void onMeasure(int wMeasureSpec, int hHeasureSpec)
    {
        int measureHeight = MeasuredHeight(hHeasureSpec);
        int measureWidth = MeasuredWidth(wMeasureSpec);

        // -----------------------------------------------------------------------------------------
        // set background image
        setBackgroundResource(R.mipmap.joystick_controller_main);

        // -----------------------------------------------------------------------------------------
        // must be called setMeasuredDimension
        // if not called, occurred run-time error !!!
        setMeasuredDimension(measureWidth, measureHeight);

    }


    // ****************************************************************************************** //
    //
    // public void onDraw(Canvas canvas)
    //
    // ****************************************************************************************** //
    // 2,560 x 1,440       (Galaxy Note 4)
    // 2,560 x 1,440 + 160 (Galaxy Note Edge)
    // 2,560 x 1,532
    @Override
    public void onDraw(Canvas canvas)
    {
    }

    // ****************************************************************************************** //
    //
    // boolean onKeyDown(int keyCode, KeyEvent keyEvent)
    //
    // ****************************************************************************************** //
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent keyEvent)
    {
        return true;
    }

    // ****************************************************************************************** //
    //
    // onKeyUp(int keyCode, KeyEvent keyEvent)
    //
    // ****************************************************************************************** //
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent keyEvent)
    {
        return true;
    }

    // ****************************************************************************************** //
    //
    // boolean onTrackballEvent(MotionEvent event)
    //
    // ****************************************************************************************** //
    @Override
    public boolean onTrackballEvent(MotionEvent event)
    {
        int actionPerformed = event.getAction();
        return true;
    }

    // ****************************************************************************************** //
    //
    // boolean onTouchEvent(MotionEvent event)
    //
    // ****************************************************************************************** //
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        mActivity.switch_view(mActivity.VIEW_MAIN_MENU_SCREEN_INDEX);
        return true;
    }

}
