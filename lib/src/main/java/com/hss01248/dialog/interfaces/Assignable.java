package com.hss01248.dialog.interfaces;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.hss01248.dialog.config.ConfigBean;

import java.util.List;

/**
 * Created by Administrator on 2016/10/9.
 */
public interface Assignable {

    ConfigBean assignMdLoading(Context context, CharSequence msg, boolean cancleable, boolean outsideTouchable);
    
    ConfigBean assignMdAlert(Activity activity, CharSequence title, CharSequence msg, final MyDialogListener listener);

    ConfigBean assignMdSingleChoose(Activity context, CharSequence title, final int defaultChosen, final CharSequence[] words,
                                   final MyItemDialogListener listener);

    ConfigBean assignMdMultiChoose(Activity context, CharSequence title, final CharSequence[] words, final boolean[] checkedItems,
                                  final MyDialogListener btnListener);

    ConfigBean assignIosAlert(Context activity, CharSequence title, CharSequence msg,final MyDialogListener listener);

    ConfigBean assignIosAlertVertical(Context activity, CharSequence title, CharSequence msg,final MyDialogListener listener);

    ConfigBean assignIosSingleChoose(Context context, List<? extends CharSequence> words,final MyItemDialogListener listener);

    ConfigBean assignBottomItemDialog(Context context,List<? extends CharSequence> words, CharSequence bottomTxt,final MyItemDialogListener listener);


    ConfigBean assignNormalInput(Context context, CharSequence title, CharSequence hint1,CharSequence hint2,
                           CharSequence firstTxt, CharSequence secondTxt,final MyDialogListener listener);

   ConfigBean assignCustom(Context context, View contentView, int gravity);

    ConfigBean assignCustomBottomSheet(Activity context, View contentView);


    ConfigBean assignLoading(Context context, CharSequence msg, boolean cancleable, boolean outsideTouchable);


    ConfigBean assignBottomSheetLv(Context context, CharSequence title, List datas,CharSequence bottomTxt,MyItemDialogListener listener);

    ConfigBean assignBottomSheetGv(Context context, CharSequence title, List datas,CharSequence bottomTxt,int columnsNum,MyItemDialogListener listener);





    
}
