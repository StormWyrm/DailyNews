package com.liqingfeng.DailyNews.main.gankio.tabs;


import android.os.Bundle;

import com.liqingfeng.DailyNews.bean.gankio.GankIoDayItemBean;
import com.liqingfeng.DailyNews.browser.BrowserActivity;
import com.liqingfeng.DailyNews.constant.BundleKeyConstant;
import com.liqingfeng.DailyNews.constant.RxBusCodeCanstant;
import com.liqingfeng.sdk.base.IBaseModel;
import com.liqingfeng.sdk.rxbus.RxBus;
import com.liqingfeng.DailyNews.detail.gankio.ImageDetailActivity;


import java.util.List;


import io.reactivex.functions.Consumer;


/**
 * Created by lonlife on 2018/1/11.
 */

public class GankioDayPresenter extends GankioDayContract.Presenter {
    private String year = "2016";
    private String month = "11";
    private String day = "24";

    private int mAndroidIndex = 0;
    private int mIosIndex = 0;

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
        if (mView == null || mModel == null) {
            return;
        }
        mModel.getGankIoDay(year, month, day)
                .subscribe(new Consumer<List<GankIoDayItemBean>>() {
                    @Override
                    public void accept(List<GankIoDayItemBean> gankIoDayItemBeans) throws Exception {
                        mView.updateContentList(gankIoDayItemBeans);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showNetworkError();
                    }
                });
    }

    @Override
    void onItemClick(int position, GankIoDayItemBean item) {
        if(item.getType().equals("福利")){
            Bundle bundle = new Bundle();
            bundle.putString(BundleKeyConstant.BUNDLE_KEY_IMAGE_DETAIL_URL,item.getUrl());
            mView.startNewActivity(ImageDetailActivity.class,bundle);
        }else{
            Bundle bundle = new Bundle();
            bundle.putString(BundleKeyConstant.BUNDLE_KEY_BROWSER_URL,item.getUrl());
            mView.startNewActivity(BrowserActivity.class,bundle);
        }
    }

    @Override
    void onRefreshItemClick(int position, GankIoDayItemBean item) {
        if (mView == null || mModel == null)
            return;

        if (item.getType().equals("Android")) {
            mAndroidIndex++;
            mView.itemNotifyChange(position, mModel.getGankIoDayAndroid(mAndroidIndex % 6));
        } else {
            mIosIndex++;
            GankIoDayItemBean gankIoDayIOS = mModel.getGankIoDayIOS(mIosIndex % 3);
            mView.itemNotifyChange(position, gankIoDayIOS);
        }

    }

    @Override
    void onGetMoreClick(int position, GankIoDayItemBean item) {
        if (item.getType().equals("福利")) {
            //切换tab条目 到福利页面
            RxBus.get().send(RxBusCodeCanstant.RX_BUS_CODE_GANKIO_SELECT_TO_CHILD, 2);

            //福利页面滚动到最上边
            RxBus.get().send(RxBusCodeCanstant.RX_BUS_CODE_GANKIO_WELFARE_TYPE);
        } else {
            //切换tab条目 到自定义页面
            RxBus.get().send(RxBusCodeCanstant.RX_BUS_CODE_GANKIO_SELECT_TO_CHILD, 1);

            //自定义页面 切换类型
            RxBus.get().send(RxBusCodeCanstant.RX_BUS_CODE_GANKIO_CUSTOM_TYPE, item.getType());
        }
    }
}
