package com.hss01248.dialog.adapter;

import android.content.Context;
import androidx.viewpager.widget.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/9.
 */

public abstract class SuperPagerAdapter extends PagerAdapter implements Refreshable,ILifeCycle{

    List datas = new ArrayList();
    List<SuperPagerHolder> mPagerHolders = new ArrayList<>();
    Context context;

    public SuperPagerAdapter(Context context){
        this.context = context;
    }


    @Override
    public int getCount() {
        Log.e("dd","SuperPagerAdapter.count:"+datas.size());
        return datas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        SuperPagerHolder holder = null;
        if(position >= mPagerHolders.size()){
            holder = generateNewHolder(context,container,position);
            mPagerHolders.add(holder);
        }else {
            holder = mPagerHolders.get(position);
        }
        if(holder == null){
            holder = generateNewHolder(context,container,position);
            mPagerHolders.add(holder);
        }
        holder.assingDatasAndEvents(context,datas.get(position),position);
        container.addView(holder.rootView);
        return holder.rootView;
    }

    protected abstract SuperPagerHolder generateNewHolder(Context context, ViewGroup container, int position);

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public void refresh(List newData) {
        datas.clear();
        datas.addAll(newData);
        notifyDataSetChanged();
    }

    @Override
    public void addAll(List newData) {
        datas.addAll(newData);
        notifyDataSetChanged();
    }

    @Override
    public void clear() {
        datas.clear();
        notifyDataSetChanged();
    }

    @Override
    public void delete(int position) {
        datas.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public void add(Object object) {
        datas.add(object);
        notifyDataSetChanged();
    }

    @Override
    public void onDestory() {

    }
}
