package com.hss01248.dialog.view;

import android.content.Context;
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
public class IosCenterItemHolder extends SuperHolder {
    public ListView lv;

    public IosCenterItemHolder(Context context) {
        super(context);
    }

    @Override
    protected void findViews() {
        lv = (ListView) rootView.findViewById(R.id.lv);
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.dialog_ios_center_item;
    }

    @Override
    public void assingDatasAndEvents(final Context context, final ConfigBean bean) {
        lv.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return bean.words.size();
            }

            @Override
            public Object getItem(int position) {
                return bean.words.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
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
                view.setText(bean.words.get(position));
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        StyledDialog.dismiss(bean.alertDialog,bean.dialog);
                        bean.itemDialogListener.onItemClick(bean.words.get(position),position);

                    }
                });

                return root;
            }
        });
    }


}
