package com.liqingfeng.DailyNews.main.home.tabs;

import com.liqingfeng.DailyNews.bean.zhihu.ZhihuNewsItemBean;
import com.liqingfeng.DailyNews.bean.zhihu.ZhihuNewsListBean;
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
 * @DESC: 知乎日报---MVP 接口
 * @VERSION: V1.0
 */
public interface ZhihuNewsContract {

    interface Model extends IBaseModel {
        Observable<ZhihuNewsListBean> getZhihuNews(String date);
    }

    interface View extends IBaseFragment {
        void updateZhihuNewsContent(List<ZhihuNewsItemBean> list);

        void showNetworkError();

        void showNoDataError();

        void showLoadMoreFail();

        void showLoadMoreEnd();
    }

    abstract class Presenter extends IBasePresenter<Model, View> {

        abstract void loadLastestZhihuNews();

        abstract void loadMoreZhihuNews();

        abstract void onItemClick(int position,ZhihuNewsItemBean zhihuNewsItemBean);

    }
}
