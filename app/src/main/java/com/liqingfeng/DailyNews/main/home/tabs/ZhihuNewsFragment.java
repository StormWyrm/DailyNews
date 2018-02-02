package com.liqingfeng.DailyNews.main.home.tabs;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liqingfeng.DailyNews.R;
import com.liqingfeng.DailyNews.bean.zhihu.ZhihuNewsItemBean;
import com.liqingfeng.sdk.base.IBasePresenter;
import com.liqingfeng.sdk.base.fragment.BaseRecycleFragment;
import com.liqingfeng.DailyNews.main.home.adapter.ZhihuNewsAdapter;

import java.util.List;

import butterknife.BindView;


/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/3/21 8:20
 * @DESC: 知乎日报---Fragment
 * @VERSION: V1.0
 */

public class ZhihuNewsFragment
        extends BaseRecycleFragment<ZhihuNewsContract.Model, ZhihuNewsContract.Presenter>
        implements ZhihuNewsContract.View,BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.rv)
    RecyclerView rv;

    private ZhihuNewsAdapter mAdapter;

    public static ZhihuNewsFragment getInstance() {
        ZhihuNewsFragment instance = new ZhihuNewsFragment();
        Bundle args = new Bundle();
        instance.setArguments(args);
        return instance;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_recycler_view;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        initRecyclerView(null);
    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mPresenter.loadLastestZhihuNews();
    }

    @Override
    public void onLoadMoreRequested() {
        mAdapter.loadMoreComplete();
        mPresenter.loadMoreZhihuNews();
    }

    @NonNull
    @Override
    public IBasePresenter initPresenter() {
        return new ZhihuNewsPresenter();
    }

    @Override
    public void updateZhihuNewsContent(List<ZhihuNewsItemBean> list) {
        if(mAdapter.getData().size() == 0){
            initRecyclerView(list);
        }else{
            mAdapter.addData(list);
        }
    }

    @Override
    public void showNetworkError() {
        mAdapter.setEmptyView(errorView);
    }

    @Override
    public void showNoDataError() {
        mAdapter.setEmptyView(emptyView);
    }

    @Override
    public void showLoadMoreFail() {
        mAdapter.loadMoreFail();
    }

    @Override
    public void showLoadMoreEnd() {
        mAdapter.loadMoreEnd();
    }

    @Override
    protected void onErrorViewClick(View v) {
        mPresenter.loadLastestZhihuNews();
    }

    @Override
    protected void showLoading() {
        mAdapter.setEmptyView(loadingView);
    }

    private void initRecyclerView(List<ZhihuNewsItemBean> zhihuNewsItemBeans) {
        if (zhihuNewsItemBeans == null) {
            mAdapter = new ZhihuNewsAdapter();
            rv.setLayoutManager(new LinearLayoutManager(mContext));
            rv.setAdapter(mAdapter);
        } else {
            mAdapter = new ZhihuNewsAdapter(zhihuNewsItemBeans);
            mAdapter.setOnLoadMoreListener(this,rv);
            mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    mPresenter.onItemClick(position, (ZhihuNewsItemBean) adapter.getItem(position));
                }
            });
            rv.setLayoutManager(new LinearLayoutManager(mContext));
            rv.setAdapter(mAdapter);
        }
    }
}
