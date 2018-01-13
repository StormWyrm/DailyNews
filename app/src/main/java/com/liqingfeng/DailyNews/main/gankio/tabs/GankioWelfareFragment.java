package com.liqingfeng.DailyNews.main.gankio.tabs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liqingfeng.DailyNews.R;
import com.liqingfeng.DailyNews.bean.gankio.GankIoWelfareItemBean;
import com.liqingfeng.DailyNews.common.ui.BaseMvpFragment;
import com.liqingfeng.DailyNews.common.ui.IBasePresenter;
import com.liqingfeng.DailyNews.main.gankio.adapter.GankioWelfareAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by lonlife on 2018/1/11.
 */

public class GankioWelfareFragment
        extends BaseMvpFragment<GankioWelfareContract.Model, GankioWelfareContract.Presenter>
        implements GankioWelfareContract.View, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.rv_gankio_welfare)
    RecyclerView rvGankioWelfare;

    private GankioWelfareAdapter mAdapter;

    @Override
    protected int getViewId() {
        return R.layout.fragment_gankio_welfare;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        initRecycleView(null);
        mPresenter.loadLastestList();
    }

    @Override
    public void onLoadMoreRequested() {
        mAdapter.loadMoreComplete();
        mPresenter.loadMoreList();
    }

    @NonNull
    @Override
    public IBasePresenter initPresenter() {
        return new GankioWelfarePresenter();
    }

    @Override
    public void updateContentList(List<GankIoWelfareItemBean> list) {
        if (mAdapter.getData().size() == 0) {
            initRecycleView(list);
        } else {
            mAdapter.addData(list);
        }
    }

    private void initRecycleView(List<GankIoWelfareItemBean> list) {
        if (list == null || list.size() == 0) {
            //设置一个空的adapter
            mAdapter = new GankioWelfareAdapter();
            rvGankioWelfare.setLayoutManager(new LinearLayoutManager(mActivity));
        } else {
            //第一次加载网络是重新初始化数据并绑定recyclerview
            mAdapter = new GankioWelfareAdapter(list);
            //设置加载更多
            mAdapter.setOnLoadMoreListener(this,rvGankioWelfare);
            mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    mPresenter.onItemClick(position, (GankIoWelfareItemBean) adapter.getItem(position));
                }
            });
            rvGankioWelfare.setAdapter(mAdapter);
            rvGankioWelfare.setLayoutManager(new StaggeredGridLayoutManager(2,
                    StaggeredGridLayoutManager.VERTICAL));
        }
    }

    @Override
    public void showNoMoreData() {
        mAdapter.loadMoreEnd();
    }

    @Override
    public void showLoadMoreError() {
        mAdapter.loadMoreFail();
    }


    @Override
    public void showNetworkError() {

    }

    @Override
    public void showLoading() {

    }


}