package com.liqingfeng.DailyNews.main.home.tabs;

import com.liqingfeng.DailyNews.bean.zhihu.ZhihuNewsItemBean;
import com.liqingfeng.DailyNews.bean.zhihu.ZhihuNewsListBean;
import com.liqingfeng.DailyNews.network.NetworkHelper;
import com.liqingfeng.DailyNews.network.ZhihuNewsServer;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lonlife on 2018/1/26.
 */

public class ZhihuNewsModel implements ZhihuNewsContract.Model {
    private ZhihuNewsServer zhihuNewsServer;

    public ZhihuNewsModel() {
        zhihuNewsServer = NetworkHelper.getInstance()
                .getService(ZhihuNewsServer.class, ZhihuNewsServer.HOST);
    }

    @Override
    public Observable<ZhihuNewsListBean> getZhihuNews(String date) {
        return zhihuNewsServer.getZhihuNews(date)
                .subscribeOn(Schedulers.io())
                .map(new Function<ZhihuNewsListBean, ZhihuNewsListBean>() {
                    @Override
                    public ZhihuNewsListBean apply(ZhihuNewsListBean zhihuNewsListBean) throws Exception {
                        List<ZhihuNewsItemBean> stories = zhihuNewsListBean.getStories();

                        for (ZhihuNewsItemBean zhihuNewsItemBean : stories) {
                            if (zhihuNewsItemBean.getImages() == null || zhihuNewsItemBean.getImages().size() == 0)
                                zhihuNewsItemBean.setItemType(1);
                        }

                        zhihuNewsListBean.setStories(stories);
                        return zhihuNewsListBean;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
    }
}
