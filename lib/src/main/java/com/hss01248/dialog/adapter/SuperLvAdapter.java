package com.hss01248.dialog.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 单一的item
 * Created by Administrator on 2016/4/15 0015.
 */
public abstract class SuperLvAdapter extends BaseAdapter implements Refreshable {
    List datas;
    Context context;
    boolean isListViewFling;

    public boolean isListViewFling() {
        return isListViewFling;
    }

    public void setListViewFling(boolean listViewFling) {
        isListViewFling = listViewFling;
    }


    public SuperLvAdapter(Context context){
        this.datas = new ArrayList();
        this.context = context;
    }
    @Override
    public int getCount() {
        if (datas == null )
        return 0;
        return datas.size();
    }



    @Override
    public Object getItem(int position) {
        if (datas == null)
        return null;
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        if (datas == null){
            return 0;
        }
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SuperLvHolder holder = null;
        if (convertView == null){
            holder = generateNewHolder(context,getItemViewType(position));
            convertView = holder.rootView;
            convertView.setTag(holder);
        }else {
            holder = (SuperLvHolder) convertView.getTag();
        }
        holder.assingDatasAndEvents(context,datas.get(position),position,position == getCount() -1,isListViewFling,datas,this);
        return convertView;
    }

    protected abstract SuperLvHolder generateNewHolder(Context context, int itemViewType);
    @Override
    public void refresh(List newData){
        if (newData == null){
            datas.clear();
            notifyDataSetChanged();
            return;
        }
        if (datas == null){
            datas = newData;
            notifyDataSetChanged();
        }else {
            datas.clear();
            datas.addAll(newData);
            notifyDataSetChanged();
        }
    }
    @Override
    public void addAll(List newData){
        if (newData == null){
            return;
        }
        if (datas == null){
            datas = newData;
            notifyDataSetChanged();
        }else {
            datas.addAll(newData);
            notifyDataSetChanged();
        }
    }
    @Override
    public void clear(){
        if (datas != null){
            datas.clear();
            notifyDataSetChanged();
        }
    }
    @Override
    public void delete(int position){
        if (datas != null && position < getCount()){
            datas.remove(position);
            notifyDataSetChanged();
        }
    }
    public List getListData(){
        return datas;
    }

    @Override
    public void add(Object object) {
        if (object ==null)
            return;
        try {
            datas.add(object);
            notifyDataSetChanged();
        }catch (Exception e){

        }
    }
}
