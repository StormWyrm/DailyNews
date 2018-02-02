package com.liqingfeng.DailyNews.main.home.tabs;



import com.liqingfeng.DailyNews.bean.douban.news.DoubanNewsItemBean;
import com.liqingfeng.DailyNews.bean.douban.news.DoubanNewsListBean;
import com.liqingfeng.sdk.base.IBaseFragment;
import com.liqingfeng.sdk.base.IBaseModel;
import com.liqingfeng.sdk.base.IBasePresenter;


import java.util.List;

import io.reactivex.Observable;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/4/30 9:57
 * @DESC: 豆瓣一刻---MVP接口
 * @VERSION: V1.0
 */
public interface DoubanNewsContract {
    interface Model extends IBaseModel {
        Observable<DoubanNewsListBean> getDoubanNews(String date);
    }


    interface View extends IBaseFragment {
        void updateNewsContent(List<DoubanNewsItemBean> list);

        void showLoadMoreFail();

        void showLoadMoreEnd();

        void showNetworkError();

        void showNoDataError();

    }

    abstract class Presenter extends IBasePresenter<Model, View> {
        abstract void loadLastestNews();

        abstract void loadMoreNews();

        abstract void onItemClick(int position,DoubanNewsItemBean doubanNewsItemBean);

    }
}
