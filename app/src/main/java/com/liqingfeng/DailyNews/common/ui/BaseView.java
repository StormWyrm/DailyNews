package com.liqingfeng.DailyNews.common.ui;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/4/25 11:04
 * @DESC: mvp模式中View的基类
 * @VERSION: V1.0
 */
public interface BaseView<T> {
    /**
     * 绑定Presenter
     * @param presenter
     */
    void setPresenter(T presenter);


}
