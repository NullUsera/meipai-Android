package com.ut.meipai.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by LZM on 2017/4/24.
 * Function:
 * Desc:
 */

public class VideoTabAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments;
    private String[] mTitles;

    public VideoTabAdapter(FragmentManager fm, List<Fragment> fragments, String[] titles) {
        super(fm);
        this.mFragments = fragments;
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
