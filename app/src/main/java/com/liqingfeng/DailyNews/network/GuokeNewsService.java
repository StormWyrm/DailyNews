package com.liqingfeng.DailyNews.network;

import com.liqingfeng.DailyNews.bean.guoke.GuokeHotNewsListBean;
import com.liqingfeng.DailyNews.bean.guoke.GuokeNewsListBean;

import io.reactivex.Observable;
import retrofit2.http.GET;


/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/5/2 23:04
 * @DESC: $TODO
 * @VERSION: V1.0
 */
public interface GuokeNewsService {
    public static final String HOST = "http://apis.guokr.com/";

    @GET("/handpick/article.json?retrieve_type=by_since&category=all&limit=25&ad=1")
    Observable<GuokeNewsListBean> getGuokeNews();

    @GET("/flowingboard/item/handpick_carousel.json")
    Observable<GuokeHotNewsListBean> getGuokeHotNews();
}
