package com.hss01248.dialog.interfaces;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.hss01248.dialog.R;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.Tool;
import com.hss01248.dialog.config.ConfigBean;
import com.hss01248.dialog.config.DefaultConfig;
import com.hss01248.dialog.view.IosAlertDialogHolder;
import com.hss01248.dialog.view.IosBottomSheetHolder;
import com.hss01248.dialog.view.IosCenterItemHolder;

/**
 * Created by Administrator on 2016/10/9.
 */
public  class Buildable {

    protected static int singleChosen;
   protected  ConfigBean buildByType(ConfigBean bean){
       Tool.fixContext(bean);

       switch (bean.type){
           case DefaultConfig.TYPE_MD_LOADING:
               Tool.newCustomDialog(bean);
               buildMdLoading(bean);
               break;
           case DefaultConfig.TYPE_MD_ALERT:
               buildMdAlert(bean);
               break;
           case DefaultConfig.TYPE_MD_SINGLE_CHOOSE:
               buildMdSingleChoose(bean);
               break;
           case DefaultConfig.TYPE_MD_MULTI_CHOOSE:
               buildMdMultiChoose(bean);
               break;
           case DefaultConfig.TYPE_IOS_HORIZONTAL:
               Tool.newCustomDialog(bean);
               buildIosAlert(bean);
               break;
           case DefaultConfig.TYPE_IOS_VERTICAL:
               Tool.newCustomDialog(bean);
               buildIosAlertVertical(bean);
               break;
           case DefaultConfig.TYPE_IOS_BOTTOM:
               Tool.newCustomDialog(bean);
               buildBottomItemDialog(bean);
               break;
           case DefaultConfig.TYPE_IOS_INPUT:
               Tool.newCustomDialog(bean);
               buildNormalInput(bean);
               break;
           case DefaultConfig.TYPE_IOS_CENTER_LIST:
               Tool.newCustomDialog(bean);
               buildIosSingleChoose(bean);
               break;
           case DefaultConfig.TYPE_CUSTOM_VIEW:
               Tool.newCustomDialog(bean);
               bean.dialog.setContentView(bean.customView);
               bean.dialog.getWindow().setGravity(bean.gravity);

               break;
          default:
              break;


       }
       
       Tool.setDialogStyle(bean);

       Tool.setCancelable(bean);
       return bean;
   }



    protected  ConfigBean buildMdLoading(ConfigBean bean){
        View root = View.inflate(bean.context, R.layout.progressview_wrapconent,null);
        TextView tvMsg = (TextView) root.findViewById(R.id.message);
        tvMsg.setText(bean.msg);
        bean.dialog.setContentView(root);
        return bean;
    }

    protected  ConfigBean buildMdAlert(final ConfigBean bean){
        AlertDialog.Builder builder = new AlertDialog.Builder(bean.context);
        builder.setTitle(bean.title)
                .setMessage(bean.msg)
                .setPositiveButton(bean.text1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        bean.listener.onFirst();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(bean.text2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        bean.listener.onSecond();
                        dialog.dismiss();
                    }
                }).setNeutralButton(bean.text3, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                bean.listener.onThird();
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                bean.listener.onCancle();
            }
        });
        bean.alertDialog = dialog;
        return bean;
    }

    protected  ConfigBean buildMdSingleChoose(final ConfigBean bean){
        AlertDialog.Builder builder = new AlertDialog.Builder(bean.context);
        singleChosen = bean.defaultChosen;
        builder.setTitle(bean.title)
                .setPositiveButton(bean.text1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (bean.listener != null){
                            StyledDialog.dismiss(dialogInterface);
                            bean.listener.onFirst();
                            bean.listener.onGetChoose(singleChosen,bean.wordsMd[singleChosen]);
                        }
                    }
                })
                .setNegativeButton(bean.text2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (bean.listener != null){
                            StyledDialog.dismiss(dialogInterface);
                            bean.listener.onSecond();
                        }
                    }
                })
                .setSingleChoiceItems( bean.wordsMd, bean.defaultChosen, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        singleChosen = i;
                        if (bean.itemListener != null){
                            bean.itemListener.onItemClick(bean.wordsMd[i],i);
                        }

                        if (bean.listener == null){
                            StyledDialog.dismiss(dialogInterface);
                        }

                    }
                });

        AlertDialog dialog = builder.create();
        bean.alertDialog = dialog;
        return bean;
    }

    protected  ConfigBean buildMdMultiChoose(final ConfigBean bean){
        AlertDialog.Builder builder = new AlertDialog.Builder(bean.context);
        builder.setTitle(bean.title)
                .setCancelable(true)
                .setPositiveButton(bean.text1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (bean.listener != null){
                            StyledDialog.dismiss(dialogInterface);
                            bean.listener.onFirst();
                            bean.listener.onGetChoose(bean.checkedItems);
                        }
                    }
                })
                .setNegativeButton(bean.text2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (bean.listener != null){
                            StyledDialog.dismiss(dialogInterface);
                            bean.listener.onSecond();
                        }
                    }
                })
                .setMultiChoiceItems(bean.wordsMd, bean.checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {

                    }
                })
        ;

        AlertDialog dialog = builder.create();
        bean.alertDialog = dialog;
        return bean;
    }

    protected  ConfigBean buildIosAlert(ConfigBean bean){
        bean.isVertical = false;
        bean.hint1 = "";
        bean.hint2 = "";
        buildIosCommon(bean);
        return bean;
    }

    protected  ConfigBean buildIosAlertVertical(ConfigBean bean){
        bean.isVertical = true;
        bean.hint1 = "";
        bean.hint2 = "";
        buildIosCommon(bean);
        return bean;
    }

    protected  ConfigBean buildIosSingleChoose(ConfigBean bean){
        IosCenterItemHolder holder = new IosCenterItemHolder(bean.context);
        bean.dialog.setContentView(holder.rootView);
        holder.assingDatasAndEvents(bean.context,bean);

        bean.viewHeight = Tool.mesureHeight(holder.rootView,holder.lv);

        Window window = bean.dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        return bean;
    }

    protected  ConfigBean buildBottomItemDialog(ConfigBean bean){
        IosBottomSheetHolder holder = new IosBottomSheetHolder(bean.context);
        bean.dialog.setContentView(holder.rootView);

        holder.assingDatasAndEvents(bean.context,bean);

        bean.viewHeight = Tool.mesureHeight(holder.rootView,holder.lv);

        Window window = bean.dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.mystyle);
        return bean;
    }


    protected  ConfigBean buildNormalInput(ConfigBean bean){
        buildIosCommon(bean);
        return bean;
    }

    private ConfigBean buildIosCommon(ConfigBean bean){



        IosAlertDialogHolder holder = new IosAlertDialogHolder(bean.context);
        bean.dialog.setContentView(holder.rootView);
        holder.assingDatasAndEvents(bean.context,bean);

        int height = Tool.mesureHeight(holder.rootView,holder.tvMsg,holder.et1,holder.et2);
        bean.viewHeight = height;


        return bean;

    }







    
}
