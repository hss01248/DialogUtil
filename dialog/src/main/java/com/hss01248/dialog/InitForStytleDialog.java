package com.hss01248.dialog;

import android.app.Application;
import android.content.Context;

import androidx.startup.Initializer;

import java.util.ArrayList;
import java.util.List;

/**
 * @Despciption todo
 * @Author hss
 * @Date 09/12/2022 10:43
 * @Version 1.0
 */
public class InitForStytleDialog implements Initializer<String> {
    @Override
    public String create(Context context) {
        StyledDialog.init(context);

        ActivityStackManager.init((Application) context);
        return "InitForStytleDialog";
    }



    @Override
    public List<Class<? extends Initializer<?>>> dependencies() {
        return new ArrayList<>();
    }
}
