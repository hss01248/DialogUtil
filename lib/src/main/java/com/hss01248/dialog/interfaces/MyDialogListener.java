package com.hss01248.dialog.interfaces;

/**
 * Created by Administrator on 2016/5/4 0004.
 */
public abstract class MyDialogListener {
    public abstract void onFirst();
    public abstract void onSecond();
    public void onThird(){}

    public void onCancle(){}

    public void onGetInput(CharSequence input1,CharSequence input2){

    }

    public void onGetChoose(int chosen,CharSequence chosenTxt){

    }

    public void onGetChoose(boolean[] states){

    }


}
