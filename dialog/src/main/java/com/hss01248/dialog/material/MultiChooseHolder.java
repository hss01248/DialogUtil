package com.hss01248.dialog.material;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.CheckedTextView;

import com.hss01248.dialog.R;
import com.hss01248.dialog.adapter.SuperLvHolder;
import com.hss01248.dialog.config.ChooseBean;

/**
 * Created by Administrator on 2017/11/19.
 */

public class MultiChooseHolder extends SuperLvHolder<ChooseBean> {
    CheckedTextView checkedTextView;
    public MultiChooseHolder(Context context) {
        super(context);
    }

    @Override
    protected void findViews() {
        checkedTextView = (CheckedTextView) rootView;
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.item_md_choose_multi;
    }

    @Override
    public void assingDatasAndEvents(Context context, @Nullable ChooseBean bean) {
        //checkedTextView.setChecked(bean.choosen);
        checkedTextView.setText(bean.txt);

    }


}
