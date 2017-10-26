package com.hss01248.dialog;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import java.lang.reflect.Method;

/**
 * Created by huangshuisheng on 2017/10/26.
 */

public class ScreenUtil {
    /**
     * 获取屏幕尺寸，但是不包括虚拟功能高度
     *
     * @return
     */
    public static int getScreenHeight() {
        int height =  getWindowManager().getDefaultDisplay().getHeight();
        return height;
    }

    /**
     * 通过反射，获取包含虚拟键的整体屏幕高度
     *
     * @return
     */
    public static int getgetScreenHeightHasVirtualKey() {
        int dpi = 0;
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        @SuppressWarnings("rawtypes")
        Class c;
        try {
            c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
            method.invoke(display, dm);
            dpi = dm.heightPixels;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dpi;
    }

    public static WindowManager getWindowManager() {
        return (WindowManager) StyledDialog.context.getSystemService(Context.WINDOW_SERVICE);
    }
}
