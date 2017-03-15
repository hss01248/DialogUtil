package com.hss01248.dialog.interfaces;

/**
 * Created by Administrator on 2016/7/24.
 */
public abstract class MyItemDialogListener {


    /**
     * IosSingleChoose,BottomItemDialog,MdSingleChoose的点击条目回调
     * @param text
     * @param position
     */
   public abstract void onItemClick(CharSequence text, int position);


    /**
     * BottomItemDialog的底部按钮(经常是取消)的点击回调
     */
   public void onBottomBtnClick(){}

}
