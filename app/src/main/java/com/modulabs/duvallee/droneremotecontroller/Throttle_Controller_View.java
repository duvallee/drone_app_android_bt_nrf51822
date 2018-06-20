package com.modulabs.duvallee.droneremotecontroller;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class Throttle_Controller_View extends View implements Runnable
{
    public static final String TAG = "Throttle_Controller_View";

    public final static long DEFAULT_LOOP_INTERVAL = 1000; // 1000 ms

    // ---------------------------------------------------------------------------------------------
    // for Main Activity
    private MainRemoteControllerActivity m_MainActivity;

    // ---------------------------------------------------------------------------------------------
    private OnJoystickMoveListener onJoystickMoveListener; // Listener
    
    private Thread thread = new Thread(this);
    private long loopInterval = DEFAULT_LOOP_INTERVAL;

    // ---------------------------------------------------------------------------------------------
    // graphic objects
    private Paint m_paint_slide_button;
    private Paint m_paint_slide_Line;

    private Paint m_arming_on_button;
    private Paint m_arming_off_button;

    // ---------------------------------------------------------------------------------------------
    // area objects
    private Rect m_slide_button_rect = new Rect();
    private Rect m_slide_line_rect = new Rect();

    private Rect m_arming_on_button_Rect = new Rect();
    private Rect m_arming_off_button_Rect = new Rect();

    private Rect m_exit_button_rect = new Rect();

    // ****************************************************************************************** //
    //
    // constructor
    //
    // ****************************************************************************************** //
    public Throttle_Controller_View(Context context)
    {
       super(context);
       m_MainActivity = (MainRemoteControllerActivity) context;
       Init_Controller_View();
    }

    // ****************************************************************************************** //
    //
    // constructor
    //
    // ****************************************************************************************** //
    public Throttle_Controller_View(Context context, AttributeSet attrs)
    {
       super(context, attrs);
       m_MainActivity = (MainRemoteControllerActivity) context;
       Init_Controller_View();
    }

    // ****************************************************************************************** //
    //
    // constructor
    //
    // ****************************************************************************************** //
    public Throttle_Controller_View(Context context, AttributeSet attrs, int defStyle)
    {
       super(context, attrs, defStyle);
       m_MainActivity = (MainRemoteControllerActivity) context;
       Init_Controller_View();
    }

    // ****************************************************************************************** //
    //
    // int MeasuredWidth(int measureSpec)
    //
    // ****************************************************************************************** //
    private void Init_Controller_View()
    {
        m_paint_slide_button = new Paint(Paint.ANTI_ALIAS_FLAG);
        m_paint_slide_button.setColor(Color.rgb(10, 10, 10));
        m_paint_slide_button.setStyle(Paint.Style.FILL);

        m_paint_slide_Line = new Paint(Paint.ANTI_ALIAS_FLAG);
        m_paint_slide_Line.setStrokeWidth(5);
        m_paint_slide_Line.setColor(Color.rgb(10, 10, 10));

        m_arming_on_button = new Paint(Paint.ANTI_ALIAS_FLAG);
        m_arming_on_button.setColor(Color.rgb(10, 10, 10));
        m_arming_on_button.setStyle(Paint.Style.FILL);

        m_arming_off_button = new Paint(Paint.ANTI_ALIAS_FLAG);
        m_arming_off_button.setColor(Color.rgb(10, 10, 10));
        m_arming_off_button.setStyle(Paint.Style.FILL);
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

        double screen_width = getWidth();
        double screen_height = getHeight();

        m_slide_button_rect.left = (int) ((screen_width / 30) * 2);
        m_slide_button_rect.top = (int) ((screen_height / 30) * 20);
        m_slide_button_rect.right = (int) ((screen_width / 30) * 28);
        m_slide_button_rect.bottom = (int) ((screen_height / 30) * 22);

    
        if (thread != null && (thread.isAlive() == false))
        {
            thread = new Thread(this);
            thread.start();
        }

        // ----------------------------------------------------------------------------------------
        DroneRemoteControllerProtocol droneProtocol = m_MainActivity.getProtocol();
        if (droneProtocol != null)
        {
            droneProtocol.reset_channel();
        }
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
        canvas.drawLine((float) m_slide_button_rect.left, (float) m_slide_button_rect.top, (float) m_slide_button_rect.right, (float) m_slide_button_rect.top, m_paint_slide_Line);
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
        int pointerIndex = 0;
        int x, y;

        switch(action & MotionEvent.ACTION_MASK)
        {
            case MotionEvent.ACTION_DOWN :
                // touch down by single ... (touch id : 0)
                x = (int) (event.getX());
                y = (int) (event.getY());
                break;

            case MotionEvent.ACTION_MOVE :
                break;

            case MotionEvent.ACTION_UP :
                loopInterval = DEFAULT_LOOP_INTERVAL;
                break;

            case MotionEvent.ACTION_POINTER_DOWN :
                // touch down by multi
                // supported only 2 point
                pointerIndex = event.getPointerId(0);
                x = (int) (event.getX(pointerIndex));
                y = (int) (event.getY(pointerIndex));

                break;

            case MotionEvent.ACTION_POINTER_UP :
                pointerIndex = event.getPointerId(event.getActionIndex());
                break;

            default :
                break;
        }

        return true;
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
       public void onValueChanged(int value);
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
//                valuechanged();
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


