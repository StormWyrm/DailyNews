package com.example.liqingfeng.DailyNews.network;

import com.example.liqingfeng.DailyNews.bean.douban.movie.HotMovieBean;
import com.example.liqingfeng.DailyNews.bean.douban.movie.MovieDetailBean;
import com.example.liqingfeng.DailyNews.bean.douban.news.DBNews;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/5/2 23:05
 * @DESC: $TODO
 * @VERSION: V1.0
 */
public interface DoubanService {

    @GET("/api/stream/date/{date}")
    Observable<DBNews> getDBNews(@Path("date") String date);

}
