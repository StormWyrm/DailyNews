package com.liqingfeng.DailyNews.main.home.tabs;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.liqingfeng.DailyNews.R;
import com.liqingfeng.DailyNews.common.framework.OnRecyclerViewClickListener;
import com.liqingfeng.DailyNews.common.ui.BaseRecyclerAdapter;
import com.liqingfeng.DailyNews.common.ui.BaseViewHolder;
import com.liqingfeng.DailyNews.common.util.Api;
import com.liqingfeng.DailyNews.common.util.NetworkImageUtil;
import com.liqingfeng.DailyNews.common.util.SnackBarUtil;
import com.liqingfeng.DailyNews.detail.news.NewsDetailActivity;
import com.liqingfeng.DailyNews.bean.douban.news.DBNews;
import com.liqingfeng.DailyNews.main.home.ui.BaseFragmentWithRecycler;

import java.util.List;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/3/21 10:23
 * @DESC: 豆瓣一刻Fragment
 * @VERSION: V1.0
 */
public class DBFragment extends BaseFragmentWithRecycler implements DBContract.View{
    private static final String TAG = "DBFragment";

    private List<DBNews.PostsBean> mData;
    private DBContract.Presenter mPresenter;

    public DBFragment() {
        new DBPresenter(this);
    }

    @Override
    protected void requestNetData() {
        mPresenter.requestNetData();
    }

    @Override
    protected void loadMore() {
        mPresenter.loadMore();
    }

    @Override
    public void setPresenter(DBContract.Presenter presenter) {
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
    public void showData(DBNews news) {
        Log.d(TAG, "onSuccess: " + news);
        if (mAdapter == null) {
            mData = news.posts;
            mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
            mAdapter = new MyAdapter();

            mAdapter.setOnRecyclerViewClickListener(new OnRecyclerViewClickListener() {
                @Override
                public void onItemClick(View itemView, int position) {

                    Intent intent = new Intent(mActivity, NewsDetailActivity.class);
                    intent.putExtra("type", 2);
                    intent.putExtra("url", Api.DOUBAN_ARTICLE_DETAIL + mData.get(position).id);
                    intent.putExtra("title", mData.get(position).title);
                    startActivity(intent);
                }
            });
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mData.addAll(news.posts);
            mAdapter.notifyDataSetChanged();
        }
    }

    private class MyAdapter extends BaseRecyclerAdapter<DBNews.PostsBean> {

        public MyAdapter() {
            super(mActivity, mData, R.layout
                    .item_main_recycler_text_detail_image);
        }

        @Override
        public void convert(BaseViewHolder holder, DBNews.PostsBean postsBean) {
            TextView tvTitle = holder.getView(R.id.tv_title);
            TextView tvContent = holder.getView(R.id.tv_content);
            ImageView ivCover = holder.getView(R.id.iv_cover);
            tvTitle.setText(postsBean.title);
            tvContent.setText(postsBean.abstractX);
            List<DBNews.PostsBean.ThumbsBean> thumbs = postsBean.thumbs;
            if (thumbs != null && thumbs.size() != 0) {
                ivCover.setVisibility(View.VISIBLE);
                tvContent.setMaxLines(3);
                NetworkImageUtil.loadNetImageDependNet(mActivity,ivCover,thumbs.get(0).small.url);
            } else {
                ivCover.setVisibility(View.GONE);
                tvContent.setMaxLines(4);
            }
        }
    }
}
