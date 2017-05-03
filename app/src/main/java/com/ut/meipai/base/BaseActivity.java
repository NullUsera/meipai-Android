package com.ut.meipai.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.Toast;

import com.ut.meipai.manager.AppManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 任和 on 2017/04/12 15:58
 * Function:
 * Desc:
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected Activity mContext;
    private Unbinder mUnbinder;
    protected boolean mIsFirstShow = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        this.mContext = this;
        AppManager.getAppManager().addActivity(this);
        this.beforeSetView();
        this.setContentView(this.getLayout());
        this.mUnbinder = ButterKnife.bind(this);
        this.beforeInitView();
        this.initView(savedInstanceState);
        loadPageData();
    }

    @LayoutRes
    protected abstract int getLayout();

    protected void beforeSetView() {
    }

    protected void beforeInitView() {
    }

    protected void onResume() {
        if(this.mIsFirstShow) {
            this.mIsFirstShow = false;
            this.loadData();
        }

        super.onResume();
    }

    protected void loadData() {
    }

    /**
     * 用于初始化当前页
     */
    protected abstract void initView(Bundle var1);

    /**
     * 加载当前页数据
     */
    public void loadPageData(){

    }


    public String getRunningActivityName() {
        ActivityManager activityManager = (ActivityManager) getSystemService(
                Context.ACTIVITY_SERVICE);
        return activityManager.getRunningTasks(1).get(0).topActivity.getClassName();
    }

    public void showToast(String message) {
        if (TextUtils.isEmpty(message)) {
            return;
        }
        Toast.makeText(this, message, message.length() > 8 ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
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
        this.mUnbinder.unbind();
    }
}
