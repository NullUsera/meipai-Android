package com.ut.meipai.entity;

import com.flyco.tablayout.listener.CustomTabEntity;

/**
 * Created by LZM on 2017/4/24.
 * Function:
 * Desc:
 */

public class VideoTabNamesEntity implements CustomTabEntity {

    String title;
    int selectedIcon;
    int unSelectedIcon;

    public VideoTabNamesEntity(String titles, int selectedIcon, int unSelectedIcon) {
        this.title = titles;
        this.selectedIcon = selectedIcon;
        this.unSelectedIcon = unSelectedIcon;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return selectedIcon;
    }

    @Override
    public int getTabUnselectedIcon() {
        return unSelectedIcon;
    }
}
