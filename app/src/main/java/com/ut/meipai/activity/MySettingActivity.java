package com.ut.meipai.activity;

import android.os.Bundle;
import android.view.View;

import com.ut.meipai.R;
import com.ut.meipai.base.BaseActivity;
import com.ut.meipai.base.BaseTitleActivity;
import com.ut.meipai.util.AppUtil;

import butterknife.OnClick;

/**
 * Created by LZM on 2017/4/21.
 * Function:
 * Desc:
 */

public class MySettingActivity extends BaseTitleActivity {

    @Override
    protected void initView(Bundle bundle) {
        super.initView(bundle);

    }

    @Override
    protected void setTitleBar() {

    }

    @Override
    protected int getLayout() {
        return R.layout.layout_my_setting;
    }

    @OnClick({R.id.stv_my_setting_account,R.id.stv_my_setting_private,R.id.stv_my_setting_common
        ,R.id.stv_my_setting_return,R.id.stv_my_setting_about,R.id.stv_my_setting_clear_cache})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.stv_my_setting_account:
                break;
            case R.id.stv_my_setting_private:
                break;
            case R.id.stv_my_setting_common:
                break;
            case R.id.stv_my_setting_return:
                break;
            case R.id.stv_my_setting_about:
                break;
            case R.id.stv_my_setting_clear_cache:
                AppUtil.clearCache(mContext);
                break;
            case R.id.stv_my_setting_login:
                break;
        }
    }
}
