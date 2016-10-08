package com.hss01248.dialog;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatDialog;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2016/5/4 0004.
 */
public class StyledDialog {

    //android.support.v7.app.

    public static Context context;


    public static void init(Context context){
        StyledDialog.context = context;

    }

    /**
     * 指定supportv7包,达到各版本ui效果一致
     * @param activity
     * @param title
     * @param msg
     * @param firstTxt
     * @param secondTxt
     * @param thirdTxt
     * @param outsideCancleable
     * @param cancleable
     * @param listener
     * @return
     */
    public static android.support.v7.app.AlertDialog showMdAlert(Activity activity, String title, String msg,
                                                                 String firstTxt, String secondTxt, String thirdTxt,
                                                                 boolean outsideCancleable, boolean cancleable,
                                                                 final MyDialogListener listener){
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(activity);
        builder.setTitle(title)
                .setMessage(msg)
                .setCancelable(cancleable)
                .setPositiveButton(firstTxt, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       listener.onFirst(dialog);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(secondTxt, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    listener.onSecond(dialog);
                    dialog.dismiss();
                }
            }).setNeutralButton(thirdTxt, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    listener.onThird(dialog);
                    dialog.dismiss();
                }
            });
        android.support.v7.app.AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(outsideCancleable);
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                listener.onCancle();
            }
        });
       showDialog(dialog);
       return dialog;
    }


    public static void dismiss(Dialog dialog){
        if (dialog != null && dialog.isShowing()){
            dialog.dismiss();
        }
    }

    public static void dismiss(AppCompatDialog dialog){
        if (dialog != null && dialog.isShowing()){
            dialog.dismiss();
        }
    }



    public static Dialog showIosAlert(Context activity, String title, String msg,
                                      String firstTxt, String secondTxt, String thirdTxt,
                                      boolean outsideCancleable, boolean cancleable,
                                      final MyDialogListener listener){
        return showIosAlert(activity,false,title,msg,firstTxt,secondTxt,thirdTxt,outsideCancleable,cancleable,listener);
    }


    /**
     * todo
     * @param activity
     * @param title
     * @param msg
     * @param firstTxt
     * @param secondTxt
     * @param thirdTxt
     * @param outsideCancleable
     * @param cancleable
     * @param listener
     * @return
     */
    public static Dialog showIosAlertVertical(Context activity, String title, String msg,
                                              String firstTxt, String secondTxt, String thirdTxt,
                                              boolean outsideCancleable, boolean cancleable,
                                              final MyDialogListener listener){
        return showIosAlert(activity,true,title,msg,firstTxt,secondTxt,thirdTxt,outsideCancleable,cancleable,listener);
    }

    private static Dialog showIosAlert(Context context, boolean isButtonVerticle, String title, String msg,
                                       String firstTxt, String secondTxt, String thirdTxt,
                                       boolean outsideCancleable, boolean cancleable,
                                       final MyDialogListener listener){

        Dialog dialog = buildDialog(context,cancleable,outsideCancleable);

       int  height =   assigIosAlertView(context,dialog,isButtonVerticle,title,msg,firstTxt,secondTxt,thirdTxt,listener);

        setDialogStyle(context,dialog,height);

       showDialog(dialog);
        return dialog;
    }

    public static Dialog showIosSingleChoose(Context context, List<String> words, boolean outsideCancleable, boolean cancleable,
                                             final MyItemDialogListener listener){
        Dialog dialog = buildDialog(context,cancleable,outsideCancleable);

        int measuredHeight =   assignListDialogView(context,dialog,words,listener);

        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);

        setDialogStyle(context,dialog,measuredHeight);

       showDialog(dialog);
        return dialog;

    }

    private static int assignListDialogView(final Context context, final Dialog dialog, final List<String> words, final MyItemDialogListener listener) {
        View root =  View.inflate(context,R.layout.dialog_ios_center_item,null);

        ListView listView = (ListView) root.findViewById(R.id.lv);
        listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return words.size();
            }

            @Override
            public Object getItem(int position) {
                return words.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                Button view = (Button) View.inflate(context,R.layout.item_btn_bottomalert,null);
                view.setText(words.get(position));
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onItemClick(words.get(position),position);
                        if (dialog != null && dialog.isShowing()){
                            dialog.dismiss();
                        }
                    }
                });

                return view;
            }
        });

        dialog.setContentView(root);

        int height =    0;

        return height;
    }

    public static Dialog showBottomItemDialog(Context context,
                                              List<String> words, String bottomTxt,
                                              boolean outsideCancleable, boolean cancleable,
                                              final MyItemDialogListener listener){
        Dialog dialog = buildDialog(context,cancleable,outsideCancleable);

        int measuredHeight =   assignBottomListDialogView(context,dialog,words,bottomTxt,listener);

        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.mystyle);


        setDialogStyle(context,dialog,measuredHeight);
        
        showDialog(dialog);


        return dialog;
    }


    /**
     * 解决badtoken问题,一劳永逸
     * @param dialog
     */
    private static void showDialog(Dialog dialog) {
        try {
            dialog.show();
        }catch (Exception e){

        }
    }

    private static int assignBottomListDialogView(final Context context, final Dialog dialog,
                                                  final List<String> words, String bottomTxt, final MyItemDialogListener listener) {
        View root = View.inflate(context,R.layout.dialog_ios_alert_bottom,null);
        Button btnBottom = (Button) root.findViewById(R.id.btn_bottom);
        if (TextUtils.isEmpty(bottomTxt)){
            btnBottom.setVisibility(View.GONE);
        }else {
            btnBottom.setVisibility(View.VISIBLE);
            btnBottom.setText(bottomTxt);
            btnBottom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onBottomBtnClick();
                    if (dialog != null && dialog.isShowing()){
                        dialog.dismiss();
                    }
                }
            });
        }

        ListView listView = (ListView) root.findViewById(R.id.lv);
        BaseAdapter adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return words.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                Button view = (Button) View.inflate(context,R.layout.item_btn_bottomalert,null);
                view.setText(words.get(position));
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onItemClick(words.get(position),position);
                        if (dialog != null && dialog.isShowing()){
                            dialog.dismiss();
                        }
                    }
                });

                return view;
            }
        };

        listView.setAdapter(adapter);
        dialog.setContentView(root);

     int height =    0;

        return height;
    }


    /**
     *
     * @param root
     * @param id  height为0,weight为1的scrollview包裹的view的id,如果没有,传0或负数即可
     * @return
     */
    private static int mesureHeight(View root, int id) {
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


    /**
     * 传Context的后果是不会再响应后退键事件,需要自己处理.如果传递的是activity,仍然可以处理
     * @param context
     * @param msg
     * @param cancleable
     * @param outsideTouchable
     * @return
     */
    public static Dialog showProgressDialog(Context context, String msg, boolean cancleable, boolean outsideTouchable) {

        Dialog dialog = buildDialog(context,cancleable,outsideTouchable);

        View root = View.inflate(context,R.layout.progressview_wrapconent,null);
        TextView tvMsg = (TextView) root.findViewById(R.id.message);
        tvMsg.setText(msg);
        dialog.setContentView(root);



        setDialogStyle(context,dialog,0);

       showDialog(dialog);
        //根据实际view高度调整
        return dialog;
    }

    private static Dialog buildDialog(Context context, boolean cancleable, boolean outsideTouchable) {


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


    private static void setDialogStyle(Context activity, Dialog dialog, int measuredHeight ) {
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
            wl.type = WindowManager.LayoutParams.TYPE_TOAST;//todo keycode to improve window level
        }

        dialog.onWindowAttributesChanged(wl);
    }

    private static int  assigIosAlertView(Context activity, final Dialog dialog, boolean isButtonVerticle,
                                          String title, String msg, String firstTxt, String secondTxt, String thirdTxt,
                                          final MyDialogListener listener) {
        View root = View.inflate(activity,isButtonVerticle ? R.layout.dialog_ios_alert_vertical : R.layout.dialog_ios_alert,null);
        TextView tvTitle = (TextView) root.findViewById(R.id.tv_title);
        if (TextUtils.isEmpty(title)){
            tvTitle.setVisibility(View.GONE);
        }else {
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(title);
        }

        TextView tvMsg = (TextView) root.findViewById(R.id.tv_msg);
        tvMsg.setText(msg);

        Button button1 = (Button) root.findViewById(R.id.btn_1);
        Button button2 = (Button) root.findViewById(R.id.btn_2);
        Button button3 = (Button) root.findViewById(R.id.btn_3);

        if (TextUtils.isEmpty(firstTxt)){
            root.findViewById(R.id.ll_container).setVisibility(View.GONE);
            root.findViewById(R.id.line).setVisibility(View.GONE);
        }else {

            button1.setVisibility(View.VISIBLE);
            button1.setText(firstTxt);
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onFirst(dialog);
                    if (dialog != null && dialog.isShowing()){
                        dialog.dismiss();
                    }
                }
            });

            //btn2
            if (TextUtils.isEmpty(secondTxt)){
                root.findViewById(R.id.line_btn2).setVisibility(View.GONE);
                button2.setVisibility(View.GONE);
            }else {
                root.findViewById(R.id.line_btn2).setVisibility(View.VISIBLE);
                button2.setVisibility(View.VISIBLE);

                button2.setText(secondTxt);
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onSecond(dialog);
                        if (dialog != null && dialog.isShowing()){
                            dialog.dismiss();
                        }
                    }
                });

                //btn 3
                if (TextUtils.isEmpty(thirdTxt)){
                    root.findViewById(R.id.line_btn3).setVisibility(View.GONE);
                    button3.setVisibility(View.GONE);
                }else {
                    root.findViewById(R.id.line_btn3).setVisibility(View.VISIBLE);
                    button3.setVisibility(View.VISIBLE);

                    button3.setText(thirdTxt);
                    button3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            listener.onThird(dialog);
                            if (dialog != null && dialog.isShowing()){
                                dialog.dismiss();
                            }
                        }
                    });


                }

            }

        }

        dialog.setContentView(root);

        int height = mesureHeight(root,R.id.tv_msg);

        return height;






    }


    private static void measureView(View child) {
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

}
