package com.ut.meipai.base;

import android.view.View;

import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;

/**
 * Created by LZM on 2017/4/18.
 * Function:
 * Desc:
 */

public abstract class BaseRefreshLoadFragment extends BaseFragment implements IUpdateDataView{
    private UpdateDataDelegate mDelegate;

    public BaseRefreshLoadFragment() {
    }

    protected void beforeInitView() {
        this.mDelegate = new UpdateDataDelegate(this.mView);
        this.mDelegate.initPTR(this, this.getRefreshHeader());
        this.mDelegate.initLoad(this, this.getAdapter(), this.getLayoutManager());
    }

    public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
        return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
    }

    public PtrUIHandler getRefreshHeader() {
        return new PtrClassicDefaultHeader(this.mContext);
    }
}
