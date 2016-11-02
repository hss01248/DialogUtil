package com.hss01248.dialog;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.hss01248.dialog.config.ConfigBean;
import com.hss01248.dialog.config.DefaultConfig;
import com.hss01248.dialog.interfaces.Assignable;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.hss01248.dialog.interfaces.MyItemDialogListener;

import java.util.List;

/**
 * Created by Administrator on 2016/10/9 0009.
 */
public class DialogAssigner implements Assignable {

    private static DialogAssigner instance;

    private DialogAssigner(){

    }

    public static DialogAssigner getInstance(){
        if (instance == null){
            instance = new DialogAssigner();
        }
        return instance;
    }


    @Override
    public ConfigBean assignMdLoading(Context context, CharSequence msg, boolean cancelable, boolean outsideTouchable) {
        ConfigBean bean = new ConfigBean();
        bean.context = context;
        bean.msg = msg;
        bean.type = DefaultConfig.TYPE_MD_LOADING;
        bean.cancelable = cancelable;
        bean.outsideTouchable = outsideTouchable;
        return bean;
    }

    @Override
    public ConfigBean assignMdAlert(Activity activity, CharSequence title, CharSequence msg, MyDialogListener listener) {

        ConfigBean bean = new ConfigBean();
        bean.context = activity;
        bean.msg = msg;
       bean.title = title;
        bean.listener = listener;
        bean.type = DefaultConfig.TYPE_MD_ALERT;
        bean.btn1Color = DefaultConfig.mdBtnColor;
        bean.btn2Color = DefaultConfig.mdBtnColor;
        bean.btn3Color = DefaultConfig.mdBtnColor;
        return bean;
    }

    @Override
    public ConfigBean assignMdSingleChoose(Activity context, CharSequence title, int defaultChosen, CharSequence[] words, MyItemDialogListener listener) {
        ConfigBean bean = new ConfigBean();
        bean.context = context;
        bean.title = title;
        bean.itemListener = listener;
        bean.wordsMd = words;
        bean.type = DefaultConfig.TYPE_MD_SINGLE_CHOOSE;
        bean.defaultChosen = defaultChosen;

        bean.btn1Color = DefaultConfig.mdBtnColor;
        bean.btn2Color = DefaultConfig.mdBtnColor;
        bean.btn3Color = DefaultConfig.mdBtnColor;
        return bean;
    }

    @Override
    public ConfigBean assignMdMultiChoose(Activity context, CharSequence title, CharSequence[] words, boolean[] checkedItems, MyDialogListener btnListener) {
        ConfigBean bean = new ConfigBean();
        bean.context = context;
        bean.msg = title;
        bean.title = title;
        bean.listener = btnListener;
        bean.wordsMd = words;
        bean.checkedItems = checkedItems;
        bean.type = DefaultConfig.TYPE_MD_MULTI_CHOOSE;

        bean.btn1Color = DefaultConfig.mdBtnColor;
        bean.btn2Color = DefaultConfig.mdBtnColor;
        bean.btn3Color = DefaultConfig.mdBtnColor;
        return bean;
    }

    @Override
    public ConfigBean assignIosAlert(Context activity, CharSequence title, CharSequence msg, MyDialogListener listener) {
        ConfigBean bean = new ConfigBean();
        bean.context = activity;
        bean.msg = msg;
        bean.title = title;
        bean.listener = listener;
        bean.type = DefaultConfig.TYPE_IOS_HORIZONTAL;
        return bean;
    }

    @Override
    public ConfigBean assignIosAlertVertical(Context activity, CharSequence title, CharSequence msg, MyDialogListener listener) {
        ConfigBean bean = new ConfigBean();
        bean.context = activity;
        bean.msg = msg;
        bean.title = title;
        bean.listener = listener;
        bean.type = DefaultConfig.TYPE_IOS_VERTICAL;
        return bean;
    }

    @Override
    public ConfigBean assignIosSingleChoose(Context context, List<? extends CharSequence> words, MyItemDialogListener listener) {
        ConfigBean bean = new ConfigBean();
        bean.context = context;
        bean.itemListener = listener;
        bean.wordsIos = words;
        bean.type = DefaultConfig.TYPE_IOS_CENTER_LIST;

        return bean;
    }

    @Override
    public ConfigBean assignBottomItemDialog(Context context, List<? extends CharSequence> words, CharSequence bottomTxt, MyItemDialogListener listener) {
        ConfigBean bean = new ConfigBean();
        bean.context = context;
        bean.itemListener = listener;
        bean.wordsIos = words;
        bean.type = DefaultConfig.TYPE_IOS_BOTTOM;
        return bean;
    }

    @Override
    public ConfigBean assignNormalInput(Context context, CharSequence title, CharSequence hint1, CharSequence hint2,
                                        CharSequence firstTxt, CharSequence secondTxt, MyDialogListener listener) {
        ConfigBean bean = new ConfigBean();
        bean.context = context;
        bean.listener = listener;
        bean.title = title;
        bean.hint1 = hint1;
        bean.hint2 = hint2;
        bean.text1 = firstTxt;
        bean.text2 = secondTxt;
        bean.type = DefaultConfig.TYPE_IOS_INPUT;
        return bean;
    }

    @Override
    public ConfigBean assignCustom(Context context, View contentView, int gravity) {
        ConfigBean bean = new ConfigBean();
        bean.context = context;
        bean.customView = contentView;
        bean.gravity = gravity;
        bean.type = DefaultConfig.TYPE_CUSTOM_VIEW;
        return bean;
    }

    @Override
    public ConfigBean assignCustomBottomSheet(Activity context, View contentView) {
        ConfigBean bean = new ConfigBean();
        bean.context = context;
        bean.customView = contentView;
        bean.type = DefaultConfig.TYPE_BOTTOM_SHEET_CUSTOM;
        return bean;
    }

    @Override
    public ConfigBean assignLoading(Context context, CharSequence msg, boolean cancelable, boolean outsideTouchable) {
        ConfigBean bean = new ConfigBean();
        bean.context = context;
        bean.msg = msg;
        bean.type = DefaultConfig.TYPE_LOADING;

        bean.cancelable = cancelable;
        bean.outsideTouchable = outsideTouchable;

        return bean;
    }

    @Override
    public ConfigBean assignBottomSheetLv(Context context, CharSequence title, List datas, CharSequence bottomTxt, MyItemDialogListener listener) {
        ConfigBean bean = new ConfigBean();
        bean.context = context;
       bean.title = title;
        bean.lvDatas = datas;
        bean.bottomTxt = bottomTxt;
        bean.itemListener = listener;
        bean.type = DefaultConfig.TYPE_BOTTOM_SHEET_LIST;
        return bean;
    }

    @Override
    public ConfigBean assignBottomSheetGv(Context context, CharSequence title, List datas, CharSequence bottomTxt, int columnsNum, MyItemDialogListener listener) {
        ConfigBean bean = new ConfigBean();
        bean.context = context;
        bean.title = title;
        bean.lvDatas = datas;
        bean.bottomTxt = bottomTxt;
        bean.itemListener = listener;
        bean.gridColumns = columnsNum;
        bean.type = DefaultConfig.TYPE_BOTTOM_SHEET_GRID;
        return bean;
    }


}
