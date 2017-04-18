package com.ut.meipai.base;

import android.os.Bundle;
import android.view.View;

import com.ut.meipai.widget.TitleBar;

/**
 * Created by LZM on 2017/4/18.
 * Function:
 * Desc:
 */

public abstract class LoadAndRefreshFragment extends BaseFragment {

//    @BindView(R.id.rv_content)
//    RecyclerView   mRecyclerView;
//    @BindView(R.id.ptr_load_and_refresh)
//    PtrFrameLayout mPtrFrameLayout;
//    @BindView(R.id.title_bar)
//    TitleBar       mTitleBar;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
//        ButterKnife.bind(this, view);
//        if (mTitleBar != null) {
//            setTitleBar(mTitleBar);
//        }
    }

    protected abstract void setTitleBar(TitleBar mTitleBar);
}
