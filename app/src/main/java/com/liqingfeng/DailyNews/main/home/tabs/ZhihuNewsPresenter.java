package com.liqingfeng.DailyNews.main.home.tabs;


import android.os.Bundle;

import com.liqingfeng.DailyNews.bean.zhihu.ZhihuNewsItemBean;
import com.liqingfeng.DailyNews.bean.zhihu.ZhihuNewsListBean;
import com.liqingfeng.DailyNews.common.ui.IBaseModel;
import com.liqingfeng.DailyNews.common.util.Api;
import com.liqingfeng.DailyNews.common.util.DateFormatter;
import com.liqingfeng.DailyNews.detail.news.NewsDetailActivity;
import com.liqingfeng.DailyNews.main.home.cache.ZhihuCache;

import java.util.Calendar;
import java.util.List;

import io.reactivex.functions.Consumer;


/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/4/30 10:00
 * @DESC: 知乎日报---MVP Presenter
 * @VERSION: V1.0
 */
public class ZhihuNewsPresenter extends ZhihuNewsContract.Presenter {

    private Calendar mCurCalendar;
    private ZhihuCache mZhihuCache;
    private boolean isLoadding = false;

    public ZhihuNewsPresenter() {
        mZhihuCache = new ZhihuCache();
    }

    @Override
    public IBaseModel getModel() {
        return new ZhihuNewsModel();
    }

    @Override
    void loadLastestZhihuNews() {
        mCurCalendar = Calendar.getInstance();
        if (mView == null || mModel == null)
            return;
        mModel.getZhihuNews(DateFormatter.ZhihuDailyNowDateFormat(mCurCalendar))
                .subscribe(new Consumer<ZhihuNewsListBean>() {
                    @Override
                    public void accept(ZhihuNewsListBean zhihuNewsListBean) throws Exception {
                        if (mView == null)
                            return;
                        List<ZhihuNewsItemBean> zhihuNewsItemBeanList = zhihuNewsListBean.getStories();
                        if (zhihuNewsItemBeanList == null || zhihuNewsItemBeanList.size() == 0) {
                            mView.showNoDataError();
                        } else {
                            mView.updateZhihuNewsContent(zhihuNewsItemBeanList);
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
    void loadMoreZhihuNews() {
        if (mView == null || mModel == null)
            return;

        if (!isLoadding) {
            isLoadding = true;
            mCurCalendar.add(Calendar.DATE, -1);
            mModel.getZhihuNews(DateFormatter.ZhihuDailyNowDateFormat(mCurCalendar))
                    .subscribe(new Consumer<ZhihuNewsListBean>() {
                        @Override
                        public void accept(ZhihuNewsListBean zhihuNewsListBean) throws Exception {
                            isLoadding = false;
                            if (mView == null)
                                return;
                            List<ZhihuNewsItemBean> zhihuNewsItemBeanList = zhihuNewsListBean.getStories();
                            if (zhihuNewsItemBeanList == null || zhihuNewsItemBeanList.size() == 0) {
                                mView.showLoadMoreEnd();//没有更多数据
                            } else {
                                mView.updateZhihuNewsContent(zhihuNewsItemBeanList);
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
    void onItemClick(int position, ZhihuNewsItemBean zhihuNewsItemBean) {
        if (mView == null)
            return;

        Bundle extra = new Bundle();
        extra.putInt("type", 0);
        extra.putString("url", Api.ZHIHU_NEWS + zhihuNewsItemBean.getId());
        extra.putString("title", zhihuNewsItemBean.getTitle());
        mView.startNewActivity(NewsDetailActivity.class, extra);
    }
}
