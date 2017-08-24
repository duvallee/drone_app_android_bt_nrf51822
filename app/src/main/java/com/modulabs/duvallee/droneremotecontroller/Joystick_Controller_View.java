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
   private MainRemoteControllerActivity mActivity;

   // ---------------------------------------------------------------------------------------------
   private final double RAD = 57.2957795;
   public final static long DEFAULT_LOOP_INTERVAL = 3000; // 1000 ms
   public final static long TOUCH_PROCESS_LOOP_INTERVAL = 500; // 100 ms

   public final static int FRONT = 3;
   public final static int FRONT_RIGHT = 4;
   public final static int RIGHT = 5;
   public final static int RIGHT_BOTTOM = 6;
   public final static int BOTTOM = 7;
   public final static int BOTTOM_LEFT = 8;
   public final static int LEFT = 1;
   public final static int LEFT_FRONT = 2;

   public final static double BUTTONRADIUS = 0.16;
   public final static double JOYSTICKRADIUS = 0.70;

   // ---------------------------------------------------------------------------------------------
   // Left Joystick (top-bottom : throttle, left-right : rudder(yaw, dron rotation))
   private int left_xPosition = 0; // Touch x position
   private int left_yPosition = 0; // Touch y position
   private Rect left_button_rect = new Rect();
   private int left_button_select = -1;

   private double left_centerX = 0; // Center view x position
   private double left_centerY = 0; // Center view y position

   private int last_left_Angle = 0;
   private int last_left_Power = 0;

   private int left_vertial_value = 0;
   private int left_horizontal_value = 0;

   private int arming_enable = 0;

   // ---------------------------------------------------------------------------------------------
   // right Joystick (top-bottom : elevator(pitch, drone forward or backward), left-right : aileron(roll, left & right move))
   private int right_xPosition = 0; // Touch x position
   private int right_yPosition = 0; // Touch y position
   private Rect right_button_rect = new Rect();
   private int right_button_select = -1;

   private double right_centerX = 0; // Center view x position
   private double right_centerY = 0; // Center view y position

   private int last_right_Angle = 0;
   private int last_right_Power = 0;

   private int right_vertial_value = 0;
   private int right_horizontal_value = 0;

   // ---------------------------------------------------------------------------------------------
   private int joystickRadius;
   private int buttonRadius;

   // ---------------------------------------------------------------------------------------------
   // exit button
   private double exit_centerX = 0; // Center view x position
   private double exit_centerY = 0; // Center view y position
   private Rect exit_button_rect = new Rect();
   private int exit_button_select = -1;

   // ---------------------------------------------------------------------------------------------
   private Paint mainCircle;
   private Paint secondaryCircle;
   private Paint button;
   private Paint horizontalLine;
   private Paint verticalLine;

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
      button.setColor(Color.MAGENTA);
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

      if (thread != null && (thread.isAlive() == false))
      {
         thread = new Thread(this);
         thread.start();
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
   // public void left_joystick_move(int x, int y)
   //
   // ****************************************************************************************** //
   public void left_joystick_move(int x, int y)
   {
      double abs = Math.sqrt((x - left_centerX) * (x - left_centerX) + (y - left_centerY) * (y - left_centerY));
      if (abs > (joystickRadius - buttonRadius))
      {
         left_xPosition = (int) ((((x - left_centerX) * (joystickRadius - buttonRadius)) / abs) + left_centerX);
         left_yPosition = (int) ((((y - left_centerY) * (joystickRadius - buttonRadius)) / abs) + left_centerY);
      }
      else
      {
         left_xPosition = x;
         left_yPosition = y;
      }
      invalidate();
   }

   // ****************************************************************************************** //
   //
   // public void right_joystick_move(int x, int y)
   //
   // ****************************************************************************************** //
   public void right_joystick_move(int x, int y)
   {
      double abs = Math.sqrt((x - right_centerX) * (x - right_centerX) + (y - right_centerY) * (y - right_centerY));
      if (abs > (joystickRadius - buttonRadius))
      {
         right_xPosition = (int) ((x - right_centerX) * (joystickRadius - buttonRadius) / abs + right_centerX);
         right_yPosition = (int) ((y - right_centerY) * (joystickRadius - buttonRadius) / abs + right_centerY);
      }
      else
      {
         right_xPosition = x;
         right_yPosition = y;
      }
      invalidate();
   }

   // ****************************************************************************************** //
   //
   // public void left_joystick_default()
   //
   // ****************************************************************************************** //
   public void left_joystick_default()
   {
      left_xPosition = (int) left_centerX;
      invalidate();
   }

   // ****************************************************************************************** //
   //
   // public void right_joystick_default()
   //
   // ****************************************************************************************** //
   public void right_joystick_default()
   {
      right_xPosition = (int) right_centerX;
      right_yPosition = (int) right_centerY;
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

            if (left_button_rect.contains(x, y) == true)
            {
               left_button_select = 0;
            }

            if (right_button_rect.contains(x, y) == true)
            {
               right_button_select = 0;
            }

            loopInterval = TOUCH_PROCESS_LOOP_INTERVAL;
            Log.d(TAG, "ACTION_DOWN (" + exit_button_select + ", " + left_button_select + ", " + right_button_select + ")");
            break;

         case MotionEvent.ACTION_MOVE:
            // touch move
            if (left_button_select > -1)
            {
               pointerIndex = event.findPointerIndex(left_button_select);
               x = (int) (event.getX(pointerIndex));
               y = (int) (event.getY(pointerIndex));
               left_joystick_move(x, y);
            }

            if (right_button_select > -1)
            {
               pointerIndex = event.findPointerIndex(right_button_select);
               x = (int) (event.getX(pointerIndex));
               y = (int) (event.getY(pointerIndex));
               right_joystick_move(x, y);
            }
            break;

         case MotionEvent.ACTION_UP:
            // touch up by single
            Log.d(TAG, "ACTION_UP ");
            if (exit_button_select != -1)
            {
               if (thread != null && thread.isAlive())
               {
                  thread.interrupt();
               }
               mActivity.switch_view(mActivity.VIEW_MAIN_MENU_SCREEN_INDEX);
               exit_button_select = -1;
            }

            if (left_button_select > -1)
            {
               pointerIndex = event.findPointerIndex(left_button_select);
               x = (int) (event.getX(pointerIndex));
               y = (int) (event.getY(pointerIndex));
               left_button_select = -1;
               left_joystick_default();
            }

            if (right_button_select > -1)
            {
               pointerIndex = event.findPointerIndex(right_button_select);
               x = (int) (event.getX(pointerIndex));
               y = (int) (event.getY(pointerIndex));
               right_button_select = -1;
               right_joystick_default();
            }
            loopInterval = DEFAULT_LOOP_INTERVAL;
            break;

         case MotionEvent.ACTION_POINTER_DOWN:
            // touch down by multi
            // supported only 2 point
            pointerIndex = event.getPointerId(0);
            x = (int) (event.getX(pointerIndex));
            y = (int) (event.getY(pointerIndex));

            if (left_button_rect.contains(x, y) == true)
            {
               left_button_select = 0;
            }
            if (right_button_rect.contains(x, y) == true)
            {
               right_button_select = 0;
            }

            pointerIndex = event.getPointerId(1);
            x = (int) (event.getX(pointerIndex));
            y = (int) (event.getY(pointerIndex));
            if (left_button_rect.contains(x, y) == true)
            {
               left_button_select = 1;
            }

            if (right_button_rect.contains(x, y) == true)
            {
               right_button_select = 1;
            }

            Log.d(TAG, "ACTION_POINTER_DOWN (" + exit_button_select + ", " + left_button_select + ", " + right_button_select + ")");
            break;

         case MotionEvent.ACTION_POINTER_UP:
            // touch up by multi
            if (exit_button_select != -1)
            {
               mActivity.switch_view(mActivity.VIEW_MAIN_MENU_SCREEN_INDEX);
               exit_button_select = -1;
            }

            if (left_button_select > -1)
            {
               pointerIndex = event.findPointerIndex(left_button_select);
               x = (int) (event.getX(pointerIndex));
               y = (int) (event.getY(pointerIndex));
               left_button_select = -1;
               left_joystick_default();
            }

            if (right_button_select > -1)
            {
               pointerIndex = event.findPointerIndex(right_button_select);
               x = (int) (event.getX(pointerIndex));
               y = (int) (event.getY(pointerIndex));
               right_button_select = -1;
               right_joystick_default();
            }
            //                Log.d(TAG, "ACTION_POINTER_UP : " + pointerIndex);
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
      int range = (int) (((joystickRadius * 2) - buttonRadius) * 0.866);

      Log.d(TAG, "radius (" + joystickRadius + ", " + buttonRadius + ")");
      Log.d(TAG, "center (" + left_centerX + ", " + left_centerY + ")");
      Log.d(TAG, "position (" + left_xPosition + ", " + left_yPosition + ")");

//      int vertial_value = 0;
//      int horizontal_value = 0;
//
//      if ((int) left_centerY > left_yPosition)
//      {
//         Log.d(TAG, "value : " + (left_centerY - left_yPosition));
//         Log.d(TAG, "total : " + ((joystickRadius - buttonRadius) * 2));
//         vertial_value = (((int) left_centerY - left_yPosition) * 100) / ((joystickRadius - buttonRadius) * 2);
//      }
//      else
//      {
//         Log.d(TAG, "value : " + ((int) left_yPosition - left_centerY + joystickRadius));
//         Log.d(TAG, "total : " + ((joystickRadius - buttonRadius) * 2));
//         vertial_value = ((left_yPosition - (int) left_centerY + joystickRadius) * 100) / ((joystickRadius - buttonRadius) * 2);
//      }
//
//      if ((int) left_centerX > left_xPosition)
//      {
//         horizontal_value = (((int) left_centerX - left_xPosition) * 100) / ((joystickRadius - buttonRadius) * 2);
//      }
//      else
//      {
//         horizontal_value = ((left_xPosition + (int) left_centerX + joystickRadius) * 100) / ((joystickRadius - buttonRadius) * 2);
//      }
//
//      Log.d(TAG, "throttle : " + vertial_value);
//      Log.d(TAG, "yaw      : " + horizontal_value);
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
