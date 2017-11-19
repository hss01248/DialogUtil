package com.hss01248.dialog.config;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;

import com.hss01248.dialog.ActivityStackManager;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.Tool;
import com.hss01248.dialog.adapter.SuperLvAdapter;
import com.hss01248.dialog.adapter.SuperLvHolder;
import com.hss01248.dialog.bottomsheet.BottomSheetBean;
import com.hss01248.dialog.interfaces.MyDialogBuilder;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.hss01248.dialog.interfaces.MyItemDialogListener;
import com.hss01248.dialog.interfaces.Styleable;
import com.hss01248.dialog.view.DialogUtil_DialogActivity;

import java.util.List;
import java.util.Map;

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

    public View customView;

    public int gravity = Gravity.CENTER;

    public CharSequence title;
    public CharSequence msg;
    public CharSequence text1 = DefaultConfig.btnTxt1;
    public CharSequence text2 = DefaultConfig.btnTxt2;
    public CharSequence text3;

    public CharSequence hint1;
    public CharSequence hint2;

    public boolean showAsActivity ;




    public boolean hasBehaviour  = false;

    public ConfigBean setNeedSoftKeyboard(boolean needSoftKeyboard) {
        this.needSoftKeyboard = needSoftKeyboard;
        return this;
    }

    public boolean needSoftKeyboard;

    public ConfigBean setInput2HideAsPassword(boolean input2HideAsPassword) {
        isInput2HideAsPassword = input2HideAsPassword;
        return this;
    }

    /**
     * 控制input类dialog中第二个框是否用*号
     */
    public boolean isInput2HideAsPassword = true;
    public BottomSheetStyle bottomSheetStyle;

    public ConfigBean setBottomSheetStyle(BottomSheetStyle bottomSheetStyle){
        this.bottomSheetStyle = bottomSheetStyle;
        return this;
    }


    public ConfigBean setCustomContentHolder(SuperLvHolder holder){
        this.customContentHolder = holder;
        return this;
    }


    /**
     * 只用于bottom_gridview和bottom_listview两种
     * 控制是否采用
     * @param hasBehaviour true:BottomSheetDialog  false: 采用普通dialog,无Behaviour
     */
    public ConfigBean setHasBehaviour(boolean hasBehaviour){
        this.hasBehaviour = hasBehaviour;
        return this;
    }






    /**
     * the dialog show by wrap-content in x-axi in default
     *
     * you can use the widthPercent:
     *  to stretch it in x-axi
     *  to determine the left and right margin if the measuredWidth is greater than or the same as the phone window width
     *
     * do not support types:
     *   case DefaultConfig.TYPE_IOS_LOADING:
         case DefaultConfig.TYPE_PROGRESS:
         case DefaultConfig.TYPE_BOTTOM_SHEET_CUSTOM:
         case DefaultConfig.TYPE_BOTTOM_SHEET_GRID:
         case DefaultConfig.TYPE_BOTTOM_SHEET_LIST:
     * @param widthPercent
     * @return
     */
    public ConfigBean setWidthPercent(@FloatRange(from = 0f,to = 1.0f)float widthPercent) {
        if(widthPercent>1.0f || widthPercent<=0f){
            return this;
        }
        this.widthPercent = widthPercent;
        return this;
    }


    /**
     * the dialog show by wrap-content in y-axi in default
     *
     * you can use the widthPercent:
     * to determine the top and bottom margin if the measuredHeight is greater than the phone window
     * to stretch it in y-axi,no recommend,because that is ugly!
     *
     * do not support types:
     *  case DefaultConfig.TYPE_IOS_LOADING:
         case DefaultConfig.TYPE_PROGRESS:
         case DefaultConfig.TYPE_BOTTOM_SHEET_CUSTOM:
         case DefaultConfig.TYPE_BOTTOM_SHEET_GRID:
         case DefaultConfig.TYPE_BOTTOM_SHEET_LIST:
     * @param heightPercent
     * @return
     */
    public ConfigBean setHeightPercent(@FloatRange(from = 0f,to = 1.0f) float heightPercent) {
        if(heightPercent>1.0f || heightPercent<=0f){
            return this;
        }
        this.heightPercent = heightPercent;
        return this;
    }

    public float widthPercent;
    public float heightPercent;

    public ConfigBean setTransparentBehind(boolean transparentBehind) {
        isTransparentBehind = transparentBehind;
        return this;
    }

    public float bottomSheetDialogMaxHeightPercent;

    public ConfigBean setBottomSheetDialogMaxHeightPercent(float bottomSheetDialogMaxHeightPercent) {
        this.bottomSheetDialogMaxHeightPercent = bottomSheetDialogMaxHeightPercent;
        return this;
    }

    public boolean isTransparentBehind;



    public MyDialogListener listener;
    public MyItemDialogListener itemListener;

    public boolean cancelable = true;//默认可以点击后退键来dismiss对话框
    public boolean outsideTouchable = false;//默认外部半透明处点击不消失

    public Dialog dialog;
    public AlertDialog alertDialog;


    public boolean dimBehind = true;



    /*public ConfigBean setBgRes(int bgRes) {
        this.bgRes = bgRes;
        useTheShadowBg = false;
        return this;
    }*/

    public @DrawableRes int bgRes;

    /**
     *  default background res: R.drawable.shadow,white background ,surround with shadow
     * @param useTheWhiteAndShadowBg default is true. set false to disable the shadow background ,and set your own background in your xml
     * @return
     */
    public ConfigBean setHasShadow(boolean useTheWhiteAndShadowBg) {
        if(bgRes>0){
            return this;
        }
        this.useTheShadowBg = useTheWhiteAndShadowBg;
        return this;
    }

    public boolean useTheShadowBg = true;





    public int viewHeight;


    //各类对话框特有的参数
    public CharSequence[] wordsMd;
    public  int defaultChosen;//
    public boolean[] checkedItems;


    public List<? extends CharSequence> wordsIos;
    //bottom sheet
    public  CharSequence bottomTxt = DefaultConfig.bottomTxt;

    //bottomsheet
    public SuperLvAdapter mAdapter;
    public List<BottomSheetBean> lvDatas;
    public int gridColumns = 4;

    public BroadcastReceiver homeKeyReceiver;



    //样式

    //三个以下按钮,颜色按此顺序
    public  @ColorRes int btn1Color = DefaultConfig.iosBtnColor;
    public  @ColorRes int btn2Color= DefaultConfig.iosBtnColor;
    public  @ColorRes int btn3Color= DefaultConfig.iosBtnColor;



    public @ColorRes int titleTxtColor = DefaultConfig.titleTxtColor;
    public @ColorRes int msgTxtColor = DefaultConfig.msgTxtColor;

    public  @ColorRes int lvItemTxtColor = DefaultConfig.lvItemTxtColor;
    public Map<Integer,Integer> colorOfPosition;//listview 的item的特殊颜色:ColorRes

    public  @ColorRes int inputTxtColor = DefaultConfig.inputTxtColor;


    /*可能需要拓展的功能，支持当个item字体大小和颜色设置 (比如底部弹出 ，有的item是红色字这种，有的是蓝色)
支持填充自定义布局
支持gridview 或者recylerview*/

    //字体大小
    public  int btnTxtSize = DefaultConfig.btnTxtSize;// in sp
    public  int titleTxtSize = DefaultConfig.titleTxtSize;
    public  int msgTxtSize = DefaultConfig.msgTxtSize;
    public  int itemTxtSize = DefaultConfig.itemTxtSize;
    public  int inputTxtSize = DefaultConfig.inputTxtSize;


    @Override
    public ConfigBean setBtnColor(@ColorRes int btn1Color, @ColorRes int btn2Color, @ColorRes int btn3Color) {
        if (btn1Color>0)
        this.btn1Color = btn1Color;
        if (btn2Color>0)
        this.btn2Color = btn2Color;
        if (btn3Color>0)
            this.btn3Color = btn3Color;
        return this;
    }

    @Override
    public ConfigBean setListItemColor(@ColorRes int lvItemTxtColor, Map<Integer, Integer> colorOfPosition) {
        if (lvItemTxtColor>0)
            this.lvItemTxtColor = lvItemTxtColor;
        if (colorOfPosition  != null && colorOfPosition.size()>0){
            this.colorOfPosition = colorOfPosition;
        }
        return this;
    }

    @Override
    public ConfigBean setTitleColor(@ColorRes int colorRes) {
        if (colorRes>0){
            this.titleTxtColor = colorRes;
        }
        return this;
    }

    @Override
    public ConfigBean setMsgColor(@ColorRes int colorRes) {
        if (colorRes >0){
            this.msgTxtColor = colorRes;
        }
        return this;
    }

    public ConfigBean setActivity(Activity activity){
        this.context = activity;
        return this;
    }

    @Override
    public ConfigBean seInputColor(@ColorRes int colorRes) {
        if (colorRes >0){
            this.inputTxtColor = colorRes;
        }
        return this;
    }

    @Override
    public ConfigBean setTitleSize(int sizeInSp) {
        if (sizeInSp >0 && sizeInSp < 30){
            this.titleTxtSize = sizeInSp;
        }
        return this;
    }

    @Override
    public ConfigBean setMsgSize(int sizeInSp) {
        if (sizeInSp >0 && sizeInSp < 30){
            this.msgTxtSize = sizeInSp;
        }
        return this;
    }

    /**
     * 最大30sp
     * @param sizeInSp
     * @return
     */
    @Override
    public ConfigBean setBtnSize(int sizeInSp) {
        if (sizeInSp >0 && sizeInSp < 30){
            this.btnTxtSize = sizeInSp;
        }
        return this;
    }

    @Override
    public ConfigBean setLvItemSize(int sizeInSp) {
        if (sizeInSp >0 && sizeInSp < 30){
            this.itemTxtSize = sizeInSp;
        }
        return this;
    }

    @Override
    public ConfigBean setInputSize(int sizeInSp) {
        if (sizeInSp >0 && sizeInSp < 30){
            this.inputTxtSize = sizeInSp;
        }
        return this;
    }

    private void showAsActivityNow(){
        Activity activity = ActivityStackManager.getInstance().getTopActivity();
        if(activity!=null){
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
                if(activity1!=null){
                    buildByType(ConfigBean.this);
                    DialogUtil_DialogActivity activity2 = (DialogUtil_DialogActivity) activity1;
                    activity2.show(ConfigBean.this);
                }else {
                    showViewWhenActivityIsReady();
                }
            }
        },300);
    }

    public void showAsActivity() {
        this.showAsActivity = true;
        show();
    }

    @Override
    public Dialog show() {


        if(showAsActivity){
            showAsActivityNow();
            return null;
        }
//Build dialog by tyle :
        //内部保存loadingdialog对象
        buildByType(this);
        if(type ==DefaultConfig.TYPE_PROGRESS){

        }


        if (dialog != null && !dialog.isShowing()){
            Tool.showDialog(dialog,this);
            if(type == DefaultConfig.TYPE_IOS_LOADING || type == DefaultConfig.TYPE_MD_LOADING){
                StyledDialog.setLoadingObj(dialog);
            }
            return dialog;
        }else if (alertDialog != null && !alertDialog.isShowing()){
            Tool.showDialog(alertDialog,this);
            if(type == DefaultConfig.TYPE_IOS_LOADING || type == DefaultConfig.TYPE_MD_LOADING){
                StyledDialog.setLoadingObj(dialog);
            }
            return alertDialog;
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
        return setBtnText(positiveTxt,negtiveText,"");
    }
    @Override
    public ConfigBean setBtnText(CharSequence positiveTxt) {
        return setBtnText(positiveTxt,"","");
    }

    @Override
    public ConfigBean setListener(MyDialogListener listener) {
        if (listener!= null){
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
