package com.hss01248.dialog;

import android.content.DialogInterface;

/**
 * Created by Administrator on 2016/5/4 0004.
 */
public abstract class MyDialogListener {
    public abstract void onFirst(DialogInterface dialog);
    public abstract void onSecond(DialogInterface dialog);
    public void onThird(DialogInterface dialog){}

    public void onCancle(){}
}
