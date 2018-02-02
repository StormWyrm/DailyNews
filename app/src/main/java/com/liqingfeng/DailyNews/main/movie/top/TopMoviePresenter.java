package com.liqingfeng.DailyNews.main.movie.top;

import android.widget.ImageView;

import com.liqingfeng.DailyNews.bean.douban.movie.HotMovieBean;
import com.liqingfeng.DailyNews.bean.douban.movie.SubjectsBean;
import com.liqingfeng.DailyNews.detail.movie.MovieDetailActivity;
import com.liqingfeng.sdk.base.IBaseModel;

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
                        if (mView == null)
                            return;
                        mStart += mCount;
                        mView.updateTopMovieContent(hotMovieBean.getSubjects());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        throwable.printStackTrace();
                        if (mView == null)
                            return;
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
                    if (mView == null)
                        return;
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
                    if (mView == null)
                        return;
                    mView.showLoadMoreError();
                }
            });
        }
    }

    @Override
    void onItemClick(int position, SubjectsBean subjectsBean, ImageView imageView) {
//        Intent intent = new Intent(mView.getBindActivity(), MovieDetailActivity.class);
//        Bundle extra = new Bundle();
//        extra.putSerializable("SubjectBean", subjectsBean);
//        intent.putExtras(extra);
//
//        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(mView.getBindActivity(),
//                imageView, mView.getBindActivity().getString(R.string.transition_movie_img));
//
//        ActivityCompat.startActivity(mView.getBindActivity(), intent, options.toBundle());
        MovieDetailActivity.start(mView.getBindActivity(), subjectsBean, imageView);

    }

}
