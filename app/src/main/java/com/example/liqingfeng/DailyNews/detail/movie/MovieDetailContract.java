package com.example.liqingfeng.DailyNews.detail.movie;

import com.example.liqingfeng.DailyNews.bean.douban.movie.MovieDetailBean;
import com.example.liqingfeng.DailyNews.common.ui.IBaseModel;
import com.example.liqingfeng.DailyNews.common.ui.IBasePresenter;
import com.example.liqingfeng.DailyNews.common.ui.IBaseView;

import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by lonlife on 2018/1/5.
 */

public interface MovieDetailContract {
    interface Model extends IBaseModel {
        Observable<MovieDetailBean> getMovieDetail(String id);
    }

    interface View extends IBaseView {
        void showMovieDetail(MovieDetailBean movieDetailBean);

        void startLoading();

        void endLoading();

        void showNetworkError();
    }

    abstract class Presenter extends IBasePresenter<Model, View> {
        abstract void getMovieDetail(String id);

        abstract void onHeaderClick(String id);

        abstract void onItemCLick();
    }
}
