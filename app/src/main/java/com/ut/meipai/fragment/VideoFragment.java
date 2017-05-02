package com.ut.meipai.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.FrameLayout;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.ut.meipai.R;
import com.ut.meipai.base.BaseFragment;
import com.ut.meipai.entity.VideoTabNamesEntity;
import com.ut.meipai.util.GlideUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 任和 on 2017/04/12 16:08
 * Function:  视频列表页
 * Desc:
 */

public class VideoFragment extends BaseFragment {

    @BindView(R.id.tab_fragment_video)CommonTabLayout mCommonTabLayout;
    @BindView(R.id.vp_container)ViewPager mViewPager;

    private List<Fragment> mFragments = new ArrayList<>();
    private List<CustomTabEntity> mTabEntity=new ArrayList<>();

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video;
    }

    public List getTab(){
        mTabEntity.add(new VideoTabNamesEntity("直播",R.drawable.ic_video_zhibo_selected,R.drawable.ic_video_zhibo_unselected));
        mTabEntity.add(new VideoTabNamesEntity("热门",R.drawable.ic_video_hot_selected,R.drawable.ic_video_hot_unselected));
        mTabEntity.add(new VideoTabNamesEntity("同城",R.drawable.ic_video_zhibo_selected,R.drawable.ic_video_zhibo_unselected));
        return mTabEntity;
    }

    public List getFragment(){
        mFragments.add(new VideoZhiboFragment());
        mFragments.add(new VideoHotFragment());
        mFragments.add(new VideoZhiboFragment());
        return mFragments;
    }


}

