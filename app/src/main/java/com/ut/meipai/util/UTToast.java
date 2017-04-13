package com.ut.meipai.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by 任和 on 2017/04/13 11:24
 * Function:  封装在任意线程可安全弹出Toast
 * Desc:      使用方法: UTToast.presentToast("content");
 */
public class UTToast {

    /**
     * 系统默认Toast
     *
     * @param context {@link Context}
     * @param content 提示内容
     */
    private static void showToast(Context context, String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }

    /**
     * 始终运行在主线程的Toast
     *
     * @param context {@link Context}
     * @param content 提示内容
     */
    public static void presentToast(final Context context, final String content) {
        if (ThreadUtil.isRunInMainThread()) {
            showToast(context, content);
        } else {
            ThreadUtil.post(new Runnable() {
                @Override
                public void run() {
                    showToast(context, content);
                }
            });
        }
    }
}
