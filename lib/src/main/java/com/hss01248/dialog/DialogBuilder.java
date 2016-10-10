package com.hss01248.dialog;

import android.app.Activity;
import android.content.Context;

import com.hss01248.dialog.config.ConfigBean;
import com.hss01248.dialog.config.DefaultConfig;
import com.hss01248.dialog.interfaces.Assignable;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.hss01248.dialog.interfaces.MyItemDialogListener;

import java.util.List;

/**
 * Created by Administrator on 2016/10/9 0009.
 */
public class DialogBuilder implements Assignable {

    private DialogBuilder instance;

    private DialogBuilder(){

    }

    public DialogBuilder getInstance(){
        if (instance == null){
            instance = new DialogBuilder();
        }
        return instance;
    }


    @Override
    public ConfigBean assignMdLoading(Context context, CharSequence msg, boolean cancleable, boolean outsideTouchable) {
        ConfigBean bean = new ConfigBean();
        bean.context = context;
        bean.msg = msg;
        bean.type = DefaultConfig.TYPE_MD_LOADING;

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
        return bean;
    }

    @Override
    public ConfigBean buildMdSingleChoose(Activity context, CharSequence title, int defaultChosen, CharSequence[] words, MyItemDialogListener listener) {
        ConfigBean bean = new ConfigBean();
        bean.context = context;
        bean.title = title;
        bean.itemListener = listener;
        bean.wordsMd = words;
        bean.type = DefaultConfig.TYPE_MD_SINGLE_CHOOSE;
        bean.defaultChosen = defaultChosen;
        return bean;
    }

    @Override
    public ConfigBean buildMdMultiChoose(Activity context, CharSequence title, CharSequence[] words, boolean[] checkedItems, MyDialogListener btnListener) {
        ConfigBean bean = new ConfigBean();
        bean.context = context;
        bean.msg = title;
        bean.title = title;
        bean.listener = btnListener;
        bean.wordsMd = words;
        bean.checkedItems = checkedItems;
        bean.type = DefaultConfig.TYPE_MD_MULTI_CHOOSE;
        return bean;
    }

    @Override
    public ConfigBean buildIosAlert(Context activity, CharSequence title, CharSequence msg, MyDialogListener listener) {
        ConfigBean bean = new ConfigBean();
        bean.context = activity;
        bean.msg = msg;
        bean.title = title;
        bean.listener = listener;
        bean.type = DefaultConfig.TYPE_IOS_HORIZONTAL;
        return bean;
    }

    @Override
    public ConfigBean buildIosAlertVertical(Context activity, CharSequence title, CharSequence msg, MyDialogListener listener) {
        ConfigBean bean = new ConfigBean();
        bean.context = activity;
        bean.msg = msg;
        bean.title = title;
        bean.listener = listener;
        bean.type = DefaultConfig.TYPE_IOS_VERTICAL;
        return bean;
    }

    @Override
    public ConfigBean buildIosSingleChoose(Context context, List<? extends CharSequence> words, MyItemDialogListener listener) {
        ConfigBean bean = new ConfigBean();
        bean.context = context;
        bean.itemListener = listener;
        bean.wordsIos = words;
        bean.type = DefaultConfig.TYPE_IOS_CENTER_LIST;

        return bean;
    }

    @Override
    public ConfigBean buildBottomItemDialog(Context context, List<? extends CharSequence> words, CharSequence bottomTxt, MyItemDialogListener listener) {
        ConfigBean bean = new ConfigBean();
        bean.context = context;
        bean.itemListener = listener;
        bean.wordsIos = words;
        bean.type = DefaultConfig.TYPE_IOS_BOTTOM;
        return bean;
    }

    @Override
    public ConfigBean buildNormalInput(Context context, CharSequence title, CharSequence hint1, CharSequence hint2, CharSequence firstTxt, CharSequence secondTxt, MyDialogListener listener) {
        ConfigBean bean = new ConfigBean();
        bean.context = context;
        bean.listener = listener;
        bean.title = title;
        bean.hint1 = hint1;
        bean.hint2 = hint2;
        bean.text1 = firstTxt;
        bean.text2 = secondTxt;
        bean.type = DefaultConfig.TYPE_IOS_BOTTOM;
        return bean;
    }


}
