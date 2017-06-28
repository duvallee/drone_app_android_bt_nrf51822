package com.modulabs.duvallee.droneremotecontroller;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.app.ActionBar;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.content.Context;
import android.util.AttributeSet;



/**
 * Created by duval on 2017-06-26.
 */

public class MainScreenView extends View
{
    private MainRemoteControllerActivity activity;

    private final String SUB_MENU_SETTING_TITLE = "Setting";
    private final String SUB_MENU_THROTTLE_TITLE = "Throttle";
    private final String SUB_MENU_YAW_TITLE = "Yaw";
    private final String SUB_MENU_PITCH_TITLE = "Pitch";
    private final String SUB_MENU_ROLL_TITLE = "Roll";

    private final int SUB_MENU_SETTING_INDEX = 0;
    private final int SUB_MENU_THROTTLE_INDEX = 1;
    private final int SUB_MENU_YAW_INDEX = 2;
    private final int SUB_MENU_PITCH_INDEX = 3;
    private final int SUB_MENU_ROLL_INDEX = 4;
    private final int SUB_MENU_MAX = 5;

    private final int MAIN_MENU_INDEX = 5;
    private final int MAIN_DISPLAY_INDEX = 6;


    private final int grid_x = 33;
    private final int grid_y = 19;

    private Rect mainDisplay_rect;
    private Rect mainButton_rect;

    private Rect[] subButton_rect;


    // ---------------------------------------------------------------------------
    // constructor
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
        mainDisplay_rect = new Rect();
        mainButton_rect = new Rect();

        subButton_rect = new Rect[SUB_MENU_MAX];

        subButton_rect[SUB_MENU_SETTING_INDEX] = new Rect();
        subButton_rect[SUB_MENU_THROTTLE_INDEX] = new Rect();
        subButton_rect[SUB_MENU_YAW_INDEX] = new Rect();
        subButton_rect[SUB_MENU_PITCH_INDEX] = new Rect();
        subButton_rect[SUB_MENU_ROLL_INDEX] = new Rect();

        setBackgroundColor(Color.BLACK);
        ActionBar actionbar = activity.getSupportActionBar();
        actionbar.hide();

        setFocusable(true);
    }

    private void init()
    {
        Resources resource = getResources();
    }

//    private final int grid_x = 33;
//    private final int grid_y = 19;
    @Override
    protected void onMeasure(int wMeasureSpec, int hHeasureSpec)
    {
        int measureHeight = measureHeight(hHeasureSpec);
        int measureWidth = MeasuredWidth(wMeasureSpec);

        int one_grid_x = measureWidth / grid_x;
        int one_grid_y = measureHeight / grid_y;

        // width : 24, height = 2,
        // start x : 1, start y = 1
        mainDisplay_rect.set(one_grid_x, one_grid_y, one_grid_x + (one_grid_x * 24), one_grid_y + (one_grid_y * 2));

        // width : 24, height = 14,
        // start x : 1, start y : 4
        mainButton_rect.set(one_grid_x, (one_grid_y * 4), one_grid_x + (one_grid_x * 24), (one_grid_y * 4) + (one_grid_y * 14));


        // width : 6, height = 2,
        // start x : 26, start y : 1
        subButton_rect[SUB_MENU_SETTING_INDEX].set((one_grid_x * 26),
                (one_grid_y * 1),
                (one_grid_x * 26) + (one_grid_x * 6),
                (one_grid_y * 1) + (one_grid_y * 2));
        // width : 6, height = 2,
        // start x : 26, start y : 7
        subButton_rect[SUB_MENU_THROTTLE_INDEX].set((one_grid_x * 26),
                (one_grid_y * 7),
                (one_grid_x * 26) + (one_grid_x * 6),
                (one_grid_y * 7) + (one_grid_y * 2));
        // width : 6, height = 2,
        // start x : 26, start y : 10
        subButton_rect[SUB_MENU_YAW_INDEX].set((one_grid_x * 26),
                (one_grid_y * 10),
                (one_grid_x * 26) + (one_grid_x * 6),
                (one_grid_y * 10) + (one_grid_y * 2));
        // width : 6, height = 2,
        // start x : 26, start y : 13
        subButton_rect[SUB_MENU_PITCH_INDEX].set((one_grid_x * 26),
                (one_grid_y * 13),
                (one_grid_x * 26) + (one_grid_x * 6),
                (one_grid_y * 13) + (one_grid_y * 2));
        // width : 6, height = 2,
        // start x : 26, start y : 16
        subButton_rect[SUB_MENU_ROLL_INDEX].set((one_grid_x * 26),
                (one_grid_y * 16),
                (one_grid_x * 26) + (one_grid_x * 6),
                (one_grid_y * 16) + (one_grid_y * 2));

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

    public void DrawButton(Canvas canvas, Rect rect, String btn_text)
    {
        int one_grid_x = (rect.right - rect.left) / 32;
        int one_grid_y = (rect.bottom - rect.top) / 16;

        Paint thin_paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Paint thick_paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Paint text_paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        thin_paint.setColor(Color.LTGRAY);
        thin_paint.setStrokeWidth(5);

        thick_paint.setColor(Color.WHITE);
        thick_paint.setStrokeWidth(18);

        text_paint.setColor(Color.BLACK);
        text_paint.setTextSize(80f);

        // thick line for left - top
        canvas.drawLine(rect.left,
                rect.top + (one_grid_y * 14),
                rect.left,
                rect.top + (one_grid_y * 2), thick_paint);

        //
        canvas.drawLine(rect.left,
                rect.top + (one_grid_y * 2),
                rect.left + (one_grid_x * 2),
                rect.top, thick_paint);

        canvas.drawLine(rect.left + (one_grid_x * 2),
                rect.top,
                rect.left + (one_grid_x * 31),
                rect.top, thick_paint);

        // thick line for right - bottom
        canvas.drawLine(rect.right,
                rect.bottom - (one_grid_y * 14),
                rect.right,
                rect.bottom - (one_grid_y * 2), thick_paint);

        //
        canvas.drawLine(rect.right,
                rect.bottom - (one_grid_y * 2),
                rect.right - (one_grid_x * 2),
                rect.bottom, thick_paint);

        canvas.drawLine(rect.right - (one_grid_x * 2),
                rect.bottom,
                rect.right - (one_grid_x * 31),
                rect.bottom, thick_paint);

        // thin line for left - bottom
        canvas.drawLine(rect.left,
                rect.top + (one_grid_y * 14),
                rect.left,
                rect.bottom, thin_paint);

        canvas.drawLine(rect.left,
                rect.bottom,
                rect.left + (one_grid_x * 2),
                rect.bottom, thin_paint);

        // thin line for right - top
        canvas.drawLine(rect.left + (one_grid_x * 31),
                rect.top,
                rect.right,
                rect.top, thin_paint);

        canvas.drawLine(rect.right,
                rect.top,
                rect.right,
                rect.top + (one_grid_y * 2), thin_paint);


//        canvas.drawLine(rect.left, rect.top, rect.right, rect.top, thin_paint);
//        canvas.drawLine(rect.right, rect.top, rect.right, rect.bottom, thin_paint);
//        canvas.drawLine(rect.right, rect.bottom, rect.left, rect.bottom, thin_paint);
//        canvas.drawLine(rect.left, rect.bottom, rect.left, rect.top, thin_paint);

        float textWidth = text_paint.measureText(btn_text);
        canvas.drawText(btn_text, (rect.left + (rect.width() / 2) - (textWidth / 2)), rect.top + (rect.height() / 2) + (rect.height() / 4), text_paint);
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
        paint.setColor(Color.YELLOW);
        paint.setTextSize(100f);

//        String displayText = "Drone Remote Controller for embedded lab";
//        float textWidth = paint.measureText(displayText);
//
//        canvas.drawText(displayText, px - (textWidth / 2), (py + (py / 2)), paint);

//        DrawButton(canvas, mainDisplay_rect, "Information");
//        DrawButton(canvas, mainButton_rect, "Main Controller");

        DrawButton(canvas, subButton_rect[SUB_MENU_SETTING_INDEX], SUB_MENU_SETTING_TITLE);
        DrawButton(canvas, subButton_rect[SUB_MENU_THROTTLE_INDEX], SUB_MENU_THROTTLE_TITLE);
        DrawButton(canvas, subButton_rect[SUB_MENU_YAW_INDEX], SUB_MENU_YAW_TITLE);
        DrawButton(canvas, subButton_rect[SUB_MENU_PITCH_INDEX], SUB_MENU_PITCH_TITLE);
        DrawButton(canvas, subButton_rect[SUB_MENU_ROLL_INDEX], SUB_MENU_ROLL_TITLE);



//        paint.setColor(Color.BLACK);
//        canvas.drawRect(mainDisplay_rect, paint);
//        canvas.drawRect(mainButton_rect, paint);
//
//        canvas.drawRect(subButton_rect[SUB_MENU_SETTING_INDEX], paint);
//        canvas.drawRect(subButton_rect[SUB_MENU_THROTTLE_INDEX], paint);
//        canvas.drawRect(subButton_rect[SUB_MENU_YAW_INDEX], paint);
//        canvas.drawRect(subButton_rect[SUB_MENU_PITCH_INDEX], paint);
//        canvas.drawRect(subButton_rect[SUB_MENU_ROLL_INDEX], paint);

//        paint.setAntiAlias(true);
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(100);
//        paint.setColor(Color.RED);
//        paint.setStyle(Paint.Style.FILL);
//        canvas.drawCircle(75, 75, 100, paint);
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
        int x = (int) event.getX();
        int y = (int) event.getY();
        int select_object = -1;

         if (mainDisplay_rect.contains(x, y) == true)
        {
            select_object = MAIN_DISPLAY_INDEX;
        }
        else if (mainButton_rect.contains(x, y) == true)
        {
            select_object = MAIN_MENU_INDEX;
        }
        else
        {
            int i;
            for (i = 0; i < SUB_MENU_MAX; i++)
            {
                if (subButton_rect[i].contains(x, y) == true)
                {
                    select_object = i;
                    break;
                }
            }
        }
        if (select_object < 0)
        {
            invalidate();
            return super.onTouchEvent(event);
        }

        if (actionPerformed == MotionEvent.ACTION_DOWN)
        {
//            switch(select_object)
//            {
//                case SUB_MENU_SETTING_INDEX:
//                    activity.switch_view(activity.VIEW_SETTING_INDEX);
//                    break;
//                case SUB_MENU_THROTTLE_INDEX:
//                    activity.switch_view(activity.VIEW_THROTTLECONTROLLER_INDEX);
//                    break;
//                case SUB_MENU_YAW_INDEX:
//                    activity.switch_view(activity.VIEW_YAWCONTROLLER_INDEX);
//                    break;
//                case SUB_MENU_PITCH_INDEX:
//                    activity.switch_view(activity.VIEW_PITCHCONTROLLER_INDEX);
//                    break;
//                case SUB_MENU_ROLL_INDEX:
//                    activity.switch_view(activity.VIEW_ROLLCONTROLLER_INDEX);
//                    break;
//                case MAIN_MENU_INDEX:
//                    activity.switch_view(activity.VIEW_JOYSTICKCONTROOLER_INDEX);
//                    break;
//                case MAIN_DISPLAY_INDEX:
//                    activity.switch_view(activity.VIEW_SPLASHCONNECTSCREEN_INDEX);
//                    break;
//            }
        }
        else if (actionPerformed == MotionEvent.ACTION_MOVE)
        {

        }
        else if (actionPerformed == MotionEvent.ACTION_UP)
        {
            switch(select_object)
            {
                case SUB_MENU_SETTING_INDEX :
                    activity.switch_view(activity.VIEW_SETTING_INDEX);
                    break;
                case SUB_MENU_THROTTLE_INDEX :
                    activity.switch_view(activity.VIEW_THROTTLECONTROLLER_INDEX);
                    break;
                case SUB_MENU_YAW_INDEX :
                    activity.switch_view(activity.VIEW_YAWCONTROLLER_INDEX);
                    break;
                case SUB_MENU_PITCH_INDEX :
                    activity.switch_view(activity.VIEW_PITCHCONTROLLER_INDEX);
                    break;
                case SUB_MENU_ROLL_INDEX :
                    activity.switch_view(activity.VIEW_ROLLCONTROLLER_INDEX);
                    break;
                case MAIN_MENU_INDEX :
                    activity.switch_view(activity.VIEW_JOYSTICKCONTROOLER_INDEX);
                    break;
                case MAIN_DISPLAY_INDEX :
                    activity.switch_view(activity.VIEW_SPLASHCONNECTSCREEN_INDEX);
                    break;
            }
        }
        else
        {

        }
        invalidate();
        return super.onTouchEvent(event);
    }
}
