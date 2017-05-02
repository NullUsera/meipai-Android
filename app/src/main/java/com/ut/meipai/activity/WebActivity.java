package com.ut.meipai.activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.ut.meipai.R;
import com.ut.meipai.base.BaseTitleActivity;

import butterknife.BindView;

/**
 * Created by LZM on 2017/4/21.
 * Function:
 * Desc:
 */

public class WebActivity extends BaseTitleActivity {

    private static final String ARG_PARAMS_TITLE = "title";
    private static final String ARG_PARAMS_URL = "url";
    @BindView(R.id.wv_mainWebView) WebView contentWebView;
    @BindView(R.id.pb_loadingWebView) ProgressBar loadingProgressBar;

    @Override
    protected void setTitleBar() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_web;
    }

    @Override
    protected void initView(Bundle bundle) {
        super.initView(bundle);

    }
}
