package com.ut.meipai.adpter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by LZM on 2017/5/2.
 * Function:
 * Desc:
 */

public class VideoViewpagerAdapter extends FragmentPagerAdapter {

    List<Fragment> mFragments;

    public VideoViewpagerAdapter(FragmentManager fm, List mFragments) {
        super(fm);
        this.mFragments=mFragments;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

//    @Override
//    public CharSequence getPageTitle(int position) {
//        return mTitles.get(position);
//    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }
}
