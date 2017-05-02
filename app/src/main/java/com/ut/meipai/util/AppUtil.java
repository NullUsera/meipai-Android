package com.ut.meipai.util;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by LZM on 2017/4/21.
 * Function:
 * Desc:
 */

public class AppUtil {
    /**
     * 清除应用缓存
     *
     * @param context
     */
    public static void clearCache(Context context) {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            deleteFilesByDirectory(context.getExternalCacheDir());
        }
    }

    private static void deleteFilesByDirectory(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File item : directory.listFiles()) {
                item.delete();
            }
        }
    }
}
