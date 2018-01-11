package com.liqingfeng.DailyNews.detail.movie;

import com.liqingfeng.DailyNews.bean.douban.movie.MovieDetailBean;
import com.liqingfeng.DailyNews.common.AppApplication;
import com.liqingfeng.DailyNews.common.ui.IBaseModel;
import com.liqingfeng.DailyNews.common.util.ToastUtil;

import rx.Subscriber;

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
        if(mModel != null){
            mModel.getMovieDetail(id).subscribe(new Subscriber<MovieDetailBean>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(MovieDetailBean movieDetailBean) {
                    if(mView != null){
                        mView.showMovieDetail(movieDetailBean);
                    }
                }
            });
        }
    }

    @Override
    void onHeaderClick(String id) {
        ToastUtil.shortMessage(AppApplication.getInstance(),"头部点击");
    }

    @Override
    void onItemCLick() {
        ToastUtil.shortMessage(AppApplication.getInstance(),"item点击");
    }
}
