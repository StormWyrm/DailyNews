package com.example.liqingfeng.DailyNews.hot;

import com.example.liqingfeng.DailyNews.common.ui.BasePresenter;
import com.example.liqingfeng.DailyNews.common.ui.BaseView;
import com.example.liqingfeng.DailyNews.bean.zhihu.ZHHotNews;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/4/30 9:11
 * @DESC: 知乎热搜界面---MVP接口定义
 * @VERSION: V1.0
 */
public interface ZHHotContract {

    interface Presenter extends BasePresenter {

        void requestNetData();

    }

    interface View extends BaseView<Presenter> {

        void endRefresh();

        void endLoadMore();

        void showError();

        void showData(ZHHotNews hotNews);

    }

}
