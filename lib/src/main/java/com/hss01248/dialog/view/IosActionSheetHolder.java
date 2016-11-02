package com.hss01248.dialog.view;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.hss01248.dialog.R;
import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.config.ConfigBean;

/**
 * Created by Administrator on 2016/10/9 0009.
 */
public class IosActionSheetHolder extends SuperHolder {
    public ListView lv;
    protected Button btnBottom;

    public IosActionSheetHolder(Context context) {
        super(context);
    }

    @Override
    protected void findViews() {
        lv = (ListView) rootView.findViewById(R.id.lv);
        btnBottom = (Button) rootView.findViewById(R.id.btn_bottom);
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.dialog_ios_alert_bottom;
    }

    @Override
    public void assingDatasAndEvents(final Context context, final ConfigBean bean) {
        if (TextUtils.isEmpty(bean.bottomTxt)){
            btnBottom.setVisibility(View.GONE);
        }else {
            btnBottom.setVisibility(View.VISIBLE);
            btnBottom.setText(bean.bottomTxt);
            btnBottom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    StyledDialog.dismiss(bean.dialog,bean.alertDialog);
                    bean.itemListener.onBottomBtnClick();

                }
            });
        }


        BaseAdapter adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return bean.wordsIos.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {

                RelativeLayout root = (RelativeLayout) View.inflate(context,R.layout.item_btn_bottomalert,null);
                Button view = (Button) root.findViewById(R.id.btn);
                if (getCount() >=2){
                    if (position ==0){
                        view.setBackgroundResource(R.drawable.selector_btn_press_all_top);
                    }else if (position == getCount() -1){
                        view.setBackgroundResource(R.drawable.selector_btn_press_all_bottom);
                    }else {
                        view.setBackgroundResource(R.drawable.selector_btn_press_no_corner);
                    }

                }else {
                    view.setBackgroundResource(R.drawable.selector_btn_press_all);
                }

                view.setText(bean.wordsIos.get(position));
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        StyledDialog.dismiss(bean.dialog,bean.alertDialog);
                        bean.itemListener.onItemClick(bean.wordsIos.get(position),position);

                    }
                });

                return root;
            }
        };

        lv.setAdapter(adapter);
    }


}
