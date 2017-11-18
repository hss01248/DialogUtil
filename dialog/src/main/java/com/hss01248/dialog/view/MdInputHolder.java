package com.hss01248.dialog.view;

import android.content.Context;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.hss01248.dialog.R;
import com.hss01248.dialog.Tool;
import com.hss01248.dialog.adapter.SuperLvHolder;
import com.hss01248.dialog.config.ConfigBean;

/**
 * Created by huangshuisheng on 2017/11/18.
 */

public class MdInputHolder extends SuperLvHolder<ConfigBean> {
    EditText et1;
    EditText et2;
    ConfigBean bean;
    public MdInputHolder(Context context) {
        super(context);
    }

    @Override
    protected void findViews() {
        et1 = (EditText) rootView.findViewById(R.id.et_1);
        et2 = (EditText) rootView.findViewById(R.id.et_2);
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.dialogutil_md_input;
    }

    @Override
    public void assingDatasAndEvents(Context context, ConfigBean bean) {
        this.bean = bean;
        setInputStyle(context,bean);

    }

    public String getTxt1(){
        if(et1!=null){
            return et1.getText().toString().trim();
        }else {
            return "";
        }
    }

    public String getTxt2(){
        if(et2!=null){
            return et2.getText().toString().trim();
        }else {
            return "";
        }
    }

    public EditText getEt1(){
        return et1;
    }

    public EditText getEt2(){
        return et2;
    }

    private void setInputStyle(Context context, ConfigBean bean) {
        if (TextUtils.isEmpty(bean.hint1)) {
            et1.setVisibility(View.GONE);
        } else {
            bean.setNeedSoftKeyboard(true);

            et1.setVisibility(View.VISIBLE);
            et1.setHint(bean.hint1);

            et1.setTextColor(Tool.getColor(et1.getContext(), bean.inputTxtColor));
            et1.setTextSize(bean.inputTxtSize);

        }

        if (TextUtils.isEmpty(bean.hint2)) {
            et2.setVisibility(View.GONE);
        } else {
            bean.setNeedSoftKeyboard(true);
            et2.setVisibility(View.VISIBLE);
            et2.setHint(bean.hint2);
            et2.setTextColor(Tool.getColor(et2.getContext(), bean.inputTxtColor));
            et2.setTextSize(bean.inputTxtSize);
            if (bean.isInput2HideAsPassword) {
                //设置EditText文本为可见的
                et2.setTransformationMethod(PasswordTransformationMethod.getInstance());
            } else {
                //设置EditText文本为隐藏的
                et2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
        }
    }

    @Override
    public void showKeyBoard(){
//弹出软键盘
        if(TextUtils.isEmpty(bean.hint2) && !TextUtils.isEmpty(bean.hint1)){
            Tool.showKeyBoard(et1);
        }else if(TextUtils.isEmpty(bean.hint1) && !TextUtils.isEmpty(bean.hint2)){
            Tool.showKeyBoard(et2);
        }else if(!TextUtils.isEmpty(bean.hint2) && !TextUtils.isEmpty(bean.hint1)){
            Tool.showKeyBoard(et1);
        }

    }

    @Override
    public void hideKeyBoard(){
        if(TextUtils.isEmpty(bean.hint2) && !TextUtils.isEmpty(bean.hint1)){
            Tool.hideKeyBoard(et1);
        }else if(TextUtils.isEmpty(bean.hint1) && !TextUtils.isEmpty(bean.hint2)){
            Tool.hideKeyBoard(et2);
        }else if(!TextUtils.isEmpty(bean.hint2) && !TextUtils.isEmpty(bean.hint1)){
            Tool.hideKeyBoard(et1);
        }
    }
}
