package com.liqingfeng.DailyNews.main.home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/3/21 7:47
 * @DESC: 主页面 ViewPager的Adapter
 * @VERSION: V1.0
 */

public class HomePagerAdapter extends FragmentStatePagerAdapter {
    private String[] titles;
    private Fragment[] fragments;
    public HomePagerAdapter(FragmentManager fm, String[] titles,Fragment[] fragments) {
        super(fm);
        this.titles = titles;
        this.fragments = fragments;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
