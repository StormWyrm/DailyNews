package com.liqingfeng.DailyNews.main.gankio.tabs;

import com.liqingfeng.DailyNews.bean.gankio.GankIoCustomItemBean;
import com.liqingfeng.DailyNews.bean.gankio.GankIoCustomListBean;
import com.liqingfeng.DailyNews.network.GankioService;
import com.liqingfeng.DailyNews.network.NetworkHelper;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by lonlife on 2018/1/11.
 */

public class GankioCustomModel implements GankioCustomContract.Model {
    private GankioService mGankioService;

    public GankioCustomModel() {
        mGankioService = NetworkHelper.getInstance().getService(GankioService.class, GankioService.HOST);
    }

    @Override
    public Observable<GankIoCustomListBean> getGankIoCustomList(String type, int pre_page, int page) {
        return mGankioService.getGankIoCustomList(type,pre_page,page)
                .map(new Func1<GankIoCustomListBean, GankIoCustomListBean>() {
                    @Override
                    public GankIoCustomListBean call(GankIoCustomListBean gankIoCustomListBean) {
                        for(GankIoCustomItemBean bean : gankIoCustomListBean.getResults()){
                            if(bean.getType().equals("福利")){
                                bean.itemType = GankIoCustomItemBean.GANK_IO_DAY_ITEM_CUSTOM_IMAGE;
                            }else if(bean.getImages() == null){
                                bean.itemType = GankIoCustomItemBean.GANK_IO_DAY_ITEM_CUSTOM_NO_IMAGE;
                            }else{
                                bean.itemType = GankIoCustomItemBean.GANK_IO_DAY_ITEM_CUSTOM_NORMAL;
                            }
                        }
                        return gankIoCustomListBean;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}