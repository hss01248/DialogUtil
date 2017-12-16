package com.hss01248.dialog.bottomsheet;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.hss01248.dialog.ScreenUtil;
import com.hss01248.dialog.Tool;
import com.hss01248.dialog.adapter.SuperLvAdapter;
import com.hss01248.dialog.adapter.SuperLvHolder;
import com.hss01248.dialog.adapter.SuperPagerHolder;
import com.hss01248.dialog.config.BottomSheetStyle;
import com.hss01248.dialog.config.ConfigBean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/9.
 */

public class BottomSheetPagerHolder extends SuperPagerHolder<List<BottomSheetBean>> {
    public BottomSheetPagerHolder(Context context) {
        super(context);
    }
    GridView mGridView;
    ConfigBean mConfigBean;
    SuperLvAdapter adapter;

    public BottomSheetPagerHolder setPageNum(int pageNum) {
        this.pageNum = pageNum;
        return this;
    }

    int pageNum;

    @Override
    protected ViewGroup setRootView(Context context) {
        mGridView = new GridView(context);
        return mGridView;
    }

    public BottomSheetPagerHolder setConfigBean(ConfigBean configBean){
        this.mConfigBean = configBean;
        setGridViewStyle(configBean);
        return this;
    }

    private void setGridViewStyle(final ConfigBean bean) {
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        BottomSheetStyle bottomSheetStyle = bean.bottomSheetStyle;

        mGridView.setLayoutParams(params);
        mGridView.setNumColumns(bean.gridColumns);
        mGridView.setVerticalSpacing(ScreenUtil.dip2px(bottomSheetStyle.gvItemMargin_V));
        mGridView.setHorizontalSpacing(ScreenUtil.dip2px(bottomSheetStyle.gvItemMargin_H));

             adapter = new SuperLvAdapter(bean.context) {
                @Override
                protected SuperLvHolder generateNewHolder(Context context, int itemViewType) {
                    BsGvHolder holder = new BsGvHolder(context);
                    holder.setStyle(bean.bottomSheetStyle);
                    return holder;
                }
            };

             mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                 @Override
                 public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                     if(bean.itemListener !=null){
                         int actualPosition = pageNum * bean.gridColumns * 2 +position;
                         bean.itemListener.onItemClick(bean.lvDatas.get(actualPosition).text,actualPosition);
                     }
                     Tool.dismiss(bean);
                 }
             });

        mGridView.setAdapter(adapter);
    }


    @Override
    protected int setLayoutRes() {
        return 0;
    }

    @Override
    protected void findViews() {

    }

    @Override
    public void assingDatasAndEvents(Context context, @Nullable List<BottomSheetBean> bean, int position) {
        adapter.refresh(bean);
    }
}
