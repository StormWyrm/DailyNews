package com.liqingfeng.DailyNews.network;

import com.liqingfeng.DailyNews.bean.zhihu.ZHHotNews;
import com.liqingfeng.DailyNews.bean.zhihu.ZhihuNewsListBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;


/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/5/2 22:28
 * @DESC: Api接口
 * @VERSION: V1.0
 */
public interface ZhihuNewsServer {
    public static final String HOST = "http://news-at.zhihu.com/";

    //根据日期获取知乎日报新闻
    @GET("/api/4/news/before/{date}")
    Observable<ZhihuNewsListBean> getZhihuNews(@Path("date") String date);

    //获取知乎热搜
    @GET("/api/3/news/hot")
    Observable<ZHHotNews> getZhihuHotNews();
}
