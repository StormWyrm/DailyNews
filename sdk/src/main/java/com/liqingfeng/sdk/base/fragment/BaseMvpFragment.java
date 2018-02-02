package com.liqingfeng.sdk.base.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.liqingfeng.sdk.base.IBaseFragment;
import com.liqingfeng.sdk.base.IBaseModel;
import com.liqingfeng.sdk.base.IBasePresenter;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by lonlife on 2018/1/3.
 * 实现mvp模式绑定的Fragment
 */

public abstract class BaseMvpFragment<M extends IBaseModel,P extends IBasePresenter>
        extends BaseFragment
        implements IBaseFragment {
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
    public void popToFragment(Class<?> targetFragmentClass, boolean includeTargetFragment) {
        popTo(targetFragmentClass,includeTargetFragment);
    }

    @Override
    public void startNewFragment(@NonNull SupportFragment supportFragment) {
        start(supportFragment);
    }

    @Override
    public void startNewFragmentWithPop(@NonNull SupportFragment supportFragment) {
        startWithPop(supportFragment);
    }

    @Override
    public void startNewFragmentForResult(@NonNull SupportFragment supportFragment, int requestCode) {
        startForResult(supportFragment,requestCode);
    }

    @Override
    public void setOnFragmentResult(int resultCode, Bundle data) {
        setOnFragmentResult(resultCode,data);
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
        mActivity.startActivityForResult(clz,bundle,requestCode);
    }
}
