package com.example.liqingfeng.DailyNews.detail.news;

import com.example.liqingfeng.DailyNews.common.ui.BasePresenter;
import com.example.liqingfeng.DailyNews.common.ui.BaseView;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/4/25 23:26
 * @DESC: Detail界面---mvp接口
 * @VERSION: V1.0
 */
public interface NewsDetailContract {
    interface Presenter extends BasePresenter {
        void loadPage(int type, String mDetailUrl);

        void jumpToBrowser(String url);

        void share(int type);

        void copyLink(int type);

        void openLinkInBrowser(int type);

    }

    interface View extends BaseView<Presenter> {
        String getActionBarTitle();

        void showImage(String imageUrl);

        void loadDataToWebView(String detailData);

        void startRefresh();

        void stopRefresh();

        void showError();
    }
}
