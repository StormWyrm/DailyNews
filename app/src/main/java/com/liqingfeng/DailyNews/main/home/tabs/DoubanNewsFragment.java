package com.liqingfeng.DailyNews.main.home.tabs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liqingfeng.DailyNews.R;
import com.liqingfeng.DailyNews.bean.douban.news.DoubanNewsItemBean;
import com.liqingfeng.sdk.base.IBasePresenter;
import com.liqingfeng.sdk.base.fragment.BaseRecycleFragment;
import com.liqingfeng.DailyNews.main.home.adapter.DoubanNewsAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/3/21 10:23
 * @DESC: 豆瓣一刻Fragment
 * @VERSION: V1.0
 */
public class DoubanNewsFragment
        extends BaseRecycleFragment<DoubanNewsContract.Model, DoubanNewsContract.Presenter>
        implements DoubanNewsContract.View ,BaseQuickAdapter.RequestLoadMoreListener{

    @BindView(R.id.rv)
    RecyclerView rv;

    private DoubanNewsAdapter mAdapter;


    public static DoubanNewsFragment getInstance() {
        Bundle arugs = new Bundle();
        DoubanNewsFragment instance = new DoubanNewsFragment();
        instance.setArguments(arugs);
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
        mPresenter.loadLastestNews();
    }

    @Override
    protected void onErrorViewClick(View v) {
        mPresenter.loadLastestNews();
    }

    @Override
    protected void showLoading() {
        mAdapter.setEmptyView(loadingView);
    }

    @Override
    public void onLoadMoreRequested() {
        mAdapter.loadMoreComplete();
        mPresenter.loadMoreNews();
    }
    @NonNull
    @Override
    public IBasePresenter initPresenter() {
        return new DoubanNewsPresenter();
    }

    @Override
    public void updateNewsContent(List<DoubanNewsItemBean> list) {
        if (mAdapter.getData().size() == 0) {
           initRecyclerView(list);
        } else {
           mAdapter.addData(list);
        }
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
    public void showNetworkError() {
        mAdapter.setEmptyView(errorView);
    }

    @Override
    public void showNoDataError() {
        mAdapter.setEmptyView(emptyView);
    }

    private void initRecyclerView(List<DoubanNewsItemBean> list) {
        if(list == null){
            mAdapter = new DoubanNewsAdapter();
            rv.setLayoutManager(new LinearLayoutManager(mContext));
            rv.setAdapter(mAdapter);
        }else{
            mAdapter = new DoubanNewsAdapter(list);
            mAdapter.setOnLoadMoreListener(this,rv);
            mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    mPresenter.onItemClick(position,(DoubanNewsItemBean) adapter.getItem(position));

                }
            });
            rv.setLayoutManager(new LinearLayoutManager(mContext));
            rv.setAdapter(mAdapter);
        }
    }


}
