package com.liqingfeng.DailyNews.detail.news;

import com.liqingfeng.DailyNews.common.ui.BasePresenter;
import com.liqingfeng.DailyNews.common.ui.BaseView;
import com.liqingfeng.DailyNews.common.ui.IBaseActivity;
import com.liqingfeng.DailyNews.common.ui.IBaseModel;
import com.liqingfeng.DailyNews.common.ui.IBasePresenter;
import com.liqingfeng.DailyNews.common.ui.IBaseView;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/4/25 23:26
 * @DESC: Detail界面---mvp接口
 * @VERSION: V1.0
 */
public interface NewsDetailContract {

    interface Model extends IBaseModel{}

    interface View extends IBaseActivity {
        String getActionBarTitle();

        void showImage(String imageUrl);

        void loadDataToWebView(String detailData);


        void showError();
    }

    abstract class Presenter extends IBasePresenter<Model,View> {
        abstract void loadPage(int type, String mDetailUrl);

        abstract void jumpToBrowser(String url);

        abstract void share(int type);

        abstract void copyLink(int type);

        abstract void openLinkInBrowser(int type);

    }
}
