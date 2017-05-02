package com.ut.meipai.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.ut.meipai.R;
import com.ut.meipai.adpter.MyHeadZhiboAdapter;
import com.ut.meipai.base.BaseFragment;

import butterknife.BindView;
import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Created by LZM on 2017/4/24.
 * Function:
 * Desc:
 */

public class VideoZhiboFragment extends BaseFragment {

    BGABanner mFirstBanner;
    BGABanner mSecondBanner;
    @BindView(R.id.rv_content)
    RecyclerView mContent;
    @BindView(R.id.btn_want_zhibo)
    Button mWantBtn;

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        initData();
    }

    private void initData() {

    }

    public View getHeader(){
        View view = LayoutInflater.from(mContext).inflate(R.layout.header_fragment_video_zhibo, null);
        mFirstBanner = (BGABanner) view.findViewById(R.id.banner_fragment_video_first);
        mSecondBanner = (BGABanner) view.findViewById(R.id.banner_fragment_video_second);
//        mSecondBanner.setAdapter(new MyHeadZhiboAdapter());
        return view;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video_common;
    }
}
