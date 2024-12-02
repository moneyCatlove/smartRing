package com.smtlink.transferprotocoldemo;

import android.app.Application;

import com.smtlink.transferprotocolsdk.ble.BleTransferManager;

public class MainApplication extends Application {

    public static MainApplication mainApplication;
    public static BleTransferManager manager;
    public static MainApplication getInstance() {
        return mainApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mainApplication = this;
        manager = BleTransferManager.initialized(this);
    }

    public boolean connectedState = false;

    public boolean isConnectedState() {
        return connectedState;
    }

    public void setConnectedState(final boolean connectedState) {
        this.connectedState = connectedState;
    }
}
