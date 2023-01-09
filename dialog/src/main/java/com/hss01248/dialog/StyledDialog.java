package com.hss01248.dialog;


import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.hss01248.dialog.adapter.SuperLvHolder;
import com.hss01248.dialog.config.ConfigBean;
import com.hss01248.dialog.config.DefaultConfig;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.hss01248.dialog.interfaces.MyItemDialogListener;
import com.hss01248.dialog.ios.IosAlertDialogHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/5/4 0004.
 */
public class StyledDialog {

    //android.support.v7.app.

    public static Context context;

    private static int singleChosen;



    //private static DialogInterface loadingDialog;//缓存加载中的dialog,便于以后可以不需要对象就让它消失
    //private static TextView tv_msg;
    private static long startTime;


    private static boolean isMiUi8 = false;//miui8用非activity的Context时,无法以TYPE_TOAST的形式弹出对话框.没有好的解决办法.....

    public static Handler getMainHandler() {
        if (mainHandler == null) {
            mainHandler = new Handler(Looper.getMainLooper());
        }
        return mainHandler;
    }

    private static Handler mainHandler;

    public static void init(Context context) {
        StyledDialog.context = context;
       /* String userAgent = System.getProperty("http.agent");
        if((!TextUtils.isEmpty(userAgent)) && userAgent.contains("MIUI") && userAgent.contains("V8")){
            isMiUi8 = true;
        }*/
        mainHandler = new Handler(Looper.getMainLooper());
        DefaultConfig.initBtnTxt(context);

    }




    /**
     * 一键让loading消失.
     */
    public static void dismissLoading(Activity activity) {
        DialogsMaintainer.dismissLoading(activity);
    }


    public static void dismiss(Dialog... dialogs) {
        if (dialogs != null && dialogs.length > 0) {
            for (DialogInterface dialog : dialogs) {
                try {
                    dialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }


    public static ConfigBean buildProgress(CharSequence msg, boolean isHorizontal) {
        return DialogAssigner.getInstance().assignProgress(null, msg, isHorizontal);
    }

    public static void updateLoadingMsg(final String msg, final Dialog dialog) {
        if (dialog == null) {
            return;
        }
        if (!dialog.isShowing()) {
            return;
        }
        getMainHandler().post(new Runnable() {
            @Override
            public void run() {
                try {
                    View view = dialog.getWindow().getDecorView().findViewById(R.id.loading_msg);
                    if (view instanceof TextView) {
                        TextView textView = (TextView) view;
                        textView.setText(msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });


    }

    /**
     * 可以在任何线程调用
     *
     * @param dialog       传入show方法返回的对象
     * @param progress
     * @param max
     * @param msg          如果是转圈圈,会将msg变成msg:78%的形式.如果是水平,msg不起作用
     * @param isHorizontal 是水平线状,还是转圈圈
     */
    public static void updateProgress(final Dialog dialog, final int progress, final int max, final CharSequence msg, final boolean isHorizontal) {

        getMainHandler().post(new Runnable() {
            @Override
            public void run() {
                if (dialog instanceof ProgressDialog && dialog.isShowing()) {
                    ProgressDialog progressDialog = (ProgressDialog) dialog;
                    if (isHorizontal) {
                        progressDialog.setProgress((int) progress);
                        progressDialog.setMax((int) max);
                    } else {
                        String pmsg = new StringBuilder(msg).append(":").append(progress * 100 / max).append("%").toString();
                        progressDialog.setMessage(pmsg);
                    }

                }
            }
        });


    }

    public static ConfigBean buildLoading(CharSequence msg) {
        return DialogAssigner.getInstance().assignLoading(null, msg, true, false);
    }

    public static ConfigBean buildLoading() {
        return DialogAssigner.getInstance().assignLoading(null, DefaultConfig.loadingTxt, true, false);
    }

    public static ConfigBean buildMdLoading() {
        return DialogAssigner.getInstance().assignMdLoading(null, DefaultConfig.loadingTxt, true, false);
    }

    public static ConfigBean buildMdLoading(CharSequence msg) {
        return DialogAssigner.getInstance().assignMdLoading(null, msg, true, false);
    }


    public static ConfigBean buildMdAlert(CharSequence title, CharSequence msg, MyDialogListener listener) {
        return DialogAssigner.getInstance().assignMdAlert(null, title, msg, listener);
    }


    public static ConfigBean buildMdSingleChoose(CharSequence title, int defaultChosen, CharSequence[] words, MyItemDialogListener listener) {
        return DialogAssigner.getInstance().assignMdSingleChoose(null, title, defaultChosen, words, listener);
    }

    @Deprecated
    public static ConfigBean buildMdMultiChoose(CharSequence title, CharSequence[] words, boolean[] checkedItems, MyDialogListener btnListener) {
        return DialogAssigner.getInstance().assignMdMultiChoose(null, title, words, checkedItems, btnListener);
    }

    public static ConfigBean buildMdMultiChoose(CharSequence title, CharSequence[] words, List<Integer> selectedIndexs, MyDialogListener btnListener) {
        return DialogAssigner.getInstance().assignMdMultiChoose(null, title, words, selectedIndexs, btnListener);
    }


    public static ConfigBean buildIosAlert(CharSequence title, CharSequence msg, MyDialogListener listener) {
        return DialogAssigner.getInstance().assignIosAlert(null, title, msg, listener).setBtnText("ok");
    }


    public static ConfigBean buildIosAlertVertical(CharSequence title, CharSequence msg, MyDialogListener listener) {
        return DialogAssigner.getInstance().assignIosAlertVertical(null, title, msg, listener);
    }


    public static ConfigBean buildIosSingleChoose(List<? extends CharSequence> words, MyItemDialogListener listener) {
        return DialogAssigner.getInstance().assignIosSingleChoose(null, words, listener);
    }


    public static ConfigBean buildBottomItemDialog(List<? extends CharSequence> words, CharSequence bottomTxt, MyItemDialogListener listener) {
        return DialogAssigner.getInstance().assignBottomItemDialog(null, words, bottomTxt, listener);
    }


    public static ConfigBean buildNormalInput(CharSequence title, CharSequence hint1, CharSequence hint2,
                                              CharSequence inputText1, CharSequence inputText2, MyDialogListener listener) {
        return DialogAssigner.getInstance().assignNormalInput(null, title, hint1, hint2, inputText1, inputText2, listener);
    }

    public static ConfigBean buildMdInput(CharSequence title, CharSequence hint1, CharSequence hint2,
                                          CharSequence inputText1, CharSequence inputText2, MyDialogListener listener) {
        return DialogAssigner.getInstance().buildMdInput(title, hint1, hint2, inputText1, inputText2, listener);

    }


    /**
     * 将view塞到meterial alert dialog 中, 可以沿用其title和button的样式和更改方法,该view作为content显示
     *
     * @param customViewHolder
     * @return
     */
    public static ConfigBean buildCustomInMd(SuperLvHolder customViewHolder, MyDialogListener listener) {
        return DialogAssigner.getInstance().buildCustomInMd(customViewHolder, listener);
    }


    /**
     * 将view塞到ios样式的 dialog 中, 可以沿用其title和button的样式和更改方法,该view作为content显示
     *
     * @param customViewHolder
     * @return
     */
    public static ConfigBean buildCustomInIos(SuperLvHolder customViewHolder, MyDialogListener listener) {
        return DialogAssigner.getInstance().buildCustomInIos(customViewHolder, listener);
    }

    /**
     * use buildCustom( SuperLvHolder viewHolder)  instead
     * 建议使用SuperLvHolder 对自定义view进行包装,如果有输入框,则实现控制软键盘的显示和隐藏方法,框架会在合适的时间帮你显示和隐藏
     *
     * @param contentView
     * @param gravity
     * @return
     */
    @Deprecated
    public static ConfigBean buildCustom(View contentView, int gravity) {
        return DialogAssigner.getInstance().assignCustom(null, contentView, gravity);
    }

    /**
     * 带x的view,x的位置可以配置
     * @param contentView
     * @return
     */
    public static ConfigBean buildCustomAsAdStyle(View contentView,  int xGravity) {
        ConfigBean configBean =  DialogAssigner.getInstance().assignCustom(null, contentView, Gravity.CENTER);
        configBean.asAdXStyle = true;
        configBean.xGravity = xGravity;
        configBean.useTheShadowBg = false;
        return configBean;
    }

    public static ConfigBean buildAlertAsAdStyle(String title,String msg,  int xGravity) {
        IosAlertDialogHolder dialogHolder = new IosAlertDialogHolder(ActivityStackManager.getInstance().getTopActivity());
        dialogHolder.showOnlyTitleAndMsg(title,msg);
        ConfigBean configBean =  DialogAssigner.getInstance().assignCustom(null, dialogHolder.rootView, Gravity.CENTER);
        configBean.hint1 = "";
        configBean.hint1 = "";
        configBean.text1 = "";
        configBean.text2 = "";
        configBean.text3 = "";
       // dialogHolder.assingDatasAndEvents(ActivityStackManager.getInstance().getTopActivity(),configBean);
        configBean.asAdXStyle = true;
        configBean.xGravity = xGravity;
        configBean.useTheShadowBg = false;
        return configBean;
    }


    public static ConfigBean buildCustom(SuperLvHolder viewHolder) {
        return DialogAssigner.getInstance().assignCustom(null, viewHolder);
    }


    public static ConfigBean buildCustomBottomSheet(View contentView) {
        return DialogAssigner.getInstance().assignCustomBottomSheet(null, contentView);
    }

    public static ConfigBean buildBottomSheetLv(CharSequence title, List datas, CharSequence bottomTxt, MyItemDialogListener listener) {
        return DialogAssigner.getInstance().assignBottomSheetLv(null, title, datas, bottomTxt, listener);
    }

    public static ConfigBean buildBottomSheetGv(CharSequence title, List datas, CharSequence bottomTxt, int columnsNum, MyItemDialogListener listener) {
        return DialogAssigner.getInstance().assignBottomSheetGv(null, title, datas, bottomTxt, columnsNum, listener);
    }

}
