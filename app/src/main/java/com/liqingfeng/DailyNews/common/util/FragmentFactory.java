package com.liqingfeng.DailyNews.common.util;

import android.util.SparseArray;

import com.liqingfeng.DailyNews.common.ui.BaseFragment;
import com.liqingfeng.DailyNews.common.ui.BaseLazyLoadFragment;
import com.liqingfeng.DailyNews.main.home.tabs.DoubanNewsFragment;
import com.liqingfeng.DailyNews.main.home.tabs.GuokeNewsFragment;
import com.liqingfeng.DailyNews.main.home.tabs.ZhihuNewsFragment;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/3/21 10:26
 * @DESC: 用于缓存Framgent
 * @VERSION: V1.0
 */

public class FragmentFactory {
    private static SparseArray<BaseFragment> fragments = new SparseArray<>();

    public static BaseFragment getFragment(int position) {
        BaseFragment fragment = fragments.get(position);
        if (fragment == null) {
            switch (position) {
                case 0:
                    fragment = new ZhihuNewsFragment();
                    break;
                case 1:
                    fragment = new GuokeNewsFragment();
                    break;
                case 2:
                    fragment = new DoubanNewsFragment();
                    break;
            }
        }
        return fragment;
    }
}
