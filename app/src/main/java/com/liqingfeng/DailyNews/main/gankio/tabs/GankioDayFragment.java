package com.liqingfeng.DailyNews.main.gankio.tabs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liqingfeng.DailyNews.R;
import com.liqingfeng.DailyNews.bean.gankio.GankIoDayItemBean;
import com.liqingfeng.DailyNews.common.ui.BaseMvpFragment;
import com.liqingfeng.DailyNews.common.ui.IBasePresenter;
import com.liqingfeng.DailyNews.common.util.ToastUtil;
import com.liqingfeng.DailyNews.main.gankio.adapter.GankioDayAdapter;



import java.util.List;

import butterknife.BindView;

/**
 * Created by lonlife on 2018/1/11.
 */

public class GankioDayFragment
        extends BaseMvpFragment<GankioDayContract.Model, GankioDayContract.Presenter>
        implements GankioDayContract.View {


    @BindView(R.id.rv_gankio_day)
    RecyclerView rvGankioDay;

    private GankioDayAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected int getViewId() {
        return R.layout.fragment_gankio_recommend;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        initRecyclerView(null);
        mPresenter.loadLastestList();
    }

    @NonNull
    @Override
    public IBasePresenter initPresenter() {
        return new GankioDayPresenter();
    }

    @Override
    public void updateContentList(List<GankIoDayItemBean> list) {
        if (mAdapter.getData().size() == 0) {
            initRecyclerView(list);
        } else {
            mAdapter.addData(list);
        }
    }


    @Override
    public void itemNotifyChange(int position, GankIoDayItemBean item) {

    }

    @Override
    public void showNetworkError() {

    }

    @Override
    public void showLodding() {

    }

    private void initRecyclerView(List<GankIoDayItemBean> list) {
        if (list == null) {
            mAdapter = new GankioDayAdapter();
            rvGankioDay.setLayoutManager(new LinearLayoutManager(mActivity));
            rvGankioDay.setAdapter(mAdapter);
        } else {
            mAdapter = new GankioDayAdapter(list);
            mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    ToastUtil.shortMessage(mActivity,"position:"+position);
                    mPresenter.onItemClick(position, (GankIoDayItemBean) adapter.getItem(position));
                }
            });
            mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    switch (view.getId()){
                        case R.id.ll_more:
                            ToastUtil.shortMessage(mActivity,"position:+"+position+"更多");
                            mPresenter.onGetMoreClick(position, (GankIoDayItemBean) adapter.getItem(position));
                            break;
                        case R.id.ll_refesh:
                            ToastUtil.shortMessage(mActivity,"position:+"+position+"刷新");
                            mPresenter.onRefreshItemClick(position, (GankIoDayItemBean) adapter.getItem(position));
                            break;
                    }
                }
            });
            rvGankioDay.setLayoutManager(new LinearLayoutManager(mActivity));
            rvGankioDay.setAdapter(mAdapter);
        }
    }


}
