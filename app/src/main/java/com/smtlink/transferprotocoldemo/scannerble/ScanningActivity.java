package com.smtlink.transferprotocoldemo.scannerble;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.smtlink.transferprotocoldemo.R;

import java.util.List;

@SuppressLint("MissingPermission")
public class ScanningActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String EXTRA_STRING = "extra_string_scaning";

    private Button mBack;
    private Button mScanning;
    private RecyclerView mRecyclerView;
    private ScanningAdapter scanningAdapter;
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothLeScanner mBluetoothLeScanner;

    private long startScanTime = 0;

    private boolean isScanning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanning);
        initView();
        initData();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
//            unregisterReceiver(bluetoothReceiver);
            mBluetoothLeScanner.stopScan(scanCallback);
            isScanning = false;
            mScanning.setText("搜索");
        } catch (Exception e) {
            Log.e("gy", "ScanningActivity onDestroy unregisterReceiver Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mBack = (Button) findViewById(R.id.back);
        mBack.setOnClickListener(this);
        mScanning = (Button) findViewById(R.id.scanning);
        mScanning.setOnClickListener(this);
    }

    private void initData() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        scanningAdapter = new ScanningAdapter(R.layout.item_activity_sacnning);
        scanningAdapter.setOnItemClickListener(itemClickListener);
        mRecyclerView.setAdapter(scanningAdapter);

//        //方式一
//        //获取默认本地蓝牙适配器的句柄
//        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
//        //注册监听
//        startDiscovery();
//        //开始搜索
//        startScanBluetooth();

        ////////////////////////////////////////////

        //方式二
        BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();
        if (mBluetoothAdapter != null) {
            mBluetoothLeScanner = mBluetoothAdapter.getBluetoothLeScanner();
            startScan();
            isScanning = true;
            mScanning.setText("搜索中...");
        }

    }

    @Override
    public void onClick(final View v) {
        if (v == mBack) {
            finish();
        } else if (v == mScanning) {//搜索
            if (isScanning) {
                Toast.makeText(this, "正在搜索中...", Toast.LENGTH_SHORT).show();
                return;
            }

            mScanning.setText("搜索中...");
            //清除之前的item
            List<BLEDeviceInfo> scanningAdapterData = scanningAdapter.getData();
            scanningAdapterData.clear();
            scanningAdapter.notifyItemRangeChanged(0, scanningAdapterData.size());

            //方式一
//            startScanBluetooth();
            //方式二
            startScan();
        }
    }

    /**
     * recyclerview item Click
     */
    private final OnItemClickListener itemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(@NonNull final BaseQuickAdapter<?, ?> adapter, @NonNull final View view, final int position) {
            try {
                BLEDeviceInfo info = (BLEDeviceInfo) adapter.getData().get(position);
                //WindowUtil.showToast(ScanningActivity.this, "onItemClick " + info.address);
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putParcelable(EXTRA_STRING, info);
                //bundle.putString(EXTRA_STRING, info.address);
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                ScanningActivity.this.finish();
                // 判断是否在搜索,如果在搜索，就取消搜索
                if (mBluetoothAdapter.isDiscovering()) {
                    mBluetoothAdapter.cancelDiscovery();
                }
                if (mBluetoothLeScanner != null) {
                    mBluetoothLeScanner.stopScan(scanCallback);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    /**
     * 注册广播 (方式一)
     */
    private void startDiscovery() {
        // 找到设备的广播
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothDevice.ACTION_FOUND);// 搜索广播
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);// 搜索完成的广播
        // 注册广播
        registerReceiver(bluetoothReceiver, filter);
        Log.d("gy", "startDiscovery: 注册广播");
    }

    /**
     * 广播接收器 (方式一)
     */
    private final BroadcastReceiver bluetoothReceiver = new BroadcastReceiver() {
        @SuppressLint({"NewApi", "MissingPermission"})
        @Override
        public void onReceive(Context context, Intent intent) {
            // 收到的广播类型
            String action = intent.getAction();
            // 发现设备的广播
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // 从intent中获取设备
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // 没有配对
                if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
                    Log.d("gy", "onReceive: " + (device.getName() + " *** " + device.getAddress()));
                    if (device.getName() != null) {
//                        if (device.getName().contains("W1")) {
                            BLEDeviceInfo deviceInfo = new BLEDeviceInfo();
                            deviceInfo.name = device.getName();
                            deviceInfo.address = device.getAddress();
                            deviceInfo.rssi = 0;
                            deviceInfo.way = 1;
                            scanningAdapter.addData(deviceInfo);//单个item添加
//                        }
                    }
                }
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                Log.d("gy", "onReceive: 搜索完成");
                isScanning = false;
                mScanning.setText("搜索");
            }
        }
    };

    /**
     * 搜索蓝牙的方法(方式一)
     */
    private void startScanBluetooth() {
        if (mBluetoothAdapter != null) {
            // 判断是否在搜索,如果在搜索，就取消搜索
            if (mBluetoothAdapter.isDiscovering()) {
                mBluetoothAdapter.cancelDiscovery();
            }
            // 开始搜索
            mBluetoothAdapter.startDiscovery();
        }
    }

    /**
     * 搜索蓝牙的方法(方式二)
     */
    private void startScan() {
        if (mBluetoothLeScanner != null) {
            startScanTime = SystemClock.currentThreadTimeMillis();
            ScanSettings settings = new ScanSettings.Builder().setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY).setReportDelay(0).build();
            mBluetoothLeScanner.startScan(null, settings, scanCallback);
        }
    }

    /**
     * 扫描BLE回调(方式二) 需要比对地址去重复
     */
    private final Object lock = new Object();
    private final ScanCallback scanCallback = new ScanCallback() {
        @Override
        public void onScanResult(final int callbackType, final ScanResult result) {//发现 BLE 广告时的回调
            super.onScanResult(callbackType, result);

            synchronized (lock) {
                BluetoothDevice device = result.getDevice();
                String name = device.getName();
                String address = device.getAddress();
                int rssi = result.getRssi();
//                if (device.getName() != null) {
//                    if (device.getName().contains("W1")) {
                        BLEDeviceInfo deviceInfo = new BLEDeviceInfo();
                        deviceInfo.name = name;
                        deviceInfo.address = address;
                        deviceInfo.rssi = rssi;
                        deviceInfo.way = 2;
                        List<BLEDeviceInfo> bleDeviceInfoList = scanningAdapter.getData();
                        Log.e("gy", "bleDeviceInfoList.size: " + bleDeviceInfoList.size());
                        if (bleDeviceInfoList.size() > 0) {
                            boolean isFlag = false;
                            for (BLEDeviceInfo info : bleDeviceInfoList) {
                                Log.i("gy", "info.address: " + info.address);
                                if (info.address.equals(address)) {
                                    isFlag = true;
                                }
                            }
                            Log.e("gy", "isFlag: " + isFlag);
                            if (!isFlag) {
                                scanningAdapter.addData(deviceInfo);//单个item添加
                            }
                        } else {
                            scanningAdapter.addData(deviceInfo);//单个item添加
                        }
//                    }
//                }

                long timeMillis = SystemClock.currentThreadTimeMillis();
                long time = (timeMillis - startScanTime) / 1000;
                Log.d("gy", "time: " + time);
                if (time > 2) {//扫描大于6秒(看log不准), 结束扫描
                    mBluetoothLeScanner.stopScan(scanCallback);
                    isScanning = false;
                    mScanning.setText("搜索");
                }
                Log.d("gy", "onScanResult: " + name + " *** " + address + " *** " + rssi);
            }
        }

        @Override
        public void onBatchScanResults(final List<ScanResult> results) {//批量结果交付时回调
            super.onBatchScanResults(results);
//            for (ScanResult result : results) {
//                BluetoothDevice device = result.getDevice();
//                String name = device.getName();
//                String address = device.getAddress();
//                int rssi = result.getRssi();
//                Log.d("gy", "onBatchScanResults: " + name + " *** " + address + " *** " + rssi);
//            }
        }

        @Override
        public void onScanFailed(final int errorCode) {//无法启动扫描时的回调
            super.onScanFailed(errorCode);
            Log.e("gy", "onScanFailed BLE errorCode: " + errorCode);
        }
    };


}