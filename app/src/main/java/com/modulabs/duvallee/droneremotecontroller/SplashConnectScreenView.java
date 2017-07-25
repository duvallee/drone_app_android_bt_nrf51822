package com.modulabs.duvallee.droneremotecontroller;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.app.ActionBar;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by duval on 2017-06-28.
 */

public class SplashConnectScreenView extends View
{
    private MainRemoteControllerActivity activity;

    // 2560 x 1440
    private final int TEXT_OUT_Y_2560x1440_COORDINAGE = 1316;
    // 1920 x 1080
    private final int TEXT_OUT_Y_1920x1080_COORDINAGE = 987;

    private int TEXT_OUT_Y_COORDINAGE = 0;

    public SplashConnectScreenView(Context context)
    {
        super(context);
        activity = (MainRemoteControllerActivity) context;
        InitView();

    }

    public SplashConnectScreenView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        activity = (MainRemoteControllerActivity) context;
        InitView();
    }

    public SplashConnectScreenView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        activity = (MainRemoteControllerActivity) context;
        InitView();
    }

    public void InitView()
    {
        setBackgroundColor(Color.BLACK);
        ActionBar actionbar = activity.getSupportActionBar();
        actionbar.hide();

        setFocusable(true);
    }

    private int x;
    private int y;

    private void init()
    {
        Resources resource = getResources();
    }

    @Override
    protected void onMeasure(int wMeasureSpec, int hHeasureSpec)
    {
        int measureHeight = measureHeight(hHeasureSpec);
        int measureWidth = MeasuredWidth(wMeasureSpec);

        setBackgroundResource(R.mipmap.splash_screen);

        if (measureHeight > 1080)   // QUHD
        {
            TEXT_OUT_Y_COORDINAGE = TEXT_OUT_Y_2560x1440_COORDINAGE;
        }
        else
        {
            // Full-HD
            TEXT_OUT_Y_COORDINAGE = TEXT_OUT_Y_1920x1080_COORDINAGE;
        }

        // must be called setMeasuredDimension
        // if not called, occurred run-time error !!!
        setMeasuredDimension(measureWidth, measureHeight);
    }

    private int MeasuredWidth(int measureSpec)
    {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        // calculate width of view ...
        if (specMode == MeasureSpec.AT_MOST)
        {

        } else if (specMode == MeasureSpec.EXACTLY)
        {

        }
        return specSize;
    }

    private int measureHeight(int measureSpec)
    {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        // calculate height of view ...
        if (specMode == MeasureSpec.AT_MOST)
        {

        } else if (specMode == MeasureSpec.EXACTLY) {

        }
        return specSize;
    }

    // 2,560 x 1,440       (Galaxy Note 4)
    // 2,560 x 1,440 + 160 (Galaxy Note Edge)
    // 2,560 x 1,532
    @Override
    public void onDraw(Canvas canvas)
    {
        int height = getMeasuredHeight();
        int width = getMeasuredWidth();

        int px = width / 2;

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.MAGENTA);
        paint.setTextSize(80f);

        String displayText = "Drone Remote Controller for embedded lab";
        float textWidth = paint.measureText(displayText);

        canvas.drawText(displayText, px - (textWidth / 2), TEXT_OUT_Y_COORDINAGE, paint);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent keyEvent)
    {
        return true;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent keyEvent)
    {
        return true;
    }

    @Override
    public boolean onTrackballEvent(MotionEvent event)
    {
        int actionPerformed = event.getAction();
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        int actionPerformed = event.getAction();
        activity.switch_view(activity.VIEW_MAIN_MENU_SCREEN_INDEX);
        return true;
    }
}