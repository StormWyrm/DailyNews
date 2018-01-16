package com.liqingfeng.DailyNews.detail.gankio;

import com.liqingfeng.DailyNews.common.ui.IBaseModel;

/**
 * Created by lonlife on 2018/1/14.
 */

public class GankioDetailPresenter extends GankioDetailContract.Presenter {
    @Override
    public IBaseModel getModel() {
        return new GankioDetailModel();
    }
}
