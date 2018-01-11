package com.liqingfeng.DailyNews.main.home;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
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
import com.liqingfeng.DailyNews.common.util.Constant;
import com.liqingfeng.DailyNews.common.util.NetworkImageUtil;
import com.liqingfeng.DailyNews.common.util.NetworkUtil;
import com.liqingfeng.DailyNews.common.util.SPUtils;
import com.liqingfeng.DailyNews.common.util.SnackBarUtil;
import com.liqingfeng.DailyNews.detail.news.NewsDetailActivity;
import com.liqingfeng.DailyNews.bean.gk.GKHotNews;
import com.liqingfeng.DailyNews.bean.gk.GKNews;
import com.liqingfeng.DailyNews.main.home.ui.BaseFragmentWithRecycler;

import java.util.List;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/3/21 8:20
 * @DESC: 果壳精选---Fragment
 * @VERSION: V1.0
 */
public class GKFragment extends BaseFragmentWithRecycler implements GKContract.View {
    private static final String TAG = "GKFragment";

    private GKContract.Presenter mPresenter;
    private List<GKNews.ResultBean> mData;
    private List<GKHotNews.ResultBean> mHotData;


    public GKFragment() {
        new GKPresenter(this);
    }

    @Override
    protected void initData() {
        super.initData();
        mRefreshLayout.setEnableLoadmore(false);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mSlider.stopAutoCycle();
    }

    @Override
    protected void requestNetData() {
        mPresenter.requestNetData();
    }

    protected void initSliderLayout() {
        mPresenter.requestSilderData();
    }

    @Override
    public void setPresenter(GKContract.Presenter presenter) {
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
    public void showData(GKNews news) {
        Log.d(TAG, "onSuccess: " + news);
        if (mAdapter == null) {
            mData = news.result;
            mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
            mAdapter = new MyAdapter();
            mAdapter.setOnRecyclerViewClickListener(new OnRecyclerViewClickListener() {
                @Override
                public void onItemClick(View itemView, int position) {
                    Intent intent = new Intent(mActivity, NewsDetailActivity.class);
                    intent.putExtra("type", 1);
                    intent.putExtra("url", mData.get(position).link);
                    intent.putExtra("title", mData.get(position).title);
                    intent.putExtra("image", mData.get(position).headline_img);
                    startActivity(intent);
                }
            });
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mData.clear();
            mData.addAll(news.result);
            mAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void showSliderData(GKHotNews hotNews) {
        mHotData = hotNews.result;
        if (mHotData != null && mHotData.size() != 0) {
            for (int i = 0; i < mHotData.size(); i++) {
                final GKHotNews.ResultBean resultBean = mHotData.get(i);
                TextSliderView textSliderView = new TextSliderView(mActivity);
                textSliderView.empty(R.drawable.icon_load_default)
                        .error(R.drawable.icon_load_error)
                        .description(resultBean.custom_title)
                        .setScaleType(BaseSliderView.ScaleType.CenterCrop);

                if (NetworkUtil.isWifi(mActivity)) {
                    if (resultBean != null && !TextUtils.isEmpty(resultBean.picture))
                        textSliderView.image(resultBean.picture);
                    else
                        textSliderView.image(R.drawable.icon_load_default);
                } else {
                    //判断是否允许非WIFT网络加载图片
                    if (!(Boolean) SPUtils.get(mActivity, Constant.Config
                            .WAY_OF_IMAGE_SHOW, false)) {
                        if (resultBean != null && !TextUtils.isEmpty(resultBean.picture))
                            textSliderView.image(resultBean.picture);
                    } else {
                        textSliderView.image(R.drawable.icon_load_default);
                    }
                }
                mSlider.addSlider(textSliderView);
            }
        }
    }


    private class MyAdapter extends BaseRecyclerAdapter<GKNews.ResultBean> {

        public MyAdapter() {
            super(mActivity, mData, R.layout
                    .item_main_recycler_text_detail_image, mHeaderView, null);
        }

        @Override
        public void convert(BaseViewHolder holder, GKNews.ResultBean resultBean) {
            TextView tvTitle = holder.getView(R.id.tv_title);
            TextView tvContent = holder.getView(R.id.tv_content);
            ImageView ivCover = holder.getView(R.id.iv_cover);
            tvTitle.setText(resultBean.title);
            tvContent.setText(resultBean.summary);
            NetworkImageUtil.loadNetImageDependNet(mActivity, ivCover, resultBean.headline_img_tb);
        }
    }
}
