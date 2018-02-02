package com.liqingfeng.DailyNews.detail.movie;

import com.liqingfeng.DailyNews.bean.douban.movie.MovieDetailBean;
import com.liqingfeng.DailyNews.bean.douban.movie.PersonBean;
import com.liqingfeng.sdk.base.IBaseActivity;
import com.liqingfeng.sdk.base.IBaseModel;
import com.liqingfeng.sdk.base.IBasePresenter;


import io.reactivex.Observable;


/**
 * Created by lonlife on 2018/1/5.
 */

public interface MovieDetailContract {
    interface Model extends IBaseModel {
        Observable<MovieDetailBean> getMovieDetail(String id);
    }

    interface View extends IBaseActivity {
        void showMovieDetail(MovieDetailBean movieDetailBean);

        void startLoading();

        void endLoading();

        void showNetworkError();
    }

    abstract class Presenter extends IBasePresenter<Model, View> {
        abstract void getMovieDetail(String id);

        abstract void onHeaderClick(int positon,MovieDetailBean movieDetailBean);

        abstract void onItemCLick(int positon, PersonBean personBean);
    }
}
