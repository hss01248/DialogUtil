package com.hss01248.dialog.config;

import androidx.annotation.ColorRes;

import com.hss01248.dialog.R;

/**
 * Created by huangshuisheng on 2017/11/13.
 */

public class BottomSheetStyle {
    public int txtSizeSp ;
    public @ColorRes
    int txtColor ;
    public int txtMarginTopDp ;
    public int iconSizeDp ;
    public int bottomTxtSizeSp ;
    public @ColorRes int bottomTxtColor ;
    public int bottomTxtHeightDp;

    public int gvMarginTopDp ;
    public int gvMarginBottomDp ;
    public int gcMarginLRDp ;
    public int gvItemMargin_H ;
    public int gvItemMargin_V ;


    public int getTxtSizeSp() {
        return txtSizeSp;
    }

    public int getTxtColor() {
        return txtColor;
    }

    public int getTxtMarginTopDp() {
        return txtMarginTopDp;
    }

    public int getIconSizeDp() {
        return iconSizeDp;
    }

    public int getBottomTxtSizeSp() {
        return bottomTxtSizeSp;
    }

    public int getBottomTxtColor() {
        return bottomTxtColor;
    }

    public int getBottomTxtHeightDp() {
        return bottomTxtHeightDp;
    }

    public int getGvMarginTopDp() {
        return gvMarginTopDp;
    }

    public int getGvMarginBottomDp() {
        return gvMarginBottomDp;
    }

    public int getGcMarginLRDp() {
        return gcMarginLRDp;
    }

    public int getGvItemMargin_H() {
        return gvItemMargin_H;
    }

    public int getGvItemMargin_V() {
        return gvItemMargin_V;
    }

    private BottomSheetStyle(Builder builder) {
        txtSizeSp = builder.txtSizeSp;
        txtColor = builder.txtColor;
        txtMarginTopDp = builder.txtMarginTopDp;
        iconSizeDp = builder.iconSizeDp;
        bottomTxtSizeSp = builder.bottomTxtSizeSp;
        bottomTxtColor = builder.bottomTxtColor;
        bottomTxtHeightDp = builder.bottomTxtHeightDp;
        gvMarginTopDp = builder.gvMarginTopDp;
        gvMarginBottomDp = builder.gvMarginBottomDp;
        gcMarginLRDp = builder.gcMarginLRDp;
        gvItemMargin_H = builder.gvItemMargin_H;
        gvItemMargin_V = builder.gvItemMargin_V;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(BottomSheetStyle copy) {
        Builder builder = new Builder();
        builder.txtSizeSp = copy.getTxtSizeSp();
        builder.txtColor = copy.getTxtColor();
        builder.txtMarginTopDp = copy.getTxtMarginTopDp();
        builder.iconSizeDp = copy.getIconSizeDp();
        builder.bottomTxtSizeSp = copy.getBottomTxtSizeSp();
        builder.bottomTxtColor = copy.getBottomTxtColor();
        builder.bottomTxtHeightDp = copy.getBottomTxtHeightDp();
        builder.gvMarginTopDp = copy.getGvMarginTopDp();
        builder.gvMarginBottomDp = copy.getGvMarginBottomDp();
        builder.gcMarginLRDp = copy.getGcMarginLRDp();
        builder.gvItemMargin_H = copy.getGvItemMargin_H();
        builder.gvItemMargin_V = copy.getGvItemMargin_V();
        return builder;
    }


    public static final class Builder {

        private int txtSizeSp = 12;
        private @ColorRes int txtColor = R.color.dialogutil_text_black;
        private int txtMarginTopDp = 4;
        private int iconSizeDp = 60;
        private int bottomTxtSizeSp = 16;
        private @ColorRes int bottomTxtColor = R.color.dialogutil_text_black_lighter;
        private int bottomTxtHeightDp = 49;

        private int gvMarginTopDp = 25;
        private int gvMarginBottomDp = 22;
        private int gcMarginLRDp = 20;
        private int gvItemMargin_H = 15;
        private int gvItemMargin_V = 15;

        private Builder() {
        }

        public Builder txtSizeSp(int val) {
            txtSizeSp = val;
            return this;
        }

        public Builder txtColor(int val) {
            txtColor = val;
            return this;
        }

        public Builder txtMarginTopDp(int val) {
            txtMarginTopDp = val;
            return this;
        }

        public Builder iconSizeDp(int val) {
            iconSizeDp = val;
            return this;
        }

        public Builder bottomTxtSizeSp(int val) {
            bottomTxtSizeSp = val;
            return this;
        }

        public Builder bottomTxtColor(int val) {
            bottomTxtColor = val;
            return this;
        }

        public Builder bottomTxtHeightDp(int val) {
            bottomTxtHeightDp = val;
            return this;
        }

        public Builder gvMarginTopDp(int val) {
            gvMarginTopDp = val;
            return this;
        }

        public Builder gvMarginBottomDp(int val) {
            gvMarginBottomDp = val;
            return this;
        }

        public Builder gcMarginLRDp(int val) {
            gcMarginLRDp = val;
            return this;
        }

        public Builder gvItemMargin_H(int val) {
            gvItemMargin_H = val;
            return this;
        }

        public Builder gvItemMargin_V(int val) {
            gvItemMargin_V = val;
            return this;
        }

        public BottomSheetStyle build() {
            return new BottomSheetStyle(this);
        }
    }
}
