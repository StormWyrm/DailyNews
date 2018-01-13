package com.liqingfeng.DailyNews.main.gankio.tabs;


import android.graphics.YuvImage;

import com.liqingfeng.DailyNews.bean.gankio.GankIoDayItemBean;
import com.liqingfeng.DailyNews.common.ui.IBaseModel;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import rx.Subscriber;

/**
 * Created by lonlife on 2018/1/11.
 */

public class GankioDayPresenter extends GankioDayContract.Presenter{
    private String year = "2016";
    private String month = "11";
    private String day ="24";

    public GankioDayPresenter() {
//        year = Calendar.getInstance().get(Calendar.YEAR)+"";
//        month = Calendar.getInstance().get(Calendar.MONTH) + 1 +"";
//        day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+"";
    }

    @Override
    public IBaseModel getModel() {
        return new GankioDayModel();
    }

    @Override
    void loadLastestList() {
        if(mView == null || mModel == null){
            return;
        }
        mModel.getGankIoDay(year,month,day)
                .subscribe(new Subscriber<List<GankIoDayItemBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showNetworkError();
                    }

                    @Override
                    public void onNext(List<GankIoDayItemBean> gankIoDayItemBeans) {
                        mView.updateContentList(gankIoDayItemBeans);
                    }
                });
    }

    @Override
    void onItemClick(int position, GankIoDayItemBean item) {

    }

    @Override
    void onRefreshItemClick(int position, GankIoDayItemBean item) {

    }

    @Override
    void onGetMoreClick(int position,GankIoDayItemBean item) {

    }
}
