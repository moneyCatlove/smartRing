package com.smtlink.transferprotocoldemo;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.List;

public class MainButtonAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public MainButtonAdapter(final int layoutResId, final List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, final String s) {
        baseViewHolder.setText(R.id.button, s);
    }

}
