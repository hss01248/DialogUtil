package com.hss01248.dialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Build;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * Created by Administrator on 2017/2/15 0015.
 *
 * 参考:https://zhuanlan.zhihu.com/p/25221428
 */

public class ActivityStackManager {


    private static ActivityStackManager sInstance = new ActivityStackManager();
    private WeakReference<Activity> topAttachedActivityWeakRef;
    private static Stack<Activity> mActivityStack = new Stack<>();



    private ActivityStackManager() {

    }

    public static ActivityStackManager getInstance() {
        return sInstance;
    }


    /**
     * activity from oncreate callback is useable for dialog ,but not usable for popupwindow
     * @return
     */
    public Activity getTopActivity() {
        Activity currentActivity = null;
        if(mActivityStack.size()>0){
            currentActivity = mActivityStack.get(mActivityStack.size()-1);
        }
        /*Activity currentActivity = null;
        if (sCurrentActivityWeakRef != null) {
            currentActivity = sCurrentActivityWeakRef.get();
            if(currentActivity ==null){
                if(mActivityStack.size()>0){
                    currentActivity = mActivityStack.get(mActivityStack.size()-1);
                }
            }
            if(currentActivity !=null && currentActivity.isFinishing()){
                currentActivity = null;
            }
        }else {
            if(mActivityStack.size()>0){
                currentActivity = mActivityStack.get(mActivityStack.size()-1);
            }
            if(currentActivity !=null && currentActivity.isFinishing()){
                currentActivity = null;
            }
        }*/
        return currentActivity;
    }

    public Activity getTopActivity(Class activityExpected){
        Activity activity = getTopActivity();
        if(activity !=null && activity.getClass().equals(activityExpected)){
            return activity;
        }else {
            return null;
        }
    }

    public Activity getActivity(Class activityExpected){
        if (mActivityStack == null) {
            return null;
        }
        for (Activity activity1 : mActivityStack) {
            if (activity1.getClass().equals(activityExpected)) {
                return activity1;
            }
        }
        return null;
    }

    /**
     *
     * @param idxTop 从上往下的index,从1开始
     * @return
     */
    public boolean finishActivityByIdx(int idxTop){
        if(mActivityStack.isEmpty()){
            return false;
        }
        int index = mActivityStack.size() -idxTop;
       Activity activity =  mActivityStack.remove(index);
       if(activity !=null && !activity.isFinishing()){
           activity.finish();
           return true;
       }
       return true;
    }


    /**
     * 在基类activity 的 onAttachToWindow时调用
     * @param activity
     */
    public void setTopAttached(Activity activity) {
        topAttachedActivityWeakRef = new WeakReference<Activity>(activity);
    }

    /**
     * 在基类activity 的 onDetachToWindow时调用
     * @param activity
     */
    public void removeTopAttached(Activity activity){
        if(activity ==null){
            return;
        }
        if(topAttachedActivityWeakRef!=null){
            Activity activity1 =  topAttachedActivityWeakRef.get();
            if(activity.equals(activity1)){
                topAttachedActivityWeakRef = null;
            }

        }
    }

    /**
     * if return in not null,then it can be used for a popupwindow
     * @return
     */
    public Activity getTopAttached(){
        if(topAttachedActivityWeakRef!=null){
            Activity activity =  topAttachedActivityWeakRef.get();
            if(isUsable(activity)){
                return activity;
            }
        }
        return null;
    }

    public static boolean isUsable(Activity activity) {
        if(activity ==null){
            return false;
        }

        if(activity.isFinishing()){
            return false;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (activity.isDestroyed()){
                return false;
            }
        }

        //是否attached
        /*if(activity.getWindowManager() ==null){
            return false;
        }
        if(!activity.getWindow().isActive()){
            return false;
        }*/

        return true;
    }


    /**
     * 返回栈大小
     *
     * @return 大小
     */
    public int size() {
        return mActivityStack.size();
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (mActivityStack == null) {
            mActivityStack = new Stack<>();
        }
        if (activity != null) {
            mActivityStack.add(activity);
            Log.e("dialog","mActivityStack.size()--addActivity:"+activity.getClass().getSimpleName()+mActivityStack.size());
        }
    }

    /**
     * 移除activity
     */
    public void removeActivity(Activity activity) {
        mActivityStack.remove(activity);
        DialogsMaintainer.onDestory(activity);
        Log.e("dialog","mActivityStack.size()--removeActivity:"+activity.getClass().getSimpleName()+mActivityStack.size());
    }

    /**
     * 判断某个activity是否还存活
     *
     * @param cls
     * @return
     */
    public boolean isActivityAlive(Class<?> cls) {
        if (mActivityStack == null) {
            return false;
        }

        for (Activity activity1 : mActivityStack) {
            if (activity1.getClass().equals(cls)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (mActivityStack == null) {
            return;
        }

        if (activity != null) {
            mActivityStack.remove(activity);
            activity.finish();
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        if (mActivityStack == null) {
            return;
        }

        for (Activity activity : mActivityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }
}
