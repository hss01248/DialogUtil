package com.hss01248.dialog.bottomsheet;

import android.content.Context;
import androidx.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hss01248.dialog.R;
import com.hss01248.dialog.ScreenUtil;
import com.hss01248.dialog.adapter.SuperLvHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/10.
 */

public class BottomVpIndicatorHolder extends SuperLvHolder<List<List<BottomSheetBean>>> {

    public LinearLayout mLinearLayout;
    List<View> dots;
    public BottomVpIndicatorHolder(Context context) {
        super(context);
    }

    @Override
    protected View setRootView(Context context) {
        mLinearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        params.bottomMargin = ScreenUtil.dip2px(10);
        params.gravity = Gravity.CENTER_HORIZONTAL;
        mLinearLayout.setLayoutParams(params);
        dots = new ArrayList<>();

        return mLinearLayout;
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected int setLayoutRes() {
        return 0;
    }

    public void onPageSelected(int position){
        for (int i = 0; i < dots.size(); i++) {
            View dot = dots.get(i);
            if(position ==i){
                dot.setEnabled(true);
            }else {
                dot.setEnabled(false);
            }
        }
    }

    @Override
    public void assingDatasAndEvents(Context context, @Nullable List<List<BottomSheetBean>> bean) {
        if(bean ==null || bean.size() ==0){
            return;
        }
        int size = bean.size();
        int height = ScreenUtil.dip2px(5);
        int margin = ScreenUtil.dip2px(8);
        for (int i = 0; i < size; i++) {
            View dot = new View(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(height,height);
            dot.setBackgroundResource(R.drawable.dialogutil_dot_selector);
            if(i==size-1){
                params.setMargins(0,0,0,0);
            }else {
                params.setMargins(0,0,margin,0);
            }
            dot.setLayoutParams(params);
            dot.setEnabled(false);
            dots.add(dot);
            mLinearLayout.addView(dot);
        }
    }
}
