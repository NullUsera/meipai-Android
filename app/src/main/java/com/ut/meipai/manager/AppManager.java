package com.ut.meipai.manager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.Stack;

/**
 * Created by 任和 on 2017/04/12 15:58
 * Function:  App管理类
 * Desc:
 */
public class AppManager {

    public static Stack<Activity> activityStack;
    private static AppManager instance;

    public static AppManager getAppManager() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }

        if (!activityStack.contains(activity)) {
            activityStack.add(activity);
        }
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 退出App
     */
    public void finishActivityAll() {
        try {
            if (activityStack.size() > 0) {
                Activity activity = activityStack.lastElement();
                if (activity != null) {
                    activityStack.remove(activity);
                    activity.finish();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        try {
            if (activityStack.size() > 0) {
                Activity activity = activityStack.lastElement();
                finishActivity(activity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
        }
    }


    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
                break;
            }
        }
    }


    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }


    /**
     * 退出应用程序
     */
    public void AppExit() {
        try {
            finishAllActivity();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Activity跳转到指定页面
     *
     * @param context {@link Context}
     * @param clazz   target activity
     */
    public static void redirectPage(Context context, Class<?> clazz) {
        Intent intent = new Intent(context, clazz);
        context.startActivity(intent);
    }


    /**
     * Activity带参跳转
     *
     * @param bundle  {@link Bundle}
     * @param context {@link Context}
     * @param clazz   target activity
     */
    public static void redirectPage(Bundle bundle, Context context, Class<?> clazz) {
        Intent intent = new Intent(context, clazz);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}