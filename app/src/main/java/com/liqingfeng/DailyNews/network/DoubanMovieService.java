package com.liqingfeng.DailyNews.network;

import com.liqingfeng.DailyNews.bean.douban.movie.HotMovieBean;
import com.liqingfeng.DailyNews.bean.douban.movie.MovieDetailBean;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lonlife on 2018/1/5.
 */

public interface DoubanMovieService {
    String HOST = "Https://api.douban.com/";
    /**
     * 豆瓣热映电影，每日更新
     */
    @GET("v2/movie/in_theaters")
    Observable<HotMovieBean> getHotMovie();

    /**
     * 获取电影详情
     *
     * @param id 电影bean里的id
     */
    @GET("v2/movie/subject/{id}")
    Observable<MovieDetailBean> getMovieDetail(@Path("id") String id);

    /**
     * 获取豆瓣电影top250
     *
     * @param start 从多少开始，如从"0"开始
     * @param count 一次请求的数目，如"10"条，最多100
     */
    @GET("v2/movie/top250")
    Observable<HotMovieBean> getMovieTop250(@Query("start") int start, @Query("count") int count);

//    /**
//     * 根据tag获取图书
//     *
//     * @param tag   搜索关键字
//     * @param start 从多少开始，如从"0"开始
//     * @param count 一次请求的数目 最多100
//     */
//
//    @GET("v2/book/search")
//    Observable<BookListBean> getBookListWithTag(@Query("tag") String tag, @Query("start") int
//            start, @Query("count") int count);
//
//    @GET("v2/book/{id}")
//    Observable<BookDetailBean> getBookDetail(@Path("id") String id);
}
