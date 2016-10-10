package com.hss01248.dialog.interfaces;

import android.app.Activity;
import android.content.Context;

import com.hss01248.dialog.config.ConfigBean;

import java.util.List;

/**
 * Created by Administrator on 2016/10/9.
 */
public interface Assignable {

    ConfigBean assignMdLoading(Context context, CharSequence msg, boolean cancleable, boolean outsideTouchable);
    
    ConfigBean assignMdAlert(Activity activity, CharSequence title, CharSequence msg, final MyDialogListener listener);

    ConfigBean buildMdSingleChoose(Activity context, CharSequence title, final int defaultChosen, final CharSequence[] words,
                                   final MyItemDialogListener listener);

    ConfigBean buildMdMultiChoose(Activity context, CharSequence title, final CharSequence[] words, final boolean[] checkedItems,
                                  final MyDialogListener btnListener);

    ConfigBean buildIosAlert(Context activity, CharSequence title, CharSequence msg,final MyDialogListener listener);

    ConfigBean buildIosAlertVertical(Context activity, CharSequence title, CharSequence msg,final MyDialogListener listener);

    ConfigBean buildIosSingleChoose(Context context, List<? extends CharSequence> words,final MyItemDialogListener listener);

    ConfigBean buildBottomItemDialog(Context context,List<? extends CharSequence> words, CharSequence bottomTxt,final MyItemDialogListener listener);


    ConfigBean buildNormalInput(Context context, CharSequence title, CharSequence hint1,CharSequence hint2,
                           CharSequence firstTxt, CharSequence secondTxt,final MyDialogListener listener);







    
}
