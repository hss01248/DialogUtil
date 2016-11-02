package com.hss01248.dialog.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;


/**
 * Created by Administrator on 2016/8/22 0022.
 */
public abstract  class SuperRcvHolder<T> extends RecyclerView.ViewHolder {

    public  View rootView;

    public SuperRcvHolder(View itemView) {
        super(itemView);
        rootView = itemView;
       // ButterKnife.bind(this,rootView);
    }

    /**
     * 如果有需要，才实现这个方法
     * @param context activity实例,用于一些点击事件
     * @param data 该条目的数据
     * @param position 该条目所在的位置
     * @param isLast 是否为最后一条,有些情况下需要用到
     * @param isListViewFling listview是不是在惯性滑动,备用
     *  @param datas 整个listview对应的数据
     * @param superRecyAdapter adapter对象引用,可用于触发notifydatesetChanged()方法刷新整个listview,比如更改的单选按钮
     */
    public  void assignDatasAndEvents(Activity context, T data, int position,
                                              boolean isLast, boolean isListViewFling,List datas, SuperRcvAdapter superRecyAdapter){
        assignDatasAndEvents(context,data);
    }

    /**
     * 一般情况下实现此方法
     * @param context
     * @param data
     */
    public  abstract   void assignDatasAndEvents(Activity context, T data);


}
