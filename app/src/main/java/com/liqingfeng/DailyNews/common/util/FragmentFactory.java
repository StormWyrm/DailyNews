package com.liqingfeng.DailyNews.common.util;

import android.util.SparseArray;

import com.liqingfeng.DailyNews.common.ui.BaseLazyLoadFragment;
import com.liqingfeng.DailyNews.main.home.DBFragment;
import com.liqingfeng.DailyNews.main.home.GKFragment;
import com.liqingfeng.DailyNews.main.home.ZHFragment;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/3/21 10:26
 * @DESC: 用于缓存Framgent
 * @VERSION: V1.0
 */

public class FragmentFactory {
    private static SparseArray<BaseLazyLoadFragment> fragments = new SparseArray<>();

    public static BaseLazyLoadFragment getFragment(int position) {
        BaseLazyLoadFragment fragment = fragments.get(position);
        if (fragment == null) {
            switch (position) {
                case 0:
                    fragment = new ZHFragment();
                    break;
                case 1:
                    fragment = new GKFragment();
                    break;
                case 2:
                    fragment = new DBFragment();
                    break;
            }
        }
        return fragment;
    }
}
