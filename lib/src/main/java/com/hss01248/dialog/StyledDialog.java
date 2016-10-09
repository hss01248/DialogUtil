package com.hss01248.dialog;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.hss01248.dialog.config.ConfigBean;
import com.hss01248.dialog.view.IosAlertDialogHolder;
import com.hss01248.dialog.view.IosBottomSheetHolder;
import com.hss01248.dialog.view.IosCenterItemHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/5/4 0004.
 */
public class StyledDialog {

    //android.support.v7.app.

    public static Context context;

    private static int singleChosen;


    public static void init(Context context){
        StyledDialog.context = context;

    }

    /**
     * 传Context的后果是不会再响应后退键事件,需要自己处理.如果传递的是activity,仍然可以处理
     * @param context
     * @param msg
     * @param cancleable
     * @param outsideTouchable
     * @return
     */
    public static Dialog showMdLoading(Context context, String msg, boolean cancleable, boolean outsideTouchable) {

        Dialog dialog = Tool.buildDialog(context,cancleable,outsideTouchable);

        View root = View.inflate(context,R.layout.progressview_wrapconent,null);
        TextView tvMsg = (TextView) root.findViewById(R.id.message);
        tvMsg.setText(msg);
        dialog.setContentView(root);

        Tool.setDialogStyle(context,dialog,0);

        Tool.showDialog(dialog);
        //根据实际view高度调整
        return dialog;
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
    public static AlertDialog showMdAlert(Activity activity, String title, String msg,
                                          String firstTxt, String secondTxt, String thirdTxt,
                                          boolean outsideCancleable, boolean cancleable,
                                          final MyDialogListener listener){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(title)
                .setMessage(msg)
                .setCancelable(cancleable)
                .setPositiveButton(firstTxt, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       listener.onFirst();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(secondTxt, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    listener.onSecond();
                    dialog.dismiss();
                }
            }).setNeutralButton(thirdTxt, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    listener.onThird();
                    dialog.dismiss();
                }
            });
       AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(outsideCancleable);
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                listener.onCancle();
            }
        });
      //  Tool.setDialogStyle(activity,dialog,0);
       Tool.showDialog(dialog);
       return dialog;
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


    public static AlertDialog showMdSingleChoose(Activity context, String title, final int defaultChosen, final CharSequence[] words,
                                                 boolean outsideCancleable, boolean cancleable, CharSequence positiveTxt, CharSequence negtiveTxt,
                                                 final MyItemDialogListener listener, final MyDialogListener btnListener){


        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        singleChosen = defaultChosen;

        builder.setTitle(title)
                .setCancelable(cancleable)
                .setPositiveButton(positiveTxt, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (btnListener != null){
                            StyledDialog.dismiss(dialogInterface);
                            btnListener.onFirst();
                            btnListener.onGetChoose(singleChosen,words[singleChosen]);
                        }
                    }
                })
                .setNegativeButton(negtiveTxt, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (btnListener != null){
                            StyledDialog.dismiss(dialogInterface);
                            btnListener.onSecond();
                        }
                    }
                })
                .setSingleChoiceItems( words, defaultChosen, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        singleChosen = i;
                        if (listener != null){
                            listener.onItemClick(words[i],i);
                        }

                        if (btnListener == null){
                            StyledDialog.dismiss(dialogInterface);
                        }

                    }
                });

        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(outsideCancleable);
       // Tool.setDialogStyle(context,dialog,0);
        Tool.showDialog(dialog);
        return dialog;


    }

    public static AlertDialog showMdMultiChoose(Activity context, String title, final CharSequence[] words, final boolean[] checkedItems,
                                                CharSequence positiveTxt, CharSequence negtiveTxt,
                                                final MyDialogListener btnListener){


        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setCancelable(true)
                .setPositiveButton(positiveTxt, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (btnListener != null){
                            StyledDialog.dismiss(dialogInterface);
                            btnListener.onFirst();
                            btnListener.onGetChoose(checkedItems);
                        }
                    }
                })
                .setNegativeButton(negtiveTxt, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (btnListener != null){
                            StyledDialog.dismiss(dialogInterface);
                            btnListener.onSecond();
                        }
                    }
                })
        .setMultiChoiceItems(words, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b) {

            }
        })
               ;

        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(true);
       // Tool.setDialogStyle(context,dialog,0);
        Tool.showDialog(dialog);
        return dialog;


    }

   



    public static Dialog showIosAlert(Context activity, String title, String msg,
                                      String firstTxt, String secondTxt, String thirdTxt,
                                      boolean outsideCancleable, boolean cancleable,
                                      final MyDialogListener listener){
        return showIosAlert(activity,false,title,msg,"","",firstTxt,secondTxt,thirdTxt,outsideCancleable,cancleable,listener);
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
        return showIosAlert(activity,true,title,msg,"","",firstTxt,secondTxt,thirdTxt,outsideCancleable,cancleable,listener);
    }

    private static Dialog showIosAlert(Context context, boolean isButtonVerticle, String title, String msg,String hint1,String hint2,
                                       String firstTxt, String secondTxt, String thirdTxt,
                                       boolean outsideCancleable, boolean cancleable,
                                       final MyDialogListener listener){

        Dialog dialog = Tool.buildDialog(context,cancleable,outsideCancleable);

       ConfigBean bean =  new ConfigBean();
        bean.context = context;
        bean.isVertical = isButtonVerticle;
        bean.title = title;
        bean.msg = msg;
        bean.text1 = firstTxt;
        bean.text2 = secondTxt;
        bean.text3 = thirdTxt;
        bean.outsideTouchable = outsideCancleable;
        bean.cancelable = cancleable;
        bean.dialogListener = listener;

        bean.hint1 = hint1;
        bean.hint2 = hint2;

        IosAlertDialogHolder holder = new IosAlertDialogHolder(context);
        dialog.setContentView(holder.rootView);
        bean.dialog = dialog;
        holder.assingDatasAndEvents(context,bean);

        int height = Tool.mesureHeight(holder.rootView,holder.tvMsg,holder.et1,holder.et2);

        Tool.setDialogStyle(context,dialog,height);

       Tool.showDialog(dialog);
        return dialog;
    }

    public static Dialog showIosSingleChoose(Context context,  List<? extends CharSequence> words, boolean outsideCancleable, boolean cancleable,
                                             final MyItemDialogListener listener){
        Dialog dialog = Tool.buildDialog(context,cancleable,outsideCancleable);

        ConfigBean bean =  new ConfigBean();
        bean.context = context;
        bean.words = words;


        bean.outsideTouchable = outsideCancleable;
        bean.cancelable = cancleable;
        bean.itemDialogListener = listener;

        IosCenterItemHolder holder = new IosCenterItemHolder(context);
        dialog.setContentView(holder.rootView);
        bean.dialog = dialog;
        holder.assingDatasAndEvents(context,bean);

        int height = Tool.mesureHeight(holder.rootView,holder.lv);

        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);

        Tool.setDialogStyle(context,dialog,height);

       Tool.showDialog(dialog);
        return dialog;

    }



    public static Dialog showBottomItemDialog(Context context,
                                              List<? extends CharSequence> words, String bottomTxt,
                                              boolean outsideCancleable, boolean cancleable,
                                              final MyItemDialogListener listener){
        Dialog dialog = Tool.buildDialog(context,cancleable,outsideCancleable);



       // int measuredHeight =   assignBottomListDialogView(context,dialog,words,bottomTxt,listener);

        ConfigBean bean =  new ConfigBean();
        bean.context = context;
        bean.words = words;
        bean.bottomTxt = bottomTxt;

        bean.outsideTouchable = outsideCancleable;
        bean.cancelable = cancleable;
        bean.itemDialogListener = listener;

        IosBottomSheetHolder holder = new IosBottomSheetHolder(context);
        dialog.setContentView(holder.rootView);
        bean.dialog = dialog;
        holder.assingDatasAndEvents(context,bean);

        int height = Tool.mesureHeight(holder.rootView,holder.lv);

        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.mystyle);


        Tool.setDialogStyle(context,dialog,height);
        
        Tool.showDialog(dialog);


        return dialog;
    }


    public static Dialog showInputBox(Context context, boolean isButtonVerticle, String title, String msg,String hint1,String hint2,
                                      String firstTxt, String secondTxt, String thirdTxt,
                                      boolean outsideCancleable, boolean cancleable,
                                      final MyDialogListener listener){
        return showIosAlert(context,isButtonVerticle,title,msg,hint1,hint2,firstTxt,secondTxt,thirdTxt,outsideCancleable,cancleable,listener);
    }

    public static Dialog ShowNormalInput(Context context, String title, String hint1,String hint2,
                                         String firstTxt, String secondTxt,boolean cancleable,
                                         final MyDialogListener listener){

        return showInputBox(context,false,title,"",hint1,hint2,firstTxt,secondTxt,"",false,cancleable,listener);

    }





   


   





    

}
