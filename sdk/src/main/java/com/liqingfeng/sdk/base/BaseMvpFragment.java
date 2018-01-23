package com.liqingfeng.sdk.base;

import android.os.Bundle;
import android.support.annotation.NonNull;

/**
 * Created by lonlife on 2018/1/3.
 * 实现mvp模式绑定的Fragment
 */

public abstract class BaseMvpFragment<M extends IBaseModel,P extends IBasePresenter>
        extends BaseFragment
        implements IBaseFragment{
    protected P mPresenter;
    private M mModel;

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        mPresenter = (P)initPresenter();
        if(mPresenter != null){
            mModel = (M) mPresenter.getModel();
            if(mModel != null){
                mPresenter.attachMV(mModel,this);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //防止内存泄漏
        if(mPresenter != null){
            mPresenter.dettachMV();
        }
    }


    @Override
    public void startNewActivity(@NonNull Class<?> clz) {
        mActivity.startActivity(clz);
    }

    @Override
    public void startNewActivity(@NonNull Class<?> clz, Bundle bundle) {
        mActivity.startActivity(clz, bundle);
    }

    @Override
    public void startNewActivityForResult(@NonNull Class<?> clz, Bundle bundle, int requestCode) {
        mActivity.startNewActivityForResult(clz,bundle,requestCode);
    }
}
