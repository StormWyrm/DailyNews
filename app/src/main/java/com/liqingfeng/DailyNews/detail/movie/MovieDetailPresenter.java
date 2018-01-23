package com.liqingfeng.DailyNews.detail.movie;

import android.os.Bundle;

import com.liqingfeng.DailyNews.bean.douban.movie.MovieDetailBean;
import com.liqingfeng.DailyNews.bean.douban.movie.PersonBean;
import com.liqingfeng.DailyNews.browser.BrowserActivity;
import com.liqingfeng.DailyNews.common.constant.BundleKeyConstant;
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
    void onHeaderClick(int positon, MovieDetailBean movieDetailBean) {
    }

    @Override
    void onItemCLick(int positon, PersonBean personBean) {
        Bundle bundle = new Bundle();
        bundle.putString(BundleKeyConstant.BUNDLE_KEY_BROWSER_URL,personBean.getAlt());
        mView.startNewActivity(BrowserActivity.class,bundle);
    }
}
