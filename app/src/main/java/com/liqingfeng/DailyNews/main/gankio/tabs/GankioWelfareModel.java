package com.liqingfeng.DailyNews.main.gankio.tabs;

import com.liqingfeng.DailyNews.bean.gankio.GankIoWelfareListBean;
import com.liqingfeng.DailyNews.network.GankioService;
import com.liqingfeng.sdk.helper.RetrofitHelper;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by lonlife on 2018/1/11.
 */

public class GankioWelfareModel implements GankioWelfareContract.Model {
    private GankioService mGankioService;

    public GankioWelfareModel() {
        mGankioService = RetrofitHelper.getInstance().getService(GankioService.class, GankioService.HOST);
    }

    @Override
    public Observable<GankIoWelfareListBean> getGankIoWelfareList(int pre_page, int page) {
        return mGankioService.getGankIoWelfareList(pre_page, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
