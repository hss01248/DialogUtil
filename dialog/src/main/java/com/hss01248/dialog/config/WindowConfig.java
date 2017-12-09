package com.hss01248.dialog.config;

import android.support.annotation.DrawableRes;
import android.view.View;

/**
 * Created by Administrator on 2017/12/7.
 */

public class WindowConfig {
    private int gravity;

    private boolean needSoftKeyboard;

    private float forceWidthPercent;
    private float maxWidthPercent;
    private float forceHeightPercent;
    private float maxHeightPercent;
    
    private boolean cancelable = true;//默认可以点击后退键来dismiss对话框
    private boolean outsideCancelable = false;//默认外部半透明处点击不消失

    private float dimAmount = 0.7f;

    private @DrawableRes int bgRes;

    private boolean showAsActivity ;
    private boolean showAsFragment ;
    private boolean showAsToast ;
    private boolean showAsDialog ;
    
    private int flags;
    
    private int windowType;
    
    private int x;
    private int y;
    
    private View rootView;

    public int getGravity() {
        return gravity;
    }

    public boolean isNeedSoftKeyboard() {
        return needSoftKeyboard;
    }

    public float getForceWidthPercent() {
        return forceWidthPercent;
    }

    public float getMaxWidthPercent() {
        return maxWidthPercent;
    }

    public float getForceHeightPercent() {
        return forceHeightPercent;
    }

    public float getMaxHeightPercent() {
        return maxHeightPercent;
    }

    public boolean isCancelable() {
        return cancelable;
    }

    public boolean isOutsideCancelable() {
        return outsideCancelable;
    }

    public float getDimAmount() {
        return dimAmount;
    }

    public int getBgRes() {
        return bgRes;
    }

    public boolean isShowAsActivity() {
        return showAsActivity;
    }

    public boolean isShowAsFragment() {
        return showAsFragment;
    }

    public boolean isShowAsToast() {
        return showAsToast;
    }

    public boolean isShowAsDialog() {
        return showAsDialog;
    }

    public int getFlags() {
        return flags;
    }

    public int getWindowType() {
        return windowType;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public View getRootView() {
        return rootView;
    }

    private WindowConfig(Builder builder) {
        gravity = builder.gravity;
        needSoftKeyboard = builder.needSoftKeyboard;
        forceWidthPercent = builder.forceWidthPercent;
        maxWidthPercent = builder.maxWidthPercent;
        forceHeightPercent = builder.forceHeightPercent;
        maxHeightPercent = builder.maxHeightPercent;
        cancelable = builder.cancelable;
        outsideCancelable = builder.outsideCancelable;
        dimAmount = builder.dimAmount;
        bgRes = builder.bgRes;
        showAsActivity = builder.showAsActivity;
        showAsFragment = builder.showAsFragment;
        showAsToast = builder.showAsToast;
        showAsDialog = builder.showAsDialog;
        flags = builder.flags;
        windowType = builder.windowType;
        x = builder.x;
        y = builder.y;
        rootView = builder.rootView;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(WindowConfig copy) {
        Builder builder = new Builder();
        builder.gravity = copy.getGravity();
        builder.needSoftKeyboard = copy.isNeedSoftKeyboard();
        builder.forceWidthPercent = copy.getForceWidthPercent();
        builder.maxWidthPercent = copy.getMaxWidthPercent();
        builder.forceHeightPercent = copy.getForceHeightPercent();
        builder.maxHeightPercent = copy.getMaxHeightPercent();
        builder.cancelable = copy.isCancelable();
        builder.outsideCancelable = copy.isOutsideCancelable();
        builder.dimAmount = copy.getDimAmount();
        builder.bgRes = copy.getBgRes();
        builder.showAsActivity = copy.isShowAsActivity();
        builder.showAsFragment = copy.isShowAsFragment();
        builder.showAsToast = copy.isShowAsToast();
        builder.showAsDialog = copy.isShowAsDialog();
        builder.flags = copy.getFlags();
        builder.windowType = copy.getWindowType();
        builder.x = copy.getX();
        builder.y = copy.getY();
        builder.rootView = copy.getRootView();
        return builder;
    }


    /**
     * {@code WindowConfig} builder static inner class.
     */
    public static final class Builder {
        private int gravity;
        private boolean needSoftKeyboard;
        private float forceWidthPercent;
        private float maxWidthPercent;
        private float forceHeightPercent;
        private float maxHeightPercent;
        private boolean cancelable;
        private boolean outsideCancelable;
        private float dimAmount;
        private int bgRes;
        private boolean showAsActivity;
        private boolean showAsFragment;
        private boolean showAsToast;
        private boolean showAsDialog;
        private int flags;
        private int windowType;
        private int x;
        private int y;
        private View rootView;

        private Builder() {
        }

        /**
         * Sets the {@code gravity} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param gravity the {@code gravity} to set
         * @return a reference to this Builder
         */
        public Builder gravity(int gravity) {
            this.gravity = gravity;
            return this;
        }

        /**
         * Sets the {@code needSoftKeyboard} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param needSoftKeyboard the {@code needSoftKeyboard} to set
         * @return a reference to this Builder
         */
        public Builder needSoftKeyboard(boolean needSoftKeyboard) {
            this.needSoftKeyboard = needSoftKeyboard;
            return this;
        }

        /**
         * Sets the {@code forceWidthPercent} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param forceWidthPercent the {@code forceWidthPercent} to set
         * @return a reference to this Builder
         */
        public Builder forceWidthPercent(float forceWidthPercent) {
            this.forceWidthPercent = forceWidthPercent;
            return this;
        }

        /**
         * Sets the {@code maxWidthPercent} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param maxWidthPercent the {@code maxWidthPercent} to set
         * @return a reference to this Builder
         */
        public Builder maxWidthPercent(float maxWidthPercent) {
            this.maxWidthPercent = maxWidthPercent;
            return this;
        }

        /**
         * Sets the {@code forceHeightPercent} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param forceHeightPercent the {@code forceHeightPercent} to set
         * @return a reference to this Builder
         */
        public Builder forceHeightPercent(float forceHeightPercent) {
            this.forceHeightPercent = forceHeightPercent;
            return this;
        }

        /**
         * Sets the {@code maxHeightPercent} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param maxHeightPercent the {@code maxHeightPercent} to set
         * @return a reference to this Builder
         */
        public Builder maxHeightPercent(float maxHeightPercent) {
            this.maxHeightPercent = maxHeightPercent;
            return this;
        }

        /**
         * Sets the {@code cancelable} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param cancelable the {@code cancelable} to set
         * @return a reference to this Builder
         */
        public Builder cancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        /**
         * Sets the {@code outsideCancelable} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param outsideCancelable the {@code outsideCancelable} to set
         * @return a reference to this Builder
         */
        public Builder outsideCancelable(boolean outsideCancelable) {
            this.outsideCancelable = outsideCancelable;
            return this;
        }

        /**
         * Sets the {@code dimAmount} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param dimAmount the {@code dimAmount} to set
         * @return a reference to this Builder
         */
        public Builder dimAmount(float dimAmount) {
            this.dimAmount = dimAmount;
            return this;
        }

        /**
         * Sets the {@code bgRes} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param bgRes the {@code bgRes} to set
         * @return a reference to this Builder
         */
        public Builder bgRes(int bgRes) {
            this.bgRes = bgRes;
            return this;
        }

        /**
         * Sets the {@code showAsActivity} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param showAsActivity the {@code showAsActivity} to set
         * @return a reference to this Builder
         */
        public Builder showAsActivity(boolean showAsActivity) {
            this.showAsActivity = showAsActivity;
            return this;
        }

        /**
         * Sets the {@code showAsFragment} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param showAsFragment the {@code showAsFragment} to set
         * @return a reference to this Builder
         */
        public Builder showAsFragment(boolean showAsFragment) {
            this.showAsFragment = showAsFragment;
            return this;
        }

        /**
         * Sets the {@code showAsToast} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param showAsToast the {@code showAsToast} to set
         * @return a reference to this Builder
         */
        public Builder showAsToast(boolean showAsToast) {
            this.showAsToast = showAsToast;
            return this;
        }

        /**
         * Sets the {@code showAsDialog} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param showAsDialog the {@code showAsDialog} to set
         * @return a reference to this Builder
         */
        public Builder showAsDialog(boolean showAsDialog) {
            this.showAsDialog = showAsDialog;
            return this;
        }

        /**
         * Sets the {@code flags} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param flags the {@code flags} to set
         * @return a reference to this Builder
         */
        public Builder flags(int flags) {
            this.flags = flags;
            return this;
        }

        /**
         * Sets the {@code windowType} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param windowType the {@code windowType} to set
         * @return a reference to this Builder
         */
        public Builder windowType(int windowType) {
            this.windowType = windowType;
            return this;
        }

        /**
         * Sets the {@code x} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param x the {@code x} to set
         * @return a reference to this Builder
         */
        public Builder x(int x) {
            this.x = x;
            return this;
        }

        /**
         * Sets the {@code y} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param y the {@code y} to set
         * @return a reference to this Builder
         */
        public Builder y(int y) {
            this.y = y;
            return this;
        }

        /**
         * Sets the {@code rootView} and returns a reference to this Builder so that the methods can be chained together.
         *
         * @param rootView the {@code rootView} to set
         * @return a reference to this Builder
         */
        public Builder rootView(View rootView) {
            this.rootView = rootView;
            return this;
        }

        /**
         * Returns a {@code WindowConfig} built from the parameters previously set.
         *
         * @return a {@code WindowConfig} built with parameters of this {@code WindowConfig.Builder}
         */
        public WindowConfig build() {
            return new WindowConfig(this);
        }
    }
}
