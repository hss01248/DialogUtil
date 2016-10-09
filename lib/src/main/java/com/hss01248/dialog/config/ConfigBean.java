package com.hss01248.dialog.config;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.v7.app.AlertDialog;

import com.hss01248.dialog.interfaces.MyDialogListener;
import com.hss01248.dialog.interfaces.MyItemDialogListener;

import java.util.List;
import java.util.Map;

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
    public AlertDialog alertDialog;

    //bottom sheet
    public  CharSequence bottomTxt;

    public List<? extends CharSequence> words;


    //样式

    //三个以下按钮,颜色按此顺序
    public  @ColorInt int btn1Color;
    public  @ColorInt int btn2Color;
    public  @ColorInt int btn3Color;

    public  @ColorInt int lvItemTxtColor;//ios 风格的list 选项共同颜色
    public Map<Integer,Integer> colorOfPosition;//listview 的item的特殊颜色:

    /*可能需要拓展的功能，支持当个item字体大小和颜色设置 (比如底部弹出 ，有的item是红色字这种，有的是蓝色)
支持填充自定义布局
支持gridview 或者recylerview*/






}
