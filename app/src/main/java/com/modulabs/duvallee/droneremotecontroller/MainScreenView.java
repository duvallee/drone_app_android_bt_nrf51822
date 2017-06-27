package com.modulabs.duvallee.droneremotecontroller;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;


/**
 * Created by duval on 2017-06-26.
 */

public class MainScreenView extends View
{
    private MainRemoteControllerActivity activity;
    public MainScreenView(Context context)
    {
        super(context);
        activity = (MainRemoteControllerActivity) context;
        InitView();

    }
    public MainScreenView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        activity = (MainRemoteControllerActivity) context;
        InitView();
    }

    public MainScreenView(Context context, AttributeSet attrs, int defStyle)
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

        setBackgroundResource(R.mipmap.main_screen);
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

        }
        else if (specMode == MeasureSpec.EXACTLY)
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

        }
        else if (specMode == MeasureSpec.EXACTLY)
        {

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
        int py = height / 2;

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.MAGENTA);
        paint.setTextSize(100f);

        String displayText = "Drone Remote Controller for embedded lab";
        float textWidth = paint.measureText(displayText);

        canvas.drawText(displayText, px - (textWidth / 2), (py + (py / 2)), paint);
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
        activity.active_view_b();
        return true;
    }
}
