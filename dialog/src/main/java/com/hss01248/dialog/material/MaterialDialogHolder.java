package com.hss01248.dialog.material;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.hss01248.dialog.R;
import com.hss01248.dialog.Tool;
import com.hss01248.dialog.adapter.SuperLvAdapter;
import com.hss01248.dialog.adapter.SuperLvHolder;
import com.hss01248.dialog.config.ConfigBean;
import com.hss01248.dialog.config.DefaultConfig;
import com.hss01248.dialog.view.MdInputHolder;

/**
 * Created by Administrator on 2017/11/19.
 */

public class MaterialDialogHolder extends SuperLvHolder<ConfigBean> {


    private LinearLayout materialBackground;
    private LinearLayout contentView;
    private TextView title;
    private ScrollView messageContentRoot;
    private LinearLayout messageContentView;
    private TextView message;
    private Button btnP;
    private LinearLayout buttonLayout;
    private Button btnN;

    MdInputHolder mdInputHolder;

    public MaterialDialogHolder(Context context) {
        super(context);
    }

    @Override
    protected void findViews() {
        materialBackground = (LinearLayout) rootView.findViewById(R.id.material_background);
        contentView = (LinearLayout) rootView.findViewById(R.id.contentView);
        title = (TextView) rootView.findViewById(R.id.title);
        messageContentRoot = (ScrollView) rootView.findViewById(R.id.message_content_root);
        messageContentView = (LinearLayout) rootView.findViewById(R.id.message_content_view);
        message = (TextView) rootView.findViewById(R.id.message);
        btnP = (Button) rootView.findViewById(R.id.btn_p);
        buttonLayout = (LinearLayout) rootView.findViewById(R.id.buttonLayout);
        btnN = (Button) rootView.findViewById(R.id.btn_n);
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.layout_material_dialog;
    }

    @Override
    public void assingDatasAndEvents(Context context, @Nullable ConfigBean bean) {
        if(bean ==null){
            return;
        }

        setTitleStyle(bean);

        setContentStyle(context,bean);

        setBtnStyleAndEvent(context,bean);



    }

    private void setBtnStyleAndEvent(Context context, final ConfigBean bean) {
        if(TextUtils.isEmpty(bean.text2)){
            btnN.setVisibility(View.GONE);
        }else {
            btnN.setVisibility(View.VISIBLE);
            btnN.setText(bean.text2);
            btnN.setTextColor(Tool.getColor(bean.context,bean.btn2Color));
            btnN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Tool.dismiss(bean);
                    bean.listener.onSecond();
                }
            });
        }

        if(TextUtils.isEmpty(bean.text1)){
            btnP.setVisibility(View.GONE);
        }else {
            btnP.setVisibility(View.VISIBLE);
            btnP.setText(bean.text1);
            btnP.setTextColor(Tool.getColor(bean.context,bean.btn1Color));
            btnP.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(bean.type == DefaultConfig.TYPE_MD_INPUT){
                        boolean isvalid = bean.listener.onInputValid(mdInputHolder.getTxt1(),mdInputHolder.getTxt2(),mdInputHolder.getEt1(),mdInputHolder.getEt2());
                        if(!isvalid){
                            return;
                        }
                        bean.listener.onGetInput(mdInputHolder.getTxt1(),mdInputHolder.getTxt2());
                    }
                    Tool.dismiss(bean);
                    bean.listener.onFirst();
                }
            });
        }

    }

    private void setContentStyle(Context context, ConfigBean bean) {

        //input
        if(bean.type == DefaultConfig.TYPE_MD_INPUT){
            message.setVisibility(View.GONE);
            mdInputHolder = new MdInputHolder(context);
           //match parent
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            mdInputHolder.rootView.setLayoutParams(params);
            messageContentView.addView(mdInputHolder.rootView);
            bean.needSoftKeyboard = true;
            mdInputHolder.assingDatasAndEvents(context,bean);
            return;
        }

        if(bean.type == DefaultConfig.TYPE_MD_SINGLE_CHOOSE){
            message.setVisibility(View.GONE);
            buildChoose(context,bean);
            return;
        }

        if(bean.type == DefaultConfig.TYPE_MD_MULTI_CHOOSE){
            message.setVisibility(View.GONE);
            buildChoose(context,bean);
            return;
        }

        //customview
        if(bean.customContentHolder !=null){
            message.setVisibility(View.GONE);
            messageContentView.addView(bean.customContentHolder.rootView);
            return;
        }

        //message
        if(TextUtils.isEmpty(bean.msg)){
            message.setVisibility(View.GONE);
        }else {
            message.setVisibility(View.VISIBLE);
            message.setText(bean.msg);
            message.setTextSize(bean.msgTxtSize);
            message.setTextColor(Tool.getColor(bean.context,bean.msgTxtColor));
        }

    }



    private void buildChoose(Context context, final ConfigBean bean) {

        messageContentRoot.setVisibility(View.GONE);
        ListView listView = new ListView(context);
        listView.setDivider(null);
        SuperLvAdapter adapter = buildAdapterByType(bean);
        listView.setAdapter(adapter);
        adapter.addAll(bean.chooseBeans);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        listView.setLayoutParams(params);

        contentView.addView(listView,1);

        if(bean.type == DefaultConfig.TYPE_MD_SINGLE_CHOOSE){
            listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            listView.setSelection(bean.defaultChosen);
            listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    bean.listener.onGetChoose(position,bean.chooseBeans.get(position).txt);
                    Tool.dismiss(bean);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }else {
            listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        }
    }

    private SuperLvAdapter buildAdapterByType(ConfigBean bean) {
        SuperLvAdapter adapter = null;
        if(bean.type == DefaultConfig.TYPE_MD_SINGLE_CHOOSE){
            adapter = new SuperLvAdapter(bean.context) {
                @Override
                protected SuperLvHolder generateNewHolder(Context context, int itemViewType) {
                    return new SingleChooseHolder(context);
                }
            };
        }else  if(bean.type == DefaultConfig.TYPE_MD_MULTI_CHOOSE){
            adapter = new SuperLvAdapter(bean.context) {
                @Override
                protected SuperLvHolder generateNewHolder(Context context, int itemViewType) {
                    return new MultiChooseHolder(context);
                }
            };
        }
        return adapter;
    }

    private void setTitleStyle(ConfigBean bean) {
        if(TextUtils.isEmpty(bean.title)){
            title.setVisibility(View.GONE);
        }else {
            title.setVisibility(View.VISIBLE);
            title.setText(bean.title);
            if(bean.titleTxtColor > 0){
                title.setTextColor(Tool.getColor(bean.context,bean.titleTxtColor));
            }
            if(bean.titleTxtSize>0){
                title.setTextSize(bean.titleTxtSize);
            }
        }

    }


}
