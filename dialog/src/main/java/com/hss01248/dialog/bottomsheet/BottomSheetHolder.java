package com.hss01248.dialog.bottomsheet;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.hss01248.dialog.R;
import com.hss01248.dialog.ScreenUtil;
import com.hss01248.dialog.Tool;
import com.hss01248.dialog.adapter.SuperLvAdapter;
import com.hss01248.dialog.adapter.SuperLvHolder;
import com.hss01248.dialog.adapter.SuperPagerAdapter;
import com.hss01248.dialog.adapter.SuperPagerHolder;
import com.hss01248.dialog.config.BottomSheetStyle;
import com.hss01248.dialog.config.ConfigBean;
import com.hss01248.dialog.config.DefaultConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/9.
 */

public class BottomSheetHolder extends SuperLvHolder<ConfigBean> {


    private TextView tvTitle;
    private View viewLineBottom;
    private TextView tvBottom;

    public BottomSheetHolder(Context context) {
        super(context);
    }

    @Override
    protected void findViews() {
        tvTitle = (TextView) rootView.findViewById(R.id.tv_title);
        viewLineBottom = (View) rootView.findViewById(R.id.view_line_bottom);
        tvBottom = (TextView) rootView.findViewById(R.id.tv_bottom);
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.bottomsheet_lv;
    }

    @Override
    public void assingDatasAndEvents(Context context, @Nullable final ConfigBean bean) {

        setTitleAndBottomButtonStyle(bean);
        AdapterView adapterView = null;
        if (bean.type == DefaultConfig.TYPE_BOTTOM_SHEET_LIST){
            adapterView =  setListView(context,bean);
        }else if(bean.type == DefaultConfig.TYPE_BOTTOM_SHEET_GRID){
            adapterView =   setGridView(context,bean);
        }

        setMdBottomSheetDialogBehaviour(adapterView,context,bean);




    }

    private void setMdBottomSheetDialogBehaviour(AdapterView adapterView, Context context, ConfigBean bean) {
        if(bean.hasBehaviour){
            //Tool.handleScrollInBottomSheetDialog(listView);
            if(adapterView !=null){
                Tool.handleScrollInBottomSheetDialog(adapterView);
            }

            //设置BottomSheetDialog的初始最大高度
            View view = bean.dialog.getWindow().findViewById(android.support.design.R.id.design_bottom_sheet);
            if(bean.bottomSheetDialogMaxHeightPercent >0f && bean.bottomSheetDialogMaxHeightPercent <1f){
                int peekHeight = (int) (bean.bottomSheetDialogMaxHeightPercent * ScreenUtil.getScreenHeight());
                BottomSheetBehavior.from(view).setPeekHeight(peekHeight);
            }
        }
    }

    private AdapterView setGridView(Context context, ConfigBean bean) {
        if(bean.hasBehaviour ){
            return buildSimpleGridView(context,bean);
        }else if(bean.lvDatas.size()<= 2* bean.gridColumns){
            return buildSimpleGridView(context,bean);
        }else {
            return buildGridViewWithViewPager(context,bean);
        }
    }

    private AdapterView buildGridViewWithViewPager(Context context, final ConfigBean bean) {
        ViewPager viewPager = new ViewPager(context);
        BottomSheetStyle style = bean.bottomSheetStyle;
        int height = ScreenUtil.dip2px( style.iconSizeDp+style.txtMarginTopDp+style.txtSizeSp+5)*2+ScreenUtil.dip2px(style.gvItemMargin_V);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
       // params.weight = 1;
       // params.height = 0;

        params.topMargin = ScreenUtil.dip2px(style.gvMarginTopDp);
        params.bottomMargin = ScreenUtil.dip2px(style.gvMarginBottomDp);
        params.leftMargin = ScreenUtil.dip2px(style.gcMarginLRDp);
        params.rightMargin = params.leftMargin;
        viewPager.setLayoutParams(params);


        final List<List<BottomSheetBean>> datas = new ArrayList<>();
        List<BottomSheetBean> data = null;
        for (int i = 0; i < bean.lvDatas.size(); i++) {
            if(i / bean.gridColumns/2 == datas.size()){
                data = new ArrayList<>();
                datas.add(data);
                data.add(bean.lvDatas.get(i));
            }else {
                data.add(bean.lvDatas.get(i));
            }
        }
        SuperPagerAdapter pagerAdapter = new SuperPagerAdapter(bean.context) {
            @Override
            protected SuperPagerHolder generateNewHolder(Context context, ViewGroup container, int position) {
                return new BottomSheetPagerHolder(context).setConfigBean(bean).setPageNum(position);
            }
        };

        viewPager.setAdapter(pagerAdapter);
        pagerAdapter.addAll(datas);
        ((ViewGroup)rootView).addView(viewPager,1);
        return null;
    }

    private AdapterView buildSimpleGridView(Context context, final ConfigBean bean) {
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
        bean.mAdapter.addAll(bean.lvDatas);

        ((ViewGroup)rootView).addView(listView,1);
        return listView;
    }

    private AbsListView setListView(Context context, final ConfigBean bean) {
        ListView listView = new ListView(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        listView.setLayoutParams(params);
        listView.setDividerHeight(0);

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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BottomSheetBean sheetBean =  bean.lvDatas.get(position);
                bean.itemListener.onItemClick(sheetBean.text,position);
                Tool.dismiss(bean);
            }
        });

        bean.mAdapter.addAll(bean.lvDatas);
        ((ViewGroup)rootView).addView(listView,1);
        return listView;
    }

    private void setTitleAndBottomButtonStyle(final ConfigBean bean) {
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
                    //finalDialog.dismiss();
                    if(bean.itemListener!=null){
                        bean.itemListener.onBottomBtnClick();
                    }
                    Tool.dismiss(bean);
                }
            });
        }else {
            tvBottom.setVisibility(View.GONE);
        }
    }


}
