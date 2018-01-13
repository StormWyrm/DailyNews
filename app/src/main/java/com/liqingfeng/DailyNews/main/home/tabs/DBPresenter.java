package com.liqingfeng.DailyNews.main.home.tabs;


import android.text.TextUtils;

import com.liqingfeng.DailyNews.common.AppApplication;
import com.liqingfeng.DailyNews.common.util.DateFormatter;
import com.liqingfeng.DailyNews.common.util.NetworkUtil;
import com.liqingfeng.DailyNews.main.home.cache.DoubanCache;
import com.liqingfeng.DailyNews.bean.douban.news.DBNews;
import com.liqingfeng.DailyNews.network.DoubanService;
import com.liqingfeng.DailyNews.network.NetworkHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Calendar;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/4/30 10:00
 * @DESC: 豆瓣一刻---MVP Presenter
 * @VERSION: V1.0
 */
public class DBPresenter implements DBContract.Presenter {
    private static final String TAG = "DBPresenter";

    private DBContract.View mDBView;
    private Calendar mCurCalendar;
    private DoubanService mDoubanServie;
    private DoubanCache mDoubanCache;
    private Gson mGson;

    public DBPresenter(DBContract.View view) {
        this.mDBView = view;
        mDBView.setPresenter(this);
        mDoubanServie = NetworkHelper.getInstance().getDoubanService();
        mDoubanCache = new DoubanCache();
        mCurCalendar = Calendar.getInstance();
        mGson = new Gson();
    }

    @Override
    public void requestNetData() {
        //没有网络时获取缓存数据
        if (!NetworkUtil.isNetworkAvailable(AppApplication.getInstance())) {
            mDBView.showError();
            String cache = mDoubanCache.findCache(new DateFormatter().DoubanDateFormat
                    (mCurCalendar), "");
            if (TextUtils.isEmpty(cache)) {

            } else {
                Type type = new TypeToken<DBNews>() {
                }.getType();
                DBNews dbNews = mGson.fromJson(cache, type);
                mDBView.showData(dbNews);
            }
            mDBView.endRefresh();
            mDBView.endLoadMore();
            return;
        }

        mDoubanServie.getDBNews(new DateFormatter().DoubanDateFormat(mCurCalendar))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<DBNews>() {
                    @Override
                    public void onCompleted() {
                        mDBView.endRefresh();
                        mDBView.endLoadMore();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mDBView.showError();
                        mDBView.endRefresh();
                        mDBView.endLoadMore();
                    }

                    @Override
                    public void onNext(DBNews dbNews) {
                        mDBView.showData(dbNews);

                        //将json缓存到本地
                        String json = mGson.toJson(dbNews);
                        mDoubanCache.addCache(new DateFormatter().DoubanDateFormat(mCurCalendar),json);
                    }
                });

    }

    @Override
    public void loadMore() {
        mCurCalendar.add(Calendar.DATE, -1);
        requestNetData();
    }
}
