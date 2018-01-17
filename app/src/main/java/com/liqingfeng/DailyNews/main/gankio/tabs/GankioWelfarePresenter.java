package com.liqingfeng.DailyNews.main.gankio.tabs;

import android.os.Bundle;

import com.liqingfeng.DailyNews.bean.gankio.GankIoWelfareItemBean;
import com.liqingfeng.DailyNews.bean.gankio.GankIoWelfareListBean;
import com.liqingfeng.DailyNews.common.constant.BundleKeyConstant;
import com.liqingfeng.DailyNews.common.ui.IBaseModel;
import com.liqingfeng.DailyNews.detail.gankio.ImageDetailActivity;

import io.reactivex.functions.Consumer;



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
        mModel.getGankIoWelfareList(pre_page, mCurPage)
                .subscribe(new Consumer<GankIoWelfareListBean>() {
                    @Override
                    public void accept(GankIoWelfareListBean gankIoWelfareListBean) throws Exception {
                        if (gankIoWelfareListBean.isError()) {
                            mView.showNetworkError();
                        } else {
                            mCurPage++;
                            mView.updateContentList(gankIoWelfareListBean.getResults());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showNetworkError();
                    }
                });
    }

    @Override
    void loadMoreList() {
        if (mModel == null || mView == null)
            return;
        if (!isLoading) {
            isLoading = true;
            mModel.getGankIoWelfareList(pre_page, mCurPage)
                    .subscribe(new Consumer<GankIoWelfareListBean>() {
                        @Override
                        public void accept(GankIoWelfareListBean gankIoWelfareListBean) throws Exception {
                            if(mView == null)
                                return;
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
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            if(mView == null)
                                return;
                            isLoading = false;
                            //加载更多失败
                            mView.showLoadMoreError();
                        }
                    });
        }

    }

    @Override
    void onItemClick(int position, GankIoWelfareItemBean item) {
        if(mView == null)
            return;
        Bundle bundle = new Bundle();
        bundle.putString(BundleKeyConstant.BUNDLE_KEY_IMAGE_DETAIL_URL,item.getUrl());
        mView.startNewActivity(ImageDetailActivity.class,bundle);
    }
}
