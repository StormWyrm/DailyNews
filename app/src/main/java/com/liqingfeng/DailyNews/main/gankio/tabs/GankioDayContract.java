package com.liqingfeng.DailyNews.main.gankio.tabs;


import com.liqingfeng.DailyNews.bean.gankio.GankIoDayItemBean;
import com.liqingfeng.DailyNews.common.ui.IBaseFragment;
import com.liqingfeng.DailyNews.common.ui.IBaseModel;
import com.liqingfeng.DailyNews.common.ui.IBasePresenter;
import com.liqingfeng.DailyNews.common.ui.IBaseView;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;



/**
 * Created by lonlife on 2018/1/11.
 */

public interface GankioDayContract {
    interface Model extends IBaseModel {
        @GET("/api/day/{year}/{month}/{day}")
        Observable<List<GankIoDayItemBean>> getGankIoDay(String year, String month, String day);

        /**
         * 获取指定page Android数据
         *
         * @param page page
         * @return Android item数据
         */
        GankIoDayItemBean getGankIoDayAndroid(int page);

        /**
         * 获取指定page IOS数据
         *
         * @param page page
         * @return IOS item数据
         */
        GankIoDayItemBean getGankIoDayIOS(int page);
    }

    interface View extends IBaseFragment {
        void updateContentList(List<GankIoDayItemBean> list);

        void itemNotifyChange(int position, GankIoDayItemBean item);

        void showNetworkError();

        void showLodding();
    }

    abstract class Presenter extends IBasePresenter<Model, View> {
        abstract void loadLastestList();

        abstract void onItemClick(int position, GankIoDayItemBean item);

        abstract void onRefreshItemClick(int position, GankIoDayItemBean item);

        abstract void onGetMoreClick(int position, GankIoDayItemBean item);
    }
}
