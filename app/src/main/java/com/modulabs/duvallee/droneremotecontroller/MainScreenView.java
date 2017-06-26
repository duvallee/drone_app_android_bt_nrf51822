package com.modulabs.duvallee.droneremotecontroller;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.view.View;
import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by duval on 2017-06-26.
 */

public class MainScreenView extends View
{
    public MainScreenView(Context context)
    {
        super(context);
    }
    public MainScreenView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }
    public MainScreenView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }



    @Override
    public void onDraw(Canvas canvas)
    {
        Paint p = new Paint();
        p.setTextSize(100);
        p.setColor(Color.BLACK);
        canvas.drawText("Test", 100, 100, p);
    }
}
