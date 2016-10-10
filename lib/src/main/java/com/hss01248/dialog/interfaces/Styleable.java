package com.hss01248.dialog.interfaces;

import android.app.Dialog;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;

import com.hss01248.dialog.config.ConfigBean;

import java.util.Map;

/**
 * Created by Administrator on 2016/10/10.
 */
public interface Styleable {

    ConfigBean setBtnColor(@ColorRes int btn1Color, @ColorRes int btn2Color, @ColorRes int btn3Color);

    ConfigBean setListItemColor(@ColorRes int lvItemTxtColor,Map<Integer,Integer> colorOfPosition);

    ConfigBean setTitleColor(@ColorRes int colorRes);
    ConfigBean setMsgColor(@ColorRes int colorRes);
    ConfigBean seInputColor(@ColorRes int colorRes);



    ConfigBean setTitleSize(int sizeInSp);
    ConfigBean setMsgSize(int sizeInSp);
    ConfigBean setBtnSize(int sizeInSp);
    ConfigBean setLvItemSize(int sizeInSp);
    ConfigBean setInputSize(int sizeInSp);

    Dialog show();

    //内容设置
    ConfigBean setBtnText(CharSequence btn1Text, @Nullable CharSequence btn2Text, @Nullable CharSequence btn3Text);

    ConfigBean setBtnText(CharSequence positiveTxt, @Nullable CharSequence negtiveText);

    ConfigBean setListener(MyDialogListener listener);



    ConfigBean setCancelable(boolean cancelable,boolean outsideCancelable);







}
