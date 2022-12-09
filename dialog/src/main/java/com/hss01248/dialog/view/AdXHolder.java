package com.hss01248.dialog.view;

import android.content.Context;
import androidx.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.hss01248.dialog.R;
import com.hss01248.dialog.ScreenUtil;
import com.hss01248.dialog.Tool;
import com.hss01248.dialog.adapter.SuperLvHolder;
import com.hss01248.dialog.config.ConfigBean;

/**
 * Created by huangshuisheng on 2018/4/18.
 */

public class AdXHolder extends SuperLvHolder<ConfigBean> {
    RelativeLayout relativeLayout;
    ImageView iv_close;
    RelativeLayout ivCloseContainer;
    FrameLayout adContainer;
    public AdXHolder(Context context) {
        super(context);
    }

    @Override
    protected void findViews() {
        relativeLayout = (RelativeLayout) rootView;
        iv_close = rootView.findViewById(R.id.iv_close_ad_real);
        ivCloseContainer = rootView.findViewById(R.id.rl_close_ad_container);
        adContainer = rootView.findViewById(R.id.fl_ad_container);
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.dialog_ad_x;
    }

    @Override
    public void assingDatasAndEvents(Context context, @Nullable final ConfigBean bean) {
        View customView = bean.customView;
        if(customView == null && bean.customContentHolder!=null){
            customView = bean.customContentHolder.rootView;
        }
        if(customView ==null){
            return;
        }
        adContainer.addView(customView);
        resetCloseBtnPosition(bean);

        ivCloseContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tool.dismiss(bean);
                bean.listener.onFirst();
            }
        });
    }

    /**
     * 根据配置的信息重新设置关闭按钮
     * @param bean
     */
    private void resetCloseBtnPosition(ConfigBean bean) {
        if(bean.xResId != 0){
            iv_close.setImageResource(bean.xResId);
        }
        if(bean.xWidth>0){
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) iv_close.getLayoutParams();
            params.width = ScreenUtil.dip2px(bean.xWidth);
            params.height = params.width;
            iv_close.setLayoutParams(params);

        }

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ivCloseContainer.getLayoutParams();
        //上方三个位置
        if(bean.xGravity == (Gravity.TOP|Gravity.RIGHT)){
            //params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

            ((RelativeLayout.LayoutParams)adContainer.getLayoutParams()).addRule(RelativeLayout.BELOW,R.id.rl_close_ad_container);
            params.bottomMargin = ScreenUtil.dip2px(bean.xMarginTB);
            if(bean.xMarginLR >0){
                params.rightMargin = ScreenUtil.dip2px(bean.xMarginLR);
            }


            //对齐按钮的位置
            params.addRule(RelativeLayout.ALIGN_RIGHT,R.id.fl_ad_container);
            //按钮内部图标也调整为右对齐
            ((RelativeLayout.LayoutParams)iv_close.getLayoutParams()).addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            ((RelativeLayout.LayoutParams)iv_close.getLayoutParams()).addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        }else if(bean.xGravity == (Gravity.TOP|Gravity.CENTER_HORIZONTAL)){
            params.addRule(RelativeLayout.CENTER_HORIZONTAL);

            ((RelativeLayout.LayoutParams)adContainer.getLayoutParams()).addRule(RelativeLayout.BELOW,R.id.rl_close_ad_container);
            params.bottomMargin = ScreenUtil.dip2px(bean.xMarginTB);

            ((RelativeLayout.LayoutParams)iv_close.getLayoutParams()).addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        }else if(bean.xGravity == (Gravity.TOP|Gravity.LEFT)){

            ((RelativeLayout.LayoutParams)adContainer.getLayoutParams()).addRule(RelativeLayout.BELOW,R.id.rl_close_ad_container);
            params.bottomMargin = ScreenUtil.dip2px(bean.xMarginTB);
            if(bean.xMarginLR >0){
                params.rightMargin = ScreenUtil.dip2px(bean.xMarginLR);
            }


            params.addRule(RelativeLayout.ALIGN_LEFT,R.id.fl_ad_container);
            ((RelativeLayout.LayoutParams)iv_close.getLayoutParams()).addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            ((RelativeLayout.LayoutParams)iv_close.getLayoutParams()).addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            //下方三个位置
        }else if(bean.xGravity == (Gravity.BOTTOM|Gravity.RIGHT)){
            //params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            params.addRule(RelativeLayout.BELOW,R.id.fl_ad_container);
            params.topMargin = ScreenUtil.dip2px(bean.xMarginTB);
            if(bean.xMarginLR >0){
                params.rightMargin = ScreenUtil.dip2px(bean.xMarginLR);
            }
            params.addRule(RelativeLayout.ALIGN_RIGHT,R.id.fl_ad_container);
            ((RelativeLayout.LayoutParams)iv_close.getLayoutParams()).addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            ((RelativeLayout.LayoutParams)iv_close.getLayoutParams()).addRule(RelativeLayout.ALIGN_PARENT_TOP);
        }else if(bean.xGravity == (Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL)){
            params.addRule(RelativeLayout.CENTER_HORIZONTAL);

            params.addRule(RelativeLayout.BELOW,R.id.fl_ad_container);
            params.topMargin = ScreenUtil.dip2px(bean.xMarginTB);

            ((RelativeLayout.LayoutParams)iv_close.getLayoutParams()).addRule(RelativeLayout.ALIGN_PARENT_TOP);
        }else if(bean.xGravity == (Gravity.BOTTOM|Gravity.LEFT)){
            if(bean.xMarginLR >0){
                params.rightMargin = ScreenUtil.dip2px(bean.xMarginLR);
            }
            //params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            params.addRule(RelativeLayout.BELOW,R.id.fl_ad_container);
            params.topMargin = ScreenUtil.dip2px(bean.xMarginTB);
            params.addRule(RelativeLayout.ALIGN_LEFT,R.id.fl_ad_container);
            ((RelativeLayout.LayoutParams)iv_close.getLayoutParams()).addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            ((RelativeLayout.LayoutParams)iv_close.getLayoutParams()).addRule(RelativeLayout.ALIGN_PARENT_TOP);
        }else {
            //默认是底部居中
            params.addRule(RelativeLayout.CENTER_HORIZONTAL);
            params.addRule(RelativeLayout.BELOW,R.id.fl_ad_container);
            params.topMargin = ScreenUtil.dip2px(bean.xMarginTB);
            ((RelativeLayout.LayoutParams)iv_close.getLayoutParams()).addRule(RelativeLayout.ALIGN_PARENT_TOP);
        }
        ivCloseContainer.setLayoutParams(params);
        relativeLayout.requestLayout();


    }
}
