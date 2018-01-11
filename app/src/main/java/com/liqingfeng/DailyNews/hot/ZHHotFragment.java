package com.liqingfeng.DailyNews.hot;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.liqingfeng.DailyNews.R;
import com.liqingfeng.DailyNews.common.framework.OnRecyclerViewClickListener;
import com.liqingfeng.DailyNews.common.ui.BaseRecyclerAdapter;
import com.liqingfeng.DailyNews.common.ui.BaseViewHolder;
import com.liqingfeng.DailyNews.common.util.NetworkImageUtil;
import com.liqingfeng.DailyNews.common.util.SnackBarUtil;
import com.liqingfeng.DailyNews.detail.news.NewsDetailActivity;
import com.liqingfeng.DailyNews.bean.zhihu.ZHHotNews;
import com.liqingfeng.DailyNews.main.home.ui.BaseFragmentWithRecycler;

import java.util.List;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/4/4 23:44
 * @DESC: 知乎热搜界面---Fragment
 * @VERSION: V1.0
 */
public class ZHHotFragment extends BaseFragmentWithRecycler implements ZHHotContract.View {
    private ZHHotContract.Presenter mPresenter;
    private MyAdapter mAdapter;
    private List<ZHHotNews.RecentBean> hotNewsRecentes;

    @Override
    protected void initData() {
        super.initData();
        mRefreshLayout.setEnableLoadmore(false);
    }

    @Override
    protected void requestNetData() {
        mPresenter.requestNetData();
    }


    //View中实现的方法
    @Override
    public void setPresenter(ZHHotContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void endRefresh() {
        mRefreshLayout.finishRefreshing();
    }

    @Override
    public void endLoadMore() {
        mRefreshLayout.finishLoadmore();
    }

    @Override
    public void showError() {
        SnackBarUtil.showMessage(mActivity.getWindow().getDecorView(),getString(R.string.load_error_message));
    }

    @Override
    public void showData(ZHHotNews hotNews) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        hotNewsRecentes = hotNews.getRecent();
        mAdapter = new MyAdapter(hotNewsRecentes);
        mAdapter.setOnRecyclerViewClickListener(new OnRecyclerViewClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Intent intent = new Intent(mActivity, NewsDetailActivity.class);
                intent.putExtra("type", 0);
                intent.putExtra("url", hotNewsRecentes.get(position).getUrl());
                intent.putExtra("title", hotNewsRecentes.get(position).getTitle());
                intent.putExtra("image", hotNewsRecentes.get(position).getThumbnail());
                mActivity.startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }


    private class MyAdapter extends BaseRecyclerAdapter<ZHHotNews.RecentBean> {

        public MyAdapter(List<ZHHotNews.RecentBean> recentBeans) {
            super(mActivity, recentBeans, R.layout.item_main_recycler_text_and_image);
        }

        @Override
        public void convert(BaseViewHolder holder, ZHHotNews.RecentBean recentBean) {
            TextView tvTitle = holder.getView(R.id.tv_title);
            ImageView ivCover = holder.getView(R.id.iv_cover);
            tvTitle.setText(recentBean.getTitle());
            NetworkImageUtil.loadNetImageDependNet(mActivity, ivCover, recentBean.getThumbnail());
        }
    }
}
