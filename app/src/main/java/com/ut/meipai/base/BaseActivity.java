package com.ut.meipai.base;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Window;

import com.ut.meipai.manager.AppManager;

/**
 * Created by 任和 on 2017/04/12 15:58
 * Function:
 * Desc:
 */
// // TODO: 2017/4/13 待完善 任和
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        AppManager.getAppManager().addActivity(this);
        initView();
        getPageData();
    }

    /**
     * 用于初始化当前页
     */
    public abstract void initView();

    /**
     * 加载当前页数据
     */
    public abstract void getPageData();


    public String getRunningActivityName() {
        ActivityManager activityManager = (ActivityManager) getSystemService(
                Context.ACTIVITY_SERVICE);
        return activityManager.getRunningTasks(1).get(0).topActivity.getClassName();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            AppManager.getAppManager().finishActivity();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        if (AppManager.activityStack.contains(this)) {
            AppManager.activityStack.remove(this);
        }
        super.onDestroy();
    }
}
