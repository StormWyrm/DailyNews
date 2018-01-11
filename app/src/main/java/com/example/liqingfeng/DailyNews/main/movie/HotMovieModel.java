package com.example.liqingfeng.DailyNews.main.movie;

import com.example.liqingfeng.DailyNews.bean.douban.movie.HotMovieBean;
import com.example.liqingfeng.DailyNews.network.DoubanMovieService;
import com.example.liqingfeng.DailyNews.network.DoubanService;
import com.example.liqingfeng.DailyNews.network.NetworkHelper;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lonlife on 2018/1/4.
 */

public class HotMovieModel implements HotMovieContract.Model {
    private DoubanMovieService mDoubanMovieService;

    public HotMovieModel(){
        mDoubanMovieService = NetworkHelper.getInstance().getService(DoubanMovieService.class,DoubanMovieService.HOST);
    }

    @Override
    public Observable<HotMovieBean> getHotMovie() {
        return mDoubanMovieService.getHotMovie().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
