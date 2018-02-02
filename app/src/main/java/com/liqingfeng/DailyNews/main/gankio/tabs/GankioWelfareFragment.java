package com.liqingfeng.DailyNews.main.gankio.tabs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liqingfeng.DailyNews.R;
import com.liqingfeng.DailyNews.bean.gankio.GankIoWelfareItemBean;
import com.liqingfeng.DailyNews.constant.RxBusCodeCanstant;
import com.liqingfeng.sdk.base.IBasePresenter;
import com.liqingfeng.sdk.rxbus.RxBus;
import com.liqingfeng.sdk.rxbus.Subscribe;
import com.liqingfeng.sdk.base.fragment.BaseRecycleFragment;
import com.liqingfeng.DailyNews.util.SnackBarUtil;
import com.liqingfeng.DailyNews.main.gankio.adapter.GankioWelfareAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by lonlife on 2018/1/11.
 */

public class GankioWelfareFragment
        extends BaseRecycleFragment<GankioWelfareContract.Model, GankioWelfareContract.Presenter>
        implements GankioWelfareContract.View, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.rv_gankio_welfare)
    RecyclerView rvGankioWelfare;

    private GankioWelfareAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RxBus.get().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBus.get().unRegister(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gankio_welfare;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        initRecycleView(null);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
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
        mAdapter.setEmptyView(errorView);
        SnackBarUtil.showMessage(getView().getRootView(),getString(R.string.load_error_message));

    }

    @Override
    protected void onErrorViewClick(View v) {
        mPresenter.loadLastestList();
    }

    @Override
    public void showLoading() {
        mAdapter.setEmptyView(loadingView);
    }

    private void initRecycleView(List<GankIoWelfareItemBean> list) {
        if (list == null || list.size() == 0) {
            //设置一个空的adapter
            mAdapter = new GankioWelfareAdapter();
            rvGankioWelfare.setLayoutManager(new LinearLayoutManager(mActivity));
            rvGankioWelfare.setAdapter(mAdapter);
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
            rvGankioWelfare.setLayoutManager(new StaggeredGridLayoutManager(2,
                    StaggeredGridLayoutManager.VERTICAL));
            rvGankioWelfare.setAdapter(mAdapter);

        }
    }

    /**
     * day页面查看更多事件触发
     */
    @Subscribe(code = RxBusCodeCanstant.RX_BUS_CODE_GANKIO_WELFARE_TYPE)
    public void rxBusEvent() {
        rvGankioWelfare.smoothScrollToPosition(0);
    }


}
