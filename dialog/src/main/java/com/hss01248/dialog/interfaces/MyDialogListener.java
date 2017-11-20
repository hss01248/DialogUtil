package com.hss01248.dialog.interfaces;

import android.widget.EditText;

import java.util.List;

/**
 * Created by Administrator on 2016/5/4 0004.
 */
public abstract class MyDialogListener {

    /**
     * md-positive button ,ios-first button
     */
    public abstract void onFirst();//

    /**
     * md-negative button,ios-second
     */
    public abstract void onSecond();//
    public void onThird(){}//md-netural,ios-第三个

    public void onCancle(){}





    /**
     * 提供给Input的回调
     * @param input1
     * @param input2
     */
    public void onGetInput(CharSequence input1,CharSequence input2){

    }


    /**
     * 每次点击btn1,则传回输入值以便校验
     * @param input1
     * @param input2
     * @param editText1
     * @param editText2
     * @return
     */
    public boolean onInputValid(CharSequence input1, CharSequence input2, EditText editText1,EditText editText2){
        return true;
    }

    /**
     * 提供给MdSingleChoose的回调
     * @param chosenIdx
     * @param chosenTxt
     */
    public void onGetChoose(int chosenIdx,CharSequence chosenTxt){

    }

    /**
     * 提供给MdMultiChoose的回调
     * @param states
     */
    @Deprecated
    public void onGetChoose(boolean[] states){

    }

    /**
     * 提供给MdMultiChoose的更详细的回调
     * @param selectedIndex
     * @param selectedStrs
     * @param states
     */
    public void onChoosen( List<Integer> selectedIndex, List<CharSequence> selectedStrs,boolean[] states){

    }


}
