package com.liqingfeng.DailyNews.main.movie;

import android.widget.ImageView;

import com.liqingfeng.DailyNews.bean.douban.movie.HotMovieBean;
import com.liqingfeng.DailyNews.bean.douban.movie.SubjectsBean;
import com.liqingfeng.DailyNews.common.ui.IBaseModel;
import com.liqingfeng.DailyNews.common.ui.IBasePresenter;
import com.liqingfeng.DailyNews.common.ui.IBaseView;

import java.util.List;

import rx.Observable;

/**
 * Created by lonlife on 2018/1/5.
 */

public interface TopMovieContract {
    interface Model extends IBaseModel {
        Observable<HotMovieBean> getMovieTop250(int start,int end);
    }

    interface View extends IBaseView {
        void showTopMovie(List<SubjectsBean> list);

        void showNetworkError();

        void showNoMoreData();
    }

    abstract class Presenter extends IBasePresenter<Model, View> {
       abstract void getMovieTop250();

       abstract void getMoreTopMovie();

       abstract void onItemClick(int position, SubjectsBean subjectsBean, ImageView imageView);
    }
}
