package com.liqingfeng.DailyNews.main.movie.top;

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
    void loadLastestTopMovie() {
        if (mModel == null || mView == null)
            return;
        mStart = 0;
        mModel.getMovieTop250(mStart, mCount)
                .subscribe(new Consumer<HotMovieBean>() {
                    @Override
                    public void accept(HotMovieBean hotMovieBean) {
                        mStart += mCount;
                        mView.updateTopMovieContent(hotMovieBean.getSubjects());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable)  {
                        throwable.printStackTrace();
                        mView.showNetworkError();
                    }
                });
    }

    @Override
    void loadMoreTopMovie() {
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
                        mView.updateTopMovieContent(hotMovieBean.getSubjects());
                    } else {
                        mView.showNoMoreData();
                    }
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    throwable.printStackTrace();
                    isLoading = false;
                    mView.showLoadMoreError();
                }
            });
        }
    }

    @Override
    void onItemClick(int position, SubjectsBean subjectsBean, ImageView imageView) {

    }

}
