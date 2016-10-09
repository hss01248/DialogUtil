package com.hss01248.dialog.view;


import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hss01248.dialog.R;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.config.ConfigBean;


/**
 * Created by Administrator on 2016/10/9 0009.
 */
public class IosAlertDialogHolder extends SuperHolder {
    protected TextView tvTitle;
    public TextView tvMsg;
    public EditText et1;
    public EditText et2;
    protected View line;
    protected Button btn1;
    protected View lineBtn2;
    protected Button btn2;
    protected View lineBtn3;
    protected Button btn3;
    protected LinearLayout llContainerHorizontal;
    protected Button btn1Vertical;
    protected View lineBtn2Vertical;
    protected Button btn2Vertical;
    protected View lineBtn3Vertical;
    protected Button btn3Vertical;
    protected LinearLayout llContainerVertical;



    public IosAlertDialogHolder(Context context) {
        super(context);
    }

    @Override
    protected void findViews() {
        tvTitle = (TextView) rootView.findViewById(R.id.tv_title);
        tvMsg = (TextView) rootView.findViewById(R.id.tv_msg);
        et1 = (EditText) rootView.findViewById(R.id.et_1);
        et2 = (EditText) rootView.findViewById(R.id.et_2);
        line = (View) rootView.findViewById(R.id.line);
        btn1 = (Button) rootView.findViewById(R.id.btn_1);
        lineBtn2 = (View) rootView.findViewById(R.id.line_btn2);
        btn2 = (Button) rootView.findViewById(R.id.btn_2);
        lineBtn3 = (View) rootView.findViewById(R.id.line_btn3);
        btn3 = (Button) rootView.findViewById(R.id.btn_3);
        llContainerHorizontal = (LinearLayout) rootView.findViewById(R.id.ll_container_horizontal);
        btn1Vertical = (Button) rootView.findViewById(R.id.btn_1_vertical);
        lineBtn2Vertical = (View) rootView.findViewById(R.id.line_btn2_vertical);
        btn2Vertical = (Button) rootView.findViewById(R.id.btn_2_vertical);
        lineBtn3Vertical = (View) rootView.findViewById(R.id.line_btn3_vertical);
        btn3Vertical = (Button) rootView.findViewById(R.id.btn_3_vertical);
        llContainerVertical = (LinearLayout) rootView.findViewById(R.id.ll_container_vertical);
    }


    @Override
    protected int setLayoutRes() {
        return R.layout.dialog_ios_alert;
    }

    @Override
    public void assingDatasAndEvents(Context context, final ConfigBean bean) {

        //隐藏view
        if (bean.isVertical) {
            llContainerVertical.setVisibility(View.VISIBLE);
            llContainerHorizontal.setVisibility(View.GONE);
        } else {
            llContainerVertical.setVisibility(View.GONE);
            llContainerHorizontal.setVisibility(View.VISIBLE);
        }

        tvTitle.setText(bean.title);

        if (TextUtils.isEmpty(bean.msg)) {
            tvMsg.setVisibility(View.GONE);
        } else {
            tvMsg.setVisibility(View.VISIBLE);
            tvMsg.setText(bean.msg);
        }

        if (TextUtils.isEmpty(bean.hint1)) {
            et1.setVisibility(View.GONE);
        } else {
            et1.setVisibility(View.VISIBLE);
            et1.setHint(bean.hint1);
        }

        if (TextUtils.isEmpty(bean.hint2)) {
            et2.setVisibility(View.GONE);
        } else {
            et2.setVisibility(View.VISIBLE);
            et2.setHint(bean.hint2);
        }

        //按钮数量
        if (TextUtils.isEmpty(bean.text3)) {
            if (bean.isVertical) {
                btn3Vertical.setVisibility(View.GONE);
                lineBtn3Vertical.setVisibility(View.GONE);
                btn2Vertical.setBackgroundResource(R.drawable.selector_btn_press_all_bottom);
            } else {
                btn3.setVisibility(View.GONE);
                lineBtn3.setVisibility(View.GONE);
                btn2.setBackgroundResource(R.drawable.selector_btn_press_right_bottom);
            }

        } else {
            if (bean.isVertical) {
                btn3Vertical.setVisibility(View.VISIBLE);
                lineBtn3Vertical.setVisibility(View.VISIBLE);
                btn3Vertical.setText(bean.text3);
            } else {
                btn3.setVisibility(View.VISIBLE);
                lineBtn3.setVisibility(View.VISIBLE);
                btn3.setText(bean.text3);
            }
        }

        if (TextUtils.isEmpty(bean.text2)) {
            if (bean.isVertical) {
                btn2Vertical.setVisibility(View.GONE);
                lineBtn2Vertical.setVisibility(View.GONE);
                btn1Vertical.setBackgroundResource(R.drawable.selector_btn_press_all_bottom);
            } else {
                btn2.setVisibility(View.GONE);
                lineBtn2.setVisibility(View.GONE);
                btn1.setBackgroundResource(R.drawable.selector_btn_press_right_bottom);
            }
        } else {
            if (bean.isVertical) {
                btn2Vertical.setVisibility(View.VISIBLE);
                lineBtn2Vertical.setVisibility(View.VISIBLE);
                btn2Vertical.setText(bean.text2);
            } else {
                btn2.setVisibility(View.VISIBLE);
                lineBtn2.setVisibility(View.VISIBLE);
                btn2.setText(bean.text2);
            }
        }

        if (bean.isVertical) {
            btn1Vertical.setText(bean.text1);
        } else {

            btn1.setText(bean.text1);
        }


        //事件

        if (bean.isVertical) {
            btn1Vertical.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    StyledDialog.dismiss(bean.dialog,bean.appCompatDialog);
                    bean.dialogListener.onFirst();

                    bean.dialogListener.onGetInput(et1.getText().toString().trim(),et2.getText().toString().trim());



                }
            });

            btn2Vertical.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    StyledDialog.dismiss(bean.dialog,bean.appCompatDialog);
                    bean.dialogListener.onSecond();
                }
            });

            btn3Vertical.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    StyledDialog.dismiss(bean.dialog,bean.appCompatDialog);
                    bean.dialogListener.onThird();
                }
            });


        } else {
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    StyledDialog.dismiss(bean.dialog,bean.appCompatDialog);
                    bean.dialogListener.onFirst();
                    bean.dialogListener.onGetInput(et1.getText().toString().trim(),et2.getText().toString().trim());
                }
            });

            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    StyledDialog.dismiss(bean.dialog,bean.appCompatDialog);
                    bean.dialogListener.onSecond();
                }
            });

            btn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    StyledDialog.dismiss(bean.dialog,bean.appCompatDialog);
                    bean.dialogListener.onThird();
                }
            });

        }


    }


}
