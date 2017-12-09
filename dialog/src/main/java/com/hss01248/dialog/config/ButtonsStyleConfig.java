package com.hss01248.dialog.config;

import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;

/**
 * Created by Administrator on 2017/12/7.
 */

public class ButtonsStyleConfig {

    public @StringRes int btn1Text ;
    public @StringRes int btn2Text ;
    public @StringRes int btn3Text;

    public  @ColorRes int btn1Color = DefaultConfig.iosBtnColor;
    public  @ColorRes int btn2Color= DefaultConfig.mdBtnCancelColor;
    public  @ColorRes int btn3Color= DefaultConfig.iosBtnColor;

    public  int btnTxtSize = DefaultConfig.btnTxtSize;// in sp

    public boolean isTxtBold = false;

    public int getBtn1Text() {
        return btn1Text;
    }

    public int getBtn2Text() {
        return btn2Text;
    }

    public int getBtn3Text() {
        return btn3Text;
    }

    public int getBtn1Color() {
        return btn1Color;
    }

    public int getBtn2Color() {
        return btn2Color;
    }

    public int getBtn3Color() {
        return btn3Color;
    }

    public int getBtnTxtSize() {
        return btnTxtSize;
    }

    public boolean isTxtBold() {
        return isTxtBold;
    }

    private ButtonsStyleConfig(Builder builder) {
        btn1Text = builder.btn1Text;
        btn2Text = builder.btn2Text;
        btn3Text = builder.btn3Text;
        btn1Color = builder.btn1Color;
        btn2Color = builder.btn2Color;
        btn3Color = builder.btn3Color;
        btnTxtSize = builder.btnTxtSize;
        isTxtBold = builder.isTxtBold;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(ButtonsStyleConfig copy) {
        Builder builder = new Builder();
        builder.btn1Text = copy.getBtn1Text();
        builder.btn2Text = copy.getBtn2Text();
        builder.btn3Text = copy.getBtn3Text();
        builder.btn1Color = copy.getBtn1Color();
        builder.btn2Color = copy.getBtn2Color();
        builder.btn3Color = copy.getBtn3Color();
        builder.btnTxtSize = copy.getBtnTxtSize();
        builder.isTxtBold = copy.isTxtBold();
        return builder;
    }


    public static final class Builder {
        private int btn1Text;
        private int btn2Text;
        private int btn3Text;
        private int btn1Color;
        private int btn2Color;
        private int btn3Color;
        private int btnTxtSize;
        private boolean isTxtBold;

        private Builder() {
        }

        public Builder btn1Text(int val) {
            btn1Text = val;
            return this;
        }

        public Builder btn2Text(int val) {
            btn2Text = val;
            return this;
        }

        public Builder btn3Text(int val) {
            btn3Text = val;
            return this;
        }

        public Builder btn1Color(int val) {
            btn1Color = val;
            return this;
        }

        public Builder btn2Color(int val) {
            btn2Color = val;
            return this;
        }

        public Builder btn3Color(int val) {
            btn3Color = val;
            return this;
        }

        public Builder btnTxtSize(int val) {
            btnTxtSize = val;
            return this;
        }

        public Builder isTxtBold(boolean val) {
            isTxtBold = val;
            return this;
        }

        public ButtonsStyleConfig build() {
            return new ButtonsStyleConfig(this);
        }
    }
}
