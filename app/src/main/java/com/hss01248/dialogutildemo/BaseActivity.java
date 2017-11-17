package com.hss01248.dialogutildemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hss01248.dialog.ActivityStackManager;

/**
 * Created by huangshuisheng on 2017/11/17.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        ActivityStackManager.getInstance().setTopAttached(this);
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ActivityStackManager.getInstance().removeTopAttached(this);
    }
}
