package com.ut.meipai.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by LZM on 2017/4/21.
 * Function:
 * Desc:
 */

public abstract class BaseMyActivity extends AppCompatActivity {

    protected Activity mContext;
    private Unbinder mUnbinder;
    protected int isFirstBack;
    protected boolean mIsFirstShow = true;


    @LayoutRes
    protected abstract int getLayout();

    @ColorInt
    protected int getStatusBarColor() {
        return 0;
    }

    protected boolean isTransparentStatusBar() {
        return false;
    }

    protected boolean isNeedSwipeBack() {
        return false;
    }

    protected void loadData() {
    }

    protected void beforeSetView() {
    }

    protected void beforeInitView() {
    }

    protected abstract void initView(Bundle var1);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = this;
        this.beforeSetView();
        this.setContentView(this.getLayout());
        this.mUnbinder = ButterKnife.bind(this);
        this.beforeInitView();
        this.initView(savedInstanceState);
    }


    protected void onResume() {
        if (this.mIsFirstShow) {
            this.mIsFirstShow = false;
            this.loadData();
        }

        super.onResume();
    }

    protected void onDestroy() {
        super.onDestroy();
        this.mUnbinder.unbind();
    }

//    protected void quitApp() {
//        if(this.isFirstBack == 0) {
//            ToastUtil.show("再按一次退出程序");
//            this.isFirstBack = 1;
//            (new Timer()).schedule(new TimerTask() {
//                public void run() {
//                    BasicActivity.this.isFirstBack = 0;
//                }
//            }, 1000L);
//        } else if(this.isFirstBack == 1) {
//            StackUtil.getIns().popAll();
//            this.finish();
//            System.exit(0);
//        }
//
//    }
}
