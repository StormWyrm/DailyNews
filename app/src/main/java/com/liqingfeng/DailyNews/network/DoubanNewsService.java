package com.liqingfeng.DailyNews.network;

import com.liqingfeng.DailyNews.bean.douban.news.DoubanNewsListBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;


/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/5/2 23:05
 * @DESC: $TODO
 * @VERSION: V1.0
 */
public interface DoubanNewsService {
    public static final String HOST = "https://moment.douban.com/";

    @GET("/api/stream/date/{date}")
    Observable<DoubanNewsListBean> getDoubanNews(@Path("date") String date);

}
