package com.liqingfeng.DailyNews.main.home.tabs;


import android.text.TextUtils;

import com.liqingfeng.DailyNews.bean.douban.news.DoubanNewsItemBean;
import com.liqingfeng.DailyNews.bean.douban.news.DoubanNewsListBean;
import com.liqingfeng.DailyNews.network.DoubanNewsService;
import com.liqingfeng.sdk.helper.RetrofitHelper;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lonlife on 2018/1/26.
 */

public class DoubanNewsModel implements DoubanNewsContract.Model {
    private DoubanNewsService doubanNewsService;

    public DoubanNewsModel() {
        doubanNewsService = RetrofitHelper.getInstance()
                .getService(DoubanNewsService.class, DoubanNewsService.HOST);
    }


    @Override
    public Observable<DoubanNewsListBean> getDoubanNews(String date) {
        return doubanNewsService.getDoubanNews(date)
                .subscribeOn(Schedulers.io())
                .map(new Function<DoubanNewsListBean, DoubanNewsListBean>() {
                    @Override
                    public DoubanNewsListBean apply(DoubanNewsListBean doubanNewsListBean) throws Exception {
                        List<DoubanNewsItemBean> posts = doubanNewsListBean.getPosts();

                        for (DoubanNewsItemBean doubanNewsItemBean : posts) {
                            doubanNewsItemBean.setItemType(TextUtils.isEmpty(doubanNewsItemBean.getShare_pic_url()) ? 1 : 0);
                        }

                        doubanNewsListBean.setPosts(posts);
                        return doubanNewsListBean;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
    }
}
