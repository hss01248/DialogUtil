package com.hss01248.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;

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
    public static void showDialog(final Dialog dialog, final ConfigBean bean) {
        StyledDialog.getMainHandler().post(new Runnable() {
            @Override
            public void run() {
                try {
                    dialog.show();
                    if (bean.alertDialog!= null){
                        setMdBtnStytle(bean);
                        adjustWH(bean.context,bean.alertDialog,bean.viewHeight,bean);
                        setListItemsStyle(bean);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }


    /**
     * 必须在show之后,button才不会返回null
     * @param bean
     */
    public static void setMdBtnStytle(ConfigBean bean){
        Button btnPositive =
                bean.alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        Button btnNegative =
                bean.alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        Button btnNatural =
                bean.alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL);

        //todo null
        if(btnPositive !=null){
            if(TextUtils.isEmpty(bean.text1)){
                btnPositive.setText(bean.text1);
            }
            if (bean.btn1Color > 0)
                btnPositive.setTextColor(getColor(null,bean.btn1Color));
            if(bean.btnTxtSize >0){
                btnPositive.setTextSize(bean.btnTxtSize);
            }
        }
        if(btnNegative !=null){
            if(TextUtils.isEmpty(bean.text2)){
                btnNegative.setText(bean.text2);
            }
            if (bean.btn2Color > 0 )
                if(bean.btn2Color == DefaultConfig.iosBtnColor ){
                    btnNegative.setTextColor(getColor(null,R.color.text_gray));
                }else {
                    btnNegative.setTextColor(getColor(null,bean.btn2Color));
                }

            if(bean.btnTxtSize >0){
                btnNegative.setTextSize(bean.btnTxtSize);
            }
        }

        if(btnNatural !=null){
            if(TextUtils.isEmpty(bean.text3)){
                btnNatural.setText(bean.text3);
            }
            if (bean.btn3Color > 0)
                btnNatural.setTextColor(getColor(null,bean.btn3Color));
            if(bean.btnTxtSize >0){
                btnNatural.setTextSize(bean.btnTxtSize);
            }
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
            bean.alertDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        }else if (bean.dialog != null){
            bean.dialog.setCancelable(bean.cancelable);
            bean.dialog.setCanceledOnTouchOutside(bean.outsideTouchable);
            bean.dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
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

    public static void adjustWH(ConfigBean bean) {
        if (bean.alertDialog!= null){
            //setMdBtnStytle(bean);
            //setListItemsStyle(bean);
           // adjustWH(bean.context,bean.dialog,bean.viewHeight,bean);

        }else {
            adjustWH(bean.context,bean.dialog,bean.viewHeight,bean);
        }
        setBg(bean);
       // bean.isTransparentBehind = true;
        setDim(bean);

        //fixNoDim(bean);
       // addShaow(bean);





    }

    private static void setDim(ConfigBean bean) {
        if(bean.type == DefaultConfig.TYPE_IOS_LOADING){//转菊花,则让背景透明
            bean.isTransparentBehind = true;
        }
        if (bean.alertDialog!= null){
           if(bean.isTransparentBehind){
               bean.alertDialog.getWindow().setDimAmount(0);
           }
            bean.alertDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED );
        }else {
            if(bean.isTransparentBehind){
                bean.dialog.getWindow().setDimAmount(0);
            }
            bean.dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED );
        }
    }

    private static void setBg(ConfigBean bean) {
        if (bean.alertDialog!= null){
            bean.alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.shadow);
        }else {
            if(bean.type == DefaultConfig.TYPE_BOTTOM_SHEET_GRID
                    || bean.type == DefaultConfig.TYPE_BOTTOM_SHEET_LIST
                    || bean.type == DefaultConfig.TYPE_BOTTOM_SHEET_CUSTOM
                    || bean.type == DefaultConfig.TYPE_PROGRESS){


            }else if(bean.type == DefaultConfig.TYPE_IOS_LOADING){
                bean.dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }else {
                bean.dialog.getWindow().setBackgroundDrawableResource(R.drawable.shadow);
            }

        }
    }



    private static void addShaow(ConfigBean bean) {
        /**/
        if(bean.type == DefaultConfig.TYPE_IOS_LOADING
                || bean.type == DefaultConfig.TYPE_PROGRESS
                || bean.type == DefaultConfig.TYPE_BOTTOM_SHEET_CUSTOM
                || bean.type == DefaultConfig.TYPE_BOTTOM_SHEET_LIST
                || bean.type == DefaultConfig.TYPE_BOTTOM_SHEET_GRID
                || bean.type == DefaultConfig.TYPE_IOS_BOTTOM){
            return;
        }

        Dialog dialog = bean.dialog;
        if(dialog ==null){
            dialog = bean.alertDialog;
        }

        /*ShadowProperty sp = new ShadowProperty()
                .setShadowColor(0x77000000)
                .setShadowDy(3)
                .setShadowRadius(3)
                .setShadowSide(ShadowProperty.ALL);

        ShadowViewDrawable sd = new ShadowViewDrawable(sp, bean.alertDialog ==null ? Color.TRANSPARENT : Color.WHITE, 0, 0);
       // ShadowViewDrawable sd = new ShadowViewDrawable(sp,Color.TRANSPARENT, 0, 0);
        ViewCompat.setBackground(dialog.getWindow().getDecorView(), sd);
        ViewCompat.setLayerType(dialog.getWindow().getDecorView(), ViewCompat.LAYER_TYPE_SOFTWARE, null);*/

       /* ShadowViewHelper.bindShadowHelper(
                new ShadowProperty()
                        .setShadowColor(0x77000000)
                        .setShadowDy(3)
                        .setShadowRadius(3)
                , dialog.getWindow().getDecorView());*/

    }

    private static void setListItemsStyle(ConfigBean bean) {
        if(bean.type == DefaultConfig.TYPE_MD_SINGLE_CHOOSE || bean.type == DefaultConfig.TYPE_MD_MULTI_CHOOSE){
            ListView listView =  bean.alertDialog.getListView();
            if(listView!=null && listView.getAdapter() !=null){
                for(int i=0;i<listView.getAdapter().getCount();i++){
                    View childAt = listView.getChildAt(i);
                    CheckedTextView itemView = (CheckedTextView) childAt.findViewById(android.R.id.text1);
                    if(itemView !=null) {
                        /*ColorStateList stateList = itemView.getCheckMarkDrawable();
                        itemView.setCheckMarkTintList();*/
                        //itemView.setCheckMarkTintList();

                    }

                }

            }

        }
    }

    public static void adjustWH(Context activity, Dialog dialog, int measuredHeight, ConfigBean bean ) {
        if (dialog == null){
            return;
        }
        Window window = dialog.getWindow();

        //window.setWindowAnimations(R.style.dialog_center);
        /*if(bean.type == DefaultConfig.TYPE_IOS_LOADING){
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//todo keycode to show round corner
        }else if(bean.type == DefaultConfig.TYPE_MD_MULTI_CHOOSE
                || bean.type == DefaultConfig.TYPE_MD_ALERT
            || bean.type == DefaultConfig.TYPE_MD_SINGLE_CHOOSE){
            window.setBackgroundDrawableResource(R.drawable.shadow);
        }*/



        WindowManager.LayoutParams wl = window.getAttributes();
       /* if(bean.type == DefaultConfig.TYPE_IOS_LOADING){//转菊花,则让背景透明
            wl.dimAmount = 0;
        }*/

       /* wl.x = 0;
        wl.y = getWindowManager().getDefaultDisplay().getHeight();*/
// 以下这两句是为了保证按钮可以水平满屏

        int width = window.getWindowManager().getDefaultDisplay().getWidth();
        int height = window.getWindowManager().getDefaultDisplay().getHeight();

        float ratio = 0.85f;
        if(bean.type ==DefaultConfig.TYPE_IOS_BOTTOM){
            ratio = 0.95f;
        }else if(bean.type ==DefaultConfig.TYPE_IOS_CENTER_LIST){
            ratio = 0.9f;
        }
        if(width > height){//宽屏
            ratio = 0.5f;
        }

        wl.width = (int) (width * ratio);


        /*if (isCustomType(bean)){
            wl.width = (int) (width * ratio);  // todo keycode to keep gap
        }else {
            wl.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        }*/

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
