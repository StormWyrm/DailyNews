package com.liqingfeng.DailyNews.main.home.tabs;

import android.text.TextUtils;

import com.liqingfeng.DailyNews.bean.guoke.GuokeHotNewsListBean;
import com.liqingfeng.DailyNews.bean.guoke.GuokeNewsItemBean;
import com.liqingfeng.DailyNews.bean.guoke.GuokeNewsListBean;
import com.liqingfeng.DailyNews.network.GuokeNewsService;
import com.liqingfeng.sdk.helper.RetrofitHelper;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lonlife on 2018/1/26.
 */

public class GuokeNewsModel implements GuokeNewsContract.Model {
    private GuokeNewsService guokeNewsService;

    public GuokeNewsModel() {
        guokeNewsService = RetrofitHelper.getInstance()
                .getService(GuokeNewsService.class, GuokeNewsService.HOST);
    }

    @Override
    public Observable<GuokeNewsListBean> getGuokeNews() {
        return guokeNewsService.getGuokeNews()
                .subscribeOn(Schedulers.io())
                .map(new Function<GuokeNewsListBean, GuokeNewsListBean>() {
                    @Override
                    public GuokeNewsListBean apply(GuokeNewsListBean guokeNewsListBean) throws Exception {
                        List<GuokeNewsItemBean> result = guokeNewsListBean.getResult();

                        for (GuokeNewsItemBean guokeNewsItemBean : result) {
                            guokeNewsItemBean.setItemType(TextUtils.isEmpty(guokeNewsItemBean.getHeadline_img()) ? 1 : 0);
                        }

                        guokeNewsListBean.setResult(result);
                        return guokeNewsListBean;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<GuokeHotNewsListBean> getGuokeHotNews() {
        return guokeNewsService.getGuokeHotNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
