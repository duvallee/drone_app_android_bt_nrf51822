package com.modulabs.duvallee.droneremotecontroller;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.icu.text.DateFormat;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.res.Configuration;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.content.Intent;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;


import android.content.pm.ActivityInfo;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MainRemoteControllerActivity extends AppCompatActivity
{
    public static final String TAG = "DroneRemoteControl";

    // ---------------------------------------------------------------------------------------------
    // view index
    public final int VIEW_SPLASHCONNECTSCREEN_INDEX = 1;
    public final int VIEW_MAIN_MENU_SCREEN_INDEX = 2;
    public final int VIEW_JOYSTICKCONTROLLER_INDEX = 3;
    public final int VIEW_THROTTLECONTROLLER_INDEX = 4;
    public final int VIEW_YAWCONTROLLER_INDEX = 5;
    public final int VIEW_PITCHCONTROLLER_INDEX = 6;
    public final int VIEW_ROLLCONTROLLER_INDEX = 7;
    public final int VIEW_SETTING_INDEX = 8;
    public final int VIEW_SEARCHING_INDEX = 9;

    public final int VIEW_MAX_INDEX = 10;

    // tag for view
    private final String VIEW_SPLASHCONNECTSCREEN_TAG = "SPLASH_CONNECT_SCREEN_TAG";
    private final String VIEW_MAIN_MENU_SCREEN_TAG = "MAIN_MENU_SCREEN_TAG";
    private final String VIEW_JOYSTICK_CONTROLLER_TAG = "JOYSTICK_CONTROLLER_TAG";
    private final String VIEW_THROTTLE_CONTROLLER_TAG = "THROTTLE_CONTROLLER_TAG";
    private final String VIEW_YAW_CONTROLLER_TAG = "YAW_CONTROLLER_TAG";
    private final String VIEW_PITCH_CONTROLLER_TAG = "PITCH_CONTROLLER_TAG";
    private final String VIEW_ROLL_CONTROLLER_TAG = "ROLL_CONTROLLER_TAG";
    private final String VIEW_SETTING_TAG = "SETTING_TAG";
    private final String VIEW_SEARCHING_TAG = "SEARCHING_TAG";

    private String[] mFlagmentTag;


    // ---------------------------------------------------------------------------------------------
    // for BT Dialog
    private static final int REQUEST_SELECT_DEVICE = 1;
    private static final int REQUEST_ENABLE_BT = 2;

    // ---------------------------------------------------------------------------------------------
    // for BT Device
    private BluetoothAdapter mBtAdapter = null;
    private BluetoothLeScanner mBtLeScanner = null;

    private String mDroneTransmitterBtDeviceAddress = null;
    private String mDroneTransmitterBtDeviceName = null;
    private BluetoothDevice mDroneTransmitterBtDevice = null;

    private UartService mDroneTransmitterBtService = null;

    private static final int UART_PROFILE_DISCONNECTED = 0;
    private static final int UART_PROFILE_CONNECTED = 1;

    private int mDroneTransmitterConnectionStatus = UART_PROFILE_DISCONNECTED;

    private DroneRemoteControllerProtocol mDroneRemoteControllerProtocol = new DroneRemoteControllerProtocol(this);

    private final int STATE_CONNECTED = 2;

    // ---------------------------------------------------------------------------------------------
    // for permission
    private static final int REQUEST_PERMISSION_ENABLE_BT = 1;
    private static final int REQUEST_PERMISSION_LOCATION = 2;


    // test function
    public int roll_position = 1000;
    public int pitch_position = 1000;
    public int yaw_position = 1000;
    public int throttle_position = 1000;
    public int arming_position = 1000;
    public void SendBTMessage()
    {
        byte[] value = new byte[16];

        roll_position++;
        pitch_position++;
        yaw_position++;
        throttle_position++;
        arming_position++;

        if (roll_position > 2000)
        {
            roll_position = 1000;
        }
        if (pitch_position > 2000)
        {
            pitch_position = 1000;
        }
        if (yaw_position > 2000)
        {
            yaw_position = 1000;
        }
        if (throttle_position > 2000)
        {
            throttle_position = 1000;
        }
        if (arming_position > 2000)
        {
            arming_position = 1000;
        }

        //send data to service
        value[0] = (byte) 0x23;
        value[1] = (byte) 0x92;

        // roll
        value[2] = (byte) (roll_position >> 8);
        value[3] = (byte) (roll_position);

        // pitch
        value[4] = (byte) (pitch_position >> 8);
        value[5] = (byte) (pitch_position);

        // yaw_position
        value[6] = (byte) (yaw_position >> 8);
        value[7] = (byte) (yaw_position);

        // throttle_position
        value[8] = (byte) (throttle_position >> 8);
        value[9] = (byte) (throttle_position);

        // arming
        value[10] = (byte) (arming_position >> 8);
        value[11] = (byte) (arming_position);

        // 00
        value[12] = (byte) (0x00);
        value[13] = (byte) (0x00);

        // 00
        value[14] = (byte) (0x00);
        value[15] = (byte) (0x00);

        mDroneTransmitterBtService.writeRXCharacteristic(value);
    };

    // ****************************************************************************************** //
    //
    // DroneRemoteControllerProtocol getProtocol()
    //
    //
    //
    // ****************************************************************************************** //
    public DroneRemoteControllerProtocol getProtocol()
    {
        return mDroneRemoteControllerProtocol;
    }

    // ****************************************************************************************** //
    //
    // void switch_view(int view_index)
    //
    // for view switch
    //
    // ****************************************************************************************** //
    public void response_protocol(byte[] data)
    {
        mDroneRemoteControllerProtocol.responseProtocolData(data);
        showMessage("reponse success !!!");
    }

    // ****************************************************************************************** //
    //
    // void switch_view(int view_index)
    //
    // for view switch
    //
    // ****************************************************************************************** //
    public void switch_view(int view_index)
    {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        switch (view_index)
        {
            case VIEW_SPLASHCONNECTSCREEN_INDEX :
                fragmentTransaction.replace(R.id.fragment_main_frame, new SplashConnectScreenFragment(this), mFlagmentTag[view_index]);
                fragmentTransaction.commit();
                break;

            case VIEW_MAIN_MENU_SCREEN_INDEX :
                fragmentTransaction.replace(R.id.fragment_main_frame, new MainMenuFragment(this), mFlagmentTag[view_index]);
                fragmentTransaction.commit();
                break;

            case VIEW_JOYSTICKCONTROLLER_INDEX :
                // ---------------------------------------------------------------------------------
                // start : for test code
                if (mDroneTransmitterBtService != null)
                {
                    if (mDroneTransmitterBtService.getStatus() == STATE_CONNECTED)
                    {
                        byte[] Data = mDroneRemoteControllerProtocol.getProtocolData();
                        mDroneTransmitterBtService.writeRXCharacteristic(Data);
                    }
                    else
                    {
                        showMessage("not connected !!!");
                    }
                }
                else
                {
                    showMessage("not initialize !!!");
                }
                // end : for test code
                // ---------------------------------------------------------------------------------
                fragmentTransaction.replace(R.id.fragment_main_frame, new Joystick_Controller_Fragment(this), mFlagmentTag[view_index]);
                fragmentTransaction.commit();
                break;

            case VIEW_THROTTLECONTROLLER_INDEX :
                fragmentTransaction.replace(R.id.fragment_main_frame, new Throttle_Controller_Fragment(this), mFlagmentTag[view_index]);
                fragmentTransaction.commit();
                break;

            case VIEW_YAWCONTROLLER_INDEX :
                fragmentTransaction.replace(R.id.fragment_main_frame, new Yaw_Controller_Fragment(this), mFlagmentTag[view_index]);
                fragmentTransaction.commit();
                break;

            case VIEW_PITCHCONTROLLER_INDEX :
                fragmentTransaction.replace(R.id.fragment_main_frame, new Pitch_Controller_Fragment(this), mFlagmentTag[view_index]);
                fragmentTransaction.commit();
                break;

            case VIEW_ROLLCONTROLLER_INDEX :
                fragmentTransaction.replace(R.id.fragment_main_frame, new Roll_Controller_Fragment(this), mFlagmentTag[view_index]);
                fragmentTransaction.commit();
                break;

            case VIEW_SETTING_INDEX :
                fragmentTransaction.replace(R.id.fragment_main_frame, new Setting_Fragment_1page(this), mFlagmentTag[view_index]);
                fragmentTransaction.commit();
                break;

            case VIEW_SEARCHING_INDEX :
                searching_drone_transmitter();
                break;

            default :
                showMessage("unknown view index : " + String.valueOf(view_index));
                break;
        }
    }

    // ****************************************************************************************** //
    //
    // void searching_drone_transmitter()
    //
    //
    //
    // ****************************************************************************************** //
    public void searching_drone_transmitter()
    {
        mDroneTransmitterBtDeviceAddress = null;
        mDroneTransmitterBtDeviceName = null;

        if (!mBtAdapter.isEnabled())
        {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
        }
        else
        {
            if (mDroneTransmitterBtService != null)
            {
                mDroneTransmitterBtService.disconnect();
            }
            Intent newIntent = new Intent(MainRemoteControllerActivity.this, DeviceListActivity.class);
            startActivityForResult(newIntent, REQUEST_SELECT_DEVICE);
        }
    }

    // ****************************************************************************************** //
    //
    // void update_view()
    //
    //
    //
    // ****************************************************************************************** //
    public void update_view()
    {
        FragmentManager fragmentManager = getFragmentManager();

        for (int i = 0; i < VIEW_MAX_INDEX; i++)
        {
            Fragment fragment = fragmentManager.findFragmentByTag(mFlagmentTag[i]);
            if (fragment != null)
            {
                if(fragment != null && fragment.isVisible())
                {
                    View view = fragment.getView();
                    if (view != null)
                    {
                        view.invalidate();
                    }
                }
            }
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu)
//    {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.main_menu, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item)
//    {
//        // Handle item selection
//        switch (item.getItemId())
//        {
//            case R.id.main_rc_control :
//                break;
//            case R.id.joystick_rc_control :
//                break;
//            case R.id.throttle_rc_control :
//                break;
//            case R.id.roll_rc_control :
//                break;
//            case R.id.pitch_rc_control :
//                break;
//            case R.id.yaw_rc_control :
//                break;
//            default :
//                break;
//        }
//        return true;
//    }


    // ****************************************************************************************** //
    //
    // void onCreate(Bundle savedInstanceState)
    //
    // ****************************************************************************************** //
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        // ----------------------------------------------------------------------------------------
        // allocate memory
        mFlagmentTag = new String[VIEW_MAX_INDEX];

        mFlagmentTag[VIEW_SPLASHCONNECTSCREEN_INDEX] = VIEW_SPLASHCONNECTSCREEN_TAG;
        mFlagmentTag[VIEW_MAIN_MENU_SCREEN_INDEX] = VIEW_MAIN_MENU_SCREEN_TAG;
        mFlagmentTag[VIEW_JOYSTICKCONTROLLER_INDEX] = VIEW_JOYSTICK_CONTROLLER_TAG;
        mFlagmentTag[VIEW_THROTTLECONTROLLER_INDEX] = VIEW_THROTTLE_CONTROLLER_TAG;
        mFlagmentTag[VIEW_YAWCONTROLLER_INDEX] = VIEW_YAW_CONTROLLER_TAG;
        mFlagmentTag[VIEW_PITCHCONTROLLER_INDEX] = VIEW_PITCH_CONTROLLER_TAG;
        mFlagmentTag[VIEW_ROLLCONTROLLER_INDEX] = VIEW_ROLL_CONTROLLER_TAG;
        mFlagmentTag[VIEW_SETTING_INDEX] = VIEW_SETTING_TAG;
        mFlagmentTag[VIEW_SEARCHING_INDEX] = VIEW_SEARCHING_TAG;

        // ----------------------------------------------------------------------------------------
        // check permission ...
        int permissionCheck = ContextCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH);
        if (permissionCheck== PackageManager.PERMISSION_DENIED)
        {
            Log.d(TAG, "denied for android.permission.BLUETOOTH");
        }
        else
        {
            Log.d(TAG, "aloowed for android.permission.BLUETOOTH");
        }

        permissionCheck = ContextCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_ADMIN);
        if (permissionCheck== PackageManager.PERMISSION_DENIED)
        {
            Log.d(TAG, "denied for android.permission.BLUETOOTH_ADMIN");
        }
        else
        {
            Log.d(TAG, "aloowed for android.permission.BLUETOOTH_ADMIN");
        }

        permissionCheck = ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionCheck== PackageManager.PERMISSION_DENIED)
        {
            Log.d(TAG, "denied for android.permission.ACCESS_FINE_LOCATION");

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.ACCESS_FINE_LOCATION))
            {
                // Display UI and wait for user interaction
            }
            else
            {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_PERMISSION_LOCATION);
            }

        }

        permissionCheck = ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION);
        if (permissionCheck== PackageManager.PERMISSION_DENIED)
        {
            Log.d(TAG, "denied for android.permission.ACCESS_COARSE_LOCATION");
        }
        else
        {
            Log.d(TAG, "aloowed for android.permission.ACCESS_COARSE_LOCATION");
        }

        // ----------------------------------------------------------------------------------------
        // get default bluetooth adater ....
        mBtAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBtAdapter == null)
        {
            Toast.makeText(this, "Bluetooth is not available", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        if (mBtAdapter.isEnabled() == false)
        {
            mBtAdapter.enable();
        }
        mBtLeScanner = mBtAdapter.getBluetoothLeScanner();
        if (mBtLeScanner == null)
        {
            Toast.makeText(this, "Bluetooth is not available", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        // ----------------------------------------------------------------------------------------
        // called before setContentView()
        // start : for full screen
        // title bar : battery, rssi of lte ...
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // or
//        setTheme(android.R.style.Theme_NoTitleBar_Fullscreen);
        // end : for full screen

        setContentView(R.layout.activity_main);

        // called after setContentView()
        ActionBar actionbar = getSupportActionBar();
        actionbar.hide();

        // display slash screen for connect to drone controller ...
        switch_view(VIEW_SPLASHCONNECTSCREEN_INDEX);

        // ----------------------------------------------------------------------------------------
        // init service for UART
        service_init();
    }

    // ****************************************************************************************** //
    //
    // bt_le_scan_start()
    //
    // ****************************************************************************************** //
    private void bt_le_scan_start()
    {
        mBtLeScanner.startScan(mBtLescanCallback);
    }

    // ****************************************************************************************** //
    //
    // bt_le_scan_stop()
    //
    // ****************************************************************************************** //
    private void bt_le_scan_stop()
    {
        mBtLeScanner.stopScan(mBtLescanCallback);
    }

    // ------------------------------------------------------------------------------------------ //
    //
    // ScanCallback member
    //
    // ------------------------------------------------------------------------------------------ //
    private ScanCallback mBtLescanCallback = new ScanCallback()
    {
        // ****************************************************************************************** //
        //
        // onScanResult()
        //
        // ****************************************************************************************** //
        @Override
        public void onScanResult(int callbackType, ScanResult result)
        {
            super.onScanResult(callbackType, result);
//            Log.d(TAG, "MainActivity.java | onScanResult", "|" + "111111111111111" + "|" + result.getDevice().getName() + "|" + result.getDevice().getAddress());
        }


        // ****************************************************************************************** //
        //
        // onScanFailed()
        //
        // ****************************************************************************************** //
        @Override
        public void onScanFailed(int errorCode)
        {
            super.onScanFailed(errorCode);
//            Log.d(TAG, "MainActivity.java | onScanFailed", "|" + "2222222222222" + "|" + errorCode);
        }

        // ****************************************************************************************** //
        //
        // onScanFailed()
        //
        // ****************************************************************************************** //
        @Override
        public void onBatchScanResults(List<ScanResult> results)
        {
            super.onBatchScanResults(results);
            for (ScanResult result : results)
            {
//                Log.d(TAG, "MainActivity.java | onBatchScanResults", "33333333333333|" + result.getDevice().getName() + "|" + result.getDevice().getAddress() + "|");
            }
        }
    };


    // ****************************************************************************************** //
    //
    // void onPostCreate(Bundle savedInstanceState)
    //
    // ****************************************************************************************** //
    @Override
    protected void onPostCreate(Bundle savedInstanceState)
    {
        super.onPostCreate(savedInstanceState);

//        delayedHide(100);
    }

    // ****************************************************************************************** //
    //
    // void onPostCreate(Bundle savedInstanceState)
    //
    //    UART service connected/disconnected
    //
    // ****************************************************************************************** //
    private ServiceConnection mServiceConnection = new ServiceConnection()
    {
        public void onServiceConnected(ComponentName className, IBinder rawBinder)
        {
            mDroneTransmitterBtService = ((UartService.LocalBinder) rawBinder).getService();
            Log.d(TAG, "onServiceConnected mService = " + mDroneTransmitterBtService);
            if (mDroneTransmitterBtService.initialize() == false)
            {
                Log.e(TAG, "Unable to initialize Bluetooth");
                finish();
            }

        }

        public void onServiceDisconnected(ComponentName classname)
        {
            // comment from original version
            // mService.disconnect(mDevice);
            mDroneTransmitterBtService = null;
        }
    };


    // ****************************************************************************************** //
    //
    // void onPostCreate(Bundle savedInstanceState)
    //
    // ****************************************************************************************** //
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults)
    {
//        if (requestCode == REQUEST_LOCATION)
//        {
//            if(grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
//            {
//                // We can now safely use the API we requested access to
//                Location myLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
//            }
//            else
//            {
//                // Permission was denied or request was cancelled
//            }
//        }

        switch (requestCode)
        {
            case REQUEST_PERMISSION_ENABLE_BT :
                break;
            case REQUEST_PERMISSION_LOCATION :
                break;
        }

//        switch (requestCode)
//        {
//            case REQUEST_ENABLE_BT :
//            {
//                // If request is cancelled, the result arrays are empty.
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
//                {
//                    // permission was granted, yay!
//                    Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//                    startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
//
//
//                }
//                else
//                {
//                    // permission denied, boo! Disable the
//                    // functionality that depends on this permission.
//                    if (CommonData.mChatService == null) {
//                        setupChat();
//                    }
//                    Toast.makeText(this, "Permission denied for bluetooth", Toast.LENGTH_SHORT).show();
//                }
//                return;
//            }
//
//            case REQUEST_CONNECT_DEVICE_INSECURE :
//            {
//                // If request is cancelled, the result arrays are empty.
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
//                {
//                    // permission was granted, yay!
//                    Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//                    startActivityForResult(enableIntent, REQUEST_CONNECT_DEVICE_INSECURE);
//                }
//                else
//                {
//                    // permission denied, boo! Disable the
//                    // functionality that depends on this permission.
//                    if (CommonData.mChatService == null)
//                    {
//                        setupChat();
//                    }
//                    Toast.makeText(this, "Permission denied for bluetooth", Toast.LENGTH_SHORT).show();
//                }
//                return;
//            }
//
//            // other 'case' lines to check for other
//            // permissions this app might request
//        }
    }

    private Handler mHandler = new Handler()
    {
        @Override
        // Handler events that received from UART service
        public void handleMessage(Message msg)
        {

        }
    };

    // ---------------------------------------------------------------------------------------------
    //
    // BroadcastReceiver UARTStatusChangeReceiver
    //
    // ---------------------------------------------------------------------------------------------
    private final BroadcastReceiver UARTStatusChangeReceiver = new BroadcastReceiver()
    {
        // ---------------------------------------------------------------------------------------------
        //
        // BroadcastReceiver UARTStatusChangeReceiver
        //
        // ---------------------------------------------------------------------------------------------
        public void onReceive(Context context, Intent intent)
        {
            String action = intent.getAction();
            final Intent mIntent = intent;

            if (action.equals(UartService.ACTION_GATT_CONNECTED))
            {
                runOnUiThread(new Runnable()
                {
                    public void run()
                    {
                        String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
                        Log.d(TAG, "UART_CONNECT_MSG");

                        mDroneTransmitterConnectionStatus = UART_PROFILE_CONNECTED;
                        update_view();
                    }
                });
            }

            //*********************//
            if (action.equals(UartService.ACTION_GATT_DISCONNECTED))
            {
                runOnUiThread(new Runnable()
                {
                    public void run()
                    {
                        String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
                        Log.d(TAG, "UART_DISCONNECT_MSG");

                        mDroneTransmitterConnectionStatus = UART_PROFILE_DISCONNECTED;
                        mDroneTransmitterBtService.close();

                        update_view();
                    }
                });
            }


            //*********************//
            if (action.equals(UartService.ACTION_GATT_SERVICES_DISCOVERED))
            {
                mDroneTransmitterBtService.enableTXNotification();
            }

            //*********************//
            if (action.equals(UartService.ACTION_DATA_AVAILABLE))
            {
                final byte[] txValue = intent.getByteArrayExtra(UartService.EXTRA_DATA);
                runOnUiThread(new Runnable()
                {
                    public void run()
                    {
                        response_protocol(txValue);
                        try
                        {
                            String text = new String(txValue, "UTF-8");
//                            String currentDateTimeString = DateFormat.getTimeInstance().format(new Date());
//                            listAdapter.add("["+currentDateTimeString+"] RX: "+text);
//                            messageListView.smoothScrollToPosition(listAdapter.getCount() - 1);

                        }
                        catch (Exception e)
                        {
                            Log.e(TAG, e.toString());
                        }
                    }
                });
            }

            if (action.equals(UartService.DEVICE_DOES_NOT_SUPPORT_UART))
            {
                showMessage("Device doesn't support UART. Disconnecting");
                mDroneTransmitterBtService.disconnect();
                mDroneTransmitterConnectionStatus = UART_PROFILE_DISCONNECTED;

                update_view();
            }
        }
    };

    // ---------------------------------------------------------------------------------------------
    //
    // void service_init()
    //
    // ---------------------------------------------------------------------------------------------
    private void service_init()
    {
        Intent bindIntent = new Intent(this, UartService.class);
        bindService(bindIntent, mServiceConnection, Context.BIND_AUTO_CREATE);

        LocalBroadcastManager.getInstance(this).registerReceiver(UARTStatusChangeReceiver, makeGattUpdateIntentFilter());
    }

    // ---------------------------------------------------------------------------------------------
    //
    // void IntentFilter makeGattUpdateIntentFilter()
    //
    // ---------------------------------------------------------------------------------------------
    private static IntentFilter makeGattUpdateIntentFilter()
    {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(UartService.ACTION_GATT_CONNECTED);
        intentFilter.addAction(UartService.ACTION_GATT_DISCONNECTED);
        intentFilter.addAction(UartService.ACTION_GATT_SERVICES_DISCOVERED);
        intentFilter.addAction(UartService.ACTION_DATA_AVAILABLE);
        intentFilter.addAction(UartService.DEVICE_DOES_NOT_SUPPORT_UART);
        return intentFilter;
    }


    // ---------------------------------------------------------------------------------------------
    //
    // for Activity's result
    //
    // ---------------------------------------------------------------------------------------------
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        // result for startActivityForResult()
        switch (requestCode)
        {
            case REQUEST_SELECT_DEVICE :
                //When the DeviceListActivity return, with the selected device address
                if (resultCode == AppCompatActivity.RESULT_OK && data != null)
                {
                    String deviceAddress = data.getStringExtra(BluetoothDevice.EXTRA_DEVICE);
                    mDroneTransmitterBtDeviceAddress = data.getStringExtra(BluetoothDevice.EXTRA_DEVICE);

                    mDroneTransmitterBtDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(deviceAddress);
                    mDroneTransmitterBtService.connect(deviceAddress);
                }
                break;

            case REQUEST_ENABLE_BT :
                // When the request to enable Bluetooth returns
                if (resultCode == AppCompatActivity.RESULT_OK)
                {
                    Toast.makeText(this, "Bluetooth has turned on ", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    // User did not enable Bluetooth or an error occurred
                    Log.d(TAG, "BT not enabled");
                    Toast.makeText(this, "Problem in BT Turning ON ", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;

            default :
                break;
        }
    }

    @Override
    public void onStart()
    {
        super.onStart();
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        try
        {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(UARTStatusChangeReceiver);
        }
        catch (Exception ignore)
        {
            Log.e(TAG, ignore.toString());
        }
        unbindService(mServiceConnection);
        mDroneTransmitterBtService.stopSelf();
        mDroneTransmitterBtService = null;
    }

    @Override
    protected void onStop()
    {
        Log.d(TAG, "onStop");
        super.onStop();
    }

    @Override
    protected void onPause()
    {
        Log.d(TAG, "onPause");
        super.onPause();
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }
    @Override
    public void onResume()
    {
        super.onResume();
        Log.d(TAG, "onResume");
        if (!mBtAdapter.isEnabled())
        {
            Log.i(TAG, "onResume - BT not enabled yet");
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed()
    {
    }

    private void showMessage(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
