package com.liqingfeng.DailyNews.main.gankio.tabs;


import com.liqingfeng.DailyNews.bean.gankio.GankIoDayBean;
import com.liqingfeng.DailyNews.bean.gankio.GankIoDayItemBean;
import com.liqingfeng.DailyNews.network.GankioService;
import com.liqingfeng.DailyNews.network.NetworkHelper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;



/**
 * Created by lonlife on 2018/1/11.
 */

public class GankioDayModel implements GankioDayContract.Model {
    private GankioService mGankioService;
    private GankIoDayBean mGankIoDayBean;

    public GankioDayModel() {
        mGankioService = NetworkHelper.getInstance().getService(GankioService.class, GankioService.HOST);
    }

    @Override
    public Observable<List<GankIoDayItemBean>> getGankIoDay(String year, String month, String day) {
        return mGankioService.getGankIoDay(year, month, day)
                .map(new Function<GankIoDayBean, List<GankIoDayItemBean>>() {
                    @Override
                    public List<GankIoDayItemBean> apply(GankIoDayBean gankIoDayBean) throws Exception {
                        mGankIoDayBean = gankIoDayBean;

                        //每一个类型都选取展示在首页
                        List<GankIoDayItemBean> list = new ArrayList<>();

                        //增加item类型
                        GankIoDayItemBean itemAndroidBean = gankIoDayBean.getResults().getAndroid().get(0);
                        GankIoDayItemBean itemIOSBean = gankIoDayBean.getResults().getiOS().get(0);
                        GankIoDayItemBean itemFrontBean = gankIoDayBean.getResults().getFront().get(0);
                        GankIoDayItemBean itemWelfareBean = gankIoDayBean.getResults().getWelfare().get(0);
                        GankIoDayItemBean itemRestMovieBean = gankIoDayBean.getResults().getRestMovie()
                                .get(0);
                        itemAndroidBean.itemType = GankIoDayItemBean.GANK_IO_DAY_ITEM_DAY_REFESH;
                        itemIOSBean.itemType = GankIoDayItemBean.GANK_IO_DAY_ITEM_DAY_REFESH;
                        itemFrontBean.itemType = GankIoDayItemBean.GANK_IO_DAY_ITEM_DAY_NORMAL;
                        itemWelfareBean.itemType = GankIoDayItemBean.GANK_IO_DAY_ITEM_DAY_NORMAL;
                        itemRestMovieBean.itemType = GankIoDayItemBean.GANK_IO_DAY_ITEM_DAY_NORMAL;
                        list.add(itemAndroidBean);
                        list.add(itemFrontBean);
                        list.add(itemWelfareBean);
                        list.add(itemIOSBean);
                        list.add(itemRestMovieBean);

                        return list;
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

        @Override
        public GankIoDayItemBean getGankIoDayAndroid ( int page){
            if (mGankIoDayBean == null)
                return null;
            return mGankIoDayBean.getResults().getAndroid().get(page);

        }

        @Override
        public GankIoDayItemBean getGankIoDayIOS ( int page){
            if (mGankIoDayBean == null)
                return null;
            return mGankIoDayBean.getResults().getiOS().get(page);
        }
    }
