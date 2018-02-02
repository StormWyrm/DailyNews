package com.liqingfeng.sdk.base.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.liqingfeng.sdk.base.IBaseActivity;
import com.liqingfeng.sdk.base.IBaseModel;
import com.liqingfeng.sdk.base.IBasePresenter;

/**
 * Created by lonlife on 2018/1/4.
 */

public abstract class BaseMvpActivity<M extends IBaseModel,P extends IBasePresenter>
        extends BaseActivity
        implements IBaseActivity {
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

    @Override
    public void startNewActivity(@NonNull Class<?> clz) {
        this.startActivity(clz);
    }

    @Override
    public void startNewActivity(@NonNull Class<?> clz, Bundle bundle) {
        this.startActivity(clz, bundle);
    }

    @Override
    public void startNewActivityForResult(@NonNull Class<?> clz, Bundle bundle, int requestCode) {
        startActivityForResult(clz, bundle, requestCode);
    }

}
