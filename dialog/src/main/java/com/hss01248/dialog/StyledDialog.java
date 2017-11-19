package com.hss01248.dialog;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatDialog;
import android.view.View;
import android.widget.TextView;

import com.hss01248.dialog.adapter.SuperLvHolder;
import com.hss01248.dialog.config.ConfigBean;
import com.hss01248.dialog.config.DefaultConfig;
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

    public static DialogInterface getLoadingDialog() {
        return loadingDialog;
    }

    private static DialogInterface loadingDialog;//缓存加载中的dialog,便于以后可以不需要对象就让它消失
    private static TextView tv_msg;
    private static long startTime;

    /**
     * 内部使用
     * @param tv_msg
     */
    public static void setTv_msg(TextView tv_msg) {
        StyledDialog.tv_msg = tv_msg;
    }

    private static boolean isMiUi8 = false;//miui8用非activity的Context时,无法以TYPE_TOAST的形式弹出对话框.没有好的解决办法.....

    public static Handler getMainHandler() {
        if(mainHandler==null){
            mainHandler = new Handler(Looper.getMainLooper());
        }
        return mainHandler;
    }

    private static Handler mainHandler;

    public static void init(Context context){
        StyledDialog.context = context;
       /* String userAgent = System.getProperty("http.agent");
        if((!TextUtils.isEmpty(userAgent)) && userAgent.contains("MIUI") && userAgent.contains("V8")){
            isMiUi8 = true;
        }*/
       mainHandler = new Handler(Looper.getMainLooper());
        DefaultConfig.initBtnTxt(context);

    }

    /**
     * 内部使用
     *
     */
     public static void setLoadingObj( DialogInterface  loading){

        dismiss(loadingDialog);
        loadingDialog = loading;
         startTime = System.currentTimeMillis();
         if(loading==null){
             tv_msg=null;
         }
    }




    /**
     * 一键让loading消失.
     */
    public static void dismissLoading(){
        if (loadingDialog != null ){
            tv_msg=null;
            long timePassed = System.currentTimeMillis() - startTime;
            if(timePassed >= 500){//500ms
                dismiss(loadingDialog);
                loadingDialog = null;
            }else {
                getMainHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dismiss(loadingDialog);
                        loadingDialog = null;
                    }
                },500 -timePassed);
            }

        }
    }

   

    public static void dismiss(DialogInterface... dialogs){
        if (dialogs != null && dialogs.length>0){
           for (DialogInterface dialog : dialogs){
               if (dialog instanceof Dialog){
                   Dialog dialog1 = (Dialog) dialog;
                   if (dialog1.isShowing()){
                       try {
                           dialog1.dismiss();
                       }catch (Exception e){
                           e.printStackTrace();
                       }

                   }
               }else if (dialog instanceof AppCompatDialog){
                   AppCompatDialog dialog2 = (AppCompatDialog) dialog;
                   if (dialog2.isShowing()){
                       try {
                           dialog2.dismiss();
                       }catch (Exception e){
                            e.printStackTrace();
                       }

                   }
               }
           }
            
        }
    }



    public static ConfigBean buildProgress( CharSequence msg,boolean isHorizontal) {
        return DialogAssigner.getInstance().assignProgress(null,msg,isHorizontal);
    }

    public static void updateLoadingMsg(final String msg){
        if(tv_msg!=null){//&& tv_msg.getVisibility() == View.VISIBLE
            getMainHandler().post(new Runnable() {
                @Override
                public void run() {
                    try {
                        tv_msg.setText(msg);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            });

        }
    }

    /**
     *  可以在任何线程调用
     * @param dialog 传入show方法返回的对象
     * @param progress
     * @param max
     * @param msg 如果是转圈圈,会将msg变成msg:78%的形式.如果是水平,msg不起作用
     * @param isHorizontal 是水平线状,还是转圈圈
     */
    public static void updateProgress(final Dialog dialog, final int progress, final int max, final CharSequence msg, final boolean isHorizontal){

        getMainHandler().post(new Runnable() {
            @Override
            public void run() {
                if(dialog instanceof ProgressDialog && dialog.isShowing()){
                    ProgressDialog progressDialog = (ProgressDialog) dialog;
                    if(isHorizontal){
                        progressDialog.setProgress((int) progress);
                        progressDialog.setMax((int) max);
                    }else {
                        String pmsg = new StringBuilder(msg).append(":").append(progress*100/max).append("%").toString();
                        progressDialog.setMessage(pmsg);
                    }

                }
            }
        });


    }

    public static ConfigBean buildLoading( CharSequence msg) {
        return DialogAssigner.getInstance().assignLoading(null,msg,true,false);
    }
    public static ConfigBean buildLoading( ) {
        return DialogAssigner.getInstance().assignLoading(null,DefaultConfig.loadingTxt,true,false);
    }

    public static ConfigBean buildMdLoading( ) {
        return DialogAssigner.getInstance().assignMdLoading(null,DefaultConfig.loadingTxt,true,false);
    }
    
    public static ConfigBean buildMdLoading( CharSequence msg) {
        return DialogAssigner.getInstance().assignMdLoading(null,msg,true,false);
    }


    public static ConfigBean buildMdAlert( CharSequence title, CharSequence msg, MyDialogListener listener) {
        return DialogAssigner.getInstance().assignMdAlert(null,title,msg,listener);
    }

    
    public static ConfigBean buildMdSingleChoose( CharSequence title, int defaultChosen, CharSequence[] words, MyItemDialogListener listener) {
        return DialogAssigner.getInstance().assignMdSingleChoose(null,title,defaultChosen,words,listener);
    }

    @Deprecated
    public static ConfigBean buildMdMultiChoose( CharSequence title, CharSequence[] words, boolean[] checkedItems, MyDialogListener btnListener) {
        return DialogAssigner.getInstance().assignMdMultiChoose(null,title,words,checkedItems,btnListener);
    }

    public static ConfigBean buildMdMultiChoose( CharSequence title, CharSequence[] words, List<Integer> selectedIndexs, MyDialogListener btnListener) {
        return DialogAssigner.getInstance().assignMdMultiChoose(null,title,words,selectedIndexs,btnListener);
    }

   
    public static ConfigBean buildIosAlert( CharSequence title, CharSequence msg, MyDialogListener listener) {
        return DialogAssigner.getInstance().assignIosAlert(null,title,msg,listener);
    }

   
    public static ConfigBean buildIosAlertVertical( CharSequence title, CharSequence msg, MyDialogListener listener) {
        return DialogAssigner.getInstance().assignIosAlertVertical(null,title,msg,listener);
    }

    
    public static ConfigBean buildIosSingleChoose( List<? extends CharSequence> words, MyItemDialogListener listener) {
        return DialogAssigner.getInstance().assignIosSingleChoose(null,words,listener);
    }

    
    public static ConfigBean buildBottomItemDialog( List<? extends CharSequence> words, CharSequence bottomTxt, MyItemDialogListener listener) {
        return DialogAssigner.getInstance().assignBottomItemDialog(null,words,bottomTxt,listener);
    }

   
    public static ConfigBean buildNormalInput( CharSequence title, CharSequence hint1, CharSequence hint2,
                                               CharSequence firstTxt, CharSequence secondTxt, MyDialogListener listener) {
        return DialogAssigner.getInstance().assignNormalInput(null,title,hint1,hint2,firstTxt,secondTxt,listener);
    }

    public static ConfigBean buildMdInput(CharSequence title, CharSequence hint1, CharSequence hint2,
                                          CharSequence firstTxt, CharSequence secondTxt, MyDialogListener listener){
        return DialogAssigner.getInstance().buildMdInput(title,hint1,hint2,firstTxt,secondTxt,listener);

    }


    /**
     * 将view塞到meterial alert dialog 中, 可以沿用其title和button的样式和更改方法,该view作为content显示
     * @param customViewHolder
     * @return
     */
    public static ConfigBean buildCustomInMd(SuperLvHolder customViewHolder,MyDialogListener listener){
        return DialogAssigner.getInstance().buildCustomInMd(customViewHolder,listener);
    }


    /**
     * 将view塞到ios样式的 dialog 中, 可以沿用其title和button的样式和更改方法,该view作为content显示
     * @param customViewHolder
     * @return
     */
    public static ConfigBean buildCustomInIos(SuperLvHolder customViewHolder,MyDialogListener listener){
      return   DialogAssigner.getInstance().buildCustomInIos(customViewHolder,listener);
    }

    /**
     * use buildCustom( SuperLvHolder viewHolder)  instead
     * @param contentView
     * @param gravity
     * @return
     */
    @Deprecated
    public static ConfigBean buildCustom( View contentView, int gravity) {
        return DialogAssigner.getInstance().assignCustom(null,contentView,gravity);
    }


    public static ConfigBean buildCustom( SuperLvHolder viewHolder) {
        return DialogAssigner.getInstance().assignCustom(null,viewHolder);
    }



    public static ConfigBean buildCustomBottomSheet( View contentView){
        return DialogAssigner.getInstance().assignCustomBottomSheet(null,contentView);
    }

    public static ConfigBean buildBottomSheetLv(CharSequence title, List datas, CharSequence bottomTxt, MyItemDialogListener listener){
        return DialogAssigner.getInstance().assignBottomSheetLv(null,title,datas,bottomTxt,listener);
    }

    public static ConfigBean buildBottomSheetGv( CharSequence title, List datas, CharSequence bottomTxt,int columnsNum ,MyItemDialogListener listener){
        return DialogAssigner.getInstance().assignBottomSheetGv(null,title,datas,bottomTxt,columnsNum,listener);
    }

}
