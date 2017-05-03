package com.ut.meipai.base;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ut.meipai.R;
import com.ut.meipai.widget.TitleBar;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by LZM on 2017/4/18.
 * Function:
 * Desc:
 */

public abstract class LoadAndRefreshFragment extends BaseRefreshLoadFragment {

    @BindView(R.id.rv_content)protected RecyclerView mRecyclerView;
    @BindView(R.id.ptr_load_and_refresh)protected PtrFrameLayout mPtrFrameLayout;
    @BindView(R.id.title_bar)TitleBar mTitleBar;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        if (mTitleBar != null){
            setTitleBar(mTitleBar);
        }
    }

    protected abstract void setTitleBar(TitleBar mTitleBar);
}
