package com.example.liqingfeng.DailyNews.main.movie;

import android.content.Intent;
import android.test.mock.MockContext;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.liqingfeng.DailyNews.bean.douban.movie.HotMovieBean;
import com.example.liqingfeng.DailyNews.bean.douban.movie.SubjectsBean;
import com.example.liqingfeng.DailyNews.bean.douban.news.DBNews;
import com.example.liqingfeng.DailyNews.common.AppApplication;
import com.example.liqingfeng.DailyNews.common.ui.BaseActivity;
import com.example.liqingfeng.DailyNews.common.ui.IBaseModel;
import com.example.liqingfeng.DailyNews.common.util.ToastUtil;
import com.example.liqingfeng.DailyNews.detail.movie.MovieDetailActivity;
import com.example.liqingfeng.DailyNews.main.movie.adapter.HotMovieAdapter;

import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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
                .subscribe(new Subscriber<HotMovieBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        mView.showNetworkError();
                    }

                    @Override
                    public void onNext(HotMovieBean hotMovieBean) {
                        if(mView == null)
                            return;
                        mView.showHotMovie(hotMovieBean.getSubjects());
                    }
                });
    }

    @Override
    void onMovieItemClick(HotMovieAdapter adapter, View view, int position, SubjectsBean subjectsBean) {
        BaseActivity activity = mView.getBindActivity();
        Intent intent = new Intent(activity,MovieDetailActivity.class);
        intent.putExtra("SubjectBean",subjectsBean);
        activity.startActivity(intent);
    }


    @Override
    void onTopMovieClick() {
        ToastUtil.shortMessage(AppApplication.getInstance(),"Top Movie: ");

    }
}
