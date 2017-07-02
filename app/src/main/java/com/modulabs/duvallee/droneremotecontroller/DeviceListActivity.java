package com.modulabs.duvallee.droneremotecontroller;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanSettings;
import android.content.BroadcastReceiver;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by duval on 2017-06-29.
 */

public class DeviceListActivity extends Activity
{
    public static final String TAG = "DeviceListActivity";

    // ---------------------------------------------------------------------------------------------
    // result for startActivityForResult()
    public final int DISCOVERY_REQUEST = 1;

    // ---------------------------------------------------------------------------------------------
    private Handler mHandler;

    // ---------------------------------------------------------------------------------------------
    // for Bluetooth
    private BluetoothAdapter mBluetoothAdapter;
    private DeviceAdapter deviceAdapter;
    private BluetoothLeScanner mBLEScanner;

    // ---------------------------------------------------------------------------------------------
    // list for Bluetooth device's
    List<BluetoothDevice> deviceList;
    Map<String, Integer> devRssiValues;

    // ---------------------------------------------------------------------------------------------
    // variable for scanning for the bluetooth
    private boolean mScanning;
    private static final long SCAN_PERIOD = 10000; // scanning for 10 seconds

    // ---------------------------------------------------------------------------------------------
    private TextView mEmptyList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);
        setContentView(R.layout.device_list);

        android.view.WindowManager.LayoutParams layoutParams = this.getWindow().getAttributes();
        layoutParams.gravity= Gravity.TOP;
        layoutParams.y = 200;

        mHandler = new Handler();

        // -----------------------------------------------------------------------------------------
        // Use this check to determine whether BLE is supported on the device.  Then you can
        // selectively disable BLE-related features.
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE))
        {
            Toast.makeText(this, R.string.ble_not_supported, Toast.LENGTH_LONG).show();
            finish();
        }

        // -----------------------------------------------------------------------------------------
        // Initializes a Bluetooth adapter.  For API level 18 and above, get a reference to
        // BluetoothAdapter through BluetoothManager.
        final BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();

//        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!mBluetoothAdapter.isEnabled())
        {
            mBluetoothAdapter.enable();
        }


        // Checks if Bluetooth is supported on the device.
        if (mBluetoothAdapter == null)
        {
            Toast.makeText(this, R.string.ble_not_supported, Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // -----------------------------------------------------------------------------------------
        // Start : changed more than SDK level 21
        mBLEScanner = mBluetoothAdapter.getBluetoothLeScanner();
        // Checks if Bluetooth LE Scanner is available.
        if (mBLEScanner == null)
        {
            Toast.makeText(this, "Can not find BLE Scanner", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        // End : changed more than SDK level 21
        // -----------------------------------------------------------------------------------------

//        ScanSettings settings = new ScanSettings.Builder()
//                .setScanMode(ScanSettings.SCAN_MODE_LOW_POWER)
//                .setCallbackType(ScanSettings.CALLBACK_TYPE_ALL_MATCHES)
//                .build();
//        List<ScanFilter> filters = new ArrayList<ScanFilter>();

//        // -----------------------------------------------------------------------------------------
//        // get scanmod for bluetooth
//        String sDiscoverable = BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE;
//        startActivityForResult(new Intent(sDiscoverable), DISCOVERY_REQUEST);

        populateList();
        mEmptyList = (TextView) findViewById(R.id.empty);
        Button cancelButton = (Button) findViewById(R.id.btn_cancel);
        cancelButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                if (mScanning == false)
                {
                    scanLeDevice(true);
                }
                else
                {
                    finish();
                }
            }
        });
    }

    // -----------------------------------------------------------------------------------------
    //
    //
    // -----------------------------------------------------------------------------------------
    private void populateList()
    {
        /* Initialize device list container */
        Log.d(TAG, "populateList");
        deviceList = new ArrayList<BluetoothDevice>();
        deviceAdapter = new DeviceAdapter(this, deviceList);
        devRssiValues = new HashMap<String, Integer>();

        ListView newDevicesListView = (ListView) findViewById(R.id.new_devices);
        newDevicesListView.setAdapter(deviceAdapter);
        newDevicesListView.setOnItemClickListener(mDeviceClickListener);

        scanLeDevice(true);

    }

    // -----------------------------------------------------------------------------------------
    //
    //
    // -----------------------------------------------------------------------------------------
    private void scanLeDevice(final boolean enable)
    {
        final Button cancelButton = (Button) findViewById(R.id.btn_cancel);
        if (enable)
        {
            // Stops scanning after a pre-defined scan period.
            mHandler.postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    mScanning = false;
                    // -----------------------------------------------------------------------------------------
                    // Start : changed more than SDK level 21
                    mBLEScanner.stopScan(mLeScanCallback);
                    // End : changed more than SDK level 21
                    // -----------------------------------------------------------------------------------------

                    // not supported at SDK level 21
                    // mBluetoothAdapter.stopLeScan(mLeScanCallback);
                    cancelButton.setText(R.string.scan);
                }
            }, SCAN_PERIOD);

            mScanning = true;

            // -----------------------------------------------------------------------------------------
            // Start : changed more than SDK level 21
            mBLEScanner.startScan(mLeScanCallback); // mBluetoothAdapter.startLeScan() 부분
            // End : changed more than SDK level 21
            // -----------------------------------------------------------------------------------------

            // not supported at SDK level 21
            // mBluetoothAdapter.startLeScan(mLeScanCallback);

            cancelButton.setText(R.string.cancel);
        }
        else
        {
            mScanning = false;

            // -----------------------------------------------------------------------------------------
            // Start : changed more than SDK level 21
            mBLEScanner.stopScan(mLeScanCallback);
            // End : changed more than SDK level 21
            // -----------------------------------------------------------------------------------------

            // not supported at SDK level 21
            // mBluetoothAdapter.stopLeScan(mLeScanCallback);
            cancelButton.setText(R.string.scan);
        }
    }

    // -----------------------------------------------------------------------------------------
    // Start : changed more than SDK level 21
    // does not supported
    // private BluetoothAdapter.LeScanCallback mLeScanCallback =
    //    new BluetoothAdapter.LeScanCallback()
    //    {
    //        @Override
    //        public void onLeScan(final BluetoothDevice device, final int rssi, byte[] scanRecord)
    //        {
    //            runOnUiThread(new Runnable()
    //            {
    //                @Override
    //                public void run()
    //                {
    //                    addDevice(device,rssi);
    //                }
    //            });
    //        }
    //    };
    // End : changed more than SDK level 21
    // -----------------------------------------------------------------------------------------


    // -----------------------------------------------------------------------------------------
    // Start : changed more than SDK level 21
    // use ScanCallback instead of BluetoothAdapter.LeScanCallback
    private ScanCallback mLeScanCallback = new ScanCallback()
    {
        @Override
        public void onScanResult(int callbackType, ScanResult result)
        {
            processResult(result);
        }

        @Override
        public void onBatchScanResults(List<ScanResult> results)
        {
            for (ScanResult result : results)
            {
                processResult(result);
            }
        }

        @Override
        public void onScanFailed(int errorCode)
        {
        }

        private void processResult(final ScanResult result)
        {
            runOnUiThread(new Runnable()
            {
                @Override
                public void run()
                {
                    BluetoothDevice device = result.getDevice();
                    addDevice(device, result.getRssi());
                }
            });
        }
    };

    // ---------------------------------------------------------------------------------------------
    //
    private void addDevice(BluetoothDevice device, int rssi)
    {
        boolean deviceFound = false;
        for (BluetoothDevice listDev : deviceList)
        {
            if (listDev.getAddress().equals(device.getAddress()))
            {
                deviceFound = true;
                break;
            }
        }
        devRssiValues.put(device.getAddress(), rssi);
        if (!deviceFound)
        {
            deviceList.add(device);
            mEmptyList.setVisibility(View.GONE);
            deviceAdapter.notifyDataSetChanged();
        }
    }

//    @Override
//    public Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter) {
//        return super.registerReceiver(receiver, filter);
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == DISCOVERY_REQUEST)
        {
            boolean isDiscoverable = resultCode > 0;
            int discoverableDuration = resultCode;
        }
    }

    @Override
    public void onStart()
    {
        super.onStart();
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);

        // from stckoverflow
        this.registerReceiver(mReceiver, filter);
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            String action = intent.getAction();

            // When discovery finds a device
            if (BluetoothDevice.ACTION_FOUND.equals(action))
            {
                //do something
            }

            else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action))
            {
                //do something else
            }
        }
    };

    @Override
    public void onStop()
    {
        super.onStop();
        // -----------------------------------------------------------------------------------------
        // Start : changed more than SDK level 21
        mBLEScanner.stopScan(mLeScanCallback);
        // End : changed more than SDK level 21
        // -----------------------------------------------------------------------------------------

        // not supported at SDK level 21
        // mBluetoothAdapter.stopLeScan(mLeScanCallback);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        // -----------------------------------------------------------------------------------------
        // Start : changed more than SDK level 21
        mBLEScanner.stopScan(mLeScanCallback);
        // End : changed more than SDK level 21
        // -----------------------------------------------------------------------------------------

        // not supported at SDK level 21
        // mBluetoothAdapter.stopLeScan(mLeScanCallback);
    }

    private OnItemClickListener mDeviceClickListener = new OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            BluetoothDevice device = deviceList.get(position);
            // -----------------------------------------------------------------------------------------
            // Start : changed more than SDK level 21
            mBLEScanner.stopScan(mLeScanCallback);
            // End : changed more than SDK level 21
            // -----------------------------------------------------------------------------------------

            // not supported at SDK level 21
            // mBluetoothAdapter.stopLeScan(mLeScanCallback);

            Bundle b = new Bundle();
            b.putString(BluetoothDevice.EXTRA_DEVICE, deviceList.get(position).getAddress());

            Intent result = new Intent();
            result.putExtras(b);
            setResult(Activity.RESULT_OK, result);
            finish();
        }
    };

    protected void onPause()
    {
        super.onPause();
        scanLeDevice(false);
    }


    class DeviceAdapter extends BaseAdapter
    {
        Context context;
        List<BluetoothDevice> devices;
        LayoutInflater inflater;

        public DeviceAdapter(Context context, List<BluetoothDevice> devices)
        {
            this.context = context;
            inflater = LayoutInflater.from(context);
            this.devices = devices;
        }

        @Override
        public int getCount()
        {
            return devices.size();
        }

        @Override
        public Object getItem(int position)
        {
            return devices.get(position);
        }

        @Override
        public long getItemId(int position)
        {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            ViewGroup vg;

            if (convertView != null)
            {
                vg = (ViewGroup) convertView;
            }
            else
            {
                vg = (ViewGroup) inflater.inflate(R.layout.device_element, null);
            }

            BluetoothDevice device = devices.get(position);
            final TextView tvadd = ((TextView) vg.findViewById(R.id.address));
            final TextView tvname = ((TextView) vg.findViewById(R.id.name));
            final TextView tvpaired = (TextView) vg.findViewById(R.id.paired);
            final TextView tvrssi = (TextView) vg.findViewById(R.id.rssi);

            tvrssi.setVisibility(View.VISIBLE);
            byte rssival = (byte) devRssiValues.get(device.getAddress()).intValue();
            if (rssival != 0)
            {
                tvrssi.setText("Rssi = " + String.valueOf(rssival));
            }

            tvname.setText(device.getName());
            tvadd.setText(device.getAddress());
            if (device.getBondState() == BluetoothDevice.BOND_BONDED)
            {
                Log.i(TAG, "device::"+device.getName());
                tvname.setTextColor(Color.WHITE);
                tvadd.setTextColor(Color.WHITE);
                tvpaired.setTextColor(Color.GRAY);
                tvpaired.setVisibility(View.VISIBLE);
                tvpaired.setText(R.string.paired);
                tvrssi.setVisibility(View.VISIBLE);
                tvrssi.setTextColor(Color.WHITE);

            }
            else
            {
                tvname.setTextColor(Color.WHITE);
                tvadd.setTextColor(Color.WHITE);
                tvpaired.setVisibility(View.GONE);
                tvrssi.setVisibility(View.VISIBLE);
                tvrssi.setTextColor(Color.WHITE);
            }
            return vg;
        }
    }

    private void showMessage(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
