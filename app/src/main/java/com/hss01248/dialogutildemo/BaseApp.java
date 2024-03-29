package com.hss01248.dialogutildemo;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.multidex.MultiDexApplication;

import com.alibaba.fastjson.JSON;

import com.orhanobut.logger.Jsonfy;
import com.orhanobut.logger.XLogUtil;

/**
 * Created by Administrator on 2017/2/15 0015.
 */

public class BaseApp extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        //StyledDialog.init(getApplicationContext());
        initlog();
        //TestTool.openStickModeIfIsDebug();
    }

    private void initlog() {
        XLogUtil.init(true, "dialog", new Jsonfy() {
            @Override
            public String toJson(Object o) {
                return JSON.toJSONString(o);
            }
        });
    }


}
