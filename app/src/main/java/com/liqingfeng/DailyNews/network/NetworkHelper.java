package com.liqingfeng.DailyNews.network;

import com.liqingfeng.DailyNews.common.util.Api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/5/2 23:23
 * @DESC: $TODO
 * @VERSION: V1.0
 */
public class NetworkHelper {
    private static NetworkHelper networkHelper;
    private static Retrofit.Builder mBuilder;

    public static NetworkHelper getInstance() {
        if (networkHelper == null) {
            synchronized (NetworkHelper.class) {
                if (networkHelper == null) {
                    networkHelper = new NetworkHelper();
                    mBuilder = new Retrofit.Builder()
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
                }
            }
        }
        return networkHelper;
    }

    public ZhihuServer getZhihuService() {
        Retrofit retrofit = mBuilder.baseUrl(Api.ZHIHU_BASE).build();
        return retrofit.create(ZhihuServer.class);
    }

    public GuokeService getGuokeServer() {
        Retrofit retrofit = mBuilder.baseUrl(Api.GUOKE_BASE).build();
        return retrofit.create(GuokeService.class);
    }

    public DoubanService getDoubanService() {
        Retrofit retrofit = mBuilder.baseUrl(Api.DOUBAN_BASE).build();
        return retrofit.create(DoubanService.class);
    }

    public <T> T getService(Class<T> clazz,String host){
        return mBuilder.baseUrl(host).build().create(clazz);
    }
}
