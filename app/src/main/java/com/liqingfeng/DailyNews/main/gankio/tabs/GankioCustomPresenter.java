package com.liqingfeng.DailyNews.main.gankio.tabs;

import com.liqingfeng.DailyNews.bean.gankio.GankIoCustomItemBean;
import com.liqingfeng.DailyNews.bean.gankio.GankIoCustomListBean;
import com.liqingfeng.DailyNews.common.ui.IBaseModel;

import rx.Subscriber;

/**
 * Created by lonlife on 2018/1/11.
 */

public class GankioCustomPresenter extends GankioCustomContract.Presenter {
    private int pre_page = 20;
    private int mCurPage = 1;

    private boolean isLoading = false;
    @Override
    public IBaseModel getModel() {
        return new GankioCustomModel();
    }

    @Override
    void loadLastestList() {
        if (mModel == null || mView == null) {
            return;
        }
       mCurPage = 1;
        mModel.getGankIoCustomList(mView.getCustomType(), pre_page, mCurPage)
                .subscribe(new Subscriber<GankIoCustomListBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mView.showNetworkError();
                    }

                    @Override
                    public void onNext(GankIoCustomListBean gankIoCustomListBean) {
                        if (gankIoCustomListBean.isError()) {
                            mView.showNetworkError();
                        } else {
                            mCurPage++;
                            mView.updateContentList(gankIoCustomListBean.getResults());
                        }
                    }
                });
    }

    @Override
    void loadMoreList() {
        if (!isLoading) {
            isLoading = true;
            mModel.getGankIoCustomList(mView.getCustomType(), pre_page, mCurPage)
                    .subscribe(new Subscriber<GankIoCustomListBean>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            isLoading = false;
                            mView.showLoadMoreError();
                        }

                        @Override
                        public void onNext(GankIoCustomListBean gankIoCustomListBean) {
                            isLoading = false;
                            if (gankIoCustomListBean.isError()) {
                                mView.showLoadMoreError();
                            } else {
                                if (gankIoCustomListBean.getResults().size()  == 0) {
                                    mView.showNoMoreData();
                                } else {
                                    mCurPage++;
                                    mView.updateContentList(gankIoCustomListBean.getResults());
                                }
                            }
                        }
                    });
        }
    }

    @Override
    void onCustomTypeChange(String customType) {
        if (mModel == null || mView == null) {
            return;
        }
        mCurPage = 1;
        mModel.getGankIoCustomList(customType, pre_page, mCurPage)
                .subscribe(new Subscriber<GankIoCustomListBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mView.showNetworkError();
                    }

                    @Override
                    public void onNext(GankIoCustomListBean gankIoCustomListBean) {
                        if (gankIoCustomListBean.isError()) {
                            mView.showNetworkError();
                        } else {
                            mCurPage++;
                            mView.refreshContentList(gankIoCustomListBean.getResults());
                        }
                    }
                });
    }

    @Override
    public void onItemClick(int position, GankIoCustomItemBean item) {

    }
}
