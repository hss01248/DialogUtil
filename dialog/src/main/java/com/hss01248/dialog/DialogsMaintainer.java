package com.hss01248.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by hss on 2018/3/24.
 */

public class DialogsMaintainer {

    private static HashMap<Activity,List<Dialog>> dialogsOfActivity = new HashMap<>();
    private static List<Dialog> loadingDialogs = new ArrayList<>();



    public static void addLoadingDialog(Dialog dialog){
        if(dialog.getOwnerActivity() instanceof Activity){
            loadingDialogs.add(dialog);
        }

    }

    public static void dismissLoading(Activity activity){
        if(activity ==null){
            return;
        }
        ListIterator<Dialog> iterator = loadingDialogs.listIterator();
        while (iterator.hasNext()){
            Dialog dialog = iterator.next();
            if(dialog.getOwnerActivity().equals(activity)){
                dialog.dismiss();
                iterator.remove();
                removeWhenDismiss(dialog);
            }
        }
    }

    public static void addWhenShow(Dialog dialog){

        Activity activity = dialog.getOwnerActivity();
        if(activity ==null){
            return;
        }
        List<Dialog> dialogs =  null;
        if(dialogsOfActivity.containsKey(activity)){
            dialogs = dialogsOfActivity.get(activity);
        }
        if(dialogs == null){
            dialogs = new ArrayList<>();
            dialogsOfActivity.put(activity,dialogs);
        }
        dialogs.add(dialog);
    }

    public static void removeWhenDismiss(Dialog dialog){
       Activity activity =  dialog.getOwnerActivity();
       if(activity ==null){
           return;
       }
        if(!dialogsOfActivity.containsKey(activity)){
            return;
        }
        List<Dialog> dialogInterfaces  =  dialogsOfActivity.get(activity);
        if(dialogInterfaces==null || dialogInterfaces.isEmpty()){
            return;
        }
        ListIterator<Dialog> iterator = dialogInterfaces.listIterator();
        while (iterator.hasNext()){
            Dialog dialog0 = iterator.next();
            if(dialog.equals(dialog0)){
                iterator.remove();
            }
        }

    }

    /**
     * 在这里移除已经dismiss的dialog引用
     * @param activity
     */
    public static void onPause(Activity activity){
        if(!dialogsOfActivity.containsKey(activity)){
            return;
        }
        List<Dialog> dialogInterfaces  =  dialogsOfActivity.get(activity);
        if(dialogInterfaces==null || dialogInterfaces.isEmpty()){
            dialogInterfaces.remove(activity);
            return;
        }
        ListIterator<Dialog> iterator = dialogInterfaces.listIterator();
        while (iterator.hasNext()){
            Dialog dialog = iterator.next();
            if(!dialog.isShowing()){
                iterator.remove();
            }
        }

    }

    public static void onDestory(Activity activity){
        if(!dialogsOfActivity.containsKey(activity)){
            return;
        }
        List<Dialog> dialogInterfaces  =  dialogsOfActivity.get(activity);
        if(dialogInterfaces==null || dialogInterfaces.isEmpty()){
            dialogInterfaces.remove(activity);
            return;
        }
        for (Dialog dialog : dialogInterfaces) {
            if(dialog!=null){
                dialog.dismiss();
            }
        }
        dialogInterfaces.remove(activity);
    }
}
