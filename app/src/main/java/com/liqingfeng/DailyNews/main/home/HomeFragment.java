package com.liqingfeng.DailyNews.main.home;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.liqingfeng.DailyNews.R;
import com.liqingfeng.DailyNews.main.home.adapter.HomePagerAdapter;
import com.liqingfeng.DailyNews.main.home.tabs.DoubanNewsFragment;
import com.liqingfeng.DailyNews.main.home.tabs.GuokeNewsFragment;
import com.liqingfeng.DailyNews.main.home.tabs.ZhihuNewsFragment;
import com.liqingfeng.sdk.base.fragment.BaseFragment;

import butterknife.BindView;

/**
 * 主页Fragment
 */
public class HomeFragment extends BaseFragment {
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.vp_home)
    ViewPager vpHome;

    private OnDrawerLayoutOpenListener onDrawerLayoutOpenListener;
    private HomePagerAdapter mAdapter;
    private String[] titles;
    private Fragment[] fragments;

    public HomeFragment() {

    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnDrawerLayoutOpenListener) {
            onDrawerLayoutOpenListener = (OnDrawerLayoutOpenListener) context;
        } else {
            throw new RuntimeException("MainActivity未实现OnDrawerLayoutOpenListener");
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        titles = new String[]{getString(R.string.main_fragment_zhihu), getString(R.string
                .main_fragment_guoke), getString(R.string.main_fragment_douban)};
        fragments = new Fragment[titles.length];
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        toolBar.setNavigationIcon(R.drawable.ic_vecotr_drawer_menu);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDrawerLayoutOpenListener.onOpen();
            }
        });

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        fragments[0] = findFragment(ZhihuNewsFragment.class);
        fragments[1] = findFragment(GuokeNewsFragment.class);
        fragments[2] = findFragment(DoubanNewsFragment.class);

        if(fragments[0] == null ){
            fragments[0] = ZhihuNewsFragment.newInstance();
        }

        if(fragments[1] == null ){
            fragments[1] = GuokeNewsFragment.newInstance();
        }

        if(fragments[2] == null ){
            fragments[2] = DoubanNewsFragment.newInstance();
        }

        mAdapter = new HomePagerAdapter(getChildFragmentManager(), titles, fragments);
        vpHome.setAdapter(mAdapter);
        tabLayout.setupWithViewPager(vpHome);
    }

    public interface OnDrawerLayoutOpenListener {
        void onOpen();
    }

}
