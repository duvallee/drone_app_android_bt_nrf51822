package com.modulabs.duvallee.droneremotecontroller;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.app.ActionBar;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by duval on 2017-07-03.
 */

public class MainMenuView extends View
{
    private MainRemoteControllerActivity activity;

    private final String MENU_MAIN_TITLE = "RC Controller";
    private final String MENU_THROTTLE_TITLE = "Throttle";
    private final String MENU_YAW_TITLE = "Yaw";
    private final String MENU_PITCH_TITLE = "Pitch";
    private final String MENU_ROLL_TITLE = "Roll";
    private final String MENU_SETTING_TITLE = "Setting";
    private final String MENU_SEARCHING_TITLE = "Searching";

    private final int MENU_MAIN_INDEX = 0;
    private final int MENU_THROTTLE_INDEX = 1;
    private final int MENU_YAW_INDEX = 2;
    private final int MENU_PITCH_INDEX = 3;
    private final int MENU_ROLL_INDEX = 4;
    private final int MENU_SETTING_INDEX = 5;
    private final int MENU_SEARCHING_INDEX = 6;

    private final int MENU_MAX = 7;

    private final int ICON_DRONE_INDEX = 0;
    private final int ICON_GPS_INDEX = 1;
    private final int ICON_BT_INDEX = 2;

    private final int ICON_MAX = 3;

    private int[] icon_status;

    // ---------------------------------------------------------------------------
    // constant ( grid coordinate ... )
    // left = start x, top = start y, right = width, bottom = height
    // menu & button
    private final Rect MENU_MAIN_RECT = new Rect(846, 398, 867, 924);
    private final Rect MENU_THROTTLE_RECT = new Rect(391, 1000, 375, 322);
    private final Rect MENU_YAW_RECT = new Rect(391, 398, 375, 322);
    private final Rect MENU_PITCH_RECT = new Rect(1793, 398, 375, 322);
    private final Rect MENU_ROLL_RECT = new Rect(1793, 1000, 375, 322);
    private final Rect MENU_SETTING_RECT = new Rect(1958, 56, 210, 210);
    private final Rect MENU_SEARCHING_RECT = new Rect(1737, 56, 210, 210);

    // icon
    private final Rect ICON_1_DRONE_STATUS_RECT = new Rect(391, 56, 210, 210);
    private final Rect ICON_2_GPS_STATUS_RECT = new Rect(611, 56, 210, 210);
    private final Rect ICON_3_BT_STATUS_RECT = new Rect(831, 56, 210, 210);

    // ---------------------------------------------------------------------------
    private int select_menu_index = -1;

    private Rect[] menu_rect;
    private Rect[] icon_rect;

    private Bitmap[] menu_unselect_Image;
    private Bitmap[] menu_select_Image;

    private Bitmap[] icon_on_Image;
    private Bitmap[] icon_off_Image;

    private String[] menu_title;

    // ---------------------------------------------------------------------------
    // constructor
    public MainMenuView(Context context)
    {
        super(context);
        activity = (MainRemoteControllerActivity) context;
        InitView();

    }
    public MainMenuView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        activity = (MainRemoteControllerActivity) context;
        InitView();
    }

    public MainMenuView(Context context, AttributeSet attrs, int defStyle)
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
        icon_rect = new Rect[ICON_MAX];

        // bitmap
        menu_unselect_Image = new Bitmap[MENU_MAX];
        menu_select_Image = new Bitmap[MENU_MAX];

        icon_on_Image = new Bitmap[ICON_MAX];
        icon_off_Image = new Bitmap[ICON_MAX];

        menu_title = new String[MENU_MAX];

        icon_status = new int[ICON_MAX];

        icon_status[ICON_DRONE_INDEX] = 1;
        icon_status[ICON_GPS_INDEX] = 1;
        icon_status[ICON_BT_INDEX] = 1;

        // -----------------------------------------------------------------------------------------
        // allocate menu title
        menu_title[MENU_MAIN_INDEX] = new String(MENU_MAIN_TITLE);
        menu_title[MENU_THROTTLE_INDEX] = new String(MENU_THROTTLE_TITLE);
        menu_title[MENU_YAW_INDEX] = new String(MENU_YAW_TITLE);
        menu_title[MENU_PITCH_INDEX] = new String(MENU_PITCH_TITLE);
        menu_title[MENU_ROLL_INDEX] = new String(MENU_ROLL_TITLE);
        menu_title[MENU_SETTING_INDEX] = new String(MENU_SETTING_TITLE);
        menu_title[MENU_SEARCHING_INDEX] = new String(MENU_SEARCHING_TITLE);

        // -----------------------------------------------------------------------------------------
        // load image from resource
        Resources resources = activity.getResources();

        menu_unselect_Image[MENU_MAIN_INDEX] = BitmapFactory.decodeResource(resources, R.mipmap.unselect_main_menu);
        menu_select_Image[MENU_MAIN_INDEX] = BitmapFactory.decodeResource(resources, R.mipmap.select_main_menu);

        menu_unselect_Image[MENU_THROTTLE_INDEX] = BitmapFactory.decodeResource(resources, R.mipmap.unselect_throttle_menu);
        menu_select_Image[MENU_THROTTLE_INDEX] = BitmapFactory.decodeResource(resources, R.mipmap.select_throttle_menu);

        menu_unselect_Image[MENU_YAW_INDEX] = BitmapFactory.decodeResource(resources, R.mipmap.unselect_yaw_menu);
        menu_select_Image[MENU_YAW_INDEX] = BitmapFactory.decodeResource(resources, R.mipmap.select_yaw_menu);

        menu_unselect_Image[MENU_PITCH_INDEX] = BitmapFactory.decodeResource(resources, R.mipmap.unselect_pitch_menu);
        menu_select_Image[MENU_PITCH_INDEX] = BitmapFactory.decodeResource(resources, R.mipmap.select_pitch_menu);

        menu_unselect_Image[MENU_ROLL_INDEX] = BitmapFactory.decodeResource(resources, R.mipmap.unselect_roll_menu);
        menu_select_Image[MENU_ROLL_INDEX] = BitmapFactory.decodeResource(resources, R.mipmap.select_roll_menu);

        menu_unselect_Image[MENU_SETTING_INDEX] = BitmapFactory.decodeResource(resources, R.mipmap.unselect_setup_menu);
        menu_select_Image[MENU_SETTING_INDEX] = BitmapFactory.decodeResource(resources, R.mipmap.select_setup_menu);

        menu_unselect_Image[MENU_SEARCHING_INDEX] = BitmapFactory.decodeResource(resources, R.mipmap.unselect_searching_menu);
        menu_select_Image[MENU_SEARCHING_INDEX] = BitmapFactory.decodeResource(resources, R.mipmap.select_searching_menu);

        icon_on_Image[ICON_DRONE_INDEX] = BitmapFactory.decodeResource(resources, R.mipmap.on_icon_drone);
        icon_off_Image[ICON_DRONE_INDEX] = BitmapFactory.decodeResource(resources, R.mipmap.off_icon_drone);

        icon_on_Image[ICON_GPS_INDEX] = BitmapFactory.decodeResource(resources, R.mipmap.on_icon_gps);
        icon_off_Image[ICON_GPS_INDEX] = BitmapFactory.decodeResource(resources, R.mipmap.off_icon_gps);

        icon_on_Image[ICON_BT_INDEX] = BitmapFactory.decodeResource(resources, R.mipmap.on_icon_bt);
        icon_off_Image[ICON_BT_INDEX] = BitmapFactory.decodeResource(resources, R.mipmap.off_icon_bt);

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

        // -----------------------------------------------------------------------------------------
        // menu title
        menu_rect[MENU_MAIN_INDEX] = MENU_MAIN_RECT;
        menu_rect[MENU_THROTTLE_INDEX] = MENU_THROTTLE_RECT;
        menu_rect[MENU_YAW_INDEX] = MENU_YAW_RECT;
        menu_rect[MENU_PITCH_INDEX] = MENU_PITCH_RECT;
        menu_rect[MENU_ROLL_INDEX] = MENU_ROLL_RECT;
        menu_rect[MENU_SETTING_INDEX] =MENU_SETTING_RECT;
        menu_rect[MENU_SEARCHING_INDEX] =MENU_SEARCHING_RECT;

        icon_rect[ICON_DRONE_INDEX] = ICON_1_DRONE_STATUS_RECT;
        icon_rect[ICON_GPS_INDEX] = ICON_2_GPS_STATUS_RECT;
        icon_rect[ICON_BT_INDEX] = ICON_3_BT_STATUS_RECT;

        if (measureHeight <= 1080)
        {
            // 1080 / 1440 = 0.75
            double rate = 0.85;
            menu_rect[MENU_MAIN_INDEX].left = (int) (menu_rect[MENU_MAIN_INDEX].left * rate);
            menu_rect[MENU_MAIN_INDEX].top = (int) (menu_rect[MENU_MAIN_INDEX].top * rate);
            menu_rect[MENU_MAIN_INDEX].right = (int) (menu_rect[MENU_MAIN_INDEX].right * rate);
            menu_rect[MENU_MAIN_INDEX].bottom = (int) (menu_rect[MENU_MAIN_INDEX].bottom * rate);

            menu_rect[MENU_THROTTLE_INDEX].left = (int) (menu_rect[MENU_THROTTLE_INDEX].left * rate);
            menu_rect[MENU_THROTTLE_INDEX].top = (int) (menu_rect[MENU_THROTTLE_INDEX].top * rate);
            menu_rect[MENU_THROTTLE_INDEX].right = (int) (menu_rect[MENU_THROTTLE_INDEX].right * rate);
            menu_rect[MENU_THROTTLE_INDEX].bottom = (int) (menu_rect[MENU_THROTTLE_INDEX].bottom * rate);

            menu_rect[MENU_YAW_INDEX].left = (int) (menu_rect[MENU_YAW_INDEX].left * rate);
            menu_rect[MENU_YAW_INDEX].top = (int) (menu_rect[MENU_YAW_INDEX].top * rate);
            menu_rect[MENU_YAW_INDEX].right = (int) (menu_rect[MENU_YAW_INDEX].right * rate);
            menu_rect[MENU_YAW_INDEX].bottom = (int) (menu_rect[MENU_YAW_INDEX].bottom * rate);

            menu_rect[MENU_PITCH_INDEX].left = (int) (menu_rect[MENU_PITCH_INDEX].left * rate);
            menu_rect[MENU_PITCH_INDEX].top = (int) (menu_rect[MENU_PITCH_INDEX].top * rate);
            menu_rect[MENU_PITCH_INDEX].right = (int) (menu_rect[MENU_PITCH_INDEX].right * rate);
            menu_rect[MENU_PITCH_INDEX].bottom = (int) (menu_rect[MENU_PITCH_INDEX].bottom * rate);

            menu_rect[MENU_ROLL_INDEX].left = (int) (menu_rect[MENU_ROLL_INDEX].left * rate);
            menu_rect[MENU_ROLL_INDEX].top = (int) (menu_rect[MENU_ROLL_INDEX].top * rate);
            menu_rect[MENU_ROLL_INDEX].right = (int) (menu_rect[MENU_ROLL_INDEX].right * rate);
            menu_rect[MENU_ROLL_INDEX].bottom = (int) (menu_rect[MENU_ROLL_INDEX].bottom * rate);

            menu_rect[MENU_SETTING_INDEX].left = (int) (menu_rect[MENU_SETTING_INDEX].left * rate);
            menu_rect[MENU_SETTING_INDEX].top = (int) (menu_rect[MENU_SETTING_INDEX].top * rate);
            menu_rect[MENU_SETTING_INDEX].right = (int) (menu_rect[MENU_SETTING_INDEX].right * rate);
            menu_rect[MENU_SETTING_INDEX].bottom = (int) (menu_rect[MENU_SETTING_INDEX].bottom * rate);

            menu_rect[MENU_SEARCHING_INDEX].left = (int) (menu_rect[MENU_SEARCHING_INDEX].left * rate);
            menu_rect[MENU_SEARCHING_INDEX].top = (int) (menu_rect[MENU_SEARCHING_INDEX].top * rate);
            menu_rect[MENU_SEARCHING_INDEX].right = (int) (menu_rect[MENU_SEARCHING_INDEX].right * rate);
            menu_rect[MENU_SEARCHING_INDEX].bottom = (int) (menu_rect[MENU_SEARCHING_INDEX].bottom * rate);

            icon_rect[ICON_DRONE_INDEX].left = (int) (icon_rect[ICON_DRONE_INDEX].left * rate);
            icon_rect[ICON_DRONE_INDEX].top = (int) (icon_rect[ICON_DRONE_INDEX].top * rate);
            icon_rect[ICON_DRONE_INDEX].right = (int) (icon_rect[ICON_DRONE_INDEX].right * rate);
            icon_rect[ICON_DRONE_INDEX].bottom = (int) (icon_rect[ICON_DRONE_INDEX].bottom * rate);

            icon_rect[ICON_GPS_INDEX].left = (int) (icon_rect[ICON_GPS_INDEX].left * rate);
            icon_rect[ICON_GPS_INDEX].top = (int) (icon_rect[ICON_GPS_INDEX].top * rate);
            icon_rect[ICON_GPS_INDEX].right = (int) (icon_rect[ICON_GPS_INDEX].right * rate);
            icon_rect[ICON_GPS_INDEX].bottom = (int) (icon_rect[ICON_GPS_INDEX].bottom * rate);

            icon_rect[ICON_BT_INDEX].left = (int) (icon_rect[ICON_BT_INDEX].left * rate);
            icon_rect[ICON_BT_INDEX].top = (int) (icon_rect[ICON_BT_INDEX].top * rate);
            icon_rect[ICON_BT_INDEX].right = (int) (icon_rect[ICON_BT_INDEX].right * rate);
            icon_rect[ICON_BT_INDEX].bottom = (int) (icon_rect[ICON_BT_INDEX].bottom * rate);
        }

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

        line_paint.setColor(Color.CYAN);
        line_paint.setStrokeWidth(5);

        Rect src = new Rect(0, 0, width, height);
        Rect des = new Rect(menu_rect[menu_index].left, menu_rect[menu_index].top, menu_rect[menu_index].left + menu_rect[menu_index].right, menu_rect[menu_index].top + menu_rect[menu_index].bottom);
        if (selected == true)
        {
            canvas.drawBitmap(menu_select_Image[menu_index], src, des, null);

//            float textWidth = text_paint.measureText(menu_title[menu_index]);
//
//            // minus value, height from baseline to top
//            float textAscent = text_paint.ascent();
//            // plus value, height from baseline to bottom
//            float textDescent = text_paint.descent();
            //        float height = Math.abs(text_paint.ascent() + text_paint.descent());

//            float start_text_x = (des.left + (des.width() / 2) - (textWidth / 2));
//            float start_text_y = des.bottom - textDescent;

//            text_paint.setTypeface(Typeface.create(Typeface.SANS_SERIF,Typeface.SANS_SERIF));
//            canvas.drawText(menu_title[menu_index], start_text_x, start_text_y, text_paint);
        }
        else
        {
            canvas.drawBitmap(menu_unselect_Image[menu_index], src, des, null);
        }
    }

    public void DrawImageIcon(Canvas canvas, int icon_index, boolean on)
    {
        int width = icon_on_Image[icon_index].getWidth();
        int height = icon_on_Image[icon_index].getHeight();

//        private Rect[] icon_rect;
//
//        private Bitmap[] icon_on_Image;
//        private Bitmap[] icon_off_Image;

        Rect src = new Rect(0, 0, width, height);
        Rect des = new Rect(icon_rect[icon_index].left, icon_rect[icon_index].top, icon_rect[icon_index].left + icon_rect[icon_index].right, icon_rect[icon_index].top + icon_rect[icon_index].bottom);
        if (on == true)
        {
            canvas.drawBitmap(icon_on_Image[icon_index], src, des, null);
        }
        else
        {
            canvas.drawBitmap(icon_off_Image[icon_index], src, des, null);
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

        for (int i = 0; i < ICON_MAX; i++)
        {
            DrawImageIcon(canvas, i, (icon_status[i] == i) ? true : false);
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
            Rect rect = new Rect(menu_rect[i].left, menu_rect[i].top, menu_rect[i].left + menu_rect[i].right, menu_rect[i].top + menu_rect[i].bottom);

            if (rect.contains(x, y) == true)
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
            switch(select_menu_index)
            {
                case MENU_MAIN_INDEX:
                    activity.switch_view(activity.VIEW_JOYSTICKCONTROLLER_INDEX);
                    break;

                case MENU_THROTTLE_INDEX:
                    activity.switch_view(activity.VIEW_THROTTLECONTROLLER_INDEX);
                    break;

                case MENU_YAW_INDEX:
                    activity.switch_view(activity.VIEW_YAWCONTROLLER_INDEX);
                    break;

                case MENU_PITCH_INDEX:
                    activity.switch_view(activity.VIEW_PITCHCONTROLLER_INDEX);
                    break;

                case MENU_ROLL_INDEX:
                    activity.switch_view(activity.VIEW_ROLLCONTROLLER_INDEX);
                    break;

                case MENU_SETTING_INDEX :
                    activity.switch_view(activity.VIEW_SETTING_PAGE_1_INDEX);
                    break;

                case MENU_SEARCHING_INDEX :
                    activity.switch_view(activity.VIEW_SEARCHING_INDEX);
                    break;
            }
            select_menu_index = -1;
        }

        invalidate();
        // Must be return is true because should be received the ACTION_MOVE, ACTION_UP event
        return true;
    }
}
