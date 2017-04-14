package com.ut.meipai.fragment;

import android.util.SparseArray;

import com.ut.meipai.base.BaseFragment;

/**
 * Created by 任和 on 2017/04/12 15:58
 * Function:
 * Desc:
 */
public class FragmentFactory {
    /**
     * 首页底部Tab1:美拍
     */
    private static final int VIDEO   = 0;
    /**
     * 首页底部Tab2:我的关注
     */
    private static final int FOCUS   = 1;
    /**
     * 首页底部Tab3:频道
     */
    private static final int CHANNEL = 3;
    /**
     * 首页底部Tab4:个人中心
     */
    private static final int MINE    = 4;

    private static SparseArray<BaseFragment> sFragmentSparseArray = new SparseArray<>();

    public static BaseFragment create(int position) {
        BaseFragment fragment = sFragmentSparseArray.get(position);

        if (fragment == null) {
            switch (position) {
                case VIDEO:
                    fragment = new VideoFragment();
                    break;
                case FOCUS:
                    fragment = new MyFocusFragment();
                    break;
                case CHANNEL:
                    fragment = new ChannelFragment();
                    break;
                case MINE:
                    fragment = new MineFragment();
                    break;
                default:
                    break;
            }

            sFragmentSparseArray.put(position, fragment);
        }

        return fragment;
    }
}
