package com.example.liqingfeng.DailyNews.network;

import com.example.liqingfeng.DailyNews.bean.zhihu.ZHHotNews;
import com.example.liqingfeng.DailyNews.bean.zhihu.ZHNews;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/5/2 22:28
 * @DESC: Api接口
 * @VERSION: V1.0
 */
public interface ZhihuServer {

    //根据日期获取知乎日报新闻
    @GET("/api/4/news/before/{date}")
    Observable<ZHNews> getZHNews(@Path("date") String date);

    //获取知乎热搜
    @GET("/api/3/news/hot")
    Observable<ZHHotNews> getZHHotNews();
}
