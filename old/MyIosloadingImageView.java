package com.hss01248.dialog.view;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.hss01248.dialog.R;

/**
 * Created by huangshuisheng on 2017/10/21.
 */

public class MyIosloadingImageView  extends AppCompatImageView{
    Animation anim;

    public MyIosloadingImageView(Context context) {
        super(context);
        anim=new AnimationUtils().loadAnimation(context, R.anim.ios_loading_center);
        anim.setFillAfter(true);
    }

    public MyIosloadingImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        anim=new AnimationUtils().loadAnimation(context, R.anim.ios_loading_center);
        anim.setFillAfter(true);
    }

    public MyIosloadingImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        anim=new AnimationUtils().loadAnimation(context, R.anim.ios_loading_center);
        anim.setFillAfter(true);
    }
   /* public MyIosloadingImageView(Context context) {
        super(context);
        anim=new AnimationUtils().loadAnimation(context, R.anim.ios_loading_center);
    }

    public MyIosloadingImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        anim=new AnimationUtils().loadAnimation(context, R.anim.ios_loading_center);
    }

    public MyIosloadingImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        anim=new AnimationUtils().loadAnimation(context, R.anim.ios_loading_center);
    }*/

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

            anim.start();


    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if(anim.hasStarted() && !anim.hasEnded()){
            anim.cancel();
        }
    }
}
