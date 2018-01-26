package com.liqingfeng.DailyNews.main.home.tabs;

import android.content.Intent;
import android.os.Bundle;

import com.liqingfeng.DailyNews.bean.guoke.GuokeHotNewsItemBean;
import com.liqingfeng.DailyNews.bean.guoke.GuokeHotNewsListBean;
import com.liqingfeng.DailyNews.bean.guoke.GuokeNewsItemBean;
import com.liqingfeng.DailyNews.bean.guoke.GuokeNewsListBean;
import com.liqingfeng.DailyNews.common.ui.IBaseModel;
import com.liqingfeng.DailyNews.detail.news.NewsDetailActivity;
import com.liqingfeng.DailyNews.main.home.cache.GuokeCache;
import com.liqingfeng.DailyNews.main.home.cache.GuokeHotCache;

import java.util.List;

import io.reactivex.functions.Consumer;


/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/4/30 10:00
 * @DESC: 果壳精选---MVP Presenter
 * @VERSION: V1.0
 */
public class GuokeNewsPresenter extends GuokeNewsContract.Presenter {

    private GuokeCache mGuokeCache;
    private GuokeHotCache mGuokeHotCache;


    public GuokeNewsPresenter() {
        mGuokeCache = new GuokeCache();
        mGuokeHotCache = new GuokeHotCache();
    }


    @Override
    public IBaseModel getModel() {
        return new GuokeNewsModel();
    }

    @Override
    void loadLastestGuokeNews() {
        if (mView == null || mModel == null)
            return;

        mModel.getGuokeNews()
                .subscribe(new Consumer<GuokeNewsListBean>() {
                    @Override
                    public void accept(GuokeNewsListBean guokeNewsListBean) throws Exception {
                        if (mView == null)
                            return;

                        List<GuokeNewsItemBean> result = guokeNewsListBean.getResult();
                        if (result == null || result.size() == 0) {
                            mView.showNoDataError();
                        } else {
                            mView.updateGuokeNewsContent(result);
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (mView == null)
                            return;
                        mView.showNetworkError();
                    }
                });
    }

    @Override
    void loadLoastestGuokeHotNews() {
        mModel.getGuokeHotNews()
                .subscribe(new Consumer<GuokeHotNewsListBean>() {
                    @Override
                    public void accept(GuokeHotNewsListBean guokeHotNewsListBean) throws Exception {
                        if (mView == null)
                            return;

                        List<GuokeHotNewsItemBean> result = guokeHotNewsListBean.getResult();
                        if (result == null || result.size() == 0) {
                            mView.showNoHotNewsError();
                        } else {
                            mView.updateGuokeHotNewsContent(result);
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (mView == null)
                            return;
                        mView.showNoHotNewsError();
                    }
                });
    }

    @Override
    void onItemClick(int position, GuokeNewsItemBean guokeNewsItemBean) {
        if (mView == null)
            return;

        Bundle extra = new Bundle();
        extra.putInt("type", 1);
        extra.putString("url", guokeNewsItemBean.getLink());
        extra.putString("title", guokeNewsItemBean.getTitle());
        extra.putString("image", guokeNewsItemBean.getHeadline_img());
        mView.startNewActivity(NewsDetailActivity.class, extra);
    }
}
