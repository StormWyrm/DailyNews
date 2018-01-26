package com.liqingfeng.DailyNews.main.home.tabs;


import android.os.Bundle;
import android.text.TextUtils;

import com.liqingfeng.DailyNews.bean.douban.news.DoubanNewsItemBean;
import com.liqingfeng.DailyNews.bean.zhihu.ZhihuNewsItemBean;
import com.liqingfeng.DailyNews.common.AppApplication;
import com.liqingfeng.DailyNews.common.ui.IBaseModel;
import com.liqingfeng.DailyNews.common.util.Api;
import com.liqingfeng.DailyNews.common.util.DateFormatter;
import com.liqingfeng.DailyNews.common.util.NetworkUtil;
import com.liqingfeng.DailyNews.detail.news.NewsDetailActivity;
import com.liqingfeng.DailyNews.main.home.cache.DoubanCache;
import com.liqingfeng.DailyNews.bean.douban.news.DoubanNewsListBean;
import com.liqingfeng.DailyNews.network.DoubanNewsService;
import com.liqingfeng.DailyNews.network.NetworkHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/4/30 10:00
 * @DESC: 豆瓣一刻---MVP Presenter
 * @VERSION: V1.0
 */
public class DoubanNewsPresenter extends DoubanNewsContract.Presenter {
    private static final String TAG = "DoubanNewsPresenter";

    private Calendar mCurCalendar;
    private boolean isLoadding;

    @Override
    public IBaseModel getModel() {
        return new DoubanNewsModel();
    }

    @Override
    void loadLastestNews() {
        if (mView == null && mModel == null)
            return;

        mCurCalendar = Calendar.getInstance();
        mModel.getDoubanNews(new DateFormatter().DoubanDateFormat(mCurCalendar))
                .subscribe(new Consumer<DoubanNewsListBean>() {
                    @Override
                    public void accept(DoubanNewsListBean doubanNewsListBean) throws Exception {
                        if (mView == null)
                            return;

                        List<DoubanNewsItemBean> doubanNewsItemBeans = doubanNewsListBean.getPosts();
                        if (doubanNewsItemBeans == null || doubanNewsItemBeans.size() == 0) {
                            mView.showNoDataError();
                        } else {
                            mView.updateNewsContent(doubanNewsItemBeans);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        if (mView == null)
                            return;

                        mView.showNetworkError();
                    }
                });
    }

    @Override
    void loadMoreNews() {
        if (!isLoadding) {
            isLoadding = true;
            mCurCalendar.add(Calendar.DATE, -1);
            mModel.getDoubanNews(new DateFormatter().DoubanDateFormat(mCurCalendar))
                    .subscribe(new Consumer<DoubanNewsListBean>() {
                        @Override
                        public void accept(DoubanNewsListBean doubanNewsListBean) throws Exception {
                            isLoadding = false;
                            if (mView == null)
                                return;

                            List<DoubanNewsItemBean> doubanNewsItemBeans = doubanNewsListBean.getPosts();
                            if (doubanNewsItemBeans == null || doubanNewsItemBeans.size() == 0) {
                                mView.showNoDataError();//没有更多数据
                            } else {
                                mView.updateNewsContent(doubanNewsItemBeans);
                            }
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            throwable.printStackTrace();
                            isLoadding = false;
                            if (mView == null)
                                return;

                            mView.showLoadMoreFail();
                        }
                    });
        }
    }

    @Override
    void onItemClick(int position, DoubanNewsItemBean doubanNewsItemBean) {
        if (mView == null)
            return;

        Bundle extra = new Bundle();
        extra.putInt("type", 2);
        extra.putString("url", Api.DOUBAN_ARTICLE_DETAIL + doubanNewsItemBean.getId());
        extra.putString("title", doubanNewsItemBean.getTitle());
        mView.startNewActivity(NewsDetailActivity.class, extra);
    }
}
