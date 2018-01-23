package com.liqingfeng.DailyNews.main.gankio.tabs;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cocosw.bottomsheet.BottomSheet;
import com.liqingfeng.DailyNews.R;
import com.liqingfeng.DailyNews.bean.gankio.GankIoCustomItemBean;
import com.liqingfeng.DailyNews.common.constant.RxBusCodeCanstant;
import com.liqingfeng.DailyNews.common.rxbus.RxBus;
import com.liqingfeng.DailyNews.common.rxbus.Subscribe;
import com.liqingfeng.DailyNews.common.ui.BaseMvpFragment;
import com.liqingfeng.DailyNews.common.ui.BaseRecycleFragment;
import com.liqingfeng.DailyNews.common.ui.IBasePresenter;
import com.liqingfeng.DailyNews.common.util.SnackBarUtil;
import com.liqingfeng.DailyNews.common.util.ToastUtil;
import com.liqingfeng.DailyNews.main.gankio.adapter.GankioCustomAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by lonlife on 2018/1/11.
 */

public class GankioCustomFragment
        extends BaseRecycleFragment<GankioCustomContract.Model, GankioCustomContract.Presenter>
        implements GankioCustomContract.View, BaseMultiItemQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.rv_gankio_custom)
    RecyclerView rvGankioCustom;

    private String mCustomType = "all";
    private View mHeaderView;
    private GankioCustomAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RxBus.get().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBus.get().unRegister(this);
    }

    @Override
    protected int getViewId() {
        return R.layout.fragment_gankio_custom;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mPresenter.loadLastestList();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        initRecylerView(null);
    }


    @NonNull
    @Override
    public IBasePresenter initPresenter() {
        return new GankioCustomPresenter();
    }

    @Override
    public String getCustomType() {
        return mCustomType;
    }

    @Override
    public void onLoadMoreRequested() {
        mAdapter.loadMoreComplete();
        mPresenter.loadMoreList();
    }

    @Override
    public void updateContentList(List<GankIoCustomItemBean> list) {
        if (mAdapter.getData().size() == 0) {
            initRecylerView(list);
        } else {
            mAdapter.addData(list);
        }
    }

    @Override
    public void refreshContentList(List<GankIoCustomItemBean> list) {
        if (mAdapter.getData().size() == 0) {
            initRecylerView(list);
        } else {
            mAdapter.replaceData(list);
        }
    }

    @Override
    public void showNetworkError() {
        mAdapter.setEmptyView(errorView);
        SnackBarUtil.showMessage(getView().getRootView(), getString(R.string.load_error_message));
    }

    @Override
    public void showLoadMoreError() {
        SnackBarUtil.showMessage(getView().getRootView(), getString(R.string.load_error_message));
    }

    @Override
    public void showNoMoreData() {
        mAdapter.loadMoreEnd();
    }

    @Override
    protected void onErrorViewClick(View v) {
        mPresenter.loadLastestList();
    }

    @Override
    public void showLoading() {
        mAdapter.setEmptyView(loadingView);
    }

    private void initRecylerView(List<GankIoCustomItemBean> list) {
        if (list == null || list.size() == 0) {
            //初始化一个空list的adapter,网络错误时候使用
            mAdapter = new GankioCustomAdapter();
            rvGankioCustom.setLayoutManager(new LinearLayoutManager(mActivity));
            rvGankioCustom.setAdapter(mAdapter);
        } else {
            //第一次加载网络时候重新初始化adapter并绑定数据
            mAdapter = new GankioCustomAdapter(list);
            mAdapter.setOnLoadMoreListener(this, rvGankioCustom);
            mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    //因为绑定的有headerView 所有position要加1
                    mPresenter.onItemClick(position + 1, (GankIoCustomItemBean) adapter.getItem(position));
                }
            });
            initHeaderView();
            mAdapter.addHeaderView(mHeaderView);
            rvGankioCustom.setLayoutManager(new LinearLayoutManager(mActivity));
            rvGankioCustom.addItemDecoration(new DividerItemDecoration(mActivity, OrientationHelper.HORIZONTAL));
            rvGankioCustom.setAdapter(mAdapter);
        }
    }

    private void initHeaderView() {
        if (mHeaderView == null) {
            mHeaderView = View.inflate(mActivity, R.layout.layout_gankio_custom_header, null);
        }
        final TextView tvHeadName = (TextView) mHeaderView.findViewById(R.id.tv_type_name);
        mHeaderView.findViewById(R.id.ll_choose_catalogue)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtil.shortMessage(mActivity, "选择分类");
                        showBottomSheet(tvHeadName);
                    }
                });

        if (mCustomType.equals("all")) {
            tvHeadName.setText("全部");
        } else {
            tvHeadName.setText(mCustomType);
        }
    }

    private void showBottomSheet(final TextView tvHeadName) {
        new BottomSheet.Builder(mActivity)
                .title(getString(R.string.gankio_custom_select_category))
                .sheet(R.menu.gankio_custom_bottom_sheet)
                .listener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case R.id.item_gank_all:
                                mCustomType = "all";
                                tvHeadName.setText("全部");
                                break;
                            case R.id.item_gank_app:
                                mCustomType = "App";
                                tvHeadName.setText("App");
                                break;
                            case R.id.item_gank_android:
                                mCustomType = "Android";
                                tvHeadName.setText("Android");
                                break;
                            case R.id.item_gank_ios:
                                mCustomType = "iOS";
                                tvHeadName.setText("iOS");
                                break;
                            case R.id.item_gank_front:
                                mCustomType = "前端";
                                tvHeadName.setText("前端");
                                break;
                            case R.id.item_gank_video:
                                mCustomType = "休息视频";
                                tvHeadName.setText("休息视频");
                                break;
                            case R.id.item_gank_tuozhan:
                                mCustomType = "拓展资源";
                                tvHeadName.setText("拓展资源");
                                break;
                        }
                        mPresenter.onCustomTypeChange(mCustomType);
                    }
                }).show();
    }

    /**
     * 每日推荐页面点击更多
     * @param customType
     */
    @Subscribe(code = RxBusCodeCanstant.RX_BUS_CODE_GANKIO_CUSTOM_TYPE)
    public void rxBusEvent(String customType){
        mCustomType = customType;
        initHeaderView();
        mPresenter.onCustomTypeChange(mCustomType);
    }

}
