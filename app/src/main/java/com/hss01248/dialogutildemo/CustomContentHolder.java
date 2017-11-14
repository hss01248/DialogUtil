package com.hss01248.dialogutildemo;

import android.content.Context;

import com.hss01248.dialog.adapter.SuperLvHolder;

/**
 * Created by huangshuisheng on 2017/11/13.
 */

public class CustomContentHolder extends SuperLvHolder {
    public CustomContentHolder(Context context) {
        super(context);
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.view_custom_holder;
    }

    @Override
    public void assingDatasAndEvents(Context context, Object bean) {

    }
}
