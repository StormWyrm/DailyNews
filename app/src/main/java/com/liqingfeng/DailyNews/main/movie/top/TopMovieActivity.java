package com.liqingfeng.DailyNews.main.movie.top;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liqingfeng.DailyNews.R;
import com.liqingfeng.DailyNews.bean.douban.movie.SubjectsBean;
import com.liqingfeng.DailyNews.common.ui.BaseMvpActivity;
import com.liqingfeng.DailyNews.common.ui.IBasePresenter;
import com.liqingfeng.DailyNews.common.util.SnackBarUtil;
import com.liqingfeng.DailyNews.main.movie.adapter.TopMovieAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lonlife on 2018/1/5.
 */

public class TopMovieActivity
        extends BaseMvpActivity<TopMovieContract.Model, TopMovieContract.Presenter>
        implements TopMovieContract.View ,BaseQuickAdapter.RequestLoadMoreListener{

    @BindView(R.id.rv_top_movie)
    RecyclerView rvTopMovie;

    private TopMovieAdapter mAdapter;

    @Override
    protected int getViewId() {
        return R.layout.activity_top_movie;
    }

    @Override
    protected void initData(Bundle saveInstanceState) {
        super.initData(saveInstanceState);
        initRecyclerView(null);
        mPresenter.loadLastestTopMovie();
    }

    @NonNull
    @Override
    public IBasePresenter initPresenter() {
        return new TopMoviePresenter();
    }


    @Override
    public void updateTopMovieContent(List<SubjectsBean> list) {
        if(mAdapter.getData().size() == 0){
            initRecyclerView(list);
        }else{
            mAdapter.addData(list);
        }
    }

    @Override
    public void showNetworkError() {
        SnackBarUtil.showMessage(getWindow().getDecorView(),getString(R.string.load_error_message));
    }

    @Override
    public void showLoadMoreError() {
        mAdapter.loadMoreFail();
    }

    @Override
    public void showNoMoreData() {
        mAdapter.loadMoreEnd();
    }
    private void initRecyclerView(List<SubjectsBean> list) {
        if(list == null || list.size() == 0){
            mAdapter = new TopMovieAdapter();
            rvTopMovie.setLayoutManager(new LinearLayoutManager(mActivity));
            rvTopMovie.setAdapter(mAdapter);
        }else{
            mAdapter = new TopMovieAdapter(list);
            mAdapter.setOnLoadMoreListener(this);
            mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                }
            });
            rvTopMovie.setLayoutManager(new StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL));
            rvTopMovie.setAdapter(mAdapter);
        }
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.loadMoreTopMovie();
    }
}
