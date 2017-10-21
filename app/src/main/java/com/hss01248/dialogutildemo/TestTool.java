package com.hss01248.dialogutildemo;

import android.os.Build;
import android.os.Debug;
import android.os.StrictMode;
import android.os.Trace;

/**
 * Created by huangshuisheng on 2017/10/13.
 */

public class TestTool {

    public final static boolean DEBUG = true;

    public static void openStickModeIfIsDebug(){
        if(DEBUG){
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectAll()//开启所有的detectXX系列方法
                //.penaltyDialog()//弹出违规提示框
                .penaltyLog()//在Logcat中打印违规日志
                .build());

            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build());
        }
    }

    /**
     * 控制Method Trace开始
     * 需要debug包
     * @param tag
     */
    public static void startTraceMethod(String tag){
        if(DEBUG)
        Debug.startMethodTracing(tag);
    }

    public static void stopTraceMethod(){
        if(DEBUG)
        Debug.stopMethodTracing();
    }

    /**
     * 仅用于SysTrace开启tag,不是用于控制SysTrace的开启,你也控制不了
     * 一定要配合命令行-a 使用,否则自定义tag无效
     * 需要debug包
     * Trace的begin与end必须在同一线程之中执行
     * @param tag
     */
    public static void startSysTraceSection(String tag){
        if(DEBUG){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                Trace.beginSection(tag);
            }
        }

    }

    public static void stopSysTraceSection(){
        if(DEBUG){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                Trace.endSection();
            }
        }

    }


}
