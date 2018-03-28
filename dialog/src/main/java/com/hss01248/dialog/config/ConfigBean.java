package com.hss01248.dialog.config;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.View;

import com.hss01248.dialog.ActivityStackManager;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.Tool;
import com.hss01248.dialog.adapter.SuperLvAdapter;
import com.hss01248.dialog.adapter.SuperLvHolder;
import com.hss01248.dialog.bottomsheet.BottomSheetBean;
import com.hss01248.dialog.building.MyDialogBuilder;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.hss01248.dialog.interfaces.MyItemDialogListener;
import com.hss01248.dialog.interfaces.Styleable;
import com.hss01248.dialog.view.DialogUtilDialogFragment;
import com.hss01248.dialog.view.DialogUtil_DialogActivity;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2016/10/9 0009.
 */
public class ConfigBean extends MyDialogBuilder implements Styleable {

    public int type;

    public Context context;
    public boolean isVertical;

    public SuperLvHolder viewHolder;
    public SuperLvHolder customContentHolder;

    public boolean isProgressHorzontal;
    public List<ChooseBean> chooseBeans;

    public View customView;

    public View contentView;

    public int gravity = Gravity.CENTER;

    public CharSequence title;
    public CharSequence msg;
    public CharSequence text1 = DefaultConfig.btnTxt1;
    public CharSequence text2 = DefaultConfig.btnTxt2;
    public CharSequence text3;

    public CharSequence hint1;
    public CharSequence hint2;

    public boolean showAsActivity = false;
    public boolean showAsFragment = false;
    public DialogUtilDialogFragment mDialogFragment;


    public boolean hasBehaviour = true;
    public boolean needSoftKeyboard;
    /**
     * 控制input类dialog中第二个框是否用*号
     */
    public boolean isInput2HideAsPassword = true;
    public BottomSheetStyle bottomSheetStyle;
    public float forceWidthPercent;
    public float forceHeightPercent;
    public float maxHeightPercent;
    public float maxWidthPercent;
    public float bottomSheetDialogMaxHeightPercent;
    public boolean isTransparentBehind;
    public MyDialogListener listener;
    public MyItemDialogListener itemListener;
    public boolean cancelable = DefaultConfig.cancelable;//默认可以点击后退键来dismiss对话框
    public boolean outsideTouchable = DefaultConfig.outsideTouchable;//默认外部半透明处点击消失
    public boolean dismissAfterResultCallback = DefaultConfig.dismissAfterResultCallback;
    public volatile Dialog dialog;
    public volatile AlertDialog alertDialog;
    public boolean dimBehind = DefaultConfig.dimBehind;
    public @DrawableRes
    int bgRes;
    public boolean useTheShadowBg = DefaultConfig.useTheShadowBg;
    public int viewHeight;
    //各类对话框特有的参数
    public CharSequence[] wordsMd;
    public int defaultChosen;//
    public boolean[] checkedItems;
    public List<? extends CharSequence> wordsIos;
    //bottom sheet
    public CharSequence bottomTxt = DefaultConfig.bottomTxt;
    //bottomsheet
    public SuperLvAdapter mAdapter;
    public List<BottomSheetBean> lvDatas;
    public int gridColumns = 4;
    public BroadcastReceiver homeKeyReceiver;
    //三个以下按钮,颜色按此顺序
    public @ColorRes
    int btn1Color = DefaultConfig.iosBtnColor;

    /*public ConfigBean setBgRes(int bgRes) {
        this.bgRes = bgRes;
        useTheShadowBg = false;
        return this;
    }*/
    public @ColorRes
    int btn2Color = DefaultConfig.mdBtnCancelColor;
    public @ColorRes
    int btn3Color = DefaultConfig.iosBtnColor;
    public @ColorRes
    int titleTxtColor = DefaultConfig.titleTxtColor;
    public @ColorRes
    int msgTxtColor = DefaultConfig.msgTxtColor;
    public @ColorRes
    int lvItemTxtColor = DefaultConfig.lvItemTxtColor;
    public Map<Integer, Integer> colorOfPosition;//listview 的item的特殊颜色:ColorRes
    public @ColorRes
    int inputTxtColor = DefaultConfig.inputTxtColor;
    //字体大小
    public int btnTxtSize = DefaultConfig.btnTxtSize;// in sp
    public int titleTxtSize = DefaultConfig.titleTxtSize;
    public int msgTxtSize = DefaultConfig.msgTxtSize;
    public int itemTxtSize = DefaultConfig.itemTxtSize;
    public int inputTxtSize = DefaultConfig.inputTxtSize;

    public ConfigBean setNeedSoftKeyboard(boolean needSoftKeyboard) {
        this.needSoftKeyboard = needSoftKeyboard;
        return this;
    }

    public ConfigBean setTitle(String title) {
        this.title = title;
        return this;
    }

    public ConfigBean setGravity(int gravity) {
        this.gravity = gravity;
        return this;
    }


    //样式

    public ConfigBean setInput2HideAsPassword(boolean input2HideAsPassword) {
        isInput2HideAsPassword = input2HideAsPassword;
        return this;
    }

    public ConfigBean setBottomSheetStyle(BottomSheetStyle bottomSheetStyle) {
        this.bottomSheetStyle = bottomSheetStyle;
        return this;
    }

    public ConfigBean setCustomContentHolder(SuperLvHolder holder) {
        this.customContentHolder = holder;
        return this;
    }

    /**
     * 只用于bottom_gridview和bottom_listview两种
     * 控制是否采用
     *
     * @param hasBehaviour true:BottomSheetDialog  false: 采用普通dialog,无Behaviour
     */
    public ConfigBean setHasBehaviour(boolean hasBehaviour) {
        this.hasBehaviour = hasBehaviour;
        return this;
    }

    /**
     * the dialog show by wrap-content in x-axi in default
     * <p>
     * you can use the forceWidthPercent:
     * to stretch it in x-axi
     * to determine the left and right margin if the measuredWidth is greater than or the same as the phone window width
     * <p>
     * do not support types:
     * case DefaultConfig.TYPE_IOS_LOADING:
     * case DefaultConfig.TYPE_PROGRESS:
     * case DefaultConfig.TYPE_BOTTOM_SHEET_CUSTOM:
     * case DefaultConfig.TYPE_BOTTOM_SHEET_GRID:
     * case DefaultConfig.TYPE_BOTTOM_SHEET_LIST:
     *
     * @param forceWidthPercent
     * @return
     */
    public ConfigBean setForceWidthPercent(@FloatRange(from = 0f, to = 1.0f) float forceWidthPercent) {
        if (forceWidthPercent > 1.0f || forceWidthPercent <= 0f) {
            return this;
        }
        this.forceWidthPercent = forceWidthPercent;
        return this;
    }

    /**
     * the dialog show by wrap-content in y-axi in default
     * <p>
     * you can use the forceWidthPercent:
     * to determine the top and bottom margin if the measuredHeight is greater than the phone window
     * to stretch it in y-axi,no recommend,because that is ugly!
     * <p>
     * do not support types:
     * case DefaultConfig.TYPE_IOS_LOADING:
     * case DefaultConfig.TYPE_PROGRESS:
     * case DefaultConfig.TYPE_BOTTOM_SHEET_CUSTOM:
     * case DefaultConfig.TYPE_BOTTOM_SHEET_GRID:
     * case DefaultConfig.TYPE_BOTTOM_SHEET_LIST:
     *
     * @param forceHeightPercent
     * @return
     */
    public ConfigBean setForceHeightPercent(@FloatRange(from = 0f, to = 1.0f) float forceHeightPercent) {
        if (forceHeightPercent > 1.0f || forceHeightPercent <= 0f) {
            return this;
        }
        this.forceHeightPercent = forceHeightPercent;
        return this;
    }

    public ConfigBean setMaxHeightPercent(@FloatRange(from = 0f, to = 1.0f) float maxHeightPercent) {
        if (maxHeightPercent > 1.0f || maxHeightPercent <= 0f) {
            return this;
        }
        this.maxHeightPercent = maxHeightPercent;
        return this;
    }

    public ConfigBean setMaxWidthPercent(@FloatRange(from = 0f, to = 1.0f) float maxWidthPercent) {
        if (maxWidthPercent > 1.0f || maxWidthPercent <= 0f) {
            return this;
        }
        this.maxWidthPercent = maxWidthPercent;
        return this;
    }


    /*可能需要拓展的功能，支持当个item字体大小和颜色设置 (比如底部弹出 ，有的item是红色字这种，有的是蓝色)
支持填充自定义布局
支持gridview 或者recylerview*/

    public ConfigBean setTransparentBehind(boolean transparentBehind) {
        isTransparentBehind = transparentBehind;
        return this;
    }

    public ConfigBean setBottomSheetDialogMaxHeightPercent(float bottomSheetDialogMaxHeightPercent) {
        this.bottomSheetDialogMaxHeightPercent = bottomSheetDialogMaxHeightPercent;
        return this;
    }

    public ConfigBean setDismissAfterResultCallback(boolean dismissAfterResultCallback) {
        this.dismissAfterResultCallback = dismissAfterResultCallback;
        return this;
    }

    public ConfigBean setBackground(int bgRes) {
        this.bgRes = bgRes;
        useTheShadowBg = false;
        return this;
    }

    /**
     * default background res: R.drawable.shadow,white background ,surround with shadow
     *
     * @param useTheWhiteAndShadowBg default is true. set false to disable the shadow background ,and set your own background in your xml
     * @return
     */
    public ConfigBean setHasShadow(boolean useTheWhiteAndShadowBg) {
        if (bgRes > 0) {
            return this;
        }
        this.useTheShadowBg = useTheWhiteAndShadowBg;
        return this;
    }

    @Override
    public ConfigBean setBtnColor(@ColorRes int btn1Color, @ColorRes int btn2Color, @ColorRes int btn3Color) {
        if (btn1Color != 0)
            this.btn1Color = btn1Color;
        if (btn2Color != 0)
            this.btn2Color = btn2Color;
        if (btn3Color != 0)
            this.btn3Color = btn3Color;
        return this;
    }

    @Override
    public ConfigBean setListItemColor(@ColorRes int lvItemTxtColor, Map<Integer, Integer> colorOfPosition) {
        if (lvItemTxtColor != 0)
            this.lvItemTxtColor = lvItemTxtColor;
        if (colorOfPosition != null && colorOfPosition.size() > 0) {
            this.colorOfPosition = colorOfPosition;
        }
        return this;
    }

    @Override
    public ConfigBean setTitleColor(@ColorRes int colorRes) {
        if (colorRes != 0) {
            this.titleTxtColor = colorRes;
        }
        return this;
    }

    @Override
    public ConfigBean setMsgColor(@ColorRes int colorRes) {
        if (colorRes != 0) {
            this.msgTxtColor = colorRes;
        }
        return this;
    }

    public ConfigBean setActivity(Activity activity) {
        this.context = activity;
        return this;
    }

    @Override
    public ConfigBean seInputColor(@ColorRes int colorRes) {
        if (colorRes > 0) {
            this.inputTxtColor = colorRes;
        }
        return this;
    }

    @Override
    public ConfigBean setTitleSize(int sizeInSp) {
        if (sizeInSp > 0 && sizeInSp < 30) {
            this.titleTxtSize = sizeInSp;
        }
        return this;
    }

    @Override
    public ConfigBean setMsgSize(int sizeInSp) {
        if (sizeInSp > 0 && sizeInSp < 30) {
            this.msgTxtSize = sizeInSp;
        }
        return this;
    }

    /**
     * 最大30sp
     *
     * @param sizeInSp
     * @return
     */
    @Override
    public ConfigBean setBtnSize(int sizeInSp) {
        if (sizeInSp > 0 && sizeInSp < 30) {
            this.btnTxtSize = sizeInSp;
        }
        return this;
    }

    @Override
    public ConfigBean setLvItemSize(int sizeInSp) {
        if (sizeInSp > 0 && sizeInSp < 30) {
            this.itemTxtSize = sizeInSp;
        }
        return this;
    }

    @Override
    public ConfigBean setInputSize(int sizeInSp) {
        if (sizeInSp > 0 && sizeInSp < 30) {
            this.inputTxtSize = sizeInSp;
        }
        return this;
    }

    private void showAsActivityNow() {
        Activity activity = ActivityStackManager.getInstance().getTopActivity();
        if (activity != null) {
            Intent intent = new Intent(activity, DialogUtil_DialogActivity.class);
            activity.startActivity(intent);
            showViewWhenActivityIsReady();

        }
    }

    private void showViewWhenActivityIsReady() {
        Tool.getMainHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Activity activity1 = ActivityStackManager.getInstance().getTopActivity(DialogUtil_DialogActivity.class);
                if (activity1 != null) {
                    //buildByType(ConfigBean.this);
                    DialogUtil_DialogActivity activity2 = (DialogUtil_DialogActivity) activity1;
                    activity2.show(ConfigBean.this);
                } else {
                    showViewWhenActivityIsReady();
                }
            }
        }, 100);
    }


    /**
     * is not usable for md style dialog
     */
    public void showAsActivity() {
        this.showAsActivity = true;
        showAsActivityNow();
    }


    /**
     * 开发中,暂时支持不全,请勿用
     *
     * @return
     */
    @Deprecated
    public DialogUtilDialogFragment showAsFragment() {
        this.showAsFragment = true;
        return showAsFragmentNow();
    }

    @Override
    public Dialog show() {
        if(Thread.currentThread().getId() == Looper.getMainLooper().getThread().getId()){
           return showInMainThread();
        }
//说明不是主线程,需要做处理
        final CountDownLatch latch = new CountDownLatch(1);
        final Dialog[] dialog = new Dialog[1];

        StyledDialog.getMainHandler().post(new Runnable() {
            @Override
            public void run() {
                dialog[0] =   showInMainThread();
                latch.countDown();
            }
        });
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return dialog[0];


    }

    private Dialog showInMainThread() {
        Tool.fixContext(this);
        if (listener == null) {
            Log.w("dialogutil", "dialog listener is null!");
            listener = new MyDialogListener() {
                @Override
                public void onFirst() {

                }

                @Override
                public void onSecond() {

                }
            };
        }
        buildByType(this);
        if (showAsActivity) {
            showAsActivityNow();
            return null;
        }

        if (showAsFragment && context instanceof FragmentActivity) {
            showAsFragment();
            return null;
        }
        if (dialog != null && !dialog.isShowing()) {
            Tool.showDialog(dialog, this);

            return dialog;
        } else if (alertDialog != null && !alertDialog.isShowing()) {
            Tool.showDialog(alertDialog, this);
            return alertDialog;
        }
        return null;
    }

    private DialogUtilDialogFragment showAsFragmentNow() {

        if (context instanceof FragmentActivity) {
            FragmentActivity activity = (FragmentActivity) context;
            DialogUtilDialogFragment fragment = new DialogUtilDialogFragment();
            Dialog dialog = this.dialog;
            if (dialog == null) {
                dialog = alertDialog;
            }
            if (dialog == null) {
                return null;
            }
            fragment.setConfigbean(this);
            fragment.setRootView(dialog.getWindow().getDecorView());
            fragment.show(activity.getSupportFragmentManager(), this.toString());
            mDialogFragment = fragment;
            return fragment;
        }
        return null;
    }

    @Override
    public ConfigBean setBtnText(CharSequence btn1Text, @Nullable CharSequence btn2Text, @Nullable CharSequence btn3Text) {
        this.text1 = btn1Text;
        this.text2 = btn2Text;
        this.text3 = btn3Text;

        return this;
    }

    @Override
    public ConfigBean setBtnText(CharSequence positiveTxt, @Nullable CharSequence negtiveText) {
        return setBtnText(positiveTxt, negtiveText, "");
    }

    @Override
    public ConfigBean setBtnText(CharSequence positiveTxt) {
        return setBtnText(positiveTxt, "", "");
    }

    @Override
    public ConfigBean setListener(MyDialogListener listener) {
        if (listener != null) {
            this.listener = listener;
        }
        return this;
    }

    @Override
    public ConfigBean setCancelable(boolean cancelable, boolean outsideCancelable) {
        this.cancelable = cancelable;
        this.outsideTouchable = outsideCancelable;
        return this;
    }


}
