package com.liqingfeng.DailyNews.main.movie.hot;

import android.os.Bundle;
import android.view.View;

import com.liqingfeng.DailyNews.bean.douban.movie.HotMovieBean;
import com.liqingfeng.DailyNews.bean.douban.movie.SubjectsBean;
import com.liqingfeng.DailyNews.common.AppApplication;
import com.liqingfeng.DailyNews.common.ui.IBaseModel;
import com.liqingfeng.DailyNews.common.util.ToastUtil;
import com.liqingfeng.DailyNews.detail.movie.MovieDetailActivity;
import com.liqingfeng.DailyNews.main.movie.adapter.HotMovieAdapter;
import com.liqingfeng.DailyNews.main.movie.top.TopMovieActivity;

import io.reactivex.functions.Consumer;


/**
 * Created by lonlife on 2018/1/4.
 */

public class HotMoviePresenter extends HotMovieContract.Presenter {

    @Override
    public IBaseModel getModel() {
        return new HotMovieModel();
    }

    @Override
    void getHotMovie() {
        if(mView == null || mModel == null){
            return;
        }
        mModel.getHotMovie()
                .subscribe(new Consumer<HotMovieBean>() {
                    @Override
                    public void accept(HotMovieBean hotMovieBean) throws Exception {
                        if(mView == null)
                            return;
                        mView.showHotMovie(hotMovieBean.getSubjects());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showNetworkError();
                    }
                });
    }


    @Override
    void onMovieItemClick(HotMovieAdapter adapter, View view, int position, SubjectsBean subjectsBean) {
        Bundle extra = new Bundle();
        extra.putSerializable("SubjectBean",subjectsBean);
        mView.startNewActivity(MovieDetailActivity.class,extra);
    }


    @Override
    void onTopMovieClick() {
        mView.startNewActivity(TopMovieActivity.class);
        ToastUtil.shortMessage(AppApplication.getInstance(),"Top Movie: ");
    }
}
