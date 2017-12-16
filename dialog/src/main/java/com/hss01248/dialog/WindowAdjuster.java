package com.hss01248.dialog;

import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;

import com.hss01248.dialog.config.ConfigBean;

/**
 * Created by Administrator on 2017/12/4.
 */

public class WindowAdjuster {

    public static void adjust(Window window,ConfigBean bean){

        setGravity(window,bean);
        setBackground(window,bean);
        setIfTypeToast(window,bean);
        setDimBehind(window,bean);
        setAnimation(window,bean);

        setStyleOnLayout(window,bean);


    }

    private static void setStyleOnLayout(final Window window, final ConfigBean bean) {
        window.getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                adjustSize(window,bean);
                showKeyBorarIfNeed(window,bean);
                window.getDecorView().getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
    }

    private static void showKeyBorarIfNeed(Window window, ConfigBean bean) {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        Tool.showSoftKeyBoardDelayed(bean.needSoftKeyboard,bean.viewHolder);
        Tool.showSoftKeyBoardDelayed(bean.needSoftKeyboard,bean.customContentHolder);

    }

    private static void setGravity(Window window, ConfigBean bean) {
        window.setGravity(bean.gravity);
    }

    private static void setAnimation(Window window, ConfigBean bean) {
        if(bean.showAsActivity){
            return;
        }
        int gravity = bean.gravity;
        if(gravity == Gravity.BOTTOM || gravity == (Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL)){
            window.setWindowAnimations(R.style.ani_bottom);
        }else if(gravity == Gravity.CENTER){
            //window.setWindowAnimations(R.style.dialog_center);
        }
    }

    private static void setDimBehind(Window window, ConfigBean bean) {
        if(bean.dimBehind){
            window.setDimAmount(0.6f);
        }else {
            window.setDimAmount(0f);
        }

        
    }

    private static void setIfTypeToast(Window window, ConfigBean bean) {
        
    }

    private static void setBackground(Window window, ConfigBean bean) {

        if(!bean.showAsActivity && bean.bgRes != 0){
            window.setBackgroundDrawableResource(bean.bgRes);
        }
        //no need to modify the background
       /* if((bean.type == DefaultConfig.TYPE_BOTTOM_SHEET_GRID && bean.hasBehaviour)
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

        }*/
    }

    private static void adjustSize(Window window, ConfigBean bean) {
        View rootView = window.getDecorView();
        //window.setWindowAnimations(R.style.dialog_center);
        WindowManager.LayoutParams wl = window.getAttributes();

        int width = window.getWindowManager().getDefaultDisplay().getWidth();
        int height = window.getWindowManager().getDefaultDisplay().getHeight();
        int measuredHeight = rootView.getMeasuredHeight();
        int measuredWidth = rootView.getMeasuredWidth();
        float widthRatio = 0f;
        float heightRatio = 0f;
        if(width > height){//宽屏
            widthRatio = 0.5f;
        }
        /*float widthRatio = 0.85f;
        float heightRatio = 0f;
        if(bean.type ==DefaultConfig.TYPE_IOS_BOTTOM){
            widthRatio = 0.95f;
        }else if(bean.type ==DefaultConfig.TYPE_IOS_CENTER_LIST){
            widthRatio = 0.9f;
        }
        if(width > height){//宽屏
            widthRatio = 0.5f;
        }*/

        //set ratio as user has set
        if(bean.maxWidthPercent > 0 && measuredWidth > bean.maxWidthPercent * width){
            widthRatio = bean.maxWidthPercent;
        }
        if(bean.forceWidthPercent >0 && bean.forceWidthPercent <=1.0f){
            widthRatio = bean.forceWidthPercent;
        }
        if(bean.maxHeightPercent> 0 && measuredHeight > bean.maxHeightPercent * height){
            heightRatio = bean.maxHeightPercent;
        }
        if(bean.forceHeightPercent >0 && bean.forceHeightPercent <=1.0f){
            heightRatio = bean.forceHeightPercent;
        }

        boolean needUpdate = false;
        if(widthRatio >0){
            wl.width = (int) (width * widthRatio);//stretch when the content is not enough,margin when the content is full fill the screen
            needUpdate = true;
        }

        //if (measuredHeight > height* heightRatio){//only work when the content is full fill the screen
        if(heightRatio>0){
            wl.height = (int) (height* heightRatio);
            needUpdate = true;
        }

        if(needUpdate){
            window.setAttributes(wl);
        }

        /*if(Tool.istheTypeOfNotAdjust(bean)){
            *//*wl.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;*//*

        }else {
            // rootView.setPadding(0,30,0,30);
            if(widthRatio >0){
                wl.width = (int) (width * widthRatio);//stretch when the content is not enough,margin when the content is full fill the screen
            }

            //if (measuredHeight > height* heightRatio){//only work when the content is full fill the screen
            if(heightRatio>0){
                wl.height = (int) (height* heightRatio);
            }

            if(bean.type == DefaultConfig.TYPE_BOTTOM_SHEET_GRID && !bean.hasBehaviour){
                wl.height =measuredHeight;
            }

            // }
        }

        window.setAttributes(wl);*/

    }
}
