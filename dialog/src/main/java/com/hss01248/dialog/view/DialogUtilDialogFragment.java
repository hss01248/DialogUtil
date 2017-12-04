package com.hss01248.dialog.view;


import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.hss01248.dialog.Tool;
import com.hss01248.dialog.config.ConfigBean;

/**
 * Created by Administrator on 2017/12/3.
 */

public class DialogUtilDialogFragment extends DialogFragment {
    protected  View myRootView;

    protected ConfigBean bean;

    public void setConfigbean(ConfigBean bean){
        this.bean = bean;

    }


    public void setRootView(View rootView){
        this.myRootView = rootView;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE); //去除标题栏
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //getDialog().setCancelable(true);
        getDialog().setCanceledOnTouchOutside(true);

        getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
                    if(bean.cancelable){
                        DialogUtilDialogFragment.this.dismiss();
                        return true;
                    }

                }
                return false;
            }
        });
        return myRootView;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if(bean.alertDialog !=null){
            return bean.alertDialog;
        }
        return super.onCreateDialog(savedInstanceState);
    }


    /**
     * 此处更改宽高
     */
    @Override
    public void onStart() {
        super.onStart();

        Window window = getDialog().getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = bean.gravity; //底部

        window.setAttributes(lp);
        Tool.adjustWH(getDialog(),bean);

//设置对话框宽高也可以使用lp.width和lp.height
    }
}
