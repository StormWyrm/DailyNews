package com.liqingfeng.DailyNews.common.ui;

import android.os.Bundle;

/**
 * Created by lonlife on 2018/1/4.
 */

public abstract class BaseMvpActivity<M extends IBaseModel,P extends IBasePresenter> extends BaseActivity implements IBaseView {
    private M mModel;
    protected P mPresenter;

    @Override
    protected void initData(Bundle saveInstanceState) {
        super.initData(saveInstanceState);
        mPresenter = (P) initPresenter();
        if(mPresenter != null){
            mModel = (M) mPresenter.getModel();
            if(mModel != null){
                mPresenter.attachMV(mModel,this);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter != null){
            mPresenter.dettachMV();
        }
    }
}
