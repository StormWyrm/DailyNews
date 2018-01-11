package com.example.liqingfeng.DailyNews.main.home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.liqingfeng.DailyNews.common.util.FragmentFactory;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/3/21 7:47
 * @DESC: 主页面 ViewPager的Adapter
 * @VERSION: V1.0
 */

public class HomePagerAdapter extends FragmentPagerAdapter {
    private String[] titles;

    public HomePagerAdapter(FragmentManager fm, String[] titles) {
        super(fm);
        this.titles = titles;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentFactory.getFragment(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
