package com.hss01248.dialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

import com.hss01248.dialog.config.ConfigBean;
import com.hss01248.dialog.config.DefaultConfig;

/**
 * Created by Administrator on 2017/12/4.
 */

public class WindowAdjuster {

    public static void adjust(Window window,ConfigBean bean){
        adjustSize(window,bean);
        setBackground(window,bean);
        setIfTypeToast(window,bean);
        setDimBehind(window,bean);
        setAnimation(window,bean);
    }

    private static void setAnimation(Window window, ConfigBean bean) {
        
    }

    private static void setDimBehind(Window window, ConfigBean bean) {
        
    }

    private static void setIfTypeToast(Window window, ConfigBean bean) {
        
    }

    private static void setBackground(Window window, ConfigBean bean) {
        //no need to modify the background
        if((bean.type == DefaultConfig.TYPE_BOTTOM_SHEET_GRID && bean.hasBehaviour)
                || bean.type == DefaultConfig.TYPE_BOTTOM_SHEET_LIST
                || bean.type == DefaultConfig.TYPE_BOTTOM_SHEET_CUSTOM
                || bean.type == DefaultConfig.TYPE_PROGRESS){
            // No need to set backgroud
            return;
        }

        if (bean.alertDialog!= null){
            if(bean.useTheShadowBg){
                window.setBackgroundDrawableResource(R.drawable.shadow);
            }else {
                if(bean.bgRes>0)
                    window.setBackgroundDrawableResource(bean.bgRes);
                else {
                    window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                }

            }
        }else {
            if(bean.type == DefaultConfig.TYPE_IOS_LOADING  ){//转菊花时,背景透明
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }else if((bean.type == DefaultConfig.TYPE_BOTTOM_SHEET_GRID && !bean.hasBehaviour)){
                window.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
            }else {
                if(bean.useTheShadowBg){
                    window.setBackgroundDrawableResource(R.drawable.shadow);
                }else {
                    if(bean.bgRes>0)
                        window.setBackgroundDrawableResource(bean.bgRes);
                    else {
                        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    }
                }
            }

        }
    }

    private static void adjustSize(Window window, ConfigBean bean) {
        
    }
}
