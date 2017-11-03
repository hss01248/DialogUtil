package com.hss01248.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;

import com.hss01248.dialog.config.ConfigBean;
import com.hss01248.dialog.config.DefaultConfig;
import com.hss01248.dialog.view.IosAlertDialogHolder;


import static com.hss01248.dialog.StyledDialog.context;

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
                        //setListItemsStyle(bean);
                    }
                    adjustWindow(dialog,bean);




                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }

    private static void adjustWindow(final Dialog dialog, final ConfigBean bean) {
        dialog.getWindow().getDecorView().getViewTreeObserver()
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {

                        doIfIsInput(bean, new MyRunnable<IosAlertDialogHolder>() {
                            @Override
                            public void run(IosAlertDialogHolder iosAlertDialogHolder) {
                                iosAlertDialogHolder.showKeyBorad();
                            }
                        });
                        adjustWH(dialog,bean);
                        dialog.getWindow().getDecorView().getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    }
                });
    }

    interface MyRunnable<T>{
        void run(T t);
    }

    public static void doIfIsInput(ConfigBean bean, final MyRunnable<IosAlertDialogHolder> runnable) {
        if(bean.type == DefaultConfig.TYPE_IOS_INPUT){
            if (bean.viewHolder instanceof IosAlertDialogHolder){
                final IosAlertDialogHolder holder = (IosAlertDialogHolder) bean.viewHolder;
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        runnable.run(holder);
                    }
                },500);

            }
        }
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

        if(btnPositive !=null){
            btnPositive.setAllCaps(false);
            if(!TextUtils.isEmpty(bean.text1)){
                btnPositive.setText(bean.text1);
            }
            if (bean.btn1Color > 0)
                btnPositive.setTextColor(getColor(bean.context,bean.btn1Color));
            if(bean.btnTxtSize >0){
                btnPositive.setTextSize(bean.btnTxtSize);
            }
        }
        if(btnNegative !=null){
            btnNegative.setAllCaps(false);
            if(!TextUtils.isEmpty(bean.text2)){
                btnNegative.setText(bean.text2);
            }
            if (bean.btn2Color > 0 )
                if(bean.btn2Color == DefaultConfig.iosBtnColor ){
                    btnNegative.setTextColor(getColor(bean.context,R.color.text_gray));
                }else {
                    btnNegative.setTextColor(getColor(bean.context,bean.btn2Color));
                }

            if(bean.btnTxtSize >0){
                btnNegative.setTextSize(bean.btnTxtSize);
            }
        }

        if(btnNatural !=null){
            btnNatural.setAllCaps(false);
            if(!TextUtils.isEmpty(bean.text3)){
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
            bean.context = context;
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
                    context = context;
                }
            }
        }

        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(cancleable);
        dialog.setCanceledOnTouchOutside(outsideTouchable);
        return dialog;
    }

    public static void adjustStyle(final ConfigBean bean) {
        /*if (bean.alertDialog!= null){
            //setMdBtnStytle(bean);
            //setListItemsStyle(bean);
           // adjustStyle(bean.context,bean.dialog,bean.viewHeight,bean);

        }else {
            adjustWH(bean.context,bean.dialog,bean.viewHeight,bean);
        }*/

        setBg(bean);
       // bean.isTransparentBehind = true;
        setDim(bean);
        Dialog dialog = bean.dialog ==null ? bean.alertDialog : bean.dialog;
        Window window = dialog.getWindow();
        window.setGravity(bean.gravity);
        if(bean.context instanceof Activity){

        }else {
            window.setType(WindowManager.LayoutParams.TYPE_TOAST);
            WindowManager.LayoutParams params = window.getAttributes();
            if(params==null){
                params = new WindowManager.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            }
            params.format = PixelFormat.RGBA_8888;
            params.flags = WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
               // WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL  |
                WindowManager.LayoutParams.FLAG_LOCAL_FOCUS_MODE  |
                WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH |
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD;
            params.dimAmount = 0.2f;
            //params.alpha = 0.5f;//the alpha of window

            window.setAttributes(params);

            // back key pressed
            window.getDecorView().setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_BACK || event.getKeyCode() == KeyEvent.KEYCODE_SETTINGS) {
                        StyledDialog.dismiss(bean.alertDialog,bean.dialog);
                        return true;
                    }
                    return false;
                }
            });
            // home key pressed
            setHomeKeyListener(window,bean);

            //todo outside not touchable

            //todo dim behind
            window.setDimAmount(0.2f);

        }

    }

    private  static void setHomeKeyListener(Window window, final ConfigBean bean){
        //在创建View时注册Receiver
        IntentFilter homeFilter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        bean.context.registerReceiver(new BroadcastReceiver() {
            final String SYSTEM_DIALOG_REASON_KEY = "reason";

            final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";

            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
                    String reason = intent.getStringExtra(SYSTEM_DIALOG_REASON_KEY);
                    if (reason != null && reason.equals(SYSTEM_DIALOG_REASON_HOME_KEY)) {
                        // 处理自己的逻辑
                        StyledDialog.dismiss(bean.alertDialog,bean.dialog);
                        context.unregisterReceiver(this);
                    }
                }
            }
        }, homeFilter);
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

    /**
     * set background of dialog window
     * @param bean
     */
    private static void setBg(ConfigBean bean) {
        //no need to modify the background
        if(bean.type == DefaultConfig.TYPE_BOTTOM_SHEET_GRID
            || bean.type == DefaultConfig.TYPE_BOTTOM_SHEET_LIST
            || bean.type == DefaultConfig.TYPE_BOTTOM_SHEET_CUSTOM
            || bean.type == DefaultConfig.TYPE_PROGRESS){
            // No need to set backgroud
            return;

        }


        if (bean.alertDialog!= null){

            if(bean.useTheShadowBg){
                bean.alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.shadow);
            }else {
                if(bean.bgRes>0)
                     bean.alertDialog.getWindow().setBackgroundDrawableResource(bean.bgRes);
                else {
                    bean.alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                }

            }
        }else {
             if(bean.type == DefaultConfig.TYPE_IOS_LOADING){//转菊花时,背景透明
                bean.dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }else {
                if(bean.useTheShadowBg){
                    bean.dialog.getWindow().setBackgroundDrawableResource(R.drawable.shadow);
                }else {
                    if(bean.bgRes>0)
                        bean.dialog.getWindow().setBackgroundDrawableResource(bean.bgRes);
                    else {
                        bean.dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    }
                }
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
           // listView.getAdapter().
            if(listView!=null && listView.getAdapter() !=null){
                int count = listView.getChildCount();
                for(int i=0;i<count;i++){
                    View childAt = listView.getChildAt(i);
                    if(childAt ==null){
                        continue;
                    }
                    CheckedTextView itemView = (CheckedTextView) childAt.findViewById(android.R.id.text1);
                    Log.e("dd",itemView+"-----"+ i);
                    if(itemView !=null) {
                        itemView.setCheckMarkDrawable(R.drawable.bg_toast);
                        //itemView.setCheckMarkTintList();

                       // itemView.setCheckMarkTintList();
                        //itemView.setCheckMarkTintList();

                    }

                }

            }

        }
    }

    public static void adjustWH( Dialog dialog,  ConfigBean bean ) {
        if (dialog == null){
            return;
        }

        Window window = dialog.getWindow();
        View rootView = window.getDecorView();
        //window.setWindowAnimations(R.style.dialog_center);
        WindowManager.LayoutParams wl = window.getAttributes();

        int width = window.getWindowManager().getDefaultDisplay().getWidth();
        int height = window.getWindowManager().getDefaultDisplay().getHeight();
        int measuredHeight = rootView.getMeasuredHeight();
        int measuredWidth = rootView.getMeasuredWidth();

        float widthRatio = 0.85f;
        float heightRatio = 0f;
        if(bean.type ==DefaultConfig.TYPE_IOS_BOTTOM){
            widthRatio = 0.95f;
        }else if(bean.type ==DefaultConfig.TYPE_IOS_CENTER_LIST){
            widthRatio = 0.9f;
        }
        if(width > height){//宽屏
            widthRatio = 0.5f;
        }

        //set ratio as user has set
        if(bean.widthPercent>0 && bean.widthPercent<=1.0f){
            widthRatio = bean.widthPercent;
        }
        if(bean.heightPercent>0 && bean.heightPercent<=1.0f){
            heightRatio = bean.heightPercent;
        }

        if(istheTypeOfNotAdjust(bean.type)){
            /*wl.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;*/
        }else {
           // rootView.setPadding(0,30,0,30);
            wl.width = (int) (width * widthRatio);//stretch when the content is not enough,margin when the content is full fill the screen
            //if (measuredHeight > height* heightRatio){//only work when the content is full fill the screen
            if(heightRatio>0){
                wl.height = (int) (height* heightRatio);
            }

           // }
        }

        dialog.onWindowAttributesChanged(wl);
    }

    private static boolean istheTypeOfNotAdjust(int type) {
        switch (type){
            case DefaultConfig.TYPE_IOS_LOADING:
            case DefaultConfig.TYPE_PROGRESS:
            case DefaultConfig.TYPE_BOTTOM_SHEET_CUSTOM:
            case DefaultConfig.TYPE_BOTTOM_SHEET_GRID:
            case DefaultConfig.TYPE_BOTTOM_SHEET_LIST:
           // case DefaultConfig.TYPE_CUSTOM_VIEW:
                return true;
            default:
                return false;
        }
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
            case DefaultConfig.TYPE_MD_ALERT:
            case DefaultConfig.TYPE_MD_MULTI_CHOOSE:
            case DefaultConfig.TYPE_MD_SINGLE_CHOOSE:
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
                        if(bean.type == DefaultConfig.TYPE_IOS_INPUT){
                            IosAlertDialogHolder iosAlertDialogHolder = (IosAlertDialogHolder) bean.viewHolder;
                            if(iosAlertDialogHolder!=null){
                                iosAlertDialogHolder.hideKeyBoard();
                            }
                        }
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

    public static void handleScrollInBottomSheetDialog(final AbsListView listView){
        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (!listView.canScrollVertically(-1)) {      //canScrollVertically(-1)的值表示是否能向下滚动，false表示已经滚动到顶部
                    listView.requestDisallowInterceptTouchEvent(false);
                }else{
                    listView.requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });


    }

    public static void showKeyBoard(View view){
        InputMethodManager imm = (InputMethodManager) StyledDialog.context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view,InputMethodManager.SHOW_FORCED);
    }

    public static void hideKeyBoard(View view){
        InputMethodManager imm = (InputMethodManager) StyledDialog.context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
    }


}
