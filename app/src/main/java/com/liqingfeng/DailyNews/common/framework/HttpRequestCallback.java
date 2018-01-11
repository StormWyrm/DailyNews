package com.liqingfeng.DailyNews.common.framework;


/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/3/23 11:17
 * @DESC: 网络请求回调接口
 * @VERSION: V1.0
 */

public abstract class HttpRequestCallback {

    /**
     * 发起网络请求之前调用
     */
    public void onStart() {
    }

    /**
     * 获取数据成功时回调
     *
     * @param result 返回结果
     */
    public abstract void onSuccess(String result);

    /**
     * 获取数据失败时回调
     *
     * @param errorCode
     * @param e
     */
    public void onFailure(int errorCode, Throwable e) {
    }

    /**
     * 当请求数据为空的时候回调
     */
    public void onEmpty() {
    }

    /**
     * 网络请求结束时回调
     */
    public void onEnd() {
    }
}
