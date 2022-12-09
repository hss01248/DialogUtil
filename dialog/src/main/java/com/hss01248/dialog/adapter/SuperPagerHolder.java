package com.hss01248.dialog.adapter;

import android.content.Context;
import androidx.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/12/9.
 */

public abstract class SuperPagerHolder<T> implements View.OnAttachStateChangeListener,ILifeCycle{

    public ViewGroup rootView;

    public SuperPagerHolder(Context context){
        int layoutRes = setLayoutRes();
        if(layoutRes !=0){
            rootView = (ViewGroup) View.inflate(context,setLayoutRes(),null);
        }else {
            rootView = setRootView(context);
        }
        rootView.addOnAttachStateChangeListener(this);
        //ButterKnife.bind(this,rootView);
        findViews();
    }

    protected ViewGroup setRootView(Context context) {
        return null;
    }

    protected abstract int setLayoutRes();

    protected abstract void findViews();

    public  abstract void assingDatasAndEvents(Context context, @Nullable T bean,int position);

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
