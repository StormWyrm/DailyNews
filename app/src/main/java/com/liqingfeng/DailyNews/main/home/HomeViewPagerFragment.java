package com.liqingfeng.DailyNews.main.home;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.liqingfeng.DailyNews.R;
import com.liqingfeng.DailyNews.common.ui.BaseFragment;
import com.liqingfeng.DailyNews.main.home.adapter.HomePagerAdapter;

import butterknife.BindView;

/**
 * 主页Fragment
 */
public class HomeViewPagerFragment extends BaseFragment {
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.vp_home)
    ViewPager vpHome;

    private OnDrawerLayoutOpenListener onDrawerLayoutOpenListener;
    private HomePagerAdapter mAdapter;
    private String[] titles;

    public HomeViewPagerFragment() {

    }

    public static HomeViewPagerFragment newInstance() {
        HomeViewPagerFragment fragment = new HomeViewPagerFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnDrawerLayoutOpenListener){
            onDrawerLayoutOpenListener = (OnDrawerLayoutOpenListener) context;
        }else{
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

        mAdapter = new HomePagerAdapter(getChildFragmentManager(),titles);
        vpHome.setAdapter(mAdapter);
        tabLayout.setupWithViewPager(vpHome);

    }

    public interface OnDrawerLayoutOpenListener{
        void onOpen();
    }

}