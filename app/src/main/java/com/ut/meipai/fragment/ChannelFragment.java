package com.ut.meipai.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ut.meipai.R;
import com.ut.meipai.adpter.ChannelHeadAdapter;
import com.ut.meipai.adpter.ChannelHotAdapter;
import com.ut.meipai.base.LoadAndRefreshFragment;
import com.ut.meipai.entity.ChannelHeadViewEntity;
import com.ut.meipai.entity.ChannelHotEntity;
import com.ut.meipai.widget.TitleBar;


import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrFrameLayout;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by 任和 on 2017/04/12 16:08
 * Function:  频道页
 * Desc:   需要下拉刷新，上拉加载
 */
public class ChannelFragment extends LoadAndRefreshFragment {

    ChannelHotAdapter mAdapter;
    List<ChannelHotEntity> hotData = new ArrayList<>();
    List<ChannelHeadViewEntity> headData = new ArrayList<>();
    static String picture = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1492666045050&di=86066287e7e7dec4f6db6044d52c16e6&imgtype=0&src=http%3A%2F%2Fimg1.gtimg.com%2Fcomic%2Fpics%2Fhv1%2F52%2F127%2F2129%2F138470662.jpg";

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        super.initView(view, savedInstanceState);
        loadData();
    }

    @Override
    protected void loadData() {
        if (hotData.size() < 5 || headData.size() < 5) {
            for (int i = 0; i < 10; i++) {
                hotData.add(new ChannelHotEntity(picture, "图" + i, 100 + i));
                headData.add(new ChannelHeadViewEntity( "名字" + i,picture));
            }
        }
        setHeader(headData);
        Observable.just(hotData)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ChannelHotEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<ChannelHotEntity> value) {
                        if (mPtrFrameLayout.isRefreshing()) {
                            mAdapter.setNewData(new ArrayList<ChannelHotEntity>());
                            mPtrFrameLayout.refreshComplete();
                        }
                        mAdapter.addData(value);
//                        mRecyclerView.setAdapter(mAdapter);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        if (hotData.size()>5) mAdapter.loadMoreEnd(true);
                    }
                });
    }

    private void setHeader(List<ChannelHeadViewEntity> headData) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_channel_head, null);
        RecyclerView headView = (RecyclerView) view.findViewById(R.id.rv_channel_head);
        headView.setLayoutManager(new GridLayoutManager(mContext, 4));
        headView.setAdapter(new ChannelHeadAdapter(headData));
        if (mAdapter.getHeaderLayoutCount() == 0){
            mAdapter.addHeaderView(view);
        }
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
        mAdapter = new ChannelHotAdapter(null);
        return mAdapter;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false);
    }

    @Override
    public void onLoadMoreRequested() {
//        mAdapter.loadMoreEnd();
    }

    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {
        loadData();
        mPtrFrameLayout.refreshComplete();
    }
}
