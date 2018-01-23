package com.liqingfeng.DailyNews.main.gankio.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.liqingfeng.DailyNews.common.ui.BaseFragment;

/**
 * Created by lonlife on 2018/1/11.
 */

public class GankioMainPagerApater extends FragmentPagerAdapter {
    private BaseFragment[] fragments;
    private String[] titles;
    public GankioMainPagerApater(FragmentManager fm, BaseFragment[] fragments,String[] titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return titles[position];
    }
}
