package com.example.liqingfeng.DailyNews.hot;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.example.liqingfeng.DailyNews.common.AppApplication;
import com.example.liqingfeng.DailyNews.common.framework.HttpRequestCallback;
import com.example.liqingfeng.DailyNews.common.framework.HttpRequestManager;
import com.example.liqingfeng.DailyNews.common.net.HttpRequestByVolley;
import com.example.liqingfeng.DailyNews.common.ui.BaseActivity;
import com.example.liqingfeng.DailyNews.common.util.Api;
import com.example.liqingfeng.DailyNews.common.util.DateFormatter;
import com.example.liqingfeng.DailyNews.common.util.NetworkUtil;
import com.example.liqingfeng.DailyNews.main.home.cache.ZhihuHotCache;
import com.example.liqingfeng.DailyNews.bean.zhihu.ZHHotNews;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.List;

import static com.android.volley.VolleyLog.TAG;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/4/30 9:13
 * @DESC: 知乎热搜界面---MVP Presenter
 * @VERSION: V1.0
 */
public class ZHHotPresenter implements ZHHotContract.Presenter {

    private Context mActivity;
    private ZHHotContract.View mZHHotView;
    private HttpRequestManager mRequestManager = new HttpRequestByVolley();
    private List<ZHHotNews.RecentBean> mHotData;
    private ZhihuHotCache mZhihuHotCache;
    private Gson mGson;


    public ZHHotPresenter(BaseActivity activity, ZHHotContract.View view) {
        this.mActivity = activity;
        this.mZHHotView = view;
        this.mZHHotView.setPresenter(this);
        mZhihuHotCache = new ZhihuHotCache();
        mGson = new Gson();
    }

    @Override
    public void requestNetData() {
        //没有网络时获取缓存数据
        if (!NetworkUtil.isNetworkAvailable(AppApplication.getInstance())) {
            mZHHotView.showError();
            String cache = mZhihuHotCache.findCache(new DateFormatter().DoubanDateFormat
                    (Calendar.getInstance()), "");
            if (TextUtils.isEmpty(cache)) {

            } else {
                Type type = new TypeToken<ZHHotNews>() {
                }.getType();
                ZHHotNews hotNews = mGson.fromJson(cache, type);
                mZHHotView.showData(hotNews);
            }
            mZHHotView.endRefresh();
            mZHHotView.endLoadMore();
            return;
        }

        mRequestManager.getRequest(Api.ZHIHU_HOT, new HttpRequestCallback() {

            @Override
            public void onStart() {
                super.onStart();
                Log.d(TAG, "onStart: ");
            }

            @Override
            public void onSuccess(String result) {
                mZhihuHotCache.addCache(new DateFormatter().DoubanDateFormat
                        (Calendar.getInstance()), result);

                Type type = new TypeToken<ZHHotNews>() {
                }.getType();
                ZHHotNews news = mGson.fromJson(result, type);
                mZHHotView.showData(news);
            }

            @Override
            public void onEnd() {
                mZHHotView.endRefresh();
                mZHHotView.endLoadMore();
            }

            @Override
            public void onFailure(int errorCode, Throwable e) {
                super.onFailure(errorCode, e);
                mZHHotView.showError();
            }
        });
    }

}
