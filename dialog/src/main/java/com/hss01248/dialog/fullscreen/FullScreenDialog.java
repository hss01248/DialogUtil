package com.hss01248.dialog.fullscreen;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

import com.hss01248.dialog.R;


/**
 * @Despciption todo
 * @Author hss
 * @Date 06/01/2023 17:08
 * @Version 1.0
 */
public class FullScreenDialog extends Dialog implements DefaultLifecycleObserver {
    public FullScreenDialog(Context context) {
       this(context,R.style.Dialog_FullScreen);
        init2(context);
    }

    public FullScreenDialog(Context context, int theme) {
        super(context, theme);
        init2(context);
    }

    protected FullScreenDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        this(context,R.style.Dialog_FullScreen);
        setCancelable(cancelable);
        setOnCancelListener(cancelListener);
        init2(context);
    }

    private void init2(Context context) {
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setDialogToFullScreen(this);
    }

    /**
     * https://www.jianshu.com/p/3ecad4bfc55e  三句代码创建全屏Dialog或者DialogFragment
     * @param dialog
     */
    public static void setDialogToFullScreen(Dialog dialog){
        Window window = dialog.getWindow();
        //etStyle(STYLE_NORMAL, R.style.Dialog_FullScreen);
        window.setBackgroundDrawable(new ColorDrawable(Color.WHITE));

        //window.requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setDimAmount(0f);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.WHITE);
        }

        //window.getDecorView().setSystemUiVisibility(Activi.getSystemUiVisibility());//获取视口全屏大小
        //window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //这个flag表示window负责绘制状态栏的背景当设置了这个flag,系统状态栏会变透明,同时这个相应的区域会被填满 getStatusBarColor() and getNavigationBarColor()的颜色

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            //window.setStatusBarContrastEnforced(true);
        }
        WindowManager.LayoutParams attributes = window.getAttributes();
        if(attributes == null){
            attributes = new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.MATCH_PARENT);
        }else {
            attributes.width = WindowManager.LayoutParams.MATCH_PARENT;
            attributes.height = WindowManager.LayoutParams.MATCH_PARENT;
        }
        window.setAttributes(attributes);

    }



    @Override
    public void show() {
        if (getWindow() != null && getWindow().getDecorView() != null) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
        super.show();
    }

    @Override
    public void onDestroy(@NonNull LifecycleOwner owner) {
        DefaultLifecycleObserver.super.onDestroy(owner);
    }
}
