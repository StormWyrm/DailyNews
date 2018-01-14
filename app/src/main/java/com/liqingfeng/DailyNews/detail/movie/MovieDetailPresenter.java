package com.liqingfeng.DailyNews.detail.movie;

import com.liqingfeng.DailyNews.bean.douban.movie.MovieDetailBean;
import com.liqingfeng.DailyNews.common.AppApplication;
import com.liqingfeng.DailyNews.common.ui.IBaseModel;
import com.liqingfeng.DailyNews.common.util.ToastUtil;

import io.reactivex.functions.Consumer;


/**
 * Created by lonlife on 2018/1/5.
 */

public class MovieDetailPresenter extends MovieDetailContract.Presenter {
    @Override
    public IBaseModel getModel() {
        return new MovieDetailModel();
    }

    @Override
    void getMovieDetail(String id) {
        if (mModel == null || mView == null)
            return;
        mModel.getMovieDetail(id)
                .subscribe(new Consumer<MovieDetailBean>() {
                    @Override
                    public void accept(MovieDetailBean movieDetailBean) throws Exception {
                        if (mView != null) {
                            mView.showMovieDetail(movieDetailBean);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });


    }

    @Override
    void onHeaderClick(String id) {
        ToastUtil.shortMessage(AppApplication.getInstance(), "头部点击");
    }

    @Override
    void onItemCLick() {
        ToastUtil.shortMessage(AppApplication.getInstance(), "item点击");
    }
}
