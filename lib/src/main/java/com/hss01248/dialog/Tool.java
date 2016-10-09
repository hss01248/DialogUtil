package com.hss01248.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.hss01248.dialog.config.ConfigBean;

/**
 * Created by Administrator on 2016/10/9 0009.
 */
public class Tool {

    /**
     * 解决badtoken问题,一劳永逸
     * @param dialog
     */
    public static void showDialog(Dialog dialog) {
        try {
            dialog.show();
        }catch (Exception e){

        }
    }

    public static void setMdBtnColor(ConfigBean bean){
        Button btnPositive =
                bean.alertDialog.getButton(android.app.AlertDialog.BUTTON_POSITIVE);
        Button btnNegative =
                bean.alertDialog.getButton(android.app.AlertDialog.BUTTON_NEGATIVE);
        Button btnNatural =
                bean.alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL);
        if (bean.btn1Color != 0)
            btnPositive.setTextColor(bean.btn1Color);
        if (bean.btn2Color != 0)
            btnNegative.setTextColor(bean.btn2Color);
        if (bean.btn3Color != 0)
            btnNatural.setTextColor(bean.btn3Color);



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

    public static void setDialogStyle(Context activity, Dialog dialog, int measuredHeight ) {
        Window window = dialog.getWindow();

        //window.setWindowAnimations(R.style.dialog_center);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//todo keycode to show round corner


        WindowManager.LayoutParams wl = window.getAttributes();
       /* wl.x = 0;
        wl.y = getWindowManager().getDefaultDisplay().getHeight();*/
// 以下这两句是为了保证按钮可以水平满屏
        int width = ((WindowManager) activity.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth();

        int height = (int) (((WindowManager) activity.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getHeight() * 0.9);

        // wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.width = (int) (width * 0.94);  // todo keycode to keep gap
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;  //TODO  一般情况下为wrapcontent,最大值为height*0.9
       /* ViewUtils.measureView(contentView);
        int meHeight = contentView.getMeasuredHeight();//height 为0,weight为1时,控件计算所得height就是0
        View textview = contentView.findViewById(R.id.tv_msg);
        ViewUtils.measureView(textview);
        int textHeight = textview.getMeasuredHeight();*/
        if (measuredHeight > height){
            wl.height = height;
        }

        //wl.horizontalMargin= 0.2f;
// 设置显示位置
        // wl.gravity = Gravity.CENTER_HORIZONTAL;

        if (activity instanceof Activity){
            Activity activity1 = (Activity) activity;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if (activity1.isDestroyed()){
                    activity = StyledDialog.context;
                }
            }

        }else {
            wl.type = WindowManager.LayoutParams.TYPE_TOAST;
            //todo keycode to improve window level,同时要让它的后面半透明背景也拦截事件,不要传递到下面去
            //todo 单例化,不然连续弹出两次,只能关掉第二次的


        }

        dialog.onWindowAttributesChanged(wl);
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
                measureView(view);
                heightExtra += view.getMeasuredHeight();
            }

        }

        return height + heightExtra;
    }

}
