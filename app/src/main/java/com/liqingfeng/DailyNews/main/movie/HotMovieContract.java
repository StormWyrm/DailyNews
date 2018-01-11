package com.liqingfeng.DailyNews.main.movie;



import com.liqingfeng.DailyNews.bean.douban.movie.HotMovieBean;
import com.liqingfeng.DailyNews.bean.douban.movie.SubjectsBean;
import com.liqingfeng.DailyNews.common.ui.BaseActivity;
import com.liqingfeng.DailyNews.common.ui.IBaseModel;
import com.liqingfeng.DailyNews.common.ui.IBasePresenter;
import com.liqingfeng.DailyNews.common.ui.IBaseView;
import com.liqingfeng.DailyNews.main.movie.adapter.HotMovieAdapter;

import java.util.List;

import rx.Observable;


/**
 * Created by lonlife on 2018/1/3.
 */

public interface HotMovieContract {
    interface Model extends IBaseModel {
        Observable<HotMovieBean> getHotMovie();
    }

    interface View extends IBaseView {
        void showHotMovie(List<SubjectsBean> subjectsBeans);

        void showNetworkError();

        BaseActivity getBindActivity();
    }

    abstract class Presenter extends IBasePresenter<Model,View> {

        /**
         * 获取热门电影列表
         * @return
         */
        abstract void getHotMovie();

        abstract void onMovieItemClick(HotMovieAdapter adapter, android.view.View view, int position,SubjectsBean subjectsBean);

        abstract void onTopMovieClick();
    }
}
