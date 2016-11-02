package com.hss01248.dialog;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatDialog;
import android.view.View;

import com.hss01248.dialog.config.ConfigBean;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.hss01248.dialog.interfaces.MyItemDialogListener;

import java.util.List;

/**
 * Created by Administrator on 2016/5/4 0004.
 */
public class StyledDialog  {

    //android.support.v7.app.

    public static Context context;

    private static int singleChosen;

    private static DialogInterface loadingDialog;//缓存加载中的dialog,便于以后可以不需要对象就让它消失


    public static void init(Context context){
        StyledDialog.context = context;

    }

    public static void setLoadingObj(DialogInterface  loading){
        loadingDialog = loading;
    }




    /**
     * 一键让loading消失.
     */
    public static void dismissLoading(){
        if (loadingDialog != null ){
            dismiss(loadingDialog);
            loadingDialog = null;
        }
    }

   

    public static void dismiss(DialogInterface... dialogs){
        if (dialogs != null && dialogs.length>0){
           for (DialogInterface dialog : dialogs){
               if (dialog instanceof Dialog){
                   Dialog dialog1 = (Dialog) dialog;
                   if (dialog1.isShowing()){
                       dialog1.dismiss();
                   }
               }else if (dialog instanceof AppCompatDialog){
                   AppCompatDialog dialog2 = (AppCompatDialog) dialog;
                   if (dialog2.isShowing()){
                       dialog2.dismiss();
                   }
               }
           }
            
        }
    }

    public static ConfigBean buildLoading(Context context, CharSequence msg, boolean cancleable, boolean outsideTouchable) {
        return DialogAssigner.getInstance().assignLoading(context,msg,cancleable,outsideTouchable);
    }
    
    public static ConfigBean buildMdLoading(Context context, CharSequence msg, boolean cancleable, boolean outsideTouchable) {
        return DialogAssigner.getInstance().assignMdLoading(context,msg,cancleable,outsideTouchable);
    }

     
    public static ConfigBean buildMdAlert(Activity activity, CharSequence title, CharSequence msg, MyDialogListener listener) {
        return DialogAssigner.getInstance().assignMdAlert(activity,title,msg,listener);
    }

    
    public static ConfigBean buildMdSingleChoose(Activity context, CharSequence title, int defaultChosen, CharSequence[] words, MyItemDialogListener listener) {
        return DialogAssigner.getInstance().assignMdSingleChoose(context,title,defaultChosen,words,listener);
    }

    
    public static ConfigBean buildMdMultiChoose(Activity context, CharSequence title, CharSequence[] words, boolean[] checkedItems, MyDialogListener btnListener) {
        return DialogAssigner.getInstance().assignMdMultiChoose(context,title,words,checkedItems,btnListener);
    }

   
    public static ConfigBean buildIosAlert(Context activity, CharSequence title, CharSequence msg, MyDialogListener listener) {
        return DialogAssigner.getInstance().assignIosAlert(activity,title,msg,listener);
    }

   
    public static ConfigBean buildIosAlertVertical(Context activity, CharSequence title, CharSequence msg, MyDialogListener listener) {
        return DialogAssigner.getInstance().assignIosAlertVertical(activity,title,msg,listener);
    }

    
    public static ConfigBean buildIosSingleChoose(Context context, List<? extends CharSequence> words, MyItemDialogListener listener) {
        return DialogAssigner.getInstance().assignIosSingleChoose(context,words,listener);
    }

    
    public static ConfigBean buildBottomItemDialog(Context context, List<? extends CharSequence> words, CharSequence bottomTxt, MyItemDialogListener listener) {
        return DialogAssigner.getInstance().assignBottomItemDialog(context,words,bottomTxt,listener);
    }

   
    public static ConfigBean buildNormalInput(Context context, CharSequence title, CharSequence hint1, CharSequence hint2, CharSequence firstTxt, CharSequence secondTxt, MyDialogListener listener) {
        return DialogAssigner.getInstance().assignNormalInput(context,title,hint1,hint2,firstTxt,secondTxt,listener);
    }

    public static ConfigBean buildCustom(Context context, View contentView, int gravity) {
        return DialogAssigner.getInstance().assignCustom(context,contentView,gravity);
    }

    public static ConfigBean buildCustomBottomSheet(Activity context, View contentView){
        return DialogAssigner.getInstance().assignCustomBottomSheet(context,contentView);
    }

    public static ConfigBean buildBottomSheetLv(Context context, CharSequence title, List datas, CharSequence bottomTxt, MyItemDialogListener listener){
        return DialogAssigner.getInstance().assignBottomSheetLv(context,title,datas,bottomTxt,listener);
    }

    public static ConfigBean buildBottomSheetGv(Context context, CharSequence title, List datas, CharSequence bottomTxt,int columnsNum ,MyItemDialogListener listener){
        return DialogAssigner.getInstance().assignBottomSheetGv(context,title,datas,bottomTxt,columnsNum,listener);
    }

}
