package com.liqingfeng.DailyNews.main.movie.top;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liqingfeng.DailyNews.R;
import com.liqingfeng.DailyNews.bean.douban.movie.SubjectsBean;
import com.liqingfeng.DailyNews.common.ui.BaseRecycleFragment;
import com.liqingfeng.DailyNews.common.ui.IBasePresenter;
import com.liqingfeng.DailyNews.common.util.SnackBarUtil;
import com.liqingfeng.DailyNews.main.movie.adapter.TopMovieAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lonlife on 2018/1/5.
 */

public class TopMovieFragment
        extends BaseRecycleFragment<TopMovieContract.Model, TopMovieContract.Presenter>
        implements TopMovieContract.View, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.rv_top_movie)
    RecyclerView rvTopMovie;
    @BindView(R.id.toolBar)
    Toolbar toolBar;

    private TopMovieAdapter mAdapter;


    public static TopMovieFragment newInstance() {
        TopMovieFragment fragment = new TopMovieFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_top_movie;
    }

    @Override
    protected void initData(Bundle saveInstanceState) {
        super.initData(saveInstanceState);
        initRecyclerView(null);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        toolBar.setNavigationIcon(R.drawable.ic_vector_arrow_back_black);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressedSupport();
            }
        });
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mPresenter.loadLastestTopMovie();
    }

    @NonNull
    @Override
    public IBasePresenter initPresenter() {
        return new TopMoviePresenter();
    }


    @Override
    public void updateTopMovieContent(List<SubjectsBean> list) {
        if (mAdapter.getData().size() == 0) {
            initRecyclerView(list);
        } else {
            mAdapter.addData(list);
        }
    }

    @Override
    public void showNetworkError() {
        mAdapter.setEmptyView(errorView);
        SnackBarUtil.showMessage(getView(), getString(R.string.load_error_message));
    }

    @Override
    public void showLoadMoreError() {
        mAdapter.loadMoreFail();
    }

    @Override
    public void showNoMoreData() {
        mAdapter.loadMoreEnd();
    }

    @Override
    public void onLoadMoreRequested() {
        mAdapter.loadMoreComplete();
        mPresenter.loadMoreTopMovie();
    }

    @Override
    protected void onErrorViewClick(View v) {
        mPresenter.loadLastestTopMovie();
    }

    @Override
    protected void showLoading() {
        mAdapter.setEmptyView(loadingView);
    }

    private void initRecyclerView(List<SubjectsBean> list) {
        if (list == null || list.size() == 0) {
            mAdapter = new TopMovieAdapter();
            rvTopMovie.setLayoutManager(new LinearLayoutManager(mActivity));
            rvTopMovie.setAdapter(mAdapter);
        } else {
            mAdapter = new TopMovieAdapter(list);
            mAdapter.setOnLoadMoreListener(this, rvTopMovie);
            mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    mPresenter.onItemClick(position,
                            (SubjectsBean) adapter.getItem(position),
                            (ImageView) view.findViewById(R.id.iv_top_moive_photo));
                }
            });
            rvTopMovie.setLayoutManager(new StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL));
            rvTopMovie.setAdapter(mAdapter);
        }
    }

}
