package com.example.liqingfeng.DailyNews.common.ui;

import android.support.annotation.NonNull;

/**
 * Created by lonlife on 2018/1/3.
 */

public abstract class IBasePresenter<M,V> {
    public M mModel;
    public V mView;

    /**
     * 返回presenter持有的model
     * @return
     */
    public abstract IBaseModel getModel();

    /**
     * 绑定Model和View
     * @param m
     * @param v
     */
    public void attachMV(@NonNull M m,@NonNull V v){
        this.mModel = m;
        this.mView = v;
    }

    /**
     * 解除和Model,View的绑定
     */
    public void dettachMV(){
        mModel = null;
        mView = null;
    }
}
