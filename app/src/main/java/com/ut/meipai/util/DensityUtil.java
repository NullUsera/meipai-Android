package com.ut.meipai.util;

import android.content.Context;
import android.util.DisplayMetrics;

import com.ut.meipai.manager.AppManager;


public class DensityUtil {

    /**
     * 根据手机的分辨率从dp的单位转成为px
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从px转成为dp
     */
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 获取屏幕宽度
     */
    public static int getWindowWidth() {
        DisplayMetrics dm = new DisplayMetrics();
        AppManager.getAppManager()
                .currentActivity()
                .getWindowManager()
                .getDefaultDisplay()
                .getMetrics(dm);
        return dm.widthPixels;
    }
}
