package com.liqingfeng.DailyNews.main.home.tabs;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.liqingfeng.DailyNews.R;
import com.liqingfeng.DailyNews.bean.guoke.GuokeHotNewsItemBean;
import com.liqingfeng.DailyNews.bean.guoke.GuokeNewsItemBean;
import com.liqingfeng.DailyNews.common.constant.Constant;
import com.liqingfeng.DailyNews.common.ui.BaseRecycleFragment;
import com.liqingfeng.DailyNews.common.ui.IBasePresenter;
import com.liqingfeng.DailyNews.common.util.NetworkUtil;
import com.liqingfeng.DailyNews.common.util.SPUtils;
import com.liqingfeng.DailyNews.main.home.adapter.GuokeNewsAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/3/21 8:20
 * @DESC: 果壳精选---Fragment
 * @VERSION: V1.0
 */
public class GuokeNewsFragment
        extends BaseRecycleFragment<GuokeNewsContract.Model, GuokeNewsContract.Presenter>
        implements GuokeNewsContract.View {

    private static final String TAG = "GuokeNewsFragment";

    @BindView(R.id.rv)
    RecyclerView rv;

    private View mHeaderView;
    private SliderLayout mSlider;

    private GuokeNewsAdapter mAdapter;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mSlider.stopAutoCycle();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_recycler_view;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        initRecyclerHeaderView();
        initRecyclerView(null);

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mHeaderView.setVisibility(View.VISIBLE);
        mPresenter.loadLastestGuokeNews();
        mPresenter.loadLoastestGuokeHotNews();
    }

    @Override
    protected void onErrorViewClick(View v) {
        mHeaderView.setVisibility(View.VISIBLE);
        mPresenter.loadLoastestGuokeHotNews();
        mPresenter.loadLastestGuokeNews();
    }

    @Override
    protected void showLoading() {
        mAdapter.setEmptyView(loadingView);
    }

    @NonNull
    @Override
    public IBasePresenter initPresenter() {
        return new GuokeNewsPresenter();
    }

    @Override
    public void updateGuokeNewsContent(List<GuokeNewsItemBean> list) {
        if (mAdapter.getData().size() == 0) {
            initRecyclerView(list);
        } else {
            mAdapter.addData(list);
        }
    }

    @Override
    public void updateGuokeHotNewsContent(List<GuokeHotNewsItemBean> list) {
        if (list != null && list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                final GuokeHotNewsItemBean resultBean = list.get(i);
                TextSliderView textSliderView = new TextSliderView(mActivity);
                textSliderView.empty(R.drawable.ic_vector_load_default)
                        .error(R.drawable.ic_vector_load_default)
                        .description(resultBean.custom_title)
                        .setScaleType(BaseSliderView.ScaleType.CenterCrop);


                if (resultBean != null && !TextUtils.isEmpty(resultBean.picture))
                    textSliderView.image(resultBean.picture);
                else
                    textSliderView.image(R.drawable.ic_vector_load_default);


                mSlider.addSlider(textSliderView);
            }
        }
    }

    @Override
    public void showNetworkError() {
        mAdapter.setEmptyView(errorView);
    }

    @Override
    public void showNoDataError() {
        mAdapter.setEmptyView(emptyView);
    }

    @Override
    public void showNoHotNewsError() {
        mHeaderView.setVisibility(View.GONE);
    }

    private void initRecyclerView(List<GuokeNewsItemBean> guokeNewsItemBeans) {
        if (guokeNewsItemBeans == null) {
            mAdapter = new GuokeNewsAdapter();
            rv.setLayoutManager(new LinearLayoutManager(mContext));
            rv.setAdapter(mAdapter);
        } else {
            mAdapter = new GuokeNewsAdapter(guokeNewsItemBeans);
            mAdapter.addHeaderView(mHeaderView);
            mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    mPresenter.onItemClick(position + 1, (GuokeNewsItemBean) adapter.getItem(position));
                }
            });
            rv.setLayoutManager(new LinearLayoutManager(mContext));
            rv.setAdapter(mAdapter);
        }
    }

    protected void initRecyclerHeaderView() {
        mHeaderView = View.inflate(mActivity, R.layout.item_main_recycler_header, null);
        mSlider = (SliderLayout) mHeaderView.findViewById(R.id.slider);
        mSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mSlider.setCustomAnimation(new DescriptionAnimation());
        mSlider.setDuration(4000);
    }


}
