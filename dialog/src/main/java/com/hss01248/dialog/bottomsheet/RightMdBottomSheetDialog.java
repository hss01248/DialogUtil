package com.hss01248.dialog.bottomsheet;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 *
 * 沉浸式状态栏参考自: http://www.jianshu.com/p/08755838c00f
 *
 * 解决滑动关闭后不能打开的问题: http://blog.csdn.net/yanzhenjie1003/article/details/51938425?locationNum=1&fps=1
 * Created by Administrator on 2017/12/10.
 */

public class RightMdBottomSheetDialog extends BottomSheetDialog {
    public RightMdBottomSheetDialog(@NonNull Context context) {
        super(context);
    }

    public RightMdBottomSheetDialog(@NonNull Context context, int theme) {
        super(context, theme);
    }

    protected RightMdBottomSheetDialog(@NonNull Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int screenHeight = getScreenHeight(getContext());
        int dialogHeight = screenHeight ;
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, dialogHeight == 0 ? ViewGroup.LayoutParams.MATCH_PARENT : dialogHeight);
    }

    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        return height;
    }




}
