package com.liqingfeng.DailyNews.main.movie;

import com.liqingfeng.DailyNews.bean.douban.movie.HotMovieBean;

import io.reactivex.Observable;


/**
 * Created by lonlife on 2018/1/5.
 */

public class TopMovieModel implements TopMovieContract.Model{

    @Override
    public Observable<HotMovieBean> getMovieTop250(int start, int end) {
        return null;
    }
}
