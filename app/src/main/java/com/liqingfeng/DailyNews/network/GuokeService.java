package com.liqingfeng.DailyNews.network;

import com.liqingfeng.DailyNews.bean.gk.GKHotNews;
import com.liqingfeng.DailyNews.bean.gk.GKNews;

import retrofit2.http.GET;
import rx.Observable;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/5/2 23:04
 * @DESC: $TODO
 * @VERSION: V1.0
 */
public interface GuokeService {

    @GET("/handpick/article.json?retrieve_type=by_since&category=all&limit=25&ad=1")
    Observable<GKNews> getGKNews();

    @GET("/flowingboard/item/handpick_carousel.json")
    Observable<GKHotNews> getGKHotNews();
}
