package com.ut.meipai.entity;

import android.support.v4.app.Fragment;

/**
 * Created by LZM on 2017/5/3.
 * Function:
 * Desc:
 */

public class TabEntity {
    public String mTitle;
    public int mSelectedIcon;
    public int mUnSelectedIcon;
    public Fragment mFragment;

    public TabEntity(String title, int unSelectedIcon, int selectedIcon, Fragment fragment) {
        this.mTitle = title;
        this.mSelectedIcon = selectedIcon;
        this.mUnSelectedIcon = unSelectedIcon;
        this.mFragment = fragment;
    }

    public TabEntity(int unSelectedIcon, int selectedIcon, Fragment fragment) {
        this.mSelectedIcon = selectedIcon;
        this.mUnSelectedIcon = unSelectedIcon;
        this.mFragment = fragment;
    }
}
