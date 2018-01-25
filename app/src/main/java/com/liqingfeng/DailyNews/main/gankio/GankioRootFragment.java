package com.liqingfeng.DailyNews.main.gankio;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.liqingfeng.DailyNews.R;
import com.liqingfeng.DailyNews.common.ui.BaseFragment;

/**
 * Created by lonlife on 2018/1/25.
 */

public class GankioRootFragment extends BaseFragment {

    public static GankioRootFragment newInstance() {
        Bundle args = new Bundle();

        GankioRootFragment fragment = new GankioRootFragment();
        fragment.setArguments(args);
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
        if(findChildFragment(GankioFragment.class) == null){
            loadRootFragment(R.id.fl_container,GankioFragment.newInstance());
        }
    }
}
