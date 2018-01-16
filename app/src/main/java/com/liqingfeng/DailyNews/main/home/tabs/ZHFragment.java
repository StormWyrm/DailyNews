package com.liqingfeng.DailyNews.main.home.tabs;


import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.liqingfeng.DailyNews.R;
import com.liqingfeng.DailyNews.common.framework.OnRecyclerViewClickListener;
import com.liqingfeng.DailyNews.common.ui.BaseRecyclerAdapter;
import com.liqingfeng.DailyNews.common.ui.BaseViewHolder;
import com.liqingfeng.DailyNews.common.util.Api;
import com.liqingfeng.DailyNews.common.constant.Constant;
import com.liqingfeng.DailyNews.common.util.NetworkImageUtil;
import com.liqingfeng.DailyNews.common.util.NetworkUtil;
import com.liqingfeng.DailyNews.common.util.SPUtils;
import com.liqingfeng.DailyNews.common.util.SnackBarUtil;
import com.liqingfeng.DailyNews.detail.news.NewsDetailActivity;
import com.liqingfeng.DailyNews.bean.zhihu.ZHHotNews;
import com.liqingfeng.DailyNews.bean.zhihu.ZHNews;
import com.liqingfeng.DailyNews.main.home.ui.BaseFragmentWithRecycler;

import java.util.List;


/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/3/21 8:20
 * @DESC: 知乎日报---Fragment
 * @VERSION: V1.0
 */

public class ZHFragment extends BaseFragmentWithRecycler implements ZHContract.View {
    private static final String TAG = "ZHFragment";

    private ZHContract.Presenter mPresenter;
    private List<ZHNews.StoriesBean> mData;
    private List<ZHHotNews.RecentBean> mHotData;


    public ZHFragment() {
        new ZHPresenter(this);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mSlider.stopAutoCycle();
    }

    @Override
    protected void initSliderLayout() {
        mPresenter.requestSilderData();
    }

    @Override
    protected void requestNetData() {
        mPresenter.requestNetData();
    }

    @Override
    protected void loadMore() {
        mPresenter.loadMore();
    }

    //ZHView接口
    @Override
    public void setPresenter(ZHContract.Presenter presenter) {
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
    public void showData(ZHNews news) {
        if (mAdapter == null) {
            mData = news.stories;
            Log.d(TAG, "onSuccess: " + mData);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
            mAdapter = new MyAdapter();
            mAdapter.setOnRecyclerViewClickListener(new OnRecyclerViewClickListener() {
                @Override
                public void onItemClick(View itemView, int position) {
                    Intent intent = new Intent(mActivity, NewsDetailActivity.class);
                    intent.putExtra("type", 0);
                    intent.putExtra("url", Api.ZHIHU_NEWS + mData.get(position).id);
                    intent.putExtra("title", mData.get(position).title);
                    startActivity(intent);
                }
            });
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mData.addAll(news.stories);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showSliderData(ZHHotNews zhihuHotNews) {
        mHotData = zhihuHotNews.getRecent();
        if (mHotData != null && mHotData.size() != 0) {
            for (int i = 0; i < mHotData.size(); i++) {
                if (i >= 5) {
                    break;
                }
                final ZHHotNews.RecentBean recentBean = mHotData.get(i);
                TextSliderView textSliderView = new TextSliderView(mActivity);
                textSliderView
                        .description(recentBean.getTitle())
                        .empty(R.drawable.icon_load_error)
                        .error(R.drawable.icon_load_default)
                        .setScaleType(BaseSliderView.ScaleType.CenterCrop)
                        .setOnSliderClickListener(new BaseSliderView
                                .OnSliderClickListener() {
                            @Override
                            public void onSliderClick(BaseSliderView slider) {
                                Intent intent = new Intent(mActivity, NewsDetailActivity.class);
                                intent.putExtra("type", 0);
                                intent.putExtra("url", recentBean.getUrl());
                                intent.putExtra("title", recentBean.getTitle());
                                intent.putExtra("image", recentBean.getThumbnail());
                                startActivity(intent);
                            }
                        });

                if (NetworkUtil.isWifi(mActivity)) {
                    textSliderView.image(recentBean.getThumbnail());
                } else {
                    //判断是否允许非WIFT网络加载图片
                    if (!(Boolean) SPUtils.get(mActivity, Constant.Config
                            .WAY_OF_IMAGE_SHOW, false)) {
                        textSliderView.image(recentBean.getThumbnail());
                    } else {
                        textSliderView.image(R.drawable.icon_load_default);
                    }
                }
                mSlider.addSlider(textSliderView);
            }

        }
    }

    private class MyAdapter extends BaseRecyclerAdapter<ZHNews.StoriesBean> {

        public MyAdapter() {
            super(mActivity, mData, R.layout
                    .item_main_recycler_text_and_image, mHeaderView, null);
        }

        @Override
        public void convert(BaseViewHolder holder, ZHNews.StoriesBean storiesBean) {
            TextView tvTitle = holder.getView(R.id.tv_title);
            ImageView ivCover = holder.getView(R.id.iv_cover);
            tvTitle.setText(storiesBean.title);
            NetworkImageUtil.loadNetImageDependNet(mActivity, ivCover, storiesBean.images.get(0));


        }
    }
}
