package com.liqingfeng.DailyNews.main.gankio.tabs;

import android.os.Bundle;

import com.liqingfeng.DailyNews.bean.gankio.GankIoCustomItemBean;
import com.liqingfeng.DailyNews.bean.gankio.GankIoCustomListBean;
import com.liqingfeng.DailyNews.browser.BrowserActivity;
import com.liqingfeng.DailyNews.common.constant.BundleKeyConstant;
import com.liqingfeng.DailyNews.common.ui.IBaseModel;
import com.liqingfeng.DailyNews.detail.gankio.ImageDetailActivity;

import io.reactivex.functions.Consumer;


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
                .subscribe(new Consumer<GankIoCustomListBean>() {
                    @Override
                    public void accept(GankIoCustomListBean gankIoCustomListBean) throws Exception {
                        if (gankIoCustomListBean.isError()) {
                            mView.showNetworkError();
                        } else {
                            mCurPage++;
                            mView.updateContentList(gankIoCustomListBean.getResults());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        mView.showNetworkError();
                    }
                });
    }

    @Override
    void loadMoreList() {
        if (!isLoading) {
            isLoading = true;
            mModel.getGankIoCustomList(mView.getCustomType(), pre_page, mCurPage)
                    .subscribe(new Consumer<GankIoCustomListBean>() {
                        @Override
                        public void accept(GankIoCustomListBean gankIoCustomListBean) throws Exception {
                            isLoading = false;
                            if (gankIoCustomListBean.isError()) {
                                mView.showLoadMoreError();
                            } else {
                                if (gankIoCustomListBean.getResults().size() == 0) {
                                    mView.showNoMoreData();
                                } else {
                                    mCurPage++;
                                    mView.updateContentList(gankIoCustomListBean.getResults());
                                }
                            }
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            throwable.printStackTrace();
                            isLoading = false;
                            mView.showLoadMoreError();
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
                .subscribe(new Consumer<GankIoCustomListBean>() {
                    @Override
                    public void accept(GankIoCustomListBean gankIoCustomListBean) throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    @Override
    public void onItemClick(int position, GankIoCustomItemBean item) {
        if(item.getType().equals("福利")){
            Bundle bundle = new Bundle();
            bundle.putString(BundleKeyConstant.BUNDLE_KEY_IMAGE_DETAIL_URL,item.getUrl());
            mView.startNewActivity(ImageDetailActivity.class,bundle);
        }else{
            Bundle bundle = new Bundle();
            bundle.putString(BundleKeyConstant.BUNDLE_KEY_BROWSER_URL,item.getUrl());
            mView.startNewActivity(BrowserActivity.class,bundle);
        }
    }
}
