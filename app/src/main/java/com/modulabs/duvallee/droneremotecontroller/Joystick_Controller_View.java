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

/**
 * Created by duval on 2017-07-25.
 */

public class Joystick_Controller_View extends View implements Runnable
{
   public static final String TAG = "Joystick_Controller_View";

   // ---------------------------------------------------------------------------------------------
   // for Main Activity
   private MainRemoteControllerActivity m_MainActivity;

   // ---------------------------------------------------------------------------------------------
   private final double RAD = 57.2957795;
   public final static long DEFAULT_LOOP_INTERVAL = 1000; // 1000 ms
   public final static long TOUCH_PROCESS_LOOP_INTERVAL = 80; // 100 ms

   public final static double BUTTONRADIUS = 0.16;
   public final static double JOYSTICKRADIUS = 0.70;

   public final static int ARMMING_OFF_VALUE = 0;
   public final static int ARMMING_ON_VALUE = 512;

   // ---------------------------------------------------------------------------------------------
   // Left Joystick (top-bottom : throttle, left-right : rudder(yaw, dron rotation))
   private double m_left_joystick_centerX = 0; // Center view x position
   private double m_left_joystick_centerY = 0; // Center view y position

   private int m_touch_left_joystick_positionX = 0; // Touch x position
   private int m_touch_left_joystick_positionY = 0; // Touch y position

   private Rect m_left_joystick_button_rect = new Rect();
   private int m_left_joystick_button_select = -1;

   // ---------------------------------------------------------------------------------------------
   // right Joystick (top-bottom : elevator(pitch, drone forward or backward), left-right : aileron(roll, left & right move))
   private double m_right_joystick_centerX = 0; // Center view x position
   private double m_right_joystick_centerY = 0; // Center view y position

   private int m_touch_right_joystick_positionX = 0; // Touch x position
   private int m_touch_right_joystick_positionY = 0; // Touch y position

   private Rect m_right_joystick_button_rect = new Rect();
   private int m_right_joystick_button_select = -1;

   // ---------------------------------------------------------------------------------------------
   // armming
   private double m_arming_button_offX = 0;
   private double m_arming_button_offY = 0;

   private double m_arming_button_onX = 0;
   private double m_arming_button_onY = 0;

   private int m_touch_arming_button_positionX = 0; // Touch x position
   private int m_touch_arming_button_positionY = 0; // Touch y position

   private int m_arming_button_select = -1;

   private Rect m_arming_button_rect = new Rect();
   private boolean m_arming_button_enable = false;
   private boolean m_arming_button_fake_enable = false;

   // ---------------------------------------------------------------------------------------------
   private int m_joystick_Radius;
   private int m_joystick_button_Radius;
   private int m_arming_button_Radius;

   // ---------------------------------------------------------------------------------------------
   // exit button
   private double exit_centerX = 0; // Center view x position
   private double exit_centerY = 0; // Center view y position
   private Rect exit_button_rect = new Rect();
   private int exit_button_select = -1;

   // ---------------------------------------------------------------------------------------------
   private Paint m_paint_joystick_mainCircle;
   private Paint m_paint_joystick_secondaryCircle;
   private Paint m_paint_joystick_button;
   private Paint m_paint_joystick_horizontalLine;
   private Paint m_paint_joystick_verticalLine;

   private Paint m_paint_arming_on_button;
   private Paint m_paint_arming_off_button;

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
      m_MainActivity = (MainRemoteControllerActivity) context;
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
      m_MainActivity = (MainRemoteControllerActivity) context;
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
      m_MainActivity = (MainRemoteControllerActivity) context;
      Init_Joystick_Controller_View();
   }

   // ****************************************************************************************** //
   //
   // constructor
   //
   // ****************************************************************************************** //
   protected void Init_Joystick_Controller_View()
   {
      m_paint_joystick_mainCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
      m_paint_joystick_mainCircle.setColor(Color.rgb(197, 197, 197));
      m_paint_joystick_mainCircle.setStyle(Paint.Style.FILL_AND_STROKE);

      m_paint_joystick_secondaryCircle = new Paint();
      m_paint_joystick_secondaryCircle.setColor(Color.rgb(97, 97, 97));
      m_paint_joystick_secondaryCircle.setStyle(Paint.Style.STROKE);

      m_paint_joystick_verticalLine = new Paint();
      m_paint_joystick_verticalLine.setStrokeWidth(5);
      m_paint_joystick_verticalLine.setColor(Color.rgb(10, 10, 10));

      m_paint_joystick_horizontalLine = new Paint();
      m_paint_joystick_horizontalLine.setStrokeWidth(2);
      m_paint_joystick_horizontalLine.setColor(Color.rgb(10, 10, 10));

      m_paint_joystick_button = new Paint(Paint.ANTI_ALIAS_FLAG);
      m_paint_joystick_button.setColor(Color.rgb(10, 10, 10));
      m_paint_joystick_button.setStyle(Paint.Style.FILL);

      m_paint_arming_on_button = new Paint(Paint.ANTI_ALIAS_FLAG);
      m_paint_arming_on_button.setColor(Color.rgb(0, 0, 230));
      m_paint_arming_on_button.setStyle(Paint.Style.FILL);

      m_paint_arming_off_button = new Paint(Paint.ANTI_ALIAS_FLAG);
      m_paint_arming_off_button.setColor(Color.rgb(230, 0, 0));
      m_paint_arming_off_button.setStyle(Paint.Style.FILL);

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

      int height_top = 0;
      int height_center = 0;
      int height_bottom = 0;

      int left_joystick_center = 0;
      int arming_center = 0;
      int right_joystick_center = 0;

      // ----------------------------------------------------------------------------------------
      int base_dimension = Math.min((int) (screen_width / 2), (int) screen_height);
      m_joystick_button_Radius = (int) (base_dimension / 2 * BUTTONRADIUS);
      m_joystick_Radius = (int) (base_dimension / 2 * JOYSTICKRADIUS);

      height_center = (int) ((screen_height - m_joystick_Radius) - (screen_height * 0.1));
      height_bottom = (int) (height_center + m_joystick_Radius);
      height_top = (int) (height_center - m_joystick_Radius);

      left_joystick_center = (int) ((screen_width / 4) * 1);
      arming_center = (int) ((screen_width / 4) * 2);
      right_joystick_center = (int) ((screen_width / 4) * 3);

      m_arming_button_Radius = (int) ((right_joystick_center - left_joystick_center - (m_joystick_Radius * 2)) * BUTTONRADIUS);
      m_arming_button_Radius = m_joystick_button_Radius;

      // ----------------------------------------------------------------------------------------
      m_left_joystick_centerX = left_joystick_center;
      m_left_joystick_centerY = height_center;

      m_right_joystick_centerX = right_joystick_center;
      m_right_joystick_centerY = height_center;

      // before measure, get the center of view
      m_touch_left_joystick_positionX = (int) m_left_joystick_centerX;
      m_touch_left_joystick_positionY = (int) (height_bottom - m_joystick_button_Radius);

      m_touch_right_joystick_positionX = (int) m_right_joystick_centerX;
      m_touch_right_joystick_positionY = (int) m_right_joystick_centerY;

      // ----------------------------------------------------------------------------------------
      m_arming_button_offX = arming_center;
      m_arming_button_offY = (height_bottom - m_arming_button_Radius);

      m_arming_button_onX = m_arming_button_offX;
      m_arming_button_onY = (height_top - m_arming_button_Radius);

      m_touch_arming_button_positionX = (int) m_arming_button_offX;
      m_touch_arming_button_positionY = (int) m_arming_button_offY;

      m_arming_button_rect.left = (int) (m_touch_arming_button_positionX - m_arming_button_Radius);
      m_arming_button_rect.right = (int) (m_touch_arming_button_positionX + m_arming_button_Radius);

      m_arming_button_rect.top = (int) (m_touch_arming_button_positionY - m_arming_button_Radius);
      m_arming_button_rect.bottom = (int) (m_touch_arming_button_positionY + m_arming_button_Radius);

      // ----------------------------------------------------------------------------------------
      exit_centerX = (screen_width * 0.08);
      exit_centerY = (screen_width * 0.08);

      exit_button_rect.left = (int) (exit_centerX - m_joystick_button_Radius);
      exit_button_rect.right = (int) (exit_centerX + m_joystick_button_Radius);
      exit_button_rect.top = (int) (exit_centerY - m_joystick_button_Radius);
      exit_button_rect.bottom = (int) (exit_centerY + m_joystick_button_Radius);

      // ----------------------------------------------------------------------------------------
      left_joystick_move(m_touch_left_joystick_positionX, m_touch_left_joystick_positionY, false);
      right_joystick_move(m_touch_right_joystick_positionX, m_touch_right_joystick_positionY, false);

      if (thread != null && (thread.isAlive() == false))
      {
         thread = new Thread(this);
         thread.start();
      }

      // ----------------------------------------------------------------------------------------
      DroneRemoteControllerProtocol droneProtocol = m_MainActivity.getProtocol();
      if (droneProtocol != null)
      {
         droneProtocol.reset_all();
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
      // left
      // painting the main circle
      canvas.drawCircle((int) m_left_joystick_centerX, (int) m_left_joystick_centerY, m_joystick_Radius, m_paint_joystick_mainCircle);
      // painting the secondary circle
      canvas.drawCircle((int) m_left_joystick_centerX, (int) m_left_joystick_centerY, m_joystick_Radius / 2, m_paint_joystick_secondaryCircle);
      // paint lines
      canvas.drawLine((float) m_left_joystick_centerX, (float) m_left_joystick_centerY, (float) m_left_joystick_centerX, (float) (m_left_joystick_centerY - m_joystick_Radius), m_paint_joystick_verticalLine);
      canvas.drawLine((float) (m_left_joystick_centerX - m_joystick_Radius), (float) m_left_joystick_centerY, (float) (m_left_joystick_centerX + m_joystick_Radius), (float) m_left_joystick_centerY, m_paint_joystick_horizontalLine);
      canvas.drawLine((float) m_left_joystick_centerX, (float) (m_left_joystick_centerY + m_joystick_Radius), (float) m_left_joystick_centerX, (float) m_left_joystick_centerY, m_paint_joystick_horizontalLine);

      // right
      // painting the main circle
      canvas.drawCircle((int) m_right_joystick_centerX, (int) m_right_joystick_centerY, m_joystick_Radius, m_paint_joystick_mainCircle);
      // painting the secondary circle
      canvas.drawCircle((int) m_right_joystick_centerX, (int) m_right_joystick_centerY, m_joystick_Radius / 2, m_paint_joystick_secondaryCircle);
      // paint lines
      canvas.drawLine((float) m_right_joystick_centerX, (float) m_right_joystick_centerY, (float) m_right_joystick_centerX, (float) (m_right_joystick_centerY - m_joystick_Radius), m_paint_joystick_verticalLine);
      canvas.drawLine((float) (m_right_joystick_centerX - m_joystick_Radius), (float) m_right_joystick_centerY, (float) (m_right_joystick_centerX + m_joystick_Radius), (float) m_right_joystick_centerY, m_paint_joystick_horizontalLine);
      canvas.drawLine((float) m_right_joystick_centerX, (float) (m_right_joystick_centerY + m_joystick_Radius), (float) m_right_joystick_centerX, (float) m_right_joystick_centerY, m_paint_joystick_horizontalLine);

      // painting the move button
      // left
      canvas.drawCircle(m_touch_left_joystick_positionX, m_touch_left_joystick_positionY, m_joystick_button_Radius, m_paint_joystick_button);

      // right
      canvas.drawCircle(m_touch_right_joystick_positionX, m_touch_right_joystick_positionY, m_joystick_button_Radius, m_paint_joystick_button);

      // exit
      canvas.drawCircle((int) exit_centerX, (int) exit_centerY, m_joystick_button_Radius, m_paint_joystick_button);

      // armming
      if (m_arming_button_enable == true || m_arming_button_fake_enable == true)
      {
         canvas.drawCircle((int) m_touch_arming_button_positionX, (int) m_touch_arming_button_positionY, m_arming_button_Radius, m_paint_arming_on_button);
      }
      else
      {
         canvas.drawCircle((int) m_touch_arming_button_positionX, (int) m_touch_arming_button_positionY, m_arming_button_Radius, m_paint_arming_off_button);
      }

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
   // public void left_joystick_move(int x, int y)
   //
   // ****************************************************************************************** //
   public void left_joystick_move(int x, int y, boolean screen_update)
   {
      double abs = Math.sqrt((x - m_left_joystick_centerX) * (x - m_left_joystick_centerX) + (y - m_left_joystick_centerY) * (y - m_left_joystick_centerY));
      if (abs > (m_joystick_Radius - m_joystick_button_Radius))
      {
         m_touch_left_joystick_positionX = (int) ((((x - m_left_joystick_centerX) * (m_joystick_Radius - m_joystick_button_Radius)) / abs) + m_left_joystick_centerX);
         m_touch_left_joystick_positionY = (int) ((((y - m_left_joystick_centerY) * (m_joystick_Radius - m_joystick_button_Radius)) / abs) + m_left_joystick_centerY);
      }
      else
      {
         m_touch_left_joystick_positionX = x;
         m_touch_left_joystick_positionY = y;
      }
      m_left_joystick_button_rect.left = (int) (m_touch_left_joystick_positionX - m_joystick_button_Radius);
      m_left_joystick_button_rect.right = (int) (m_touch_left_joystick_positionX + m_joystick_button_Radius);
      m_left_joystick_button_rect.top = (int) (m_touch_left_joystick_positionY - m_joystick_button_Radius);
      m_left_joystick_button_rect.bottom = (int) (m_touch_left_joystick_positionY + m_joystick_button_Radius);
      if (screen_update == true)
      {
         invalidate();
      }
   }


   // ****************************************************************************************** //
   //
   // public void right_joystick_move(int x, int y)
   //
   // ****************************************************************************************** //
   public void right_joystick_move(int x, int y, boolean screen_update)
   {
      double abs = Math.sqrt((x - m_right_joystick_centerX) * (x - m_right_joystick_centerX) + (y - m_right_joystick_centerY) * (y - m_right_joystick_centerY));
      if (abs > (m_joystick_Radius - m_joystick_button_Radius))
      {
         m_touch_right_joystick_positionX = (int) ((x - m_right_joystick_centerX) * (m_joystick_Radius - m_joystick_button_Radius) / abs + m_right_joystick_centerX);
         m_touch_right_joystick_positionY = (int) ((y - m_right_joystick_centerY) * (m_joystick_Radius - m_joystick_button_Radius) / abs + m_right_joystick_centerY);
      }
      else
      {
         m_touch_right_joystick_positionX = x;
         m_touch_right_joystick_positionY = y;
      }
      m_right_joystick_button_rect.left = (int) (m_touch_right_joystick_positionX - m_joystick_button_Radius);
      m_right_joystick_button_rect.right = (int) (m_touch_right_joystick_positionX + m_joystick_button_Radius);
      m_right_joystick_button_rect.top = (int) (m_touch_right_joystick_positionY - m_joystick_button_Radius);
      m_right_joystick_button_rect.bottom = (int) (m_touch_right_joystick_positionY + m_joystick_button_Radius);
      if (screen_update == true)
      {
         invalidate();
      }
   }

   // ****************************************************************************************** //
   //
   // public void armming_button_move(int x, int y, boolean screen_update)
   //
   // ****************************************************************************************** //
   public void armming_button_move(int x, int y, boolean screen_update)
   {
      if (y < (int) (m_arming_button_onY + m_arming_button_Radius))
      {
         m_arming_button_fake_enable = true;
      }
      else
      {
         m_arming_button_fake_enable = false;
      }

      if (y > m_arming_button_offY)
      {
         m_touch_arming_button_positionY = (int) m_arming_button_offY;
      }
      else if (y < m_arming_button_onY)
      {
         m_touch_arming_button_positionY = (int) m_arming_button_onY;
      }
      else
      {
         m_touch_arming_button_positionY = y;
      }
      m_arming_button_rect.top = (int) (m_touch_arming_button_positionY - m_arming_button_Radius);
      m_arming_button_rect.bottom = (int) (m_touch_arming_button_positionY + m_arming_button_Radius);

      if (screen_update == true)
      {
         invalidate();
      }
   }

   // ****************************************************************************************** //
   //
   // public void armming_button_finish(int x, int y, boolean screen_update)
   //
   // ****************************************************************************************** //
   public void armming_button_finish(int x, int y)
   {
      m_arming_button_fake_enable = false;
      if (y < (int) (m_arming_button_onY + m_arming_button_Radius))
      {
         m_touch_arming_button_positionY = (int) m_arming_button_onY;
         m_arming_button_enable = true;
      }
      else
      {
         m_touch_arming_button_positionY = (int) m_arming_button_offY;
         m_arming_button_enable = false;
      }      
      m_arming_button_rect.top = (int) (m_touch_arming_button_positionY - m_arming_button_Radius);
      m_arming_button_rect.bottom = (int) (m_touch_arming_button_positionY + m_arming_button_Radius);
      invalidate();
   }

   // ****************************************************************************************** //
   //
   // public void left_joystick_default()
   //
   // ****************************************************************************************** //
   public void left_joystick_default()
   {
      m_touch_left_joystick_positionX = (int) m_left_joystick_centerX;

      m_left_joystick_button_rect.left = (int) (m_touch_left_joystick_positionX - m_joystick_button_Radius);
      m_left_joystick_button_rect.right = (int) (m_touch_left_joystick_positionX + m_joystick_button_Radius);
      m_left_joystick_button_rect.top = (int) (m_touch_left_joystick_positionY - m_joystick_button_Radius);
      m_left_joystick_button_rect.bottom = (int) (m_touch_left_joystick_positionY + m_joystick_button_Radius);
      invalidate();
   }

   // ****************************************************************************************** //
   //
   // public void right_joystick_default()
   //
   // ****************************************************************************************** //
   public void right_joystick_default()
   {
      m_touch_right_joystick_positionX = (int) m_right_joystick_centerX;
      m_touch_right_joystick_positionY = (int) m_right_joystick_centerY;

      m_right_joystick_button_rect.left = (int) (m_touch_right_joystick_positionX - m_joystick_button_Radius);
      m_right_joystick_button_rect.right = (int) (m_touch_right_joystick_positionX + m_joystick_button_Radius);
      m_right_joystick_button_rect.top = (int) (m_touch_right_joystick_positionY - m_joystick_button_Radius);
      m_right_joystick_button_rect.bottom = (int) (m_touch_right_joystick_positionY + m_joystick_button_Radius);
      invalidate();
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
         case MotionEvent.ACTION_DOWN:
            // touch down by single ... (touch id : 0)
            x = (int) (event.getX());
            y = (int) (event.getY());

            if (exit_button_rect.contains(x, y) == true)
            {
               exit_button_select = 0;
            }

            if (m_left_joystick_button_rect.contains(x, y) == true)
            {
               m_left_joystick_button_select = 0;
            }

            if (m_right_joystick_button_rect.contains(x, y) == true)
            {
               m_right_joystick_button_select = 0;
            }

            if (m_arming_button_rect.contains(x, y) == true)
            {
               m_arming_button_select = 0;
            }

            loopInterval = TOUCH_PROCESS_LOOP_INTERVAL;
//            Log.d(TAG, "ACTION_DOWN (" + exit_button_select + ", " + m_left_joystick_button_select + ", " + m_right_joystick_button_select + ")");
            break;

         case MotionEvent.ACTION_MOVE:
            // touch move
            if (m_left_joystick_button_select > -1)
            {
               pointerIndex = event.findPointerIndex(m_left_joystick_button_select);
               x = (int) (event.getX(pointerIndex));
               y = (int) (event.getY(pointerIndex));
               left_joystick_move(x, y, true);
            }

            if (m_right_joystick_button_select > -1)
            {
               pointerIndex = event.findPointerIndex(m_right_joystick_button_select);
               x = (int) (event.getX(pointerIndex));
               y = (int) (event.getY(pointerIndex));
               right_joystick_move(x, y, true);
            }

            if (m_arming_button_select > -1)
            {
               pointerIndex = event.findPointerIndex(m_arming_button_select);
               x = (int) (event.getX(pointerIndex));
               y = (int) (event.getY(pointerIndex));
               armming_button_move(x, y, true);
            }
            break;

         case MotionEvent.ACTION_UP:
            // touch up by single
//            Log.d(TAG, "ACTION_UP ");
            if (exit_button_select != -1)
            {
               if (thread != null && thread.isAlive())
               {
                  thread.interrupt();
               }
               DroneRemoteControllerProtocol droneProtocol = m_MainActivity.getProtocol();
               if (droneProtocol != null)
               {
                  droneProtocol.reset_all();
               }
               m_MainActivity.switch_view(m_MainActivity.VIEW_MAIN_MENU_SCREEN_INDEX);
               exit_button_select = -1;
            }

            if (m_left_joystick_button_select > -1)
            {
               pointerIndex = event.findPointerIndex(m_left_joystick_button_select);
               x = (int) (event.getX(pointerIndex));
               y = (int) (event.getY(pointerIndex));
               m_left_joystick_button_select = -1;
               left_joystick_default();
            }

            if (m_right_joystick_button_select > -1)
            {
               pointerIndex = event.findPointerIndex(m_right_joystick_button_select);
               x = (int) (event.getX(pointerIndex));
               y = (int) (event.getY(pointerIndex));
               m_right_joystick_button_select = -1;
               right_joystick_default();
            }

            if (m_arming_button_select > -1)
            {
               pointerIndex = event.findPointerIndex(m_arming_button_select);
               x = (int) (event.getX(pointerIndex));
               y = (int) (event.getY(pointerIndex));
               m_arming_button_select = -1;
               armming_button_finish(x, y);
            }

            loopInterval = DEFAULT_LOOP_INTERVAL;
            break;

         case MotionEvent.ACTION_POINTER_DOWN:
            // touch down by multi
            // supported only 2 point
            pointerIndex = event.getPointerId(0);
            x = (int) (event.getX(pointerIndex));
            y = (int) (event.getY(pointerIndex));

            if (m_left_joystick_button_rect.contains(x, y) == true)
            {
               m_left_joystick_button_select = 0;
            }
            if (m_right_joystick_button_rect.contains(x, y) == true)
            {
               m_right_joystick_button_select = 0;
            }
            if (m_arming_button_rect.contains(x, y) == true)
            {
               m_arming_button_select = 0;
            }

            pointerIndex = event.getPointerId(1);
            x = (int) (event.getX(pointerIndex));
            y = (int) (event.getY(pointerIndex));
            if (m_left_joystick_button_rect.contains(x, y) == true)
            {
               m_left_joystick_button_select = 1;
            }

            if (m_right_joystick_button_rect.contains(x, y) == true)
            {
               m_right_joystick_button_select = 1;
            }
            if (m_arming_button_rect.contains(x, y) == true)
            {
               m_arming_button_select = 1;
            }

//            Log.d(TAG, "ACTION_POINTER_DOWN (" + exit_button_select + ", " + m_left_joystick_button_select + ", " + m_right_joystick_button_select + ")");
            break;

         case MotionEvent.ACTION_POINTER_UP:
            pointerIndex = event.getPointerId(event.getActionIndex());
//            Log.d(TAG, "ACTION_POINTER_UP : " + pointerIndex);

            // touch up by multi
            if (exit_button_select != -1 && pointerIndex == exit_button_select)
            {
               DroneRemoteControllerProtocol droneProtocol = m_MainActivity.getProtocol();
               if (droneProtocol != null)
               {
                  droneProtocol.reset_all();
               }
               m_MainActivity.switch_view(m_MainActivity.VIEW_MAIN_MENU_SCREEN_INDEX);
               exit_button_select = -1;
            }

            if (m_left_joystick_button_select > -1 && pointerIndex == m_left_joystick_button_select)
            {
               pointerIndex = event.findPointerIndex(m_left_joystick_button_select);
               x = (int) (event.getX(pointerIndex));
               y = (int) (event.getY(pointerIndex));
               m_left_joystick_button_select = -1;
               left_joystick_default();
            }

            if (m_right_joystick_button_select > -1 && pointerIndex == m_right_joystick_button_select)
            {
               pointerIndex = event.findPointerIndex(m_right_joystick_button_select);
               x = (int) (event.getX(pointerIndex));
               y = (int) (event.getY(pointerIndex));
               m_right_joystick_button_select = -1;
               right_joystick_default();
            }

            if (m_arming_button_select > -1 && pointerIndex == m_arming_button_select)
            {
               pointerIndex = event.findPointerIndex(m_arming_button_select);
               x = (int) (event.getX(pointerIndex));
               y = (int) (event.getY(pointerIndex));
               m_arming_button_select = -1;
               armming_button_finish(x, y);
            }

//            Log.d(TAG, "ACTION_POINTER_UP : " + pointerIndex);
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
   // public int valuechanged()
   //
   // ****************************************************************************************** //
   public int valuechanged()
   {
      int x_start = (int) (m_left_joystick_centerX - m_joystick_Radius + m_joystick_button_Radius);
      int x_end = (int) (m_left_joystick_centerX + m_joystick_Radius - m_joystick_button_Radius);
      int x_width = x_end - x_start;
      int x_pos = (m_touch_left_joystick_positionX - x_start);

      int y_start = (int) (m_left_joystick_centerY - m_joystick_Radius + m_joystick_button_Radius);
      int y_end = (int) (m_left_joystick_centerY + m_joystick_Radius - m_joystick_button_Radius);
      int y_width = y_end - y_start;
      int y_pos = y_width - (m_touch_left_joystick_positionY - y_start);

      double yaw_value = (((double) x_pos / (double) x_width) * 100.0) * 10.24;
      double throttle_value = (((double) y_pos / (double) y_width) * 100.0) * 10.24;

      x_start = (int) (m_right_joystick_centerX - m_joystick_Radius + m_joystick_button_Radius);
      x_end = (int) (m_right_joystick_centerX + m_joystick_Radius - m_joystick_button_Radius);
      x_width = x_end - x_start;
      x_pos = (m_touch_right_joystick_positionX - x_start);

      y_start = (int) (m_right_joystick_centerY - m_joystick_Radius + m_joystick_button_Radius);
      y_end = (int) (m_right_joystick_centerY + m_joystick_Radius - m_joystick_button_Radius);
      y_width = y_end - y_start;
      y_pos = y_width - (m_touch_right_joystick_positionY - y_start);

      double roll_value = (((double) x_pos / (double) x_width) * 100.0) * 10.24;
      double pitch_value = (((double) y_pos / (double) y_width) * 100.0) * 10.24;

      Log.d(TAG, "armming = " + m_arming_button_enable + ", t = " + (int) throttle_value + ", y = " + (int) yaw_value +
                  ", r = " + (int) roll_value + ", p = " + (int) pitch_value);

      DroneRemoteControllerProtocol droneProtocol = m_MainActivity.getProtocol();
      UartService uartservice = m_MainActivity.getUartService();

      if (uartservice == null)
      {
         droneProtocol.reset_all();
         Toast.makeText(m_MainActivity, "Not connected the Drone BT Transmitter", Toast.LENGTH_SHORT).show();
         m_MainActivity.switch_view(m_MainActivity.VIEW_MAIN_MENU_SCREEN_INDEX);
         return 0;
      }

      if (m_arming_button_enable == true)
      {
         droneProtocol.set_gear_value(ARMMING_ON_VALUE);
      }
      else
      {
         droneProtocol.set_gear_value(ARMMING_OFF_VALUE);
      }

      droneProtocol.set_throttle_value((int) throttle_value);
      droneProtocol.set_yaw_value((int) yaw_value);
      droneProtocol.set_roll_value((int) roll_value);
      droneProtocol.set_pitch_value((int) pitch_value);

      if (droneProtocol.Send_Channel_Message(uartservice) < 0)
      {
         Toast.makeText(m_MainActivity, "Busy state !!!", Toast.LENGTH_SHORT).show();
      }
      return 0;
   };

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
               valuechanged();
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
