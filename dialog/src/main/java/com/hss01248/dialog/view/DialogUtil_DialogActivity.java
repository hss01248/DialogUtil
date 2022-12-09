package com.hss01248.dialog.view;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;

import com.hss01248.dialog.WindowAdjuster;
import com.hss01248.dialog.config.ConfigBean;

/**
 * Created by Administrator on 2017/11/19.
 */

public class DialogUtil_DialogActivity extends Activity {

    ConfigBean bean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public void show(ConfigBean bean){
        this.bean = bean;
        View view = null;
        if(bean.dialog !=null){
            view =bean.dialog.getWindow().getDecorView();
        }else if(bean.alertDialog !=null){
            view = bean.alertDialog.getWindow().getDecorView();
        }
        if(view!=null){
            setContentView(view);
            WindowAdjuster.adjust(getWindow(),bean);
            bean.listener.onShow();
        }else {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(bean!=null && bean.homeKeyReceiver!=null){
            try {
                unregisterReceiver(bean.homeKeyReceiver);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onBackPressed() {

        if(bean.cancelable){
            super.onBackPressed();
        }

    }
}
