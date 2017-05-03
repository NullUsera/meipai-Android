package com.ut.meipai.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ut.meipai.R;
import com.ut.meipai.adpter.MyHeadZhiboAdapter;
import com.ut.meipai.adpter.VideoZhiboItemAdapter;
import com.ut.meipai.base.BaseFragment;
import com.ut.meipai.base.BaseRefreshLoadFragment;
import com.ut.meipai.util.DensityUtil;
import com.ut.meipai.util.GlideUtil;

import butterknife.BindView;
import cn.bingoogolapple.bgabanner.BGABanner;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by LZM on 2017/4/24.
 * Function:
 * Desc:
 */

public class VideoZhiboFragment extends BaseRefreshLoadFragment {

    BGABanner mFirstBanner;
    BGABanner mSecondBanner;
    @BindView(R.id.rv_content)
    RecyclerView mContent;
//    @BindView(R.id.btn_want_zhibo)
    Button mWantBtn;
    BaseQuickAdapter mAdapter;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        initData();

    }

    private void initData() {

    }

    public View getHeader(){
        View view = LayoutInflater.from(mContext).inflate(R.layout.header_fragment_video_zhibo, null);
        if (mIsFirstShow){
            int width = DensityUtil.getWindowWidth();
            mFirstBanner = (BGABanner) view.findViewById(R.id.banner_fragment_video_first);
            ViewGroup.LayoutParams firstParams = mFirstBanner.getLayoutParams();
            firstParams.height = (int) (width * 400.0 / 1000.0);
            mSecondBanner = (BGABanner) view.findViewById(R.id.banner_fragment_video_second);
            ViewGroup.LayoutParams secondParams = mSecondBanner.getLayoutParams();
            secondParams.height = (int) (width *100.0 /600.0);
        }
        mFirstBanner.setAdapter(new BGABanner.Adapter() {
            @Override
            public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
                GlideUtil.loadImg(ChannelFragment.picture, (ImageView) view);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
            }
        });
//        mSecondBanner.setAdapter(new MyHeadZhiboAdapter());
        return view;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video_common;
    }

    @Override
    public BaseQuickAdapter getAdapter() {
        mAdapter =new VideoZhiboItemAdapter(null);
        return mAdapter;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(mContext,2);
    }

    @Override
    public void onLoadMoreRequested() {

    }

    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {

    }


}
