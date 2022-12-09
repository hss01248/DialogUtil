package com.hss01248.dialogutildemo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by huangshuisheng on 2017/9/30.
 */

public class BadTokenBeforeActy extends AppCompatActivity {


    long startTime;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.button3)
    Button button3;

    @Override
    protected Dialog onCreateDialog(int id) {
        return super.onCreateDialog(id);
    }

    @Nullable
    @Override
    protected Dialog onCreateDialog(int id, Bundle args) {
        return super.onCreateDialog(id, args);
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acty_token_before);
        ButterKnife.bind(this);
        //new PopupWindow(View.inflate(this,R.layout.dialog_ios_alert,null)).showAsDropDown(button3);
        // PopupWindow必导致badtoken,如果在onAttachedToWindow里弹出,则不会

        new AlertDialog.Builder(this).setTitle("crash").setMessage("ddddd").show();
        //AlertDialog测试机未出现badtoken,会等待activity界面就绪后再弹出,不知道其他手机??


        startTime = System.currentTimeMillis();
        Logger.e("onCreate---time:" + startTime);

    }

    private void takeTime() {
        int length = 10000;
        int[] arr = new int[length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * length);
        }

        selectionSort(arr);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Logger.e("onStart---time:" + (System.currentTimeMillis() - startTime));
        takeTime();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.e("onResume---time:" + (System.currentTimeMillis() - startTime));

    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Logger.e("onAttachedToWindow---time:" + (System.currentTimeMillis() - startTime));
        //new PopupWindow(View.inflate(this,R.layout.dialog_ios_alert,null)).showAsDropDown(button3);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Logger.e("onStop---time:" + (System.currentTimeMillis() - startTime));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Logger.e("onDestroy---time:" + (System.currentTimeMillis() - startTime));
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Logger.e("onDetachedFromWindow---time:" + (System.currentTimeMillis() - startTime));
    }

    public static void selectionSort(int[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int k = i;
            // 找出最小值的小标
            for (int j = i + 1; j < n; j++) {
                if (a[j] < a[k]) {
                    k = j;
                }
            }
            // 将最小值放到排序序列末尾
            if (k > i) {
                int tmp = a[i];
                a[i] = a[k];
                a[k] = tmp;
            }
        }
    }
}
