package com.hss01248.dialog;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Bundle;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;



/**
 * Created by Administrator on 2017/2/15 0015.
 *
 * 参考:https://zhuanlan.zhihu.com/p/25221428
 *
 *
 */

public class ActivityStackManager {


    private static ActivityStackManager sInstance = new ActivityStackManager();
    private WeakReference<Activity> topAttachedActivityWeakRef;
    private static Stack<Activity> mActivityStack = new Stack<>();
    private static boolean isDebug;
    static boolean hasInit = false;

    public static boolean isApkInDebug(Context context) {
        try {
            ApplicationInfo info = context.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            return false;
        }
    }

    public static void init(Application application){
        ActivityStackManager.isDebug = isApkInDebug(application);
        if(hasInit){
            return;
        }
        hasInit = true;
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
                ActivityStackManager.getInstance().addActivity(activity);
                ActivityStackManager.getInstance().setTopAttached(activity);
            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {

            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {
                ActivityStackManager.getInstance().setTopAttached(activity);
            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {
                if(activity.isFinishing()){
                    ActivityStackManager.getInstance().removeActivity(activity);
                }
                DialogsMaintainer.onPause(activity);

            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {
                ActivityStackManager.getInstance().removeActivity(activity);

            }
        });
    }


    private ActivityStackManager() {

    }

    public static ActivityStackManager getInstance() {
        return sInstance;
    }

    public Stack<Activity> getActivityStack(){
        if(mActivityStack != null){
            return mActivityStack;
        }
        return null;
    }

    /**
     * activity from oncreate callback is useable for dialog ,but not usable for popupwindow
     *
     * 几个情况:
     * 启动系统的activity: topAttachedActivityWeakRef被置为 null,那就从mActivityStack里拿
     * 启动应用内的activity后: topAttachedActivityWeakRef就是真正在上面的activity.
     * 退出应用后: topAttachedActivityWeakRef= null, mActivityStack也是null,拿到的是null
     *
     * A启动B,B调用setResult(),finish()-> A onActivityResult()回调执行->B onPause()执行->A OnResume()执行
     *
     * 如果是在某个Activity的onActivityResult回调里,拿到这个activity,用于startActivityForResult启动另一个activity,
     * 由于onActivityResult先于onResume回调,如果立刻去getTopActivity(),拿到的是上一个即将finish的activity.
     * 所以需要判断isFinishing
     *
     2021-01-18 13:01:03.463 8377-8377/com.hss01248.dialogutildemo D/onresult: com.hss01248.dialogutildemo.ActivityResultActivity@3be12ac,Intent { (has extras) }, top activity:com.hss01248.dialogutildemo.ActivityResultActivity@3be12ac
     2021-01-18 13:01:03.494 8377-8377/com.hss01248.dialogutildemo D/onresult: com.hss01248.dialogutildemo.ActivityResultActivity@464fba1,Intent { (has extras) }, top activity:com.hss01248.dialogutildemo.ActivityResultActivity@464fba1
     2021-01-18 13:01:03.527 8377-8377/com.hss01248.dialogutildemo D/onresult: com.hss01248.dialogutildemo.ActivityResultActivity@3ccc2b7,Intent { (has extras) }, top activity:com.hss01248.dialogutildemo.ActivityResultActivity@3ccc2b7
     2021-01-18 13:01:03.555 8377-8377/com.hss01248.dialogutildemo D/onresult: com.hss01248.dialogutildemo.MainActivity@6144e8a,Intent { (has extras) }, top activity:com.hss01248.dialogutildemo.MainActivity@6144e8a

     * @return
     */
    public Activity getTopActivity() {
        Activity currentActivity = null;
        if (topAttachedActivityWeakRef != null && topAttachedActivityWeakRef.get() != null) {
            currentActivity =  topAttachedActivityWeakRef.get();
            if(isUsable(currentActivity)){
                return currentActivity;
            }
        }
        if(mActivityStack.size()>0){
            currentActivity = getUsableActivity(mActivityStack);

        }
        return currentActivity;
    }

    private Activity getUsableActivity(Stack<Activity> mActivityStack) {
        for (int i = mActivityStack.size() - 1; i >= 0; i--) {
            Activity activity = mActivityStack.get(i);
            if(isUsable(activity)){
                return activity;
            }
        }
        return null;
    }

    public Activity getTopActivity(Class activityExpected){
        for (int i = mActivityStack.size() - 1; i >= 0; i--) {
            Activity activity = mActivityStack.get(i);
            if(isUsable(activity)){
                if(activity.getClass().equals(activityExpected)){
                    return activity;
                }

            }
        }
        return null;
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

    public Activity getActivityAt(int index){
        Activity activity = null;
        if (mActivityStack == null) {
            return null;
        }
        if(mActivityStack.size() >= index){
            activity =  mActivityStack.get(index);
        }
        return activity;
    }

    /**
     * 获取指定位置的activity
     *
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
   public   void setTopAttached(Activity activity) {
        topAttachedActivityWeakRef = new WeakReference<Activity>(activity);
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
   public   void addActivity(Activity activity) {
        if (mActivityStack == null) {
            mActivityStack = new Stack<>();
        }
        if (activity != null) {
            if(!mActivityStack.contains(activity)){
                mActivityStack.add(activity);
            }

           // Log.i("dialog","mActivityStack.size()--addActivity:"+activity.getClass().getSimpleName()+mActivityStack.size());
        }
        printStack("onCreate:\n");
    }

    /**
     * 移除activity
     */
    public void removeActivity(Activity activity) {
        if(mActivityStack == null){
            return;
        }
        if(mActivityStack.contains(activity)){
            mActivityStack.remove(activity);
        }
       // Log.i("dialog"," mActivityStack.size()--removeActivity:"+activity.getClass().getSimpleName()+mActivityStack.size());
        printStack("onDestory:\n");
    }

    private void printStack(String time) {
        if (!isDebug){
            return;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = mActivityStack.size()-1; i >=0; i--) {
            builder.append(mActivityStack.get(i).getClass().getSimpleName())
                .append("-:")
                .append(i)
                .append("\n");
        }
        Log.i("printStack dialog",time+builder.toString());
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

        try {
            Iterator<Activity> iterator = mActivityStack.iterator();
            List<Activity> destoryedActivities = new ArrayList<>();
            while (iterator.hasNext()){
                Activity activity = iterator.next();
                if(activity.getClass().equals(cls)){
                    iterator.remove();
                    destoryedActivities.add(activity);
                }
            }
            if(destoryedActivities.size()>0){
                for (Activity activity:destoryedActivities) {
                    if(activity.isFinishing()){
                        return;
                    }
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                        if(activity.isDestroyed()){
                            return;
                        }
                    }
                    activity.finish();
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }



    /**
     * finish除了指定activity类型之外的其他所有activity
     * @param cls 不需要移除的activity的Class类型
     */
    public void finishActivitiesExcept(Class<?>... cls){
        if(mActivityStack.isEmpty()){
            return;
        }
        if(cls.length==0){
            return;
        }
        List list = Arrays.asList(cls);
        Iterator<Activity> iterator = mActivityStack.iterator();
        while (iterator.hasNext()){
            Activity activity = iterator.next();
            Class clazz = activity.getClass();
            if(!list.contains(clazz)){
                iterator.remove();
                activity.finish();
            }
        }
    }

    /**
     * 清除栈内所有的实例并退出app
     */
    public void finishAllActivityAndExit () {
        if (null != mActivityStack) {
            for (int i = 0, size = mActivityStack.size(); i < size; i++) {
                if (null != mActivityStack.get(i)) {
                    try {
                        ActivityCompat.finishAfterTransition(mActivityStack.get(i));
                    } catch (Exception ignored) {}
                }
            }
//            clean ();
//            android.os.Process.killProcess(android.os.Process.myPid());
//            System.exit(1);
        }
    }

    public void clean () {
        if (sInstance != null) {
            mActivityStack.clear();
            mActivityStack = null;
            sInstance = null;
        }
    }
}
