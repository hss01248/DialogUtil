package com.hss01248.dialogutildemo;

import android.app.Activity;
import android.view.View;
import android.widget.Button;

import com.hss01248.dialog.adapter.SuperRcvHolder;

/**
 * Created by Administrator on 2016/10/9 0009.
 */
public class TextItemHolder extends SuperRcvHolder<String> {
    Button btn;
    public TextItemHolder(View itemView) {
        super(itemView);


    }

    @Override
    public void assignDatasAndEvents(Activity context, String data) {

    }


   /* public ListView lv;

    public TextItemHolder(Context context) {
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
    public void assingDatasAndEvents(final Context context, final String bean) {
        lv.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return bean.wordsIos.size();
            }

            @Override
            public Object getItem(int position) {
                return bean.wordsIos.get(position);
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
                view.setText(bean.wordsIos.get(position));
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        StyledDialog.dismiss(bean.alertDialog,bean.dialog);
                        bean.itemListener.onItemClick(bean.wordsIos.get(position),position);

                    }
                });

                return root;
            }
        });*/



}
