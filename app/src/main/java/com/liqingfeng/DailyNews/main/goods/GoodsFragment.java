package com.liqingfeng.DailyNews.main.goods;

import android.os.Bundle;

import com.liqingfeng.DailyNews.R;
import com.liqingfeng.DailyNews.common.ui.BaseFragment;

public class GoodsFragment extends BaseFragment {

    public GoodsFragment() {
    }

    public static GoodsFragment newInstance() {
        GoodsFragment fragment = new GoodsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getViewId() {
        return R.layout.fragment_goods;
    }
}
