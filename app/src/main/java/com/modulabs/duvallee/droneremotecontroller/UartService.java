package com.modulabs.duvallee.droneremotecontroller;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.util.List;
import java.util.UUID;

/**
 * Created by duval on 2017-06-05.
 */

/**
 * Service for managing connection and data communication with a GATT server hosted on a
 * given Bluetooth LE device.
 */
public class UartService extends Service
{
    private final static String TAG = UartService.class.getSimpleName();

    private BluetoothManager mBluetoothManager;
    private BluetoothAdapter mBluetoothAdapter;
    private String mBluetoothDeviceAddress;
    private BluetoothGatt mBluetoothGatt;
    private int mConnectionState = STATE_DISCONNECTED;

    private static final int STATE_DISCONNECTED = 0;
    private static final int STATE_CONNECTING = 1;
    private static final int STATE_CONNECTED = 2;

    public int getStatus()
    {
        return mConnectionState;
    };

    public final static String ACTION_GATT_CONNECTED = "com.modulabs.duvallee.droneremotecontroller.ACTION_GATT_CONNECTED";
    public final static String ACTION_GATT_DISCONNECTED = "com.modulabs.duvallee.droneremotecontroller.ACTION_GATT_DISCONNECTED";
    public final static String ACTION_GATT_SERVICES_DISCOVERED = "com.modulabs.duvallee.droneremotecontroller.ACTION_GATT_SERVICES_DISCOVERED";
    public final static String ACTION_DATA_AVAILABLE = "com.modulabs.duvallee.droneremotecontroller.ACTION_DATA_AVAILABLE";
    public final static String EXTRA_DATA = "com.modulabs.duvallee.droneremotecontroller.EXTRA_DATA";
    public final static String DEVICE_DOES_NOT_SUPPORT_UART = "com.modulabs.duvallee.droneremotecontroller.DEVICE_DOES_NOT_SUPPORT_UART";

//    public static final UUID TX_POWER_UUID = UUID.fromString("00001804-0000-1000-8000-00805f9b34fb");
//    public static final UUID TX_POWER_LEVEL_UUID = UUID.fromString("00002a07-0000-1000-8000-00805f9b34fb");
//    public static final UUID FIRMWARE_REVISON_UUID = UUID.fromString("00002a26-0000-1000-8000-00805f9b34fb");
//    public static final UUID DIS_UUID = UUID.fromString("0000180a-0000-1000-8000-00805f9b34fb");


   // ----------------------------------------------------------------------------------------------------------
   // nRF51822 of the nordic
	// Client Characteristic Configuration Descriptor. : 0x2902
   public static final UUID CCCD = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");

   public static final UUID nRF51822_RX_SERVICE_UUID     = UUID.fromString("6e400001-b5a3-f393-e0a9-e50e24dcca9e");
   public static final UUID nRF51822_RX_CHAR_UUID        = UUID.fromString("6e400002-b5a3-f393-e0a9-e50e24dcca9e");
   public static final UUID nRF51822_TX_CHAR_UUID        = UUID.fromString("6e400003-b5a3-f393-e0a9-e50e24dcca9e");

   // ----------------------------------------------------------------------------------------------------------
   // BlueNRG of the ST
   public static final UUID ST_BLUE_NRG_RX_SERVICE_UUID  = UUID.fromString("00000000-000E-11e1-9ab4-0002a5d5c51b");
   public static final UUID ST_BLUE_NRG_RX_CHAR_UUID     = UUID.fromString("00000001-000E-11e1-ac36-0002a5d5c51b");
   public static final UUID ST_BLUE_NRG_TX_CHAR_UUID     = UUID.fromString("00000002-000E-11e1-ac36-0002a5d5c51b");

// Console Service
//   #define COPY_CONSOLE_SERVICE_UUID(uuid_struct)           COPY_UUID_128(uuid_struct, 0x00, 0x00, 0x00, 0x00, 0x00, 0x0E, 0x11, 0xe1, 0x9a, 0xb4, 0x00, 0x02, 0xa5, 0xd5, 0xc5, 0x1b)
//   #define COPY_TERM_CHAR_UUID(uuid_struct)                 COPY_UUID_128(uuid_struct, 0x00, 0x00, 0x00, 0x01, 0x00, 0x0E, 0x11, 0xe1, 0xac, 0x36, 0x00, 0x02, 0xa5, 0xd5, 0xc5, 0x1b)
//   #define COPY_STDERR_CHAR_UUID(uuid_struct)               COPY_UUID_128(uuid_struct, 0x00, 0x00, 0x00, 0x02, 0x00, 0x0E, 0x11, 0xe1, 0xac, 0x36, 0x00, 0x02, 0xa5, 0xd5, 0xc5, 0x1b)

// Configuration Service
//   #define COPY_CONFIG_SERVICE_UUID(uuid_struct)			 COPY_UUID_128(uuid_struct, 0x00, 0x00, 0x00, 0x00, 0x00, 0x0F, 0x11, 0xe1, 0x9a, 0xb4, 0x00, 0x02, 0xa5, 0xd5, 0xc5, 0x1b)
//   #define COPY_CONFIG_W2ST_CHAR_UUID(uuid_struct) 		 COPY_UUID_128(uuid_struct, 0x00, 0x00, 0x00, 0x02, 0x00, 0x0F, 0x11, 0xe1, 0xac, 0x36, 0x00, 0x02, 0xa5, 0xd5, 0xc5, 0x1b)



    // Implements callback methods for GATT events that the app cares about.  For example,
    // connection change and services discovered.
    private final BluetoothGattCallback mGattCallback = new BluetoothGattCallback()
    {
        // ---------------------------------------------------------------------------------------------
        //
        // void onConnectionStateChange(BluetoothGatt gatt, int status, int newState)
        //
        // ---------------------------------------------------------------------------------------------
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState)
        {
            String intentAction;

            if (newState == BluetoothProfile.STATE_CONNECTED)
            {
                intentAction = ACTION_GATT_CONNECTED;
                mConnectionState = STATE_CONNECTED;
                broadcastUpdate(intentAction);
                Log.i(TAG, "Connected to GATT server.");
                // Attempts to discover services after successful connection.
                Log.i(TAG, "Attempting to start service discovery:" + mBluetoothGatt.discoverServices());

            }
            else if (newState == BluetoothProfile.STATE_DISCONNECTED)
            {
                intentAction = ACTION_GATT_DISCONNECTED;
                mConnectionState = STATE_DISCONNECTED;
                Log.i(TAG, "Disconnected from GATT server.");
                broadcastUpdate(intentAction);
            }
        }

        // ---------------------------------------------------------------------------------------------
        //
        // void onServicesDiscovered(BluetoothGatt gatt, int status)
        //
        // ---------------------------------------------------------------------------------------------
        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status)
        {
            if (status == BluetoothGatt.GATT_SUCCESS)
            {
                Log.w(TAG, "mBluetoothGatt = " + mBluetoothGatt );

                broadcastUpdate(ACTION_GATT_SERVICES_DISCOVERED);
            }
            else
            {
                Log.w(TAG, "onServicesDiscovered received: " + status);
            }
        }

        // ---------------------------------------------------------------------------------------------
        //
        // void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status)
        //
        // ---------------------------------------------------------------------------------------------
        @Override
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status)
        {
            if (status == BluetoothGatt.GATT_SUCCESS)
            {
                broadcastUpdate(ACTION_DATA_AVAILABLE, characteristic);
            }
        }

        // ---------------------------------------------------------------------------------------------
        //
        // void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic)
        //
        // ---------------------------------------------------------------------------------------------
        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic)
        {
            broadcastUpdate(ACTION_DATA_AVAILABLE, characteristic);
        }
    };

    // ---------------------------------------------------------------------------------------------
    //
    // void broadcastUpdate(final String action)
    //
    // ---------------------------------------------------------------------------------------------
    private void broadcastUpdate(final String action)
    {
        final Intent intent = new Intent(action);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    // ---------------------------------------------------------------------------------------------
    //
    // broadcastUpdate(final String action, final BluetoothGattCharacteristic characteristic)
    //
    // ---------------------------------------------------------------------------------------------
    private void broadcastUpdate(final String action, final BluetoothGattCharacteristic characteristic)
    {
        final Intent intent = new Intent(action);

        // This is handling for the notification on TX Character of NUS service
        if (nRF51822_TX_CHAR_UUID.equals(characteristic.getUuid()))
        {
            // Log.d(TAG, String.format("Received TX: %d",characteristic.getValue() ));
            intent.putExtra(EXTRA_DATA, characteristic.getValue());
        }
        else if (ST_BLUE_NRG_TX_CHAR_UUID.equals(characteristic.getUuid()))
        {
            // Log.d(TAG, String.format("Received TX: %d",characteristic.getValue() ));
            intent.putExtra(EXTRA_DATA, characteristic.getValue());
        }
        else
        {

        }
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    // ---------------------------------------------------------------------------------------------
    //
    // broadcastUpdate(final String action, final BluetoothGattCharacteristic characteristic)
    //
    // ---------------------------------------------------------------------------------------------
    public class LocalBinder extends Binder
    {
        UartService getService()
        {
            return UartService.this;
        }
    }

    // ---------------------------------------------------------------------------------------------
    //
    // IBinder onBind(Intent intent)
    //
    // ---------------------------------------------------------------------------------------------
    @Override
    public IBinder onBind(Intent intent)
    {
        return mBinder;
    }

    // ---------------------------------------------------------------------------------------------
    //
    // boolean onUnbind(Intent intent)
    //
    // ---------------------------------------------------------------------------------------------
    @Override
    public boolean onUnbind(Intent intent)
    {
        // After using a given device, you should make sure that BluetoothGatt.close() is called
        // such that resources are cleaned up properly.  In this particular example, close() is
        // invoked when the UI is disconnected from the Service.
        close();
        return super.onUnbind(intent);
    }

    private final IBinder mBinder = new LocalBinder();


    // ---------------------------------------------------------------------------------------------
    //
    // boolean initialize()
    //
    // ---------------------------------------------------------------------------------------------
    /**
     * Initializes a reference to the local Bluetooth adapter.
     *
     * @return Return true if the initialization is successful.
     */
    public boolean initialize()
    {
        // For API level 18 and above, get a reference to BluetoothAdapter through
        // BluetoothManager.
        if (mBluetoothManager == null)
        {
            mBluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
            if (mBluetoothManager == null)
            {
                Log.e(TAG, "Unable to initialize BluetoothManager.");
                return false;
            }
        }

        mBluetoothAdapter = mBluetoothManager.getAdapter();
        if (mBluetoothAdapter == null)
        {
            Log.e(TAG, "Unable to obtain a BluetoothAdapter.");
            return false;
        }

        return true;
    }

    // ---------------------------------------------------------------------------------------------
    //
    // boolean connect(final String address)
    //
    // ---------------------------------------------------------------------------------------------
    /**
     * Connects to the GATT server hosted on the Bluetooth LE device.
     *
     * @param address The device address of the destination device.
     *
     * @return Return true if the connection is initiated successfully. The connection result
     *         is reported asynchronously through the
     *         {@code BluetoothGattCallback#onConnectionStateChange(android.bluetooth.BluetoothGatt, int, int)}
     *         callback.
     */
    public boolean connect(final String address)
    {
        if (mBluetoothAdapter == null || address == null)
        {
            Log.w(TAG, "BluetoothAdapter not initialized or unspecified address.");
            return false;
        }

        // Previously connected device.  Try to reconnect.
        if (mBluetoothDeviceAddress != null && address.equals(mBluetoothDeviceAddress) && mBluetoothGatt != null)
        {
            Log.d(TAG, "Trying to use an existing mBluetoothGatt for connection.");
            if (mBluetoothGatt.connect())
            {
                mConnectionState = STATE_CONNECTING;
                return true;
            }
            else
            {
                return false;
            }
        }

        final BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);
        if (device == null)
        {
            Log.w(TAG, "Device not found.  Unable to connect.");
            return false;
        }
        // We want to directly connect to the device, so we are setting the autoConnect
        // parameter to false.
        mBluetoothGatt = device.connectGatt(this, false, mGattCallback);
        Log.d(TAG, "Trying to create a new connection.");
        mBluetoothDeviceAddress = address;
        mConnectionState = STATE_CONNECTING;
        return true;
    }


    // ---------------------------------------------------------------------------------------------
    //
    // void disconnect()
    //
    // ---------------------------------------------------------------------------------------------
    /**
     * Disconnects an existing connection or cancel a pending connection. The disconnection result
     * is reported asynchronously through the
     * {@code BluetoothGattCallback#onConnectionStateChange(android.bluetooth.BluetoothGatt, int, int)}
     * callback.
     */
    public void disconnect()
    {
        if (mBluetoothAdapter == null || mBluetoothGatt == null)
        {
            Log.w(TAG, "BluetoothAdapter not initialized");
            return;
        }
        mBluetoothGatt.disconnect();
        // mBluetoothGatt.close();
    }

    // ---------------------------------------------------------------------------------------------
    //
    // void close()
    //
    // ---------------------------------------------------------------------------------------------
    /**
     * After using a given BLE device, the app must call this method to ensure resources are
     * released properly.
     */
    public void close()
    {
        if (mBluetoothGatt == null)
        {
            return;
        }
        Log.w(TAG, "mBluetoothGatt closed");
        mBluetoothDeviceAddress = null;
        mBluetoothGatt.close();
        mBluetoothGatt = null;
    }

    // ---------------------------------------------------------------------------------------------
    //
    // void readCharacteristic(BluetoothGattCharacteristic characteristic)
    //
    // ---------------------------------------------------------------------------------------------
    /**
     * Request a read on a given {@code BluetoothGattCharacteristic}. The read result is reported
     * asynchronously through the {@code BluetoothGattCallback#onCharacteristicRead(android.bluetooth.BluetoothGatt, android.bluetooth.BluetoothGattCharacteristic, int)}
     * callback.
     *
     * @param characteristic The characteristic to read from.
     */
    public void readCharacteristic(BluetoothGattCharacteristic characteristic)
    {
        if (mBluetoothAdapter == null || mBluetoothGatt == null)
        {
            Log.w(TAG, "BluetoothAdapter not initialized");
            return;
        }
        mBluetoothGatt.readCharacteristic(characteristic);
    }

    /**
     * Enables or disables notification on a give characteristic.
     *

     */

    // ---------------------------------------------------------------------------------------------
    //
    // void enableTXNotification()
    //
    // ---------------------------------------------------------------------------------------------
    /**
     * Enable Notification on TX characteristic
     *
     * @return
     */
    public void enableTXNotification()
    {
    	/*
    	if (mBluetoothGatt == null) {
    		showMessage("mBluetoothGatt null" + mBluetoothGatt);
    		broadcastUpdate(DEVICE_DOES_NOT_SUPPORT_UART);
    		return;
    	}
    		*/
        BluetoothGattService RxService = mBluetoothGatt.getService(nRF51822_RX_SERVICE_UUID);
        if (RxService == null)
        {
            RxService =  mBluetoothGatt.getService(ST_BLUE_NRG_RX_SERVICE_UUID);
            if (RxService == null)
            {
                showMessage("Rx service not found!");
                broadcastUpdate(DEVICE_DOES_NOT_SUPPORT_UART);
                return;
            }
        }
        BluetoothGattCharacteristic TxChar = RxService.getCharacteristic(nRF51822_TX_CHAR_UUID);
        if (TxChar == null)
        {
            TxChar = RxService.getCharacteristic(ST_BLUE_NRG_TX_CHAR_UUID);
            if (TxChar == null)
            {
                showMessage("Tx charateristic not found!");
                broadcastUpdate(DEVICE_DOES_NOT_SUPPORT_UART);
                return;
            }
        }
        mBluetoothGatt.setCharacteristicNotification(TxChar,true);

        BluetoothGattDescriptor descriptor = TxChar.getDescriptor(CCCD);
        descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
        mBluetoothGatt.writeDescriptor(descriptor);
    }

    // ---------------------------------------------------------------------------------------------
    //
    // void writeRXCharacteristic(byte[] value)
    //
    // ---------------------------------------------------------------------------------------------
    public void writeRXCharacteristic(byte[] value)
    {
        BluetoothGattService RxService = mBluetoothGatt.getService(nRF51822_RX_SERVICE_UUID);
        showMessage("mBluetoothGatt null"+ mBluetoothGatt);
        if (RxService == null)
        {
            RxService =  mBluetoothGatt.getService(ST_BLUE_NRG_RX_SERVICE_UUID);
            if (RxService == null)
            {
                showMessage("Rx service not found!");
                broadcastUpdate(DEVICE_DOES_NOT_SUPPORT_UART);
                return;
            }
        }
        BluetoothGattCharacteristic RxChar = RxService.getCharacteristic(nRF51822_RX_CHAR_UUID);
        if (RxChar == null)
        {
            RxChar = RxService.getCharacteristic(ST_BLUE_NRG_RX_CHAR_UUID);
            if (RxChar == null)
            {
                showMessage("Rx charateristic not found!");
                broadcastUpdate(DEVICE_DOES_NOT_SUPPORT_UART);
                return;
            }
        }
        RxChar.setValue(value);
        boolean status = mBluetoothGatt.writeCharacteristic(RxChar);

        Log.d(TAG, "write TXchar - status=" + status);
    }

    // ---------------------------------------------------------------------------------------------
    //
    // void showMessage(String msg)
    //
    // ---------------------------------------------------------------------------------------------
    private void showMessage(String msg)
    {
        Log.e(TAG, msg);
    }

    // ---------------------------------------------------------------------------------------------
    //
    // List<BluetoothGattService> getSupportedGattServices()
    //
    // ---------------------------------------------------------------------------------------------
    /**
     * Retrieves a list of supported GATT services on the connected device. This should be
     * invoked only after {@code BluetoothGatt#discoverServices()} completes successfully.
     *
     * @return A {@code List} of supported services.
     */
    public List<BluetoothGattService> getSupportedGattServices()
    {
        if (mBluetoothGatt == null)
        {
            return null;
        }
        return mBluetoothGatt.getServices();
    }
}

