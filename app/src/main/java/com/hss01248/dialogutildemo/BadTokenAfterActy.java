package com.hss01248.dialogutildemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

/**
 * Created by huangshuisheng on 2017/9/30.
 */

public class BadTokenAfterActy extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        /*StyledDialog.buildMdAlert("dd", "after", new MyDialogListener() {
            @Override
            public void onFirst() {

            }

            @Override
            public void onSecond() {

            }
        }).setActivity(this).show();*/
       // AlertDialog dialog = new AlertDialog.Builder(this).setTitle("crash").setMessage("after").show();
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                new AlertDialog.Builder(BadTokenAfterActy.this).setTitle("crash").setMessage("after").show();//必crash

            }
        },500);

    }
}
