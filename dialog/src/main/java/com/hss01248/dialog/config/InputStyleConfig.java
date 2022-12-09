package com.hss01248.dialog.config;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

/**
 * Created by Administrator on 2017/12/7.
 */

public class InputStyleConfig {
    public @StringRes int hint1;
    public @StringRes int hint2;

    public CharSequence inputOriginal1 ;
    public CharSequence inputOriginal2 ;

    public @StringRes int tagTxt1;
    public @StringRes int tagTxt2;

    public boolean isInput2HideAsPassword = true;

    public  int inputTxtSize = DefaultConfig.inputTxtSize;
    public @ColorRes int inputTxtColor ;
    public @ColorRes int hintTxtColor ;

    public  @DrawableRes int cursorResId;
    public @DrawableRes int editTextBg;
    public int editTextPaddingTopBottomInDp;
    public int editTextPaddingLeftRightInDp;





}
