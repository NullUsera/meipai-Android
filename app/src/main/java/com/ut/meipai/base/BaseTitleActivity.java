package com.ut.meipai.base;

import android.os.Bundle;

import com.ut.meipai.R;
import com.ut.meipai.widget.TitleBar;

import butterknife.BindView;

/**
 * Created by LZM on 2017/4/21.
 * Function:
 * Desc:
 */

public abstract class BaseTitleActivity extends BaseMyActivity {

    @BindView(R.id.title_bar) TitleBar titleBar;

    @Override
    protected void initView(Bundle bundle) {
        if (titleBar !=null){
            setTitleBar();
        }
    }

    protected abstract void setTitleBar();
}
