package com.hss01248.dialog.bottomsheet;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2017/12/9.
 */

public class BottomViewPager extends PagerAdapter {
    List<List<BottomSheetBean>> datas;

    public BottomViewPager(){

    }

    public BottomViewPager(List<List<BottomSheetBean>> datas){
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}
