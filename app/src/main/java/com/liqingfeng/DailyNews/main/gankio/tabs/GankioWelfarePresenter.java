package com.liqingfeng.DailyNews.main.gankio.tabs;

import com.liqingfeng.DailyNews.bean.gankio.GankIoWelfareItemBean;
import com.liqingfeng.DailyNews.bean.gankio.GankIoWelfareListBean;
import com.liqingfeng.DailyNews.common.ui.IBaseModel;

import rx.Subscriber;


/**
 * Created by lonlife on 2018/1/11.
 */

public class GankioWelfarePresenter extends GankioWelfareContract.Presenter {
    private int pre_page = 10;
    private int mCurPage = 20;

    private boolean isLoading = false;

    @Override
    public IBaseModel getModel() {
        return new GankioWelfareModel();
    }

    @Override
    void loadLastestList() {
        if (mModel == null || mView == null)
            return;
        mModel.getGankIoWelfareList(pre_page, mCurPage).subscribe(new Subscriber<GankIoWelfareListBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.showNetworkError();
            }

            @Override
            public void onNext(GankIoWelfareListBean gankIoWelfareListBean) {
                if (gankIoWelfareListBean.isError()) {
                    mView.showNetworkError();
                } else {
                    mCurPage++;
                    mView.updateContentList(gankIoWelfareListBean.getResults());
                }

            }
        });
    }

    @Override
    void loadMoreList() {
        if (mModel == null || mView == null)
            return;
        if (!isLoading) {
            isLoading = true;
            mModel.getGankIoWelfareList(pre_page, mCurPage).subscribe(new Subscriber<GankIoWelfareListBean>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    isLoading = false;
                    //加载更多失败
                    mView.showLoadMoreError();
                }

                @Override
                public void onNext(GankIoWelfareListBean gankIoWelfareListBean) {
                    isLoading = false;
                    if (gankIoWelfareListBean.isError()) {
                        mView.showNetworkError();
                    } else {
                        if (gankIoWelfareListBean.getResults().size() > 0) {
                            mCurPage++;
                            mView.updateContentList(gankIoWelfareListBean.getResults());

                        } else {
                            mView.showNoMoreData();
                        }
                    }
                }
            });
        }

    }

    @Override
    void onItemClick(int position, GankIoWelfareItemBean item) {

    }
}
