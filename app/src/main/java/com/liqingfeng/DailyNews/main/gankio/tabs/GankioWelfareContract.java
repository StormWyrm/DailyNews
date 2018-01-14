package com.liqingfeng.DailyNews.main.gankio.tabs;

import com.liqingfeng.DailyNews.bean.gankio.GankIoWelfareItemBean;
import com.liqingfeng.DailyNews.bean.gankio.GankIoWelfareListBean;
import com.liqingfeng.DailyNews.common.ui.IBaseModel;
import com.liqingfeng.DailyNews.common.ui.IBasePresenter;
import com.liqingfeng.DailyNews.common.ui.IBaseView;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by lonlife on 2018/1/11.
 */

public interface GankioWelfareContract {
    interface Model extends IBaseModel {
        Observable<GankIoWelfareListBean> getGankIoWelfareList(int pre_page, int page);
    }

    interface View extends IBaseView {
        void updateContentList(List<GankIoWelfareItemBean> list);

        void showNetworkError();

        void showLoading();

        void showNoMoreData();

        void showLoadMoreError();
    }

    abstract class Presenter extends IBasePresenter<Model, View> {
        abstract void loadLastestList();

        abstract void loadMoreList();

        abstract void onItemClick(int position, GankIoWelfareItemBean item);
    }
}
