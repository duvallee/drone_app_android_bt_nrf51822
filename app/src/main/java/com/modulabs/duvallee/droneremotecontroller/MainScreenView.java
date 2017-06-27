package com.modulabs.duvallee.droneremotecontroller;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;


/**
 * Created by duval on 2017-06-26.
 */

public class MainScreenView extends View
{
    public MainScreenView(Context context)
    {
        super(context);
        setBackgroundColor(Color.BLACK);
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
        p.setColor(Color.CYAN);
        canvas.drawText("Test", 100, 100, p);
    }


}
