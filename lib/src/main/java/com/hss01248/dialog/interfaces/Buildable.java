package com.hss01248.dialog.interfaces;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.ColorInt;

import com.hss01248.dialog.config.ConfigBean;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/9.
 */
public interface Buildable {

    ConfigBean buildMdLoading(Context context, String msg, boolean cancleable, boolean outsideTouchable);
    
    ConfigBean buildMdAlert(Activity activity, String title, String msg,
                           String firstTxt, String secondTxt, String thirdTxt,
                           final MyDialogListener listener);

    ConfigBean buildMdSingleChoose(Activity context, String title, final int defaultChosen, final CharSequence[] words,
                                   CharSequence positiveTxt, CharSequence negtiveTxt,
                                   final MyItemDialogListener listener, final MyDialogListener btnListener);

    ConfigBean buildMdMultiChoose(Activity context, String title, final CharSequence[] words, final boolean[] checkedItems,
                                  CharSequence positiveTxt, CharSequence negtiveTxt,
                                  final MyDialogListener btnListener);

    ConfigBean buildIosAlert(Context activity, String title, String msg,
                        String firstTxt, String secondTxt, String thirdTxt,
                        final MyDialogListener listener);

    ConfigBean buildIosAlertVertical(Context activity, String title, String msg,
                                String firstTxt, String secondTxt, String thirdTxt,
                                final MyDialogListener listener);

    ConfigBean buildIosSingleChoose(Context context, List<? extends CharSequence> words,
                               final MyItemDialogListener listener);

    ConfigBean buildBottomItemDialog(Context context,
                                List<? extends CharSequence> words, String bottomTxt,
                                final MyItemDialogListener listener);

    ConfigBean buildInputBox(Context context, boolean isButtonVerticle, String title, String msg,String hint1,String hint2,
                        String firstTxt, String secondTxt, String thirdTxt,
                        final MyDialogListener listener);

    ConfigBean buildNormalInput(Context context, String title, String hint1,String hint2,
                           String firstTxt, String secondTxt,final MyDialogListener listener);


    ConfigBean setBtnColor(@ColorInt int btn1Color,@ColorInt int btn2Color,@ColorInt int btn3Color);

    ConfigBean setListItemColor(@ColorInt int lvItemTxtColor,Map<Integer,Integer> colorOfPosition);


    
}
