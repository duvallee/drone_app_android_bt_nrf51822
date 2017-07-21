package com.modulabs.duvallee.droneremotecontroller;

import android.content.Context;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by duval on 2017-07-21.
 */

public class Sensor_Controller_View extends View implements SensorEventListener
{
    private MainRemoteControllerActivity mMainActivity = null;

    private SensorManager mSensorManager;
    private Sensor mGyroscope;
    private Sensor accSensor;

    // ****************************************************************************************** //
    //
    // constructor
    //
    // ****************************************************************************************** //
    public Sensor_Controller_View(Context context)
    {
        super(context);
        mMainActivity = (MainRemoteControllerActivity) context;
        Init();
    }

    // ****************************************************************************************** //
    //
    // constructor
    //
    // ****************************************************************************************** //
    public Sensor_Controller_View(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        mMainActivity = (MainRemoteControllerActivity) context;
        Init();
    }

    // ****************************************************************************************** //
    //
    // constructor
    //
    // ****************************************************************************************** //
    public Sensor_Controller_View(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        mMainActivity = (MainRemoteControllerActivity) context;
        Init();
    }

    // ****************************************************************************************** //
    //
    // int MeasuredWidth(int measureSpec)
    //
    // ****************************************************************************************** //
    private int Init()
    {
//        private SensorManager mSensorManager;
//        private Sensor mGyroscope;
//        private Sensor accSensor;

        // TYPE_ACCELEROMETER
        // TYPE_GRAVITY
        // TYPE_GYROSCOPE
        // TYPE_LINEAR_ACCELERATION
        // TYPE_MAGNETIC_FIELD
        // TYPE_PROXIMITY

        //센서 매니저 얻기
        mSensorManager = (SensorManager) mMainActivity.getSystemService(Context.SENSOR_SERVICE);
        //자이로스코프 센서(회전)
        mGyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        //엑셀로미터 센서(가속)
        accSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        return 0;
    }

    // ****************************************************************************************** //
    //
    // void onAttachedToWindow()
    //
    // ****************************************************************************************** //
    @Override
    protected void onAttachedToWindow()
    {
        super.onAttachedToWindow();
        mSensorManager.registerListener(this, mGyroscope,SensorManager.SENSOR_DELAY_FASTEST);
        mSensorManager.registerListener(this, accSensor,SensorManager.SENSOR_DELAY_FASTEST);
        Toast.makeText(mMainActivity, "Attached Window for Sensor Controller !!!", Toast.LENGTH_LONG).show();
    }

    // ****************************************************************************************** //
    //
    // void onDetachedFromWindow()
    //
    // ****************************************************************************************** //
    @Override
    protected void onDetachedFromWindow()
    {
        super.onDetachedFromWindow();
        mSensorManager.unregisterListener(this);
        Toast.makeText(mMainActivity, "Deattached Window for Sensor Controller !!!", Toast.LENGTH_LONG).show();
    }

    // ****************************************************************************************** //
    //
    // int onAccuracyChanged(int measureSpec)
    // 정확도에 대한 메소드 호출 (사용안함)
    //
    // ****************************************************************************************** //
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {
    }

    // ****************************************************************************************** //
    //
    // int onAccuracyChanged(int measureSpec)
    // 센서값들 얻어오기
    //
    // ****************************************************************************************** //
    public void onSensorChanged(SensorEvent event)
    {
        Sensor sensor = event.sensor;

        if (sensor.getType() == Sensor.TYPE_GYROSCOPE)
        {
//            gyroX = Math.round(event.values[0] * 1000);
//            gyroY = Math.round(event.values[1] * 1000);
//            gyroZ = Math.round(event.values[2] * 1000);
//            System.out.println("gyroX ="+gyroX);
//            System.out.println("gyroY ="+gyroY);
//            System.out.println("gyroZ ="+gyroZ);
        }

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
        {
//            accelXValue = (int) event.values[0];
//            accelYValue = (int) event.values[1];
//            accelZValue = (int) event.values[2];
//            System.out.println("accelXValue="+accelXValue);
//            System.out.println("accelYValue="+accelYValue);
//            System.out.println("accelZValue="+accelZValue);
        }
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
        setBackgroundResource(R.mipmap.main_screen);

        // -----------------------------------------------------------------------------------------
        // must be called setMeasuredDimension
        // if not called, occurred run-time error !!!
        setMeasuredDimension(measureWidth, measureHeight);

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
        mMainActivity.switch_view(mMainActivity.VIEW_MAIN_MENU_SCREEN_INDEX);
        return true;
    }

}
