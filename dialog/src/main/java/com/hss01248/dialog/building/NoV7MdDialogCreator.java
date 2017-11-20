package com.hss01248.dialog.building;

import com.hss01248.dialog.config.ConfigBean;
import com.hss01248.dialog.material.MaterialDialogHolder;

/**
 * Created by Administrator on 2017/11/19.
 */

public class NoV7MdDialogCreator {


    public static void buildAlert(ConfigBean bean){

    }

    public static void buildInput(ConfigBean bean){

    }

    public static void buildSingleChoose(ConfigBean bean){

    }

    public static void buildMultiChoose(ConfigBean bean){

    }

    private static void buildBase(ConfigBean bean){
        MaterialDialogHolder holder = new MaterialDialogHolder(bean.context);
        bean.viewHolder = holder;
    }

    private static void setBtnEvent(ConfigBean bean){

    }
}
