package com.hss01248.dialog.bottomsheet;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hss01248.dialog.R;
import com.hss01248.dialog.ScreenUtil;
import com.hss01248.dialog.adapter.SuperLvHolder;

/**
 * Created by Administrator on 2016/10/19.
 */

public class BsGvHolder extends SuperLvHolder<BottomSheetBean> {
    public ImageView ivIcon;
    public TextView mTextView;

    public BsGvHolder(Context context){
        super(context);

    }

    @Override
    protected void findViews() {
        ivIcon = (ImageView) rootView.findViewById(R.id.iv_icon);
        mTextView = (TextView) rootView.findViewById(R.id.tv_msg);
    }

    public void setStyle(BottomSheetStyle style){
        mTextView.setTextSize(style.txtSizeSp);
        mTextView.setTextColor(mTextView.getContext().getResources().getColor(style.txtColor));
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) ivIcon.getLayoutParams();
        if(params==null){
            params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        }
        params.bottomMargin = ScreenUtil.dip2px(style.txtMarginTopDp);
        params.width = ScreenUtil.dip2px(style.iconSizeDp);
        params.height = ScreenUtil.dip2px(style.iconSizeDp);
        ivIcon.setLayoutParams(params);


    }

    @Override
    protected int setLayoutRes() {
        return R.layout.item_bottomsheet_gv;
    }

    @Override
    public void assingDatasAndEvents(Context context, BottomSheetBean bean) {
        if (bean.icon<=0){
            ivIcon.setVisibility(View.GONE);
        }else {
            ivIcon.setImageResource(bean.icon);
            ivIcon.setVisibility(View.VISIBLE);
        }

        mTextView.setText(bean.text);

       /* rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
    }
}
