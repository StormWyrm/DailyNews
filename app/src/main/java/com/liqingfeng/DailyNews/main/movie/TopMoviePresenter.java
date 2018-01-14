package com.liqingfeng.DailyNews.main.movie;

import android.widget.ImageView;

import com.liqingfeng.DailyNews.bean.douban.movie.HotMovieBean;
import com.liqingfeng.DailyNews.bean.douban.movie.SubjectsBean;
import com.liqingfeng.DailyNews.common.ui.IBaseModel;

import io.reactivex.functions.Consumer;


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
        mModel.getMovieTop250(mStart, mCount)
                .subscribe(new Consumer<HotMovieBean>() {
                    @Override
                    public void accept(HotMovieBean hotMovieBean) throws Exception {
                        mStart += mCount;
                        mView.showTopMovie(hotMovieBean.getSubjects());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    @Override
    void getMoreTopMovie() {
        if (mModel == null || mView == null)
            return;

        if (!isLoading) {
            isLoading = true;
            mModel.getMovieTop250(mStart, mCount).subscribe(new Consumer<HotMovieBean>() {
                @Override
                public void accept(HotMovieBean hotMovieBean) throws Exception {
                    isLoading = false;
                    if (hotMovieBean != null && hotMovieBean.getSubjects() != null &&
                            hotMovieBean.getSubjects().size() > 0) {
                        mStart += mCount;
                        mView.showTopMovie(hotMovieBean.getSubjects());
                    } else {
                        mView.showNoMoreData();
                    }
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    isLoading = false;
                    mView.showNetworkError();
                }
            });
        }
    }

    @Override
    void onItemClick(int position, SubjectsBean subjectsBean, ImageView imageView) {

    }

}
