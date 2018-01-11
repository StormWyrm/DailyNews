package com.example.liqingfeng.DailyNews.main.movie;

import android.support.annotation.NonNull;
import android.view.Display;

import com.example.liqingfeng.DailyNews.R;
import com.example.liqingfeng.DailyNews.bean.douban.movie.HotMovieBean;
import com.example.liqingfeng.DailyNews.bean.douban.movie.SubjectsBean;
import com.example.liqingfeng.DailyNews.common.ui.BaseMvpActivity;
import com.example.liqingfeng.DailyNews.common.ui.IBaseModel;
import com.example.liqingfeng.DailyNews.common.ui.IBasePresenter;
import com.example.liqingfeng.DailyNews.common.ui.IBaseView;

import java.util.List;

/**
 * Created by lonlife on 2018/1/5.
 */

public class TopMovieFragment extends BaseMvpActivity<TopMovieContract.Model,TopMovieContract.Presenter> implements TopMovieContract.View {


    @Override
    protected int getViewId() {
        return R.layout.fragment_top_movie;
    }

    @NonNull
    @Override
    public IBasePresenter initPresenter() {
        return new TopMoviePresenter();
    }


    @Override
    public void showTopMovie(List<SubjectsBean> list) {

    }

    @Override
    public void showNetworkError() {

    }

    @Override
    public void showNoMoreData() {

    }
}
