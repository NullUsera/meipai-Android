package com.ut.meipai.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.PtrUIHandler;

/**
 * Created by LZM on 2017/4/18.
 * Function:
 * Desc:
 */

public class UpdateDataDelegate {
    private PtrFrameLayout mPtrLayout;
    private RecyclerView mRecyclerView;
    private View mRootView;

    public UpdateDataDelegate(View rootView) {
        this.mRootView = rootView;
    }

    public void initLoad(BaseQuickAdapter.RequestLoadMoreListener listener, BaseQuickAdapter adapter, RecyclerView.LayoutManager manager) {
        this.getRecyclerView(this.mRootView);
        if(null != manager && null != this.mRecyclerView) {
            this.mRecyclerView.setLayoutManager(manager);
        }

        this.setAdapter(listener, adapter);
    }

    public void initPTR(PtrHandler ptrHandler, PtrUIHandler header) {
        this.getPtrLayout(this.mRootView);
        if(null != this.mPtrLayout) {
            this.mPtrLayout.setPtrHandler(ptrHandler);
            this.mPtrLayout.disableWhenHorizontalMove(true);
            this.mPtrLayout.setHeaderView((View)header);
            this.mPtrLayout.addPtrUIHandler(header);
        }

    }

    private void getPtrLayout(View rootView) {
        if(this.isPTR(rootView) && this.mPtrLayout == null) {
            this.mPtrLayout = (PtrFrameLayout)rootView;
        } else if(rootView instanceof ViewGroup) {
            ViewGroup contentView = (ViewGroup)rootView;
            int childCount = contentView.getChildCount();

            for(int i = 0; i < childCount; ++i) {
                View childView = contentView.getChildAt(i);
                this.getPtrLayout(childView);
            }
        }

    }

    private void getRecyclerView(View rootView) {
        if(this.isRV(rootView) && this.mRecyclerView == null) {
            this.mRecyclerView = (RecyclerView)rootView;
        } else if(rootView instanceof ViewGroup) {
            ViewGroup contentView = (ViewGroup)rootView;
            int childCount = contentView.getChildCount();

            for(int i = 0; i < childCount; ++i) {
                View childView = contentView.getChildAt(i);
                this.getRecyclerView(childView);
            }
        }

    }

    private void setAdapter(BaseQuickAdapter.RequestLoadMoreListener listener, BaseQuickAdapter adapter) {
        if(null != adapter && null != this.mRecyclerView) {
            adapter.setOnLoadMoreListener(listener);
            this.mRecyclerView.setAdapter(adapter);
        }

    }

    private boolean isPTR(View view) {
        return view instanceof PtrFrameLayout;
    }

    private boolean isRV(View view) {
        return view instanceof RecyclerView;
    }
}
