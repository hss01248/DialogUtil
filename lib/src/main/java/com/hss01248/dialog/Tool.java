package com.hss01248.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.hss01248.dialog.config.ConfigBean;
import com.hss01248.dialog.config.DefaultConfig;

/**
 * Created by Administrator on 2016/10/9 0009.
 */
public class Tool {

    /**
     * 解决badtoken问题,一劳永逸
     * @param dialog
     */
    public static void showDialog(final Dialog dialog) {
        StyledDialog.getMainHandler().post(new Runnable() {
            @Override
            public void run() {
                try {
                    dialog.show();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }

    public static void setMdBtnStytle(ConfigBean bean){
        Button btnPositive =
                bean.alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
        Button btnNegative =
                bean.alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        Button btnNatural =
                bean.alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL);

        //todo null

        if (btnPositive != null && btnNegative != null){
            btnPositive.setTextSize(bean.btnTxtSize);
            btnNegative.setTextSize(bean.btnTxtSize);
            btnNatural.setTextSize(bean.btnTxtSize);

            if (bean.btn1Color != 0)
                btnPositive.setTextColor(getColor(null,bean.btn1Color));
            if (bean.btn2Color != 0)
                btnNegative.setTextColor(getColor(null,bean.btn2Color));
            if (bean.btn3Color != 0)
                btnNatural.setTextColor(getColor(null,bean.btn3Color));
        }





    }

    public static ConfigBean fixContext(ConfigBean bean){
        if (bean.context instanceof Activity){
            Activity activity1 = (Activity) bean.context;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if (!activity1.isDestroyed()){
                    return bean;
                }
            }else {
                return bean;
            }
        }
        Activity activity = MyActyManager.getInstance().getCurrentActivity();
        if(activity!=null){
            bean.context = activity;
            return bean;
        }

        if (bean.context == null){
            bean.context = StyledDialog.context;
        }

        if (bean.context instanceof Activity){
            Activity activity1 = (Activity) bean.context;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if (activity1.isDestroyed()){
                    bean.context = StyledDialog.context;
                }
            }
        }
        return bean;
    }

    public static ConfigBean newCustomDialog(ConfigBean bean){
        Dialog dialog = new Dialog(bean.context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        bean.dialog = dialog;
        return bean;
    }

    public static ConfigBean setCancelable(ConfigBean bean){

        if (bean.alertDialog != null){
            bean.alertDialog.setCancelable(bean.cancelable);
            bean.alertDialog.setCanceledOnTouchOutside(bean.outsideTouchable);
        }else if (bean.dialog != null){
            bean.dialog.setCancelable(bean.cancelable);
            bean.dialog.setCanceledOnTouchOutside(bean.outsideTouchable);
        }


        return bean;
    }





    public static Dialog buildDialog(Context context, boolean cancleable, boolean outsideTouchable) {


        if (context instanceof Activity){//todo keycode
            Activity activity = (Activity) context;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if (activity.isDestroyed()){
                    context = StyledDialog.context;
                }
            }
        }

        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(cancleable);
        dialog.setCanceledOnTouchOutside(outsideTouchable);
        return dialog;
    }

    public static void setDialogStyle(ConfigBean bean) {
        if (bean.alertDialog!= null){
            setMdBtnStytle(bean);
        }else {
            setDialogStyle(bean.context,bean.dialog,bean.viewHeight,bean);
        }

    }

    public static void setDialogStyle(Context activity, Dialog dialog, int measuredHeight,ConfigBean bean ) {
        if (dialog == null){
            return;
        }
        Window window = dialog.getWindow();

        //window.setWindowAnimations(R.style.dialog_center);
        if(bean.type != DefaultConfig.TYPE_PROGRESS){
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//todo keycode to show round corner
        }



        WindowManager.LayoutParams wl = window.getAttributes();
       /* wl.x = 0;
        wl.y = getWindowManager().getDefaultDisplay().getHeight();*/
// 以下这两句是为了保证按钮可以水平满屏

        int width = window.getWindowManager().getDefaultDisplay().getWidth();
        int height = window.getWindowManager().getDefaultDisplay().getHeight();

        float ratio = 0.8f;
        if(width > height){//宽屏
            ratio = 0.5f;
        }


        if (isCustomType(bean)){
            wl.width = (int) (width * ratio);  // todo keycode to keep gap
        }else {
            wl.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        }

        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;  //TODO  一般情况下为wrapcontent,最大值为height*0.9

        if (measuredHeight > height* 0.9){
            wl.height = (int) (height* 0.9);
        }

        //wl.horizontalMargin= 0.2f;
// 设置显示位置
        // wl.gravity = Gravity.CENTER_HORIZONTAL;

        if (activity instanceof Activity){
           /* Activity activity1 = (Activity) activity;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if (activity1.isDestroyed()){
                    activity = StyledDialog.context;
                }
            }*/

        }else {
            wl.type = WindowManager.LayoutParams.TYPE_TOAST;
            //todo keycode to improve window level,同时要让它的后面半透明背景也拦截事件,不要传递到下面去
            //todo 单例化,不然连续弹出两次,只能关掉第二次的
           // wl.flags =


        }

        dialog.onWindowAttributesChanged(wl);
    }

    private static boolean isCustomType(ConfigBean bean) {
        switch (bean.type){
            case DefaultConfig.TYPE_IOS_HORIZONTAL:
            case DefaultConfig.TYPE_IOS_VERTICAL:
            case DefaultConfig.TYPE_IOS_BOTTOM:
            case DefaultConfig.TYPE_IOS_CENTER_LIST:
            case DefaultConfig.TYPE_IOS_INPUT:
            case DefaultConfig.TYPE_CUSTOM_VIEW:
            case DefaultConfig.TYPE_MD_LOADING:
            return true;
            default:
                return false;
        }
    }


    public static void measureView(View child) {
        ViewGroup.LayoutParams p = child.getLayoutParams();
        if (p == null) {
            p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                    ,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        int lpHeight = p.height;
        int lpWidth = p.width;
        int childHeightSpec;
        int childWidthSpec;
        if (lpHeight > 0) {   //如果Height是一个定值，那么我们测量的时候就使用这个定值
            childHeightSpec = View.MeasureSpec.makeMeasureSpec(lpHeight,
                    View.MeasureSpec.EXACTLY);
        } else {  // 否则，我们将mode设置为不指定，size设置为0
            childHeightSpec = View.MeasureSpec.makeMeasureSpec(0,
                    View.MeasureSpec.UNSPECIFIED);
        }
        if (lpWidth > 0) {
            childWidthSpec= View.MeasureSpec.makeMeasureSpec(lpHeight,
                    View.MeasureSpec.EXACTLY);
        } else {
            childWidthSpec= View.MeasureSpec.makeMeasureSpec(0,
                    View.MeasureSpec.UNSPECIFIED);
        }
        child.measure(childWidthSpec, childHeightSpec);
    }

    /**
     *
     * @param root
     * @param id  height为0,weight为1的scrollview包裹的view的id,如果没有,传0或负数即可
     * @return
     */
    public static int mesureHeight(View root, int id) {
        measureView(root);
        int height = root.getMeasuredHeight();
        int heightExtra = 0;
        if (id > 0){
            View view = root.findViewById(id);
            if (view != null){
                measureView(view);
                heightExtra = view.getMeasuredHeight();
            }

        }
        return height + heightExtra;
    }

    public static int mesureHeight(View root, View... subViews) {
        measureView(root);
        int height = root.getMeasuredHeight();
        int heightExtra = 0;
        if (subViews != null && subViews.length>0){
            for (View view : subViews){
                if(view.getVisibility() == View.VISIBLE){//确保设置了gone的view不再出现
                    measureView(view);
                    heightExtra += view.getMeasuredHeight();
                }

            }

        }

        return height + heightExtra;
    }

    public static int getColor(Context context,int colorRes){
        if (context ==null){
            context = StyledDialog.context;
        }
       return context.getResources().getColor(colorRes);

    }


    public static void setCancelListener(final ConfigBean bean) {

            if(bean.dialog!=null){
                bean.dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        if(bean.listener!=null) {
                            bean.listener.onCancle();
                        }
                        if (bean.dialog == StyledDialog.getLoadingDialog()) {
                            StyledDialog.setLoadingObj(null);

                        }

                    }
                });
            }
            if(bean.alertDialog!=null){
                bean.alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        if (bean.listener != null) {
                            bean.listener.onCancle();
                        }
                        if (bean.alertDialog == StyledDialog.getLoadingDialog()) {
                            StyledDialog.setLoadingObj(null);
                        }
                    }
                });


            }


    }
}
