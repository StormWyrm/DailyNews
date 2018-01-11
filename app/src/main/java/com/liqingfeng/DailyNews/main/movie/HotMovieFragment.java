package com.liqingfeng.DailyNews.main.movie;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liqingfeng.DailyNews.R;
import com.liqingfeng.DailyNews.bean.douban.movie.SubjectsBean;
import com.liqingfeng.DailyNews.common.ui.BaseActivity;
import com.liqingfeng.DailyNews.common.ui.BaseMvpFragment;
import com.liqingfeng.DailyNews.common.ui.IBasePresenter;
import com.liqingfeng.DailyNews.common.util.SnackBarUtil;
import com.liqingfeng.DailyNews.main.movie.adapter.HotMovieAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HotMovieFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HotMovieFragment extends BaseMvpFragment<HotMovieContract.Model, HotMovieContract.Presenter> implements HotMovieContract.View {
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.rv_hot_movie)
    RecyclerView rvHotMovie;

    private HotMovieAdapter mAdapter;
    private View mheaderView;


    public HotMovieFragment() {
    }

    public static HotMovieFragment newInstance() {
        HotMovieFragment fragment = new HotMovieFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getViewId() {
        return R.layout.fragment_movie;
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.getHotMovie();
    }

    @NonNull
    @Override
    public IBasePresenter initPresenter() {
        return new HotMoviePresenter();
    }

    @Override
    public void showHotMovie(List<SubjectsBean> subjectsBeans) {
        if (mAdapter == null) {
            initRecyclerView(subjectsBeans);
        } else {
            mAdapter.setNewData(subjectsBeans);
        }
    }

    @Override
    public void showNetworkError() {
        SnackBarUtil.showMessage(mActivity.getWindow().getDecorView(),getString(R.string.load_error_message));
    }

    @Override
    public BaseActivity getBindActivity() {
        return mActivity;
    }

    private void initRecyclerView(List<SubjectsBean> subjectsBeans) {
        mAdapter = new HotMovieAdapter(subjectsBeans);

        mheaderView = View.inflate(mContext, R.layout.layout_hot_movie_header, null);

        mheaderView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onTopMovieClick();
            }
        });
        mAdapter.setHeaderView(mheaderView);

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mPresenter.onMovieItemClick((HotMovieAdapter) adapter, view, position,(SubjectsBean) adapter.getData().get(position));
            }
        });

        rvHotMovie.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        rvHotMovie.setLayoutManager(new LinearLayoutManager(mContext));
        rvHotMovie.setAdapter(mAdapter);
    }
}
