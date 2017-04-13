package com.ut.meipai.util;

import android.os.Handler;

import com.ut.meipai.app.MeiPaiApplication;

/**
 * Created by 任和 on 2017/04/13 11:25
 * Function:
 * Desc:
 */
public class ThreadUtil {

    /**
     * 获取主线程ID
     */
    private static long getMainThreadId() {
        return MeiPaiApplication.getMainThreadId();
    }

    /**
     * 获取主线程handler
     */
    private static Handler getHandler() {
        return MeiPaiApplication.getMainThreadHandler();
    }


    /**
     * 判断当前线程是否在主线程
     */
    public static boolean isRunInMainThread() {
        return android.os.Process.myTid() == getMainThreadId();
    }

    /**
     * 主线程执行runnable
     */
    public static boolean post(Runnable runnable) {
        return getHandler().post(runnable);
    }
}
