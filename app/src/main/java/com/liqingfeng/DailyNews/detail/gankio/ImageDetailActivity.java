package com.liqingfeng.DailyNews.detail.gankio;

import android.support.annotation.NonNull;
import android.os.Bundle;

import com.liqingfeng.DailyNews.R;
import com.liqingfeng.DailyNews.common.ui.BaseMvpActivity;
import com.liqingfeng.DailyNews.common.ui.IBasePresenter;

public class ImageDetailActivity
        extends BaseMvpActivity<ImageDetailContract.Model,ImageDetailContract.Presenter>
        implements ImageDetailContract.View
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gankio_welfare_detail);
    }

    @Override
    protected int getViewId() {
        return 0;
    }

    @NonNull
    @Override
    public IBasePresenter initPresenter() {
        return null;
    }
}
