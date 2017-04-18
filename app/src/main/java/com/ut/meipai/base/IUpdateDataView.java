package com.ut.meipai.base;

import android.support.v7.widget.RecyclerView.LayoutManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter.RequestLoadMoreListener;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.PtrUIHandler;

/**
 * Created by LZM on 2017/4/18.
 * Function:
 * Desc: 加载更多，下拉刷新
 */

public interface IUpdateDataView extends RequestLoadMoreListener ,PtrHandler{

    BaseQuickAdapter getAdapter();

    LayoutManager getLayoutManager();

    PtrUIHandler getRefreshHeader();

}
