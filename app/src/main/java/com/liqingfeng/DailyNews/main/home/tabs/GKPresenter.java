package com.liqingfeng.DailyNews.main.home.tabs;

import android.text.TextUtils;

import com.liqingfeng.DailyNews.common.AppApplication;
import com.liqingfeng.DailyNews.common.util.DateFormatter;
import com.liqingfeng.DailyNews.common.util.NetworkUtil;
import com.liqingfeng.DailyNews.main.home.cache.GuokeCache;
import com.liqingfeng.DailyNews.main.home.cache.GuokeHotCache;
import com.liqingfeng.DailyNews.bean.gk.GKHotNews;
import com.liqingfeng.DailyNews.bean.gk.GKNews;
import com.liqingfeng.DailyNews.network.GuokeService;
import com.liqingfeng.DailyNews.network.NetworkHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Calendar;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/4/30 10:00
 * @DESC: 果壳精选---MVP Presenter
 * @VERSION: V1.0
 */
public class GKPresenter implements GKContract.Presenter {
    private GKContract.View mGKView;
    private GuokeService mGuokeService;
    private GuokeCache mGuokeCache;
    private GuokeHotCache mGuokeHotCache;
    private Gson mGson;

    public GKPresenter(GKContract.View view) {
        this.mGKView = view;
        this.mGKView.setPresenter(this);
        mGuokeService = NetworkHelper.getInstance().getGuokeServer();
        mGuokeCache = new GuokeCache();
        mGuokeHotCache = new GuokeHotCache();
        mGson = new Gson();
    }

    @Override
    public void requestSilderData() {
        //没有网络时 换
        if (!NetworkUtil.isNetworkAvailable(AppApplication.getInstance())) {
            String cache = mGuokeCache.findCache(DateFormatter.ZhihuDailyNowDateFormat(Calendar
                    .getInstance()), "");
            if (TextUtils.isEmpty(cache)) {

            } else {
                Type type = new TypeToken<GKHotNews>() {
                }.getType();
                GKHotNews hotNews = mGson.fromJson(cache, type);
                mGKView.showSliderData(hotNews);
            }
            mGKView.endRefresh();
            mGKView.endLoadMore();
            return;
        }

        mGuokeService.getGKHotNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GKHotNews>() {
                    @Override
                    public void accept(GKHotNews gkHotNews) throws Exception {
                        mGKView.showSliderData(gkHotNews);

                        //将json缓存到本地
                        String json = mGson.toJson(gkHotNews);
                        mGuokeHotCache.addCache(DateFormatter.ZhihuDailyNowDateFormat(Calendar
                                .getInstance()), json);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    @Override
    public void requestNetData() {
        //没有网络时 换
        if (!NetworkUtil.isNetworkAvailable(AppApplication.getInstance())) {
            mGKView.showError();//显示网络错误信息

            String cache = mGuokeCache.findCache(DateFormatter.ZhihuDailyNowDateFormat(Calendar
                    .getInstance()), "");
            if (TextUtils.isEmpty(cache)) {

            } else {
                Type type = new TypeToken<GKNews>() {
                }.getType();
                GKNews gkNews = mGson.fromJson(cache, type);
                mGKView.showData(gkNews);
            }
            mGKView.endRefresh();
            mGKView.endLoadMore();
            return;
        }
        mGuokeService.getGKNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GKNews>() {
                    @Override
                    public void accept(GKNews gkNews) throws Exception {
                        mGKView.endLoadMore();
                        mGKView.endRefresh();

                        mGKView.showData(gkNews);
                        //将json缓存到本地
                        String json = mGson.toJson(gkNews);
                        mGuokeCache.addCache(DateFormatter.ZhihuDailyNowDateFormat(Calendar
                                .getInstance()), json);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mGKView.showError();
                        mGKView.endLoadMore();
                        mGKView.endRefresh();
                    }
                });

    }
}
