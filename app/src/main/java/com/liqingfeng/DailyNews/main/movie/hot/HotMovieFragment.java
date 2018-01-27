package com.liqingfeng.DailyNews.main.movie.hot;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.liqingfeng.DailyNews.R;
import com.liqingfeng.DailyNews.bean.douban.movie.SubjectsBean;
import com.liqingfeng.DailyNews.common.ui.BaseActivity;
import com.liqingfeng.DailyNews.common.ui.BaseRecycleFragment;
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
public class HotMovieFragment
        extends BaseRecycleFragment<HotMovieContract.Model, HotMovieContract.Presenter>
        implements HotMovieContract.View {

    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.rv_hot_movie)
    RecyclerView rvHotMovie;

    private HotMovieAdapter mAdapter;
    private View mheaderView;


    public static HotMovieFragment newInstance() {
        HotMovieFragment fragment = new HotMovieFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_movie;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mPresenter.getHotMovie();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        initRecyclerView(null);
    }

    @NonNull
    @Override
    public IBasePresenter initPresenter() {
        return new HotMoviePresenter();
    }

    @Override
    public void showHotMovie(List<SubjectsBean> subjectsBeans) {
        if (mAdapter.getData().size() == 0) {
            initRecyclerView(subjectsBeans);
        } else {
            mAdapter.addData(subjectsBeans);
        }
    }

    @Override
    public void showNetworkError() {
        mAdapter.setEmptyView(errorView);
        SnackBarUtil.showMessage(mActivity.getWindow().getDecorView(), getString(R.string.load_error_message));
    }

    @Override
    protected void onErrorViewClick(View v) {
        mPresenter.getHotMovie();
    }

    @Override
    protected void showLoading() {
        mAdapter.setEmptyView(loadingView);
    }

    @Override
    public BaseActivity getBindActivity() {
        return mActivity;
    }

    private void initRecyclerView(List<SubjectsBean> subjectsBeans) {
        if (subjectsBeans == null) {
            mAdapter = new HotMovieAdapter();
            rvHotMovie.setLayoutManager(new LinearLayoutManager(mActivity));
            rvHotMovie.setAdapter(mAdapter);
        } else {
            initHeaderView();
            mAdapter = new HotMovieAdapter(subjectsBeans);
            mAdapter.setHeaderView(mheaderView);
            mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    mPresenter.onItemClick(position + 1,
                            (SubjectsBean) adapter.getData().get(position),
                            (ImageView) view.findViewById(R.id.iv_movie)
                    );
                }
            });
            rvHotMovie.setLayoutManager(new LinearLayoutManager(mContext));
            rvHotMovie.setAdapter(mAdapter);
        }
    }

    private void initHeaderView() {
        mheaderView = View.inflate(mContext, R.layout.layout_hot_movie_header, null);
        mheaderView.findViewById(R.id.rl_top_movie)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPresenter.onTopMovieClick();
                    }
                });
    }


}
