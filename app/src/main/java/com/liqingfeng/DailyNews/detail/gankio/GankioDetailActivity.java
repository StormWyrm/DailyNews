package com.liqingfeng.DailyNews.detail.gankio;

import android.support.annotation.NonNull;

import com.liqingfeng.DailyNews.R;
import com.liqingfeng.DailyNews.common.ui.BaseMvpActivity;
import com.liqingfeng.DailyNews.common.ui.IBasePresenter;

/**
 * Created by lonlife on 2018/1/14.
 */

public class GankioDetailActivity
        extends BaseMvpActivity<GankioDetailContract.Model, GankioDetailContract.Presenter>
        implements GankioDetailContract.View {


    @Override
    protected int getViewId() {
        return R.layout.layout_gank_io_detail;
    }

    @NonNull
    @Override
    public IBasePresenter initPresenter() {
        return null;
    }

}
