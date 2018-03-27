package com.hss01248.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by hss on 2018/3/24.
 */

public class DialogsMaintainer {

    private static HashMap<Activity, Set<Dialog>> dialogsOfActivity = new HashMap<>();
    private static HashMap<Activity, Set<Dialog>> loadingDialogs = new HashMap<>();


    public static void addLoadingDialog(Context context, Dialog dialog) {

        if (!(context instanceof Activity)) {
            return;
        }
        Activity activity = (Activity) context;

        Set<Dialog> dialogs = null;
        if (loadingDialogs.containsKey(activity)) {
            dialogs = loadingDialogs.get(activity);
        }
        if (dialogs == null) {
            dialogs = new HashSet<>();
            loadingDialogs.put(activity, dialogs);
        }
        dialogs.add(dialog);


    }

    public static void dismissLoading(Activity activity) {

        if (activity == null) {
            return;
        }
        if (!loadingDialogs.containsKey(activity)) {
            return;
        }
        Set<Dialog> dialogSet = loadingDialogs.get(activity);
        for (Dialog dialog : dialogSet) {
            dialog.dismiss();
            //在callback内部自动会去移除在dialogsOfActivity的引用
        }
        loadingDialogs.remove(activity);

    }

    public static void dismissLoading(Dialog dialog) {
        Iterator<Map.Entry<Activity, Set<Dialog>>> entryIterator = loadingDialogs.entrySet().iterator();
        while (entryIterator.hasNext()) {
            Map.Entry<Activity, Set<Dialog>> entry = entryIterator.next();
            Set<Dialog> dialogInterfaces = entry.getValue();
            if (dialogInterfaces == null || dialogInterfaces.isEmpty()) {
                entryIterator.remove();
                return;
            }
            Iterator<Dialog> iterator = dialogInterfaces.iterator();
            while (iterator.hasNext()) {
                Dialog dialog0 = iterator.next();
                if (dialog.equals(dialog0)) {
                    iterator.remove();
                }
            }
            if (dialogInterfaces == null || dialogInterfaces.isEmpty()) {
                entryIterator.remove();
                return;
            }
        }
    }

    public static void addWhenShow(Context context, Dialog dialog) {

        if (!(context instanceof Activity)) {
            return;
        }

        Activity activity = (Activity) context;

        Set<Dialog> dialogs = null;
        if (dialogsOfActivity.containsKey(activity)) {
            dialogs = dialogsOfActivity.get(activity);
        }
        if (dialogs == null) {
            dialogs = new HashSet<>();
            dialogsOfActivity.put(activity, dialogs);
        }
        dialogs.add(dialog);

    }

    public static void removeWhenDismiss(Dialog dialog) {

        try {
            Iterator<Map.Entry<Activity, Set<Dialog>>> entryIterator = dialogsOfActivity.entrySet().iterator();
            while (entryIterator.hasNext()) {
                Map.Entry<Activity, Set<Dialog>> entry = entryIterator.next();
                Set<Dialog> dialogInterfaces = entry.getValue();
                if (dialogInterfaces == null || dialogInterfaces.isEmpty()) {
                    entryIterator.remove();
                    return;
                }
                Iterator<Dialog> iterator = dialogInterfaces.iterator();
                while (iterator.hasNext()) {
                    Dialog dialog0 = iterator.next();
                    if (dialog.equals(dialog0)) {
                        iterator.remove();
                    }
                }
                if (dialogInterfaces == null || dialogInterfaces.isEmpty()) {
                    entryIterator.remove();
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 在这里移除已经dismiss的dialog引用
     *
     * @param activity
     */
    public static void onPause(Activity activity) {
        if (!dialogsOfActivity.containsKey(activity)) {
            return;
        }
        Set<Dialog> dialogInterfaces = dialogsOfActivity.get(activity);
        if (dialogInterfaces == null || dialogInterfaces.isEmpty()) {
            dialogInterfaces.remove(activity);
            return;
        }
        Iterator<Dialog> iterator = dialogInterfaces.iterator();
        while (iterator.hasNext()) {
            Dialog dialog = iterator.next();
            if (!dialog.isShowing()) {
                iterator.remove();
            }
        }
        if (dialogInterfaces == null || dialogInterfaces.isEmpty()) {
            dialogInterfaces.remove(activity);
            return;
        }

    }

    public static void onDestory(Activity activity) {

        if (!dialogsOfActivity.containsKey(activity)) {
            return;
        }
        Set<Dialog> dialogInterfaces = dialogsOfActivity.get(activity);
        if (dialogInterfaces == null || dialogInterfaces.isEmpty()) {
            dialogInterfaces.remove(activity);
            return;
        }
        for (Dialog dialog : dialogInterfaces) {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        }
        dialogInterfaces.remove(activity);

    }
}
