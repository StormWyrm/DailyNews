package com.liqingfeng.DailyNews.main.gankio.tabs;

import com.liqingfeng.DailyNews.bean.gankio.GankIoCustomItemBean;
import com.liqingfeng.DailyNews.bean.gankio.GankIoCustomListBean;
import com.liqingfeng.DailyNews.common.ui.IBaseModel;
import com.liqingfeng.DailyNews.common.ui.IBasePresenter;
import com.liqingfeng.DailyNews.common.ui.IBaseView;

import java.util.List;

import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by lonlife on 2018/1/11.
 */

public interface GankioCustomContract {
    interface Model extends IBaseModel {
        Observable<GankIoCustomListBean> getGankIoCustomList(String type, int
                pre_page, int page);
    }

    interface View extends IBaseView {
        //获取自定义类型
        String getCustomType();

        void updateContentList(List<GankIoCustomItemBean> list);

        void refreshContentList(List<GankIoCustomItemBean> list);

        void showNetworkError();

        void showLoadMoreError();

        void showNoMoreData();

        void showLoading();
    }

    abstract class Presenter extends IBasePresenter<Model, View> {
        abstract void loadLastestList();

        abstract void loadMoreList();

        abstract void onCustomTypeChange(String customType);

        public abstract void onItemClick(int position, GankIoCustomItemBean item);
    }
}
