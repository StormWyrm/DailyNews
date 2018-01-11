package com.example.liqingfeng.DailyNews.common.framework;


import java.util.Map;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/3/24 17:56
 * @DESC: 网络请求的接口
 * @VERSION: V1.0
 */
public interface  HttpRequestManager {

    /**
     * HTTP进行Get请求
     * @param url
     * @param callback
     */
    void  getRequest(String url, HttpRequestCallback callback);


    /**
     * HTTP进行Post请求
     * @param url
     * @param map
     * @param callback
     */
   void postRequest(String url, Map<String,String> map,HttpRequestCallback callback);
}
