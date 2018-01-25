package com.liqingfeng.DailyNews.main.movie;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.liqingfeng.DailyNews.R;
import com.liqingfeng.DailyNews.common.ui.BaseFragment;
import com.liqingfeng.DailyNews.main.movie.hot.HotMovieFragment;

/**
 * Created by lonlife on 2018/1/25.
 */

public class MovieRootFragment extends BaseFragment {

    public static MovieRootFragment newInstance() {
        Bundle args = new Bundle();

        MovieRootFragment fragment = new MovieRootFragment();
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
        if(findChildFragment(HotMovieFragment.class) == null){
            loadRootFragment(R.id.fl_container,HotMovieFragment.newInstance());
        }
    }
}
