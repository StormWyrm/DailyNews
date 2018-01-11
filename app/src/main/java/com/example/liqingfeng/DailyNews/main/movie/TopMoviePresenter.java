package com.example.liqingfeng.DailyNews.main.movie;

import android.widget.ImageView;

import com.example.liqingfeng.DailyNews.bean.douban.movie.HotMovieBean;
import com.example.liqingfeng.DailyNews.bean.douban.movie.SubjectsBean;
import com.example.liqingfeng.DailyNews.common.ui.IBaseModel;

import rx.Subscriber;

/**
 * Created by lonlife on 2018/1/5.
 */

public class TopMoviePresenter extends TopMovieContract.Presenter {
    private int mStart;
    private int mCount = 30;
    private boolean isLoading;

    @Override
    public IBaseModel getModel() {
        return new TopMovieModel();
    }

    @Override
    void getMovieTop250() {
        if (mModel == null || mView == null)
            return;
        mStart = 0;
        mModel.getMovieTop250(mStart, mCount).subscribe(new Subscriber<HotMovieBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(HotMovieBean hotMovieBean) {
                mStart += mCount;
                mView.showTopMovie(hotMovieBean.getSubjects());
            }
        });
    }

    @Override
    void getMoreTopMovie() {
        if (mModel == null || mView == null)
            return;

        if (!isLoading) {
            isLoading = true;
            mModel.getMovieTop250(mStart, mCount).subscribe(new Subscriber<HotMovieBean>() {
                @Override
                public void onCompleted() {
                    isLoading = false;
                }

                @Override
                public void onError(Throwable e) {
                    isLoading = false;
                    mView.showNetworkError();
                }

                @Override
                public void onNext(HotMovieBean hotMovieBean) {
                    isLoading = false;
                    if (hotMovieBean != null && hotMovieBean.getSubjects() != null &&
                            hotMovieBean.getSubjects().size() > 0) {
                        mStart += mCount;
                        mView.showTopMovie(hotMovieBean.getSubjects());
                    } else {
                        mView.showNoMoreData();
                    }
                }
            });
        }
    }

    @Override
    void onItemClick(int position, SubjectsBean subjectsBean, ImageView imageView) {

    }

}
