package com.liqingfeng.DailyNews.main.gankio;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.liqingfeng.DailyNews.R;
import com.liqingfeng.DailyNews.constant.RxBusCodeCanstant;
import com.liqingfeng.sdk.base.fragment.BaseFragment;
import com.liqingfeng.sdk.rxbus.RxBus;
import com.liqingfeng.sdk.rxbus.Subscribe;
import com.liqingfeng.DailyNews.main.gankio.adapter.GankioMainPagerApater;
import com.liqingfeng.DailyNews.main.gankio.tabs.GankioCustomFragment;
import com.liqingfeng.DailyNews.main.gankio.tabs.GankioDayFragment;
import com.liqingfeng.DailyNews.main.gankio.tabs.GankioWelfareFragment;

import butterknife.BindView;

public class GankioFragment extends BaseFragment {

    @BindView(R.id.tab_gank)
    TabLayout tabGank;
    @BindView(R.id.vp_gank)
    ViewPager vpGank;
    @BindView(R.id.fab_type)
    FloatingActionButton fabType;

    private GankioMainPagerApater mAdapter;
    private String[] titles;
    private BaseFragment[] fragments;

    public GankioFragment() {
    }

    public static GankioFragment newInstance() {
        GankioFragment fragment = new GankioFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        RxBus.get().register(this);
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onDestroy() {
        RxBus.get().unRegister(this);
        super.onDestroy();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_goods;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        titles = new String[]{
                getString(R.string.gankio_day),
                getString(R.string.gankio_custom),
                getString(R.string.gankio_welfare)
        };

        fragments = new BaseFragment[3];
        fragments[0] = new GankioDayFragment();
        fragments[1] = new GankioCustomFragment();
        fragments[2] = new GankioWelfareFragment();


    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mAdapter = new GankioMainPagerApater(getChildFragmentManager(), fragments, titles);
        vpGank.setAdapter(mAdapter);
        tabGank.setupWithViewPager(vpGank);
    }


    /**
     * 切换tabs
     */
    @Subscribe(code = RxBusCodeCanstant.RX_BUS_CODE_GANKIO_SELECT_TO_CHILD)
    public void rxBusEvent(Integer index) {
        tabGank.setVerticalScrollbarPosition(index);
        vpGank.setCurrentItem(index);
    }

}
