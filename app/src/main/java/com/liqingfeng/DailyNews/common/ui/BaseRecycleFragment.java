package com.liqingfeng.DailyNews.common.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liqingfeng.DailyNews.R;

/**
 * Created by lonlife on 2018/1/23.
 */

public abstract class BaseRecycleFragment<M extends  IBaseModel,P extends IBasePresenter>
    extends BaseMvpFragment<M,P> {
    /**
     * 网络异常View
     */
    protected View errorView;
    /**
     * loadingView
     */
    protected View loadingView;
    /**
     * 没有内容view
     */
    protected View emptyView;

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        showLoading();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        errorView = inflater.inflate(R.layout.layout_network_error, container, false);
        loadingView = inflater.inflate(R.layout.layout_loading, container, false);
        emptyView = inflater.inflate(R.layout.layout_empty, container, false);
        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoading();
                onErrorViewClick(v);
            }
        });

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected abstract void onErrorViewClick(View v);

    protected abstract void showLoading();
}
