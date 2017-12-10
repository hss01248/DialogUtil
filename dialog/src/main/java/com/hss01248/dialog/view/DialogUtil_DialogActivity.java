package com.hss01248.dialog.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hss01248.dialog.config.ConfigBean;

/**
 * Created by Administrator on 2017/11/19.
 */

public class DialogUtil_DialogActivity extends AppCompatActivity {

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
        }else {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(bean!=null && bean.homeKeyReceiver!=null){
            unregisterReceiver(bean.homeKeyReceiver);
        }
    }

    @Override
    public void onBackPressed() {

        if(bean.cancelable){
            super.onBackPressed();
        }

    }
}
