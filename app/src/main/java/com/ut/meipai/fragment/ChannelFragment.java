package com.ut.meipai.fragment;

import android.os.Bundle;
import android.view.View;

import com.ut.meipai.R;
import com.ut.meipai.base.LoadAndRefreshFragment;
import com.ut.meipai.widget.TitleBar;


/**
 * Created by 任和 on 2017/04/12 16:08
 * Function:  频道页
 * Desc:
 */
public class ChannelFragment extends LoadAndRefreshFragment {


    @Override
    protected void initView(View view, Bundle savedInstanceState) {
    }

    @Override
    protected void setTitleBar(TitleBar mTitleBar) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_channel;
    }

}
