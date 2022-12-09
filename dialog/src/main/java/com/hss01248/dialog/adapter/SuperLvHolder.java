package com.hss01248.dialog.adapter;

import android.content.Context;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import android.view.View;

import java.util.List;



/**
 * Created by Administrator on 2016/4/15 0015.
 */
public abstract class SuperLvHolder<T> implements View.OnAttachStateChangeListener,ILifeCycle{
    public View rootView;

    /*public SuperLvHolder(){

    }*/

    public SuperLvHolder(Context context){
        if(setLayoutRes() !=0){
            rootView = View.inflate(context,setLayoutRes(),null);
        }else {
            rootView = setRootView(context);
        }
        rootView.addOnAttachStateChangeListener(this);

        //ButterKnife.bind(this,rootView);
        findViews();
    }

    protected View setRootView(Context context) {
        return null;
    }

    protected abstract void findViews();

    protected abstract  @LayoutRes  int setLayoutRes();

    /**
     * 一般情况下，实现这个方法就足够了
     * if use as custom view holder ,the bean will return as null,do not use it
     * @param context
     * @param bean
     */
    public  abstract void assingDatasAndEvents(Context context, @Nullable T bean);

    public void showKeyBoard(){

    }

    public void hideKeyBoard(){

    }

    /**
     * 如果有需要，才实现这个方法
     * @param context activity实例,用于一些点击事件
     * @param bean 该条目的数据
     * @param position 该条目所在的位置
     * @param isLast 是否为最后一条,有些情况下需要用到
     * @param isListViewFling listview是不是在惯性滑动,备用
     *  @param datas 整个listview对应的数据
     * @param superAdapter adapter对象引用,可用于触发notifydatesetChanged()方法刷新整个listview,比如更改的单选按钮
     */
    public void assingDatasAndEvents(Context context, T bean, int position , boolean isLast,
                                     boolean isListViewFling, List datas, SuperLvAdapter superAdapter){
        assingDatasAndEvents(context,bean);
    }

    @Override
    public void onViewAttachedToWindow(View v) {

    }

    @Override
    public void onViewDetachedFromWindow(View v) {

    }

    @Override
    public void onDestory() {

    }
}
