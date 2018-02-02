package com.liqingfeng.sdk.base;

import android.support.annotation.NonNull;

/**
 * Created by lonlife on 2018/1/3.
 */

public interface IBaseView {

    @NonNull
    IBasePresenter initPresenter();
}
