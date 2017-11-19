package com.hss01248.dialog.interfaces;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.hss01248.dialog.R;
import com.hss01248.dialog.ScreenUtil;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.Tool;
import com.hss01248.dialog.adapter.SuperLvAdapter;
import com.hss01248.dialog.adapter.SuperLvHolder;
import com.hss01248.dialog.bottomsheet.BottomSheetBean;
import com.hss01248.dialog.config.BottomSheetStyle;
import com.hss01248.dialog.bottomsheet.BsGvHolder;
import com.hss01248.dialog.bottomsheet.BsLvHolder;
import com.hss01248.dialog.config.ConfigBean;
import com.hss01248.dialog.config.DefaultConfig;
import com.hss01248.dialog.view.IosActionSheetHolder;
import com.hss01248.dialog.view.IosAlertDialogHolder;
import com.hss01248.dialog.view.IosCenterItemHolder;
import com.hss01248.dialog.view.MdInputHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/9.
 */
public  class MyDialogBuilder {

    protected static int singleChosen;
   protected  ConfigBean buildByType(ConfigBean bean){
       Tool.fixContext(bean);


       switch (bean.type){
           case DefaultConfig.TYPE_MD_LOADING:
               Tool.newCustomDialog(bean);
               buildMdLoading(bean);
               break;
           case DefaultConfig.TYPE_PROGRESS:
               buildProgress(bean);
               break;
           case DefaultConfig.TYPE_MD_ALERT:
           case DefaultConfig.TYPE_MD_INPUT:
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
               if(bean.customContentHolder!=null){
                   bean.dialog.setContentView(bean.customContentHolder.rootView);
                   bean.customContentHolder.assingDatasAndEvents(bean.context,null);
               }else {
                   bean.dialog.setContentView(bean.customView);
               }



               break;
           case DefaultConfig.TYPE_BOTTOM_SHEET_CUSTOM:
              buildBottomSheet(bean);


               break;
           case DefaultConfig.TYPE_BOTTOM_SHEET_LIST:
               buildBottomSheetLv(bean);


               break;
           case DefaultConfig.TYPE_BOTTOM_SHEET_GRID:
               buildBottomSheetLv(bean);

               break;

           case DefaultConfig.TYPE_IOS_LOADING:
               Tool.newCustomDialog(bean);
               buildLoading(bean);
               break;
          default:
              break;


       }

       Tool.setWindowAnimation(bean);
       Tool.setCancelable(bean);
       Tool.setCancelListener(bean);
       Tool.adjustStyle(bean);
       return bean;
   }

    private void buildProgress(ConfigBean bean) {
        ProgressDialog dialog = new ProgressDialog(bean.context);
        dialog.setTitle("");
        dialog.setMessage(bean.msg);
        dialog.setProgressStyle(bean.isProgressHorzontal ? ProgressDialog.STYLE_HORIZONTAL:ProgressDialog.STYLE_SPINNER);
        dialog.setIndeterminate(false);
        bean.dialog = dialog;
    }



    private void buildBottomSheetLv(final ConfigBean bean) {
         Dialog dialog = null;
       if(bean.hasBehaviour){
            dialog = new BottomSheetDialog(bean.context);
       }else {
           Tool.newCustomDialog(bean);
           dialog = bean.dialog;
           bean.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
           bean.widthPercent= 0.99f;
           bean.heightPercent = 0;
           bean.bgRes = R.color.dialogutil_bg_white;
       }
        final Dialog finalDialog = dialog;

        if(bean.bottomSheetStyle ==null){
            bean.bottomSheetStyle =  BottomSheetStyle.newBuilder().build();
        }

        LinearLayout root = (LinearLayout) View.inflate(bean.context, R.layout.bottomsheet_lv,null);
        TextView tvTitle = (TextView) root.findViewById(R.id.tv_title);
        TextView tvBottom = (TextView) root.findViewById(R.id.tv_bottom);
        if (TextUtils.isEmpty(bean.title)){
            tvTitle.setVisibility(View.GONE);
        }else {
            tvTitle.setText(bean.title);
            tvTitle.setVisibility(View.VISIBLE);
        }
        if(!TextUtils.isEmpty(bean.bottomTxt)){
            tvBottom.setVisibility(View.VISIBLE);
            tvBottom.setText(bean.bottomTxt);
            tvBottom.setTextSize(bean.bottomSheetStyle.bottomTxtSizeSp);
            tvBottom.setTextColor(tvBottom.getContext().getResources().getColor(bean.bottomSheetStyle.bottomTxtColor));

            tvBottom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finalDialog.dismiss();
                }
            });
        }





        if (bean.type == DefaultConfig.TYPE_BOTTOM_SHEET_LIST){
            ListView listView = new ListView(bean.context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            listView.setLayoutParams(params);
            listView.setDividerHeight(0);
            root.addView(listView,1);

            if (bean.mAdapter == null){
                SuperLvAdapter adapter = new SuperLvAdapter(bean.context) {
                    @Override
                    protected SuperLvHolder generateNewHolder(Context context, int itemViewType) {
                        return new BsLvHolder(context);
                    }
                };

                bean.mAdapter = adapter;
            }


            listView.setAdapter(bean.mAdapter);

            final Dialog finalDialog1 = dialog;
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    BottomSheetBean sheetBean = (BottomSheetBean) bean.lvDatas.get(position);
                    finalDialog1.dismiss();
                    bean.itemListener.onItemClick(sheetBean.text,position);
                }
            });
            if(bean.hasBehaviour){
                Tool.handleScrollInBottomSheetDialog(listView);
            }

            //处理滑动冲突


            bean.mAdapter.addAll(bean.lvDatas);
        }else if(bean.type == DefaultConfig.TYPE_BOTTOM_SHEET_GRID){
            GridView listView = new GridView(bean.context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            BottomSheetStyle bottomSheetStyle = bean.bottomSheetStyle;
            params.topMargin = ScreenUtil.dip2px(bottomSheetStyle.gvMarginTopDp);
            params.bottomMargin = ScreenUtil.dip2px(bottomSheetStyle.gvMarginBottomDp);
            params.leftMargin = ScreenUtil.dip2px(bottomSheetStyle.gcMarginLRDp);
            params.rightMargin = params.leftMargin;


            listView.setLayoutParams(params);
            listView.setNumColumns(bean.gridColumns);
            listView.setVerticalSpacing(ScreenUtil.dip2px(bottomSheetStyle.gvItemMargin_V));
            listView.setHorizontalSpacing(ScreenUtil.dip2px(bottomSheetStyle.gvItemMargin_H));

            root.addView(listView,1);

            if (bean.mAdapter == null){
                SuperLvAdapter adapter = new SuperLvAdapter(bean.context) {
                    @Override
                    protected SuperLvHolder generateNewHolder(Context context, int itemViewType) {
                        BsGvHolder holder = new BsGvHolder(context);
                        holder.setStyle(bean.bottomSheetStyle);
                        return holder;
                    }
                };
                bean.mAdapter = adapter;
            }


            listView.setAdapter(bean.mAdapter);

            final Dialog finalDialog2 = dialog;
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    BottomSheetBean sheetBean = (BottomSheetBean) bean.lvDatas.get(position);
                    finalDialog2.dismiss();
                    bean.itemListener.onItemClick(sheetBean.text,position);
                }
            });
            if(bean.hasBehaviour){
                Tool.handleScrollInBottomSheetDialog(listView);
            }


            bean.mAdapter.addAll(bean.lvDatas);
        }



        dialog.setContentView(root);

        if(bean.hasBehaviour){
            //设置BottomSheetDialog的最大高度
            View view = dialog.getWindow().findViewById(android.support.design.R.id.design_bottom_sheet);
            if(bean.bottomSheetDialogMaxHeightPercent >0 && bean.bottomSheetDialogMaxHeightPercent <1){
                int peekHeight = (int) (bean.bottomSheetDialogMaxHeightPercent * ScreenUtil.getScreenHeight());
                BottomSheetBehavior.from(view).setPeekHeight(peekHeight);
            }
        }




        bean.dialog = dialog;
    }

    private void buildBottomSheet(ConfigBean bean) {
        final BottomSheetDialog dialog = new BottomSheetDialog(bean.context);
        dialog.setContentView(bean.customView);
        dialog.setCancelable(bean.cancelable);
        dialog.setCanceledOnTouchOutside(bean.outsideTouchable);
        bean.dialog = dialog;
    }

    protected  ConfigBean buildLoading(ConfigBean bean){
        View root = View.inflate(bean.context, R.layout.loading,null);
        ImageView gifMovieView = (ImageView) root.findViewById(R.id.iv_loading);
        AnimationDrawable drawable = (AnimationDrawable) gifMovieView.getDrawable();
        if(drawable!=null){
            drawable.start();
        }
        TextView tvMsg = (TextView) root.findViewById(R.id.loading_msg);
        StyledDialog.setTv_msg(tvMsg);
        tvMsg.setText(bean.msg);
        bean.dialog.setContentView(root);
        return bean;
    }


    protected  ConfigBean buildMdLoading(ConfigBean bean){
        View root = View.inflate(bean.context, R.layout.progressview_wrapconent,null);
        TextView tvMsg = (TextView) root.findViewById(R.id.loading_msg);
        StyledDialog.setTv_msg(tvMsg);
        tvMsg.setText(bean.msg);
        bean.dialog.setContentView(root);
        return bean;
    }

    protected  ConfigBean buildMdAlert(final ConfigBean bean){
        Tool.fixContext(bean);
        final AlertDialog.Builder builder = new AlertDialog.Builder(bean.context);
        if(bean.customContentHolder ==null){
            if(bean.type == DefaultConfig.TYPE_MD_INPUT){
                MdInputHolder holder = new MdInputHolder(bean.context);
                bean.viewHolder = holder;
                bean.setNeedSoftKeyboard(true);
                holder.assingDatasAndEvents(bean.context,bean);
                builder.setView(bean.viewHolder.rootView);
            }else {
                builder.setMessage(bean.msg);
            }

        }else {
            builder.setView(bean.customContentHolder.rootView);
            bean.customContentHolder.assingDatasAndEvents(Tool.fixContext(bean).context,bean);
        }
        builder.setTitle(bean.title)
                .setPositiveButton(bean.text1, null)//不让点击默认消失，而是做出判断，见Tool.setMdBtnStytle
                .setNegativeButton(bean.text2, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        bean.listener.onSecond();
                        Tool.hideKeyBorad(bean);
                        dialog.dismiss();
                    }
                }).setNeutralButton(bean.text3, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            bean.listener.onThird();
                            Tool.hideKeyBorad(bean);
                            dialog.dismiss();
                        }
        });
        AlertDialog dialog = builder.create();
        
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Tool.hideKeyBorad(bean);
                if(bean.listener!=null)
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

        AlertDialog dialog = builder.create();// crash when context is not activity
        bean.alertDialog = dialog;
        //dialog.getWindow().getDecorView()
       // addShaow(bean,dialog);


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
                            List<Integer> selectedIndex = new ArrayList<Integer>();
                            List<CharSequence> selectedStrs = new ArrayList<CharSequence>();
                            for(int j=0;j<bean.checkedItems.length;j++){
                                if(bean.checkedItems[j]){
                                    selectedIndex.add(j);
                                    selectedStrs.add(bean.wordsMd[j]);
                                }
                            }
                            bean.listener.onChoosen(selectedIndex,selectedStrs,bean.checkedItems);


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
        bean.viewHolder = holder;

        bean.dialog.setContentView(holder.rootView);
        holder.assingDatasAndEvents(bean.context,bean);

        bean.viewHeight = Tool.mesureHeight(holder.rootView,holder.lv);

        Window window = bean.dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        return bean;
    }

    protected  ConfigBean buildBottomItemDialog(ConfigBean bean){
        IosActionSheetHolder holder = new IosActionSheetHolder(bean.context);
        bean.viewHolder = holder;
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
        bean.viewHolder = holder;
        bean.dialog.setContentView(holder.rootView);
        holder.assingDatasAndEvents(bean.context,bean);

        int height = Tool.mesureHeight(holder.rootView,holder.tvMsg,holder.et1,holder.et2);
        bean.viewHeight = height;


        return bean;

    }







    
}
