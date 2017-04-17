package com.ut.meipai.app;

import android.app.Application;
import android.os.Handler;

import com.ut.meipai.util.FileUtil;
import com.yixia.camera.VCamera;

import java.io.File;

/**
 * Created by 任和 on 2017/04/13 11:26
 * Function:
 * Desc:
 */
public class MeiPaiApplication extends Application {

    public static int mMainThreadId;
    public static Handler mMainThreadHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        mMainThreadId = android.os.Process.myTid();
        mMainThreadHandler = new Handler();

    }

    public static int getMainThreadId() {
        return mMainThreadId;
    }

    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }
}
