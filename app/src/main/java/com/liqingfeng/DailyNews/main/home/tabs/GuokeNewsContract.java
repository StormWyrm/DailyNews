package com.liqingfeng.DailyNews.main.home.tabs;

import com.liqingfeng.DailyNews.bean.guoke.GuokeHotNewsItemBean;
import com.liqingfeng.DailyNews.bean.guoke.GuokeHotNewsListBean;
import com.liqingfeng.DailyNews.bean.guoke.GuokeNewsItemBean;
import com.liqingfeng.DailyNews.bean.guoke.GuokeNewsListBean;
import com.liqingfeng.DailyNews.common.ui.IBaseFragment;
import com.liqingfeng.DailyNews.common.ui.IBaseModel;
import com.liqingfeng.DailyNews.common.ui.IBasePresenter;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/4/30 9:57
 * @DESC: 果壳精选---MVP 接口
 * @VERSION: V1.0
 */
public interface GuokeNewsContract {

    interface Model extends IBaseModel {
        Observable<GuokeNewsListBean> getGuokeNews();

        Observable<GuokeHotNewsListBean> getGuokeHotNews();
    }

    interface View extends IBaseFragment {
        void updateGuokeNewsContent(List<GuokeNewsItemBean> list);

        void updateGuokeHotNewsContent(List<GuokeHotNewsItemBean> list);

        void showNetworkError();

        void showNoDataError();

        void showNoHotNewsError();
    }

    abstract class Presenter extends IBasePresenter<Model, View> {
        abstract void loadLastestGuokeNews();

        abstract void loadLoastestGuokeHotNews();

        abstract void onItemClick(int position,GuokeNewsItemBean guokeNewsItemBean);
    }
}
