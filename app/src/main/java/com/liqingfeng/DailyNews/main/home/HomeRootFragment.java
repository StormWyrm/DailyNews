package com.liqingfeng.DailyNews.main.home;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.liqingfeng.DailyNews.R;
import com.liqingfeng.sdk.base.fragment.BaseFragment;


/**
 * Created by lonlife on 2018/1/25.
 */

public class HomeRootFragment extends BaseFragment {

    public static HomeRootFragment newInstance() {
        Bundle args = new Bundle();

        HomeRootFragment fragment = new HomeRootFragment();

        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_container;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        //懒加载
        if(findChildFragment(HomeFragment.class) == null){
            loadRootFragment(R.id.fl_container, HomeFragment.newInstance());
        }
    }
}
