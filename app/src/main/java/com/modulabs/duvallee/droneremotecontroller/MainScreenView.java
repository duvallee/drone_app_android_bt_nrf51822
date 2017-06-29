package com.modulabs.duvallee.droneremotecontroller;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.Toast;


/**
 * Created by duval on 2017-06-26.
 */

public class MainScreenView extends View
{
    private MainRemoteControllerActivity activity;

    private final String MENU_MAIN_TITLE = "RC Controller";
    private final String MENU_SETTING_TITLE = "Setting";
    private final String MENU_THROTTLE_TITLE = "Throttle";
    private final String MENU_YAW_TITLE = "Yaw";
    private final String MENU_PITCH_TITLE = "Pitch";
    private final String MENU_ROLL_TITLE = "Roll";

    private final int MENU_MAIN_INDEX = 0;
    private final int MENU_SETTING_INDEX = 1;
    private final int MENU_THROTTLE_INDEX = 2;
    private final int MENU_YAW_INDEX = 3;
    private final int MENU_PITCH_INDEX = 4;
    private final int MENU_ROLL_INDEX = 5;
    private final int MENU_MAX = 6;

    // ---------------------------------------------------------------------------
    // constant ( grid coordinate ... )
    private final Rect MENU_MAIN_RECT = new Rect(11, 7, 23, 16);
    private final Rect MENU_SETTING_RECT = new Rect(27, 3, 31, 5);
    private final Rect MENU_THROTTLE_RECT = new Rect(3, 7, 7, 11);
    private final Rect MENU_YAW_RECT = new Rect(3, 12, 7, 16);
    private final Rect MENU_PITCH_RECT = new Rect(27, 12, 31, 16);
    private final Rect MENU_ROLL_RECT = new Rect(27, 7, 31, 11);

    // ---------------------------------------------------------------------------
    private int select_menu_index = -1;

    // one grid : 80 x 80 in xxxhdpi mode (2560 x 1440)
    private final int grid_x_divide = 32;
    private final int grid_y_divide = 18;

    private Rect[] menu_rect;

    private Bitmap[] menu_unselect_Image;
    private Bitmap[] menu_select_Image;

    private String[] menu_title;

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
        // -----------------------------------------------------------------------------------------
        // allocate memory ...
        menu_rect = new Rect[MENU_MAX];
        menu_unselect_Image = new Bitmap[MENU_MAX];
        menu_select_Image = new Bitmap[MENU_MAX];
        menu_title = new String[MENU_MAX];

        // -----------------------------------------------------------------------------------------
        // allocate menu title
        menu_title[MENU_MAIN_INDEX] = new String(MENU_MAIN_TITLE);
        menu_title[MENU_SETTING_INDEX] = new String(MENU_SETTING_TITLE);
        menu_title[MENU_THROTTLE_INDEX] = new String(MENU_THROTTLE_TITLE);
        menu_title[MENU_YAW_INDEX] = new String(MENU_YAW_TITLE);
        menu_title[MENU_PITCH_INDEX] = new String(MENU_PITCH_TITLE);
        menu_title[MENU_ROLL_INDEX] = new String(MENU_ROLL_TITLE);

        // -----------------------------------------------------------------------------------------
        // load image from resource
        Resources resources = activity.getResources();

        menu_unselect_Image[MENU_MAIN_INDEX] = BitmapFactory.decodeResource(resources, R.mipmap.unselect_main_menu);
        menu_select_Image[MENU_MAIN_INDEX] = BitmapFactory.decodeResource(resources, R.mipmap.select_main_menu);

        menu_unselect_Image[MENU_SETTING_INDEX] = BitmapFactory.decodeResource(resources, R.mipmap.unselect_setup_menu);
        menu_select_Image[MENU_SETTING_INDEX] = BitmapFactory.decodeResource(resources, R.mipmap.select_setup_menu);

        menu_unselect_Image[MENU_THROTTLE_INDEX] = BitmapFactory.decodeResource(resources, R.mipmap.unselect_throttle_menu);
        menu_select_Image[MENU_THROTTLE_INDEX] = BitmapFactory.decodeResource(resources, R.mipmap.select_throttle_menu);

        menu_unselect_Image[MENU_YAW_INDEX] = BitmapFactory.decodeResource(resources, R.mipmap.unselect_yaw_menu);
        menu_select_Image[MENU_YAW_INDEX] = BitmapFactory.decodeResource(resources, R.mipmap.select_yaw_menu);

        menu_unselect_Image[MENU_PITCH_INDEX] = BitmapFactory.decodeResource(resources, R.mipmap.unselect_pitch_menu);
        menu_select_Image[MENU_PITCH_INDEX] = BitmapFactory.decodeResource(resources, R.mipmap.select_pitch_menu);

        menu_unselect_Image[MENU_ROLL_INDEX] = BitmapFactory.decodeResource(resources, R.mipmap.unselect_roll_menu);
        menu_select_Image[MENU_ROLL_INDEX] = BitmapFactory.decodeResource(resources, R.mipmap.select_roll_menu);

        // -----------------------------------------------------------------------------------------
        // hide action bar
        setBackgroundColor(Color.BLACK);
        ActionBar actionbar = activity.getSupportActionBar();
        actionbar.hide();

        setFocusable(true);
    }

    private void init()
    {
        Resources resource = getResources();
    }

    @Override
    protected void onMeasure(int wMeasureSpec, int hHeasureSpec)
    {
        int measureHeight = measureHeight(hHeasureSpec);
        int measureWidth = MeasuredWidth(wMeasureSpec);

        int one_grid_x = measureWidth / grid_x_divide;
        int one_grid_y = measureHeight / grid_y_divide;

        // -----------------------------------------------------------------------------------------
        // allocate menu title
        menu_rect[MENU_MAIN_INDEX] = new Rect(one_grid_x * MENU_MAIN_RECT.left,
                one_grid_y * MENU_MAIN_RECT.top,
                one_grid_x * MENU_MAIN_RECT.right,
                one_grid_y * MENU_MAIN_RECT.bottom);
        menu_rect[MENU_SETTING_INDEX] = new Rect(one_grid_x * MENU_SETTING_RECT.left,
                one_grid_y * MENU_SETTING_RECT.top,
                one_grid_x * MENU_SETTING_RECT.right,
                one_grid_y * MENU_SETTING_RECT.bottom);
        menu_rect[MENU_THROTTLE_INDEX] = new Rect(one_grid_x * MENU_THROTTLE_RECT.left,
                one_grid_y * MENU_THROTTLE_RECT.top,
                one_grid_x * MENU_THROTTLE_RECT.right,
                one_grid_y * MENU_THROTTLE_RECT.bottom);
        menu_rect[MENU_YAW_INDEX] = new Rect(one_grid_x * MENU_YAW_RECT.left,
                one_grid_y * MENU_YAW_RECT.top,
                one_grid_x * MENU_YAW_RECT.right,
                one_grid_y * MENU_YAW_RECT.bottom);
        menu_rect[MENU_PITCH_INDEX] = new Rect(one_grid_x * MENU_PITCH_RECT.left,
                one_grid_y * MENU_PITCH_RECT.top,
                one_grid_x * MENU_PITCH_RECT.right,
                one_grid_y * MENU_PITCH_RECT.bottom);
        menu_rect[MENU_ROLL_INDEX] = new Rect(one_grid_x * MENU_ROLL_RECT.left,
                one_grid_y * MENU_ROLL_RECT.top,
                one_grid_x * MENU_ROLL_RECT.right,
                one_grid_y * MENU_ROLL_RECT.bottom);

        // -----------------------------------------------------------------------------------------
        // set background image
        setBackgroundResource(R.mipmap.main_screen);

        // -----------------------------------------------------------------------------------------
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

    public void DrawButton(Canvas canvas, Rect rect, String btn_text, boolean selected)
    {
//        int one_grid_x = (rect.right - rect.left) / 32;
//        int one_grid_y = (rect.bottom - rect.top) / 16;
//
//        Paint thin_paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        Paint thick_paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        Paint text_paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//
//        if (selected == true)
//        {
//            thin_paint.setColor(Color.BLUE);
//            thick_paint.setColor(Color.LTGRAY);
//            text_paint.setColor(Color.DKGRAY);
//        }
//        else
//        {
//            thin_paint.setColor(Color.CYAN);
//            thick_paint.setColor(Color.WHITE);
//            text_paint.setColor(Color.MAGENTA);
//        }
//        thin_paint.setStrokeWidth(5);
//        thick_paint.setStrokeWidth(18);
//        text_paint.setTextSize(80f);
//
//        // thick line for left - top
//        canvas.drawLine(rect.left,
//                rect.top + (one_grid_y * 14),
//                rect.left,
//                rect.top + (one_grid_y * 2), thick_paint);
//
//        //
//        canvas.drawLine(rect.left,
//                rect.top + (one_grid_y * 2),
//                rect.left + (one_grid_x * 2),
//                rect.top, thick_paint);
//
//        canvas.drawLine(rect.left + (one_grid_x * 2),
//                rect.top,
//                rect.left + (one_grid_x * 31),
//                rect.top, thick_paint);
//
//        // thick line for right - bottom
//        canvas.drawLine(rect.right,
//                rect.bottom - (one_grid_y * 14),
//                rect.right,
//                rect.bottom - (one_grid_y * 2), thick_paint);
//
//        //
//        canvas.drawLine(rect.right,
//                rect.bottom - (one_grid_y * 2),
//                rect.right - (one_grid_x * 2),
//                rect.bottom, thick_paint);
//
//        canvas.drawLine(rect.right - (one_grid_x * 2),
//                rect.bottom,
//                rect.right - (one_grid_x * 31),
//                rect.bottom, thick_paint);
//
//        // thin line for left - bottom
//        canvas.drawLine(rect.left,
//                rect.top + (one_grid_y * 14),
//                rect.left,
//                rect.bottom, thin_paint);
//
//        canvas.drawLine(rect.left,
//                rect.bottom,
//                rect.left + (one_grid_x * 2),
//                rect.bottom, thin_paint);
//
//        // thin line for right - top
//        canvas.drawLine(rect.left + (one_grid_x * 31),
//                rect.top,
//                rect.right,
//                rect.top, thin_paint);
//
//        canvas.drawLine(rect.right,
//                rect.top,
//                rect.right,
//                rect.top + (one_grid_y * 2), thin_paint);
//
//        float textWidth = text_paint.measureText(btn_text);
//
////        // minus value, height from baseline to top
////        float textAscent = text_paint.ascent();
////        // plus value, height from baseline to bottom
////        float textDescent = text_paint.descent();
//
//        float height = Math.abs(text_paint.ascent() + text_paint.descent());
//
//        float start_text_x = (rect.left + (rect.width() / 2) - (textWidth / 2));
//        float start_text_y = rect.top + (rect.height() / 2) + (height / 2);
//        canvas.drawText(btn_text, start_text_x, start_text_y, text_paint);
    }

    public void DrawImageButton(Canvas canvas, int menu_index, boolean selected)
    {
        int width = menu_unselect_Image[menu_index].getWidth();
        int height = menu_unselect_Image[menu_index].getHeight();

        Paint line_paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Paint text_paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        line_paint.setColor(Color.CYAN);
        line_paint.setStrokeWidth(5);
        text_paint.setTextSize(50f);

        Rect src = new Rect(0, 0, width, height);
        if (selected == true)
        {
            canvas.drawBitmap(menu_select_Image[menu_index], src, menu_rect[menu_index], null);
            float textWidth = text_paint.measureText(menu_title[menu_index]);

            // minus value, height from baseline to top
            float textAscent = text_paint.ascent();
            // plus value, height from baseline to bottom
            float textDescent = text_paint.descent();

    //        float height = Math.abs(text_paint.ascent() + text_paint.descent());

            float start_text_x = (menu_rect[menu_index].left + (menu_rect[menu_index].width() / 2) - (textWidth / 2));
            float start_text_y = menu_rect[menu_index].bottom - textDescent;

//            text_paint.setTypeface(Typeface.create(Typeface.SANS_SERIF,Typeface.SANS_SERIF));
            canvas.drawText(menu_title[menu_index], start_text_x, start_text_y, text_paint);
        }
        else
        {
            canvas.drawBitmap(menu_unselect_Image[menu_index], src, menu_rect[menu_index], null);
        }
    }

    // 2,560 x 1,440       (Galaxy Note 4)
    // 2,560 x 1,440 + 160 (Galaxy Note Edge)
    // 2,560 x 1,532
    @Override
    public void onDraw(Canvas canvas)
    {
        for (int i = 0; i < MENU_MAX; i++)
        {
            DrawImageButton(canvas, i, (select_menu_index == i) ? true : false);
        }
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
        int action = event.getAction();
        int x = (int) event.getX();
        int y = (int) event.getY();

        for (int i = 0; i < MENU_MAX; i++)
        {
            if (menu_rect[i].contains(x, y) == true)
            {
                select_menu_index = i;
                break;
            }
        }

        if (select_menu_index < 0)
        {
            invalidate();
            // Must be return is true because should be received the ACTION_MOVE, ACTION_UP event
            return true;
        }

        if (action == MotionEvent.ACTION_UP)
        {
            switch(select_menu_index) {
                case MENU_MAIN_INDEX:
                    activity.switch_view(activity.VIEW_JOYSTICKCONTROOLER_INDEX);
                    Toast.makeText(activity, MENU_MAIN_TITLE, Toast.LENGTH_SHORT).show();
                    break;

                case MENU_SETTING_INDEX:
                    activity.switch_view(activity.VIEW_SETTING_INDEX);
                    Toast.makeText(activity, MENU_SETTING_TITLE, Toast.LENGTH_SHORT).show();
                    break;

                case MENU_THROTTLE_INDEX:
                    activity.switch_view(activity.VIEW_THROTTLECONTROLLER_INDEX);
                    Toast.makeText(activity, MENU_THROTTLE_TITLE, Toast.LENGTH_SHORT).show();
                    break;

                case MENU_YAW_INDEX:
                    activity.switch_view(activity.VIEW_YAWCONTROLLER_INDEX);
                    Toast.makeText(activity, MENU_YAW_TITLE, Toast.LENGTH_SHORT).show();
                    break;

                case MENU_PITCH_INDEX:
                    activity.switch_view(activity.VIEW_PITCHCONTROLLER_INDEX);
                    Toast.makeText(activity, MENU_PITCH_TITLE, Toast.LENGTH_SHORT).show();
                    break;

                case MENU_ROLL_INDEX:
                    activity.switch_view(activity.VIEW_ROLLCONTROLLER_INDEX);
                    Toast.makeText(activity, MENU_ROLL_TITLE, Toast.LENGTH_SHORT).show();
                    break;
            }
            select_menu_index = -1;
        }

        invalidate();
        // Must be return is true because should be received the ACTION_MOVE, ACTION_UP event
        return true;
    }
}
