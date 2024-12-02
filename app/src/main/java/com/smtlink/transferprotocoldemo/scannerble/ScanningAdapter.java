package com.smtlink.transferprotocoldemo.scannerble;

import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.smtlink.transferprotocoldemo.R;

public class ScanningAdapter extends BaseQuickAdapter<BLEDeviceInfo, BaseViewHolder> {

    public ScanningAdapter(final int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder baseViewHolder, BLEDeviceInfo info) {
        String name = info.name;
        if (TextUtils.isEmpty(name)) {
            name = "unknown";
        }
        baseViewHolder.setText(R.id.name, name);
        baseViewHolder.setText(R.id.address, info.address);
        if (info.way == 2) {
            baseViewHolder.setText(R.id.rssi, String.valueOf(info.rssi));
        }
    }
}
