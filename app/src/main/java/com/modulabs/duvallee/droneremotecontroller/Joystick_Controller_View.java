package com.modulabs.duvallee.droneremotecontroller;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by duval on 2017-07-25.
 */

public class Joystick_Controller_View extends View implements Runnable
{
    // ---------------------------------------------------------------------------------------------
    // for Main Activity
    private MainRemoteControllerActivity mActivity;

    // ---------------------------------------------------------------------------------------------
    private final double RAD = 57.2957795;
    public final static long DEFAULT_LOOP_INTERVAL = 100; // 100 ms
    public final static int FRONT = 3;
    public final static int FRONT_RIGHT = 4;
    public final static int RIGHT = 5;
    public final static int RIGHT_BOTTOM = 6;
    public final static int BOTTOM = 7;
    public final static int BOTTOM_LEFT = 8;
    public final static int LEFT = 1;
    public final static int LEFT_FRONT = 2;

    public final static double BUTTONRADIUS = 0.20;
    public final static double JOYSTICKRADIUS = 0.70;

    // ---------------------------------------------------------------------------------------------
    private int left_xPosition = 0; // Touch x position
    private int left_yPosition = 0; // Touch y position

    private int right_xPosition = 0; // Touch x position
    private int right_yPosition = 0; // Touch y position

    private double left_centerX = 0; // Center view x position
    private double left_centerY = 0; // Center view y position

    private double right_centerX = 0; // Center view x position
    private double right_centerY = 0; // Center view y position

    private double exit_centerX = 0; // Center view x position
    private double exit_centerY = 0; // Center view y position

    private Rect left_button_rect = new Rect();
    private Rect right_button_rect = new Rect();
    private Rect exit_button_rect = new Rect();

    private int left_button_select = -1;
    private int right_button_select = -1;
    private int exit_button_select = -1;

    private Paint mainCircle;
    private Paint secondaryCircle;
    private Paint button;
    private Paint horizontalLine;
    private Paint verticalLine;
    private int joystickRadius;
    private int buttonRadius;
    private int lastAngle = 0;
    private int lastPower = 0;

    // ---------------------------------------------------------------------------------------------
    private OnJoystickMoveListener onJoystickMoveListener; // Listener
    private Thread thread = new Thread(this);
    private long loopInterval = DEFAULT_LOOP_INTERVAL;

    // ****************************************************************************************** //
    //
    // constructor
    //
    // ****************************************************************************************** //
    public Joystick_Controller_View(Context context)
    {
        super(context);
        mActivity = (MainRemoteControllerActivity) context;
        Init_Joystick_Controller_View();
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
        Init_Joystick_Controller_View();
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
        Init_Joystick_Controller_View();
    }


    // ****************************************************************************************** //
    //
    // constructor
    //
    // ****************************************************************************************** //
    protected void Init_Joystick_Controller_View()
    {
        mainCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
        mainCircle.setColor(Color.LTGRAY);
        mainCircle.setStyle(Paint.Style.FILL_AND_STROKE);

        secondaryCircle = new Paint();
        secondaryCircle.setColor(Color.GREEN);
        secondaryCircle.setStyle(Paint.Style.STROKE);

        verticalLine = new Paint();
        verticalLine.setStrokeWidth(5);
        verticalLine.setColor(Color.RED);

        horizontalLine = new Paint();
        horizontalLine.setStrokeWidth(2);
        horizontalLine.setColor(Color.BLACK);

        button = new Paint(Paint.ANTI_ALIAS_FLAG);
        button.setColor(Color.DKGRAY);
        button.setStyle(Paint.Style.FILL);
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

//        int d = Math.min(measure(wMeasureSpec), measure(hHeasureSpec));

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
    // void onMeasure(int wMeasureSpec, int hHeasureSpec)
    //
    // ****************************************************************************************** //
    private int measure(int measureSpec)
    {
        int result = 0;

        // Decode the measurement specifications.
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.UNSPECIFIED)
        {
            // Return a default size of 200 if no bounds are specified.
            result = 200;
        }
        else
        {
            // As you want to fill the available space
            // always return the full available bounds.
            result = specSize;
        }
        return result;
    }

    // ****************************************************************************************** //
    //
    // protected void onFinishInflate()
    //
    // ****************************************************************************************** //
    @Override
    protected void onFinishInflate()
    {
    }

    // ****************************************************************************************** //
    //
    // void onSizeChanged(int xNew, int yNew, int xOld, int yOld)
    //
    // ****************************************************************************************** //
    @Override
    protected void onSizeChanged(int xNew, int yNew, int xOld, int yOld)
    {
        super.onSizeChanged(xNew, yNew, xOld, yOld);

        double width_unit = (getWidth()) / 4;
        double height_unit = (getHeight()) / 2;

        int d = Math.min(xNew, yNew);
        buttonRadius = (int) (d / 2 * BUTTONRADIUS);
        joystickRadius = (int) (d / 2 * JOYSTICKRADIUS);

        // before measure, get the center of view
        left_xPosition = (int) width_unit;
        left_yPosition = (int) (height_unit + joystickRadius - buttonRadius);

        right_xPosition = (int) width_unit * 3;
        right_yPosition = (int) getHeight() / 2;

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
        double width_unit = (getWidth()) / 4;
        double height_unit = (getHeight()) / 2;

        left_centerX = width_unit;
        left_centerY = height_unit;

        right_centerX = width_unit * 3;
        right_centerY = height_unit;

        width_unit = (getWidth()) / 12;
        height_unit = (getHeight()) / 16;

        exit_centerX = width_unit;
        exit_centerY = height_unit * 2;

        // left
        // painting the main circle
        canvas.drawCircle((int) left_centerX, (int) left_centerY, joystickRadius, mainCircle);
        // painting the secondary circle
        canvas.drawCircle((int) left_centerX, (int) left_centerY, joystickRadius / 2, secondaryCircle);
        // paint lines
        canvas.drawLine((float) left_centerX, (float) left_centerY, (float) left_centerX, (float) (left_centerY - joystickRadius), verticalLine);
        canvas.drawLine((float) (left_centerX - joystickRadius), (float) left_centerY, (float) (left_centerX + joystickRadius), (float) left_centerY, horizontalLine);
        canvas.drawLine((float) left_centerX, (float) (left_centerY + joystickRadius), (float) left_centerX, (float) left_centerY, horizontalLine);

        // right
        // painting the main circle
        canvas.drawCircle((int) right_centerX, (int) right_centerY, joystickRadius, mainCircle);
        // painting the secondary circle
        canvas.drawCircle((int) right_centerX, (int) right_centerY, joystickRadius / 2, secondaryCircle);
        // paint lines
        canvas.drawLine((float) right_centerX, (float) right_centerY, (float) right_centerX, (float) (right_centerY - joystickRadius), verticalLine);
        canvas.drawLine((float) (right_centerX - joystickRadius), (float) right_centerY, (float) (right_centerX + joystickRadius), (float) right_centerY, horizontalLine);
        canvas.drawLine((float) right_centerX, (float) (right_centerY + joystickRadius), (float) right_centerX, (float) right_centerY, horizontalLine);

        // painting the move button
        // left
        canvas.drawCircle(left_xPosition, left_yPosition, buttonRadius, button);
        left_button_rect.left = (int) (left_xPosition - height_unit);
        left_button_rect.right = (int) (left_xPosition + height_unit);
        left_button_rect.top = (int) (left_yPosition - height_unit);
        left_button_rect.bottom = (int) (left_yPosition + height_unit);

        // right
        canvas.drawCircle(right_xPosition, right_yPosition, buttonRadius, button);
        right_button_rect.left = (int) (right_xPosition - height_unit);
        right_button_rect.right = (int) (right_xPosition + height_unit);
        right_button_rect.top = (int) (right_yPosition - height_unit);
        right_button_rect.bottom = (int) (right_yPosition + height_unit);

        // exit
        canvas.drawCircle((int) exit_centerX, (int) exit_centerY, (float) height_unit, button);
        exit_button_rect.left = (int) (exit_centerX - height_unit);
        exit_button_rect.right = (int) (exit_centerX + height_unit);
        exit_button_rect.top = (int) (exit_centerY - height_unit);
        exit_button_rect.bottom = (int) (exit_centerY + height_unit);
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
        final int action = event.getAction();
        int touch_count = event.getPointerCount();

        int x1, x2, y1, y2;
        int pid1, pid2;

        if (touch_count > 2)
        {
            touch_count = 2;
        }

        switch(action & MotionEvent.ACTION_MASK)
        {
            case MotionEvent.ACTION_DOWN:
                // 처음 터치가 눌러졌을 때
                pid1 = event.getPointerId(0);
                x1 = (int) (event.getX());
                y1 = (int) (event.getY());

                if (exit_button_rect.contains(x1, y1) == true)
                {
                    exit_button_select = 0;
                }

                if (left_button_rect.contains(x1, y1) == true)
                {
                    left_button_select = 0;
                }

                if (right_button_rect.contains(x1, y1) == true)
                {
                    right_button_select = 0;
                }
                break;

            case MotionEvent.ACTION_MOVE:
                // 터치가 눌린 상태에서 움직일 때
                if (touch_count > 1)
                {
                    pid1 = event.getPointerId(0);                           // 터치한 순간부터 부여되는 포인트 고유번호.
                    x1 = (int) (event.getX(0));
                    y1 = (int) (event.getY(0));

                    pid2 = event.getPointerId(1);                           // 터치한 순간부터 부여되는 포인트 고유번호.
                    y2 = (int) (event.getX(1));
                    x2 = (int) (event.getY(1));

                    String string = "Mulit 0 (" + pid1 + ", " + x1 + ", " + y1 + "), 1 (" + pid2 + ", " + x2 + ", " + y2 + ")";
//                    Toast.makeText(mActivity, string, Toast.LENGTH_SHORT).show();
                }
                else
                {
                    pid1 = event.getPointerId(0);                           // 터치한 순간부터 부여되는 포인트 고유번호.
                    x1 = (int) (event.getX(0));
                    y1 = (int) (event.getY(0));

                    String string = "single 0 (" + pid1 + ", " + x1 + ", " + y1 + ")";
//                    Toast.makeText(mActivity, string, Toast.LENGTH_SHORT).show();
                }
                break;

            case MotionEvent.ACTION_UP:
                // 터치가 떼어졌을 때
                if (exit_button_select != -1)
                {
                    mActivity.switch_view(mActivity.VIEW_MAIN_MENU_SCREEN_INDEX);
                    exit_button_select = -1;
                }
                left_button_select = -1;
                right_button_select = -1;
                break;

            case MotionEvent.ACTION_POINTER_DOWN:
                // 터치가 두 개 이상일 때 눌러졌을 때
                pid1 = event.getPointerId(0);                           // 터치한 순간부터 부여되는 포인트 고유번호.
                x1 = (int) (event.getX(0));
                y1 = (int) (event.getY(0));

                pid2 = event.getPointerId(1);                           // 터치한 순간부터 부여되는 포인트 고유번호.
                y2 = (int) (event.getX(1));
                x2 = (int) (event.getY(1));

                if (left_button_rect.contains(x1, y1) == true)
                {
                    left_button_select = 0;
                }
                if (left_button_rect.contains(x2, y2) == true)
                {
                    left_button_select = 1;
                }

                if (right_button_rect.contains(x1, y1) == true)
                {
                    right_button_select = 0;
                }
                if (right_button_rect.contains(x2, y2) == true)
                {
                    right_button_select = 1;
                }


                String string = "Mulit 0 (" + pid1 + ", " + x1 + ", " + y1 + "), 1 (" + pid2 + ", " + x2 + ", " + y2 + ") "
                        + left_button_select + ", " + right_button_select;
                Toast.makeText(mActivity, string, Toast.LENGTH_SHORT).show();
                break;

            case MotionEvent.ACTION_POINTER_UP:
                // 터치가 두 개 이상일 때 떼어졌을 때
//                final int pointerIndex = (action & MotionEvent.ACTION_POINTER_INDEX_MASK) >> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
                break;
            default :
                break;
        }

//        xPosition = (int) event.getX();
//        yPosition = (int) event.getY();
//        double abs = Math.sqrt((xPosition - centerX) * (xPosition - centerX) + (yPosition - centerY) * (yPosition - centerY));
//        if (abs > joystickRadius)
//        {
//            xPosition = (int) ((xPosition - centerX) * joystickRadius / abs + centerX);
//            yPosition = (int) ((yPosition - centerY) * joystickRadius / abs + centerY);
//        }
//        invalidate();
//        if (event.getAction() == MotionEvent.ACTION_UP)
//        {
//            xPosition = (int) centerX;
//            yPosition = (int) centerY;
//            thread.interrupt();
//            if (onJoystickMoveListener != null) {
//                onJoystickMoveListener.onValueChanged(getAngle(), getPower(), getDirection());
//            }
//        }
//        if (onJoystickMoveListener != null && event.getAction() == MotionEvent.ACTION_DOWN)
//        {
//            if (thread != null && thread.isAlive())
//            {
//                thread.interrupt();
//            }
//            thread = new Thread(this);
//            thread.start();
//            if (onJoystickMoveListener != null) {
//                onJoystickMoveListener.onValueChanged(getAngle(), getPower(), getDirection());
//            }
//        }

        return true;
    }

    // ****************************************************************************************** //
    //
    // void setOnJoystickMoveListener(OnJoystickMoveListener listener, long repeatInterval)
    //
    // ****************************************************************************************** //
    private int getAngle()
    {
//        if (xPosition > centerX)
//        {
//            if (yPosition < centerY)
//            {
//                return lastAngle = (int) (Math.atan((yPosition - centerY) / (xPosition - centerX)) * RAD + 90);
//            }
//            else if (yPosition > centerY)
//            {
//                return lastAngle = (int) (Math.atan((yPosition - centerY) / (xPosition - centerX)) * RAD) + 90;
//            }
//            else
//            {
//                return lastAngle = 90;
//            }
//        }
//        else if (xPosition < centerX)
//        {
//            if (yPosition < centerY)
//            {
//                return lastAngle = (int) (Math.atan((yPosition - centerY) / (xPosition - centerX)) * RAD - 90);
//            }
//            else if (yPosition > centerY)
//            {
//                return lastAngle = (int) (Math.atan((yPosition - centerY) / (xPosition - centerX)) * RAD) - 90;
//            }
//            else
//            {
//                return lastAngle = -90;
//            }
//        }
//        else
//        {
//            if (yPosition <= centerY)
//            {
//                return lastAngle = 0;
//            }
//            else
//            {
//                if (lastAngle < 0)
//                {
//                    return lastAngle = -180;
//                }
//                else
//                {
//                    return lastAngle = 180;
//                }
//            }
//        }
        return 0;
    }

    // ****************************************************************************************** //
    //
    // private int getPower()
    //
    // ****************************************************************************************** //
    private int getPower()
    {
        return 1;
        // return (int) (100 * Math.sqrt((xPosition - centerX) * (xPosition - centerX) + (yPosition - centerY) * (yPosition - centerY)) / joystickRadius);
    }

    // ****************************************************************************************** //
    //
    // private int getPower()
    //
    // ****************************************************************************************** //
    private int getDirection()
    {
        if (lastPower == 0 && lastAngle == 0)
        {
            return 0;
        }
        int a = 0;
        if (lastAngle <= 0)
        {
            a = (lastAngle * -1) + 90;
        }
        else if (lastAngle > 0)
        {
            if (lastAngle <= 90)
            {
                a = 90 - lastAngle;
            }
            else
            {
                a = 360 - (lastAngle - 90);
            }
        }

        int direction = (int) (((a + 22) / 45) + 1);

        if (direction > 8)
        {
            direction = 1;
        }
        return direction;
    }

    // ****************************************************************************************** //
    //
    // void setOnJoystickMoveListener(OnJoystickMoveListener listener, long repeatInterval)
    //
    // ****************************************************************************************** //
    public void setOnJoystickMoveListener(OnJoystickMoveListener listener, long repeatInterval)
    {
        this.onJoystickMoveListener = listener;
        this.loopInterval = repeatInterval;
    }

    // ****************************************************************************************** //
    //
    // public interface OnJoystickMoveListener
    //
    // ****************************************************************************************** //
    public interface OnJoystickMoveListener
    {
        public void onValueChanged(int angle, int power, int direction);
    }

    // ****************************************************************************************** //
    //
    // void run()
    //
    // ****************************************************************************************** //
    @Override // Runnable
    public void run()
    {
        while (!Thread.interrupted())
        {
            post(new Runnable()
            {
                public void run()
                {
                    if (onJoystickMoveListener != null) {
                        onJoystickMoveListener.onValueChanged(getAngle(), getPower(), getDirection());
                    }
                }
            });
            try
            {
                Thread.sleep(loopInterval);
            }
            catch (InterruptedException e)
            {
                break;
            }
        }

    }
}
