package com.liqingfeng.DailyNews.main.movie.top;

import android.widget.ImageView;

import com.liqingfeng.DailyNews.bean.douban.movie.HotMovieBean;
import com.liqingfeng.DailyNews.bean.douban.movie.SubjectsBean;
import com.liqingfeng.sdk.base.IBaseFragment;
import com.liqingfeng.sdk.base.IBaseModel;
import com.liqingfeng.sdk.base.IBasePresenter;
import com.liqingfeng.sdk.base.activity.BaseActivity;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by lonlife on 2018/1/5.
 */

public interface TopMovieContract {
    interface Model extends IBaseModel {
        Observable<HotMovieBean> getMovieTop250(int start, int end);
    }

    interface View extends IBaseFragment {
        void updateTopMovieContent(List<SubjectsBean> list);

        void showNetworkError();

        void showLoadMoreError();

        void showNoMoreData();

        BaseActivity getBindActivity();
    }

    abstract class Presenter extends IBasePresenter<Model, View> {
       abstract void loadLastestTopMovie();

       abstract void loadMoreTopMovie();

       abstract void onItemClick(int position, SubjectsBean subjectsBean, ImageView imageView);
    }
}
