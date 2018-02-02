package com.liqingfeng.sdk.helper;

import retrofit2.Retrofit;

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/5/2 23:23
 * @DESC: $TODO
 * @VERSION: V1.0
 */
public class RetrofitHelper {
    private static RetrofitHelper retrofitHelper;
    private static Retrofit.Builder mBuilder;

    public static RetrofitHelper getInstance() {
        if (retrofitHelper == null) {
            synchronized (RetrofitHelper.class) {
                if (retrofitHelper == null) {
                    retrofitHelper = new RetrofitHelper();
                    mBuilder = new Retrofit.Builder()
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
                }
            }
        }
        return retrofitHelper;
    }
    
    public <T> T getService(Class<T> clazz,String host){
        return mBuilder.baseUrl(host).build().create(clazz);
    }
}
