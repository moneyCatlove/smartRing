package com.smtlink.transferprotocoldemo.scannerble;

import android.os.Parcel;
import android.os.Parcelable;

public class BLEDeviceInfo implements Parcelable {

    public String name;
    public String address;
    public int rssi;
    public int way;//为1即方式1, 为2即方式2

    public BLEDeviceInfo() {}

    protected BLEDeviceInfo(Parcel in) {
        name = in.readString();
        address = in.readString();
        rssi = in.readInt();
        way = in.readInt();
    }

    public static final Creator<BLEDeviceInfo> CREATOR = new Creator<BLEDeviceInfo>() {
        @Override
        public BLEDeviceInfo createFromParcel(Parcel in) {
            return new BLEDeviceInfo(in);
        }

        @Override
        public BLEDeviceInfo[] newArray(int size) {
            return new BLEDeviceInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeString(name);
        dest.writeString(address);
        dest.writeInt(rssi);
        dest.writeInt(way);
    }
}
