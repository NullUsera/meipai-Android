package com.ut.meipai.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ut.meipai.R;
import com.ut.meipai.base.LoadAndRefreshFragment;
import com.ut.meipai.widget.TitleBar;

import in.srain.cube.views.ptr.PtrFrameLayout;


/**
 * Created by 任和 on 2017/04/12 16:08
 * Function:  频道页
 * Desc:   需要下拉刷新，上拉加载
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

    @Override
    public BaseQuickAdapter getAdapter() {
        return null;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return null;
    }

    @Override
    public void onLoadMoreRequested() {

    }

    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {

    }
}
