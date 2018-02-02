package com.liqingfeng.DailyNews.main.home.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.liqingfeng.DailyNews.R;
import com.liqingfeng.sdk.base.IBaseModel;
import com.liqingfeng.sdk.base.IBasePresenter;
import com.liqingfeng.sdk.base.fragment.BaseMvpFragment;
import com.liqingfeng.sdk.recyclerview.OnRecyclerViewClickListener;
import com.liqingfeng.sdk.recyclerview.BaseRecyclerAdapter;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/3/28 19:53
 * @DESC: 带有RecyclerView的Fragment基类
 * @VERSION: V1.0
 */

public abstract class BaseFragmentWithRecycler<M extends IBaseModel, P extends IBasePresenter>
        extends BaseMvpFragment<M, P> {
    private static final String TAG = "BaseFragmentWithRecycle";

    protected TwinklingRefreshLayout mRefreshLayout;
    protected RecyclerView mRecyclerView;
    protected View mHeaderView;
    protected SliderLayout mSlider;//轮播图布局


    protected String url;
    protected BaseRecyclerAdapter mAdapter;
    protected OnRecyclerViewClickListener mListener;

    @Override
    protected int getLayoutId() {
        return R.layout.layout_recycler_view;
    }

    //    @Override
//    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_base, container, false);
//        mRefreshLayout = (TwinklingRefreshLayout) view.findViewById(R.id.refreshLayout);
//        mRefreshLayout.setFloatRefresh(true);//设置为浮动下拉刷新模式
//        mRefreshLayout.setHeaderView(new ProgressLayout(mActivity));
//        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
//        initRecyclerHeaderView();//初始化Slider头布局
//        return view;
//    }
//
//
//    @Override
//    protected void initData() {
//        super.initData();
//        initSliderLayout();
//    }


    @Override
    protected void initListener() {
        mRefreshLayout.startRefresh();
        mRefreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                requestNetData();
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                loadMore();
            }
        });
    }

    /**
     * 初始化轮播图头布局
     */
    protected void initRecyclerHeaderView() {
        mHeaderView = View.inflate(mActivity, R.layout.item_main_recycler_header, null);
        mSlider = (SliderLayout) mHeaderView.findViewById(R.id.slider);
        mSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mSlider.setCustomAnimation(new DescriptionAnimation());
        mSlider.setDuration(4000);
    }


    /**
     * 根据url请求网络数据
     */
    protected abstract void requestNetData();


    /**
     * 上拉加载更多
     */
    protected void loadMore() {
    }

    /**
     * 请求网络并初始化Slider
     */
    protected void initSliderLayout() {
    }

}
