package com.hss01248.dialogutildemo;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.alibaba.fastjson.JSON;
import com.hss01248.dialog.ActivityStackManager;
import com.hss01248.dialog.DialogsMaintainer;
import com.hss01248.dialog.StyledDialog;
import com.orhanobut.logger.Jsonfy;
import com.orhanobut.logger.XLogUtil;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Administrator on 2017/2/15 0015.
 */

public class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
        StyledDialog.init(getApplicationContext());
        registCallback();
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

    private void registCallback() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                ActivityStackManager.getInstance().addActivity(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
            }

            @Override
            public void onActivityPaused(Activity activity) {
                DialogsMaintainer.onPause(activity);

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                ActivityStackManager.getInstance().removeActivity(activity);
            }
        });
    }
}
