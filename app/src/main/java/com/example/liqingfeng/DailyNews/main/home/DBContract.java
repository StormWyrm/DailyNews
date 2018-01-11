package com.example.liqingfeng.DailyNews.main.home;

import com.example.liqingfeng.DailyNews.common.ui.BasePresenter;
import com.example.liqingfeng.DailyNews.common.ui.BaseView;
import com.example.liqingfeng.DailyNews.bean.douban.news.DBNews;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/4/30 9:57
 * @DESC: 豆瓣一刻---MVP接口
 * @VERSION: V1.0
 */
public interface DBContract {

    interface Presenter extends BasePresenter {
        void requestNetData();

        void loadMore();
    }

    interface View extends BaseView<Presenter> {
        void endRefresh();

        void endLoadMore();

        void showError();

        void showData(DBNews news);

    }
}
