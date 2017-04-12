package com.ut.meipai.fragment;

import android.support.v4.app.Fragment;
import android.util.SparseArray;

/**
 * Created by 任和 on 2017/04/12 15:58
 * Function:
 * Desc:
 */
public class FragmentFactory {

    private static SparseArray<Fragment> sFragmentSparseArray = new SparseArray<>();

    public static Fragment create(int position) {
        Fragment fragment = sFragmentSparseArray.get(position);

        if (fragment == null) {
            switch (position) {
                case 0:
                    fragment = new FragmentVideo();
                    break;
                case 1:
                    fragment = new FragmentFocus();
                    break;
                case 2:
                    fragment = new FragmentChannel();
                    break;
                case 3:
                    fragment = new FragmentMine();
                    break;
                default:
                    break;
            }

            sFragmentSparseArray.put(position, fragment);
        }

        return fragment;
    }
}
