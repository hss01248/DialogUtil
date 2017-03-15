package com.hss01248.dialog.interfaces;

/**
 * Created by Administrator on 2016/5/4 0004.
 */
public abstract class MyDialogListener {
    /**
     *
     */
    public abstract void onFirst();
    public abstract void onSecond();
    public void onThird(){}

    public void onCancle(){}

    /**
     * 提供给NormalInput的回调
     * @param input1
     * @param input2
     */
    public void onGetInput(CharSequence input1,CharSequence input2){

    }

    /**
     * 提供给MdSingleChoose的回调
     * @param chosen
     * @param chosenTxt
     */
    public void onGetChoose(int chosen,CharSequence chosenTxt){

    }

    /**
     * 提供给MdMultiChoose的回调
     * @param states
     */
    public void onGetChoose(boolean[] states){

    }


}
