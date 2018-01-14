package com.liqingfeng.DailyNews.main.home.tabs;

import android.text.TextUtils;

import com.liqingfeng.DailyNews.common.AppApplication;
import com.liqingfeng.DailyNews.common.util.DateFormatter;
import com.liqingfeng.DailyNews.common.util.NetworkUtil;
import com.liqingfeng.DailyNews.main.home.cache.ZhihuCache;
import com.liqingfeng.DailyNews.main.home.cache.ZhihuHotCache;
import com.liqingfeng.DailyNews.bean.zhihu.ZHHotNews;
import com.liqingfeng.DailyNews.bean.zhihu.ZHNews;
import com.liqingfeng.DailyNews.network.NetworkHelper;
import com.liqingfeng.DailyNews.network.ZhihuServer;
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
 * @DESC: 知乎日报---MVP Presenter
 * @VERSION: V1.0
 */
public class ZHPresenter implements ZHContract.Presenter {
    private ZHContract.View mZHView;
    private Calendar mCurCalendar;
    private ZhihuServer mZhihuService;
    private ZhihuCache mZhihuCache;
    private ZhihuHotCache mZhihuHotCache;
    private Gson mGson;

    public ZHPresenter(ZHContract.View view) {
        this.mZHView = view;
        this.mZHView.setPresenter(this);
        mZhihuService = NetworkHelper.getInstance().getZhihuService();
        mZhihuCache = new ZhihuCache();
        mZhihuHotCache = new ZhihuHotCache();
        mGson = new Gson();
        mCurCalendar = Calendar.getInstance();
    }

    @Override
    public void requestSilderData() {
        //没有网络时 换
        if (!NetworkUtil.isNetworkAvailable(AppApplication.getInstance())) {
            String cache = mZhihuHotCache.findCache(DateFormatter.ZhihuDailyNowDateFormat(Calendar
                    .getInstance()), "");
            if (TextUtils.isEmpty(cache)) {

            } else {
                Type type = new TypeToken<ZHHotNews>() {
                }.getType();
                ZHHotNews hotNews = mGson.fromJson(cache, type);
                mZHView.showSliderData(hotNews);
            }
            return;
        }
        mZhihuService.getZHHotNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ZHHotNews>() {
                    @Override
                    public void accept(ZHHotNews zhHotNews) throws Exception {
                        mZHView.showSliderData(zhHotNews);

                        //将json缓存到本地
                        String json = mGson.toJson(zhHotNews);
                        mZhihuHotCache.addCache(DateFormatter.ZhihuDailyNowDateFormat(Calendar
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
            mZHView.showError();
            String cache = mZhihuCache.findCache(DateFormatter.ZhihuDailyNowDateFormat
                    (mCurCalendar), "");
            if (TextUtils.isEmpty(cache)) {

            } else {
                Type type = new TypeToken<ZHNews>() {
                }.getType();
                ZHNews zhNews = mGson.fromJson(cache, type);
                mZHView.showData(zhNews);
            }
            mZHView.endRefresh();
            mZHView.endLoadMore();
            return;
        }

        mZhihuService.getZHNews(DateFormatter.ZhihuDailyNowDateFormat(mCurCalendar))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ZHNews>() {
                    @Override
                    public void accept(ZHNews zhNews) throws Exception {
                        mZHView.endRefresh();
                        mZHView.endLoadMore();

                        mZHView.showData(zhNews);
                        //将json缓存到本地
                        String json = mGson.toJson(zhNews);
                        mZhihuCache.addCache(new DateFormatter().ZhihuDailyNowDateFormat
                                (mCurCalendar), json);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mZHView.showError();
                        mZHView.endRefresh();
                        mZHView.endLoadMore();
                    }
                });

    }

    @Override
    public void loadMore() {
        mCurCalendar.add(Calendar.DATE, -1);
        requestNetData();
    }

}
