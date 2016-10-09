package com.hss01248.dialog.config;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AppCompatDialog;

import com.hss01248.dialog.MyDialogListener;
import com.hss01248.dialog.MyItemDialogListener;

import java.util.List;

/**
 * Created by Administrator on 2016/10/9 0009.
 */
public class ConfigBean {

    public Context context;
    public boolean isVertical;

    public CharSequence title;
    public CharSequence msg;
    public CharSequence text1;
    public CharSequence text2;
    public CharSequence text3;

    public String hint1;
    public String hint2;



    public MyDialogListener dialogListener;
    public MyItemDialogListener itemDialogListener;

    public boolean cancelable;
    public boolean outsideTouchable;

    public Dialog dialog;
    public AppCompatDialog appCompatDialog;

    //bottom sheet
    public  CharSequence bottomTxt;

    public List<? extends CharSequence> words;
}
