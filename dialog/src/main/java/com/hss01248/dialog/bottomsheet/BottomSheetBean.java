package com.hss01248.dialog.bottomsheet;

import android.support.annotation.DrawableRes;

/**
 * Created by Administrator on 2016/10/19.
 */

public class BottomSheetBean {
    public @DrawableRes  int icon;
    public String text;

    public BottomSheetBean(){

    }

    public BottomSheetBean(int icon, String text) {
        this.icon = icon;
        this.text = text;
    }
}
