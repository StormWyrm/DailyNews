package com.liqingfeng.DailyNews.detail.movie;

import com.liqingfeng.DailyNews.bean.douban.movie.MovieDetailBean;
import com.liqingfeng.DailyNews.network.DoubanMovieService;
import com.liqingfeng.sdk.helper.RetrofitHelper;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by lonlife on 2018/1/5.
 */

public class MovieDetailModel implements MovieDetailContract.Model {
    private DoubanMovieService doubanMovieService;

    public MovieDetailModel() {
        this.doubanMovieService = RetrofitHelper.getInstance().
                getService(DoubanMovieService.class, DoubanMovieService.HOST);
    }

    @Override
    public Observable<MovieDetailBean> getMovieDetail(String id) {
        return doubanMovieService.getMovieDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
