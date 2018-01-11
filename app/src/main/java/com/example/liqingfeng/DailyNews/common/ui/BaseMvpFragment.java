package com.example.liqingfeng.DailyNews.common.ui;

/**
 * Created by lonlife on 2018/1/3.
 * 实现mvp模式绑定的Fragment
 */

public abstract class BaseMvpFragment<M extends IBaseModel,P extends IBasePresenter> extends BaseFragment implements IBaseView{
    protected P mPresenter;
    private M mModel;

    @Override
    protected void initData() {
        super.initData();
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
}
