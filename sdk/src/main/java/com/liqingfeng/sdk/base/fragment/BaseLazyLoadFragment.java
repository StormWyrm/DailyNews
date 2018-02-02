package com.liqingfeng.sdk.base.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.google.gson.Gson;
import com.liqingfeng.sdk.base.activity.BaseActivity;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/3/21 7:47
 * @DESC: Fragment懒加载基类,解决Fragment复用的问题
 * @VERSION: V1.0
 */

public abstract class BaseLazyLoadFragment extends Fragment {
    protected View mContentView;

    protected BaseActivity mActivity;
    protected Gson mGson;
    private boolean isCreateView;//判断View是否被创建
    private boolean isLoadData;//判断数据是否被加载

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (BaseActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        if (mContentView == null) {
            mContentView = initView(inflater, container,savedInstanceState);
        } else {
            ViewParent parent = mContentView.getParent();
            if (parent instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) parent;
                viewGroup.removeView(mContentView);
            }
        }
        mGson = new Gson();
        isCreateView = true;
        return mContentView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //ViewPager加载第一个页面时,还未创建页面,我们需要在这里加载数据
        if (getUserVisibleHint() && isCreateView && !isLoadData) {
            initData();
            initListener();

        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isCreateView && !isLoadData) {
            initData();
            initListener();
        }

    }

    /**
     * 初始化Fragment的布局
     *
     * @param inflater  LayoutInflater填充器
     * @param container 父ViewGroup
     * @param savedInstanceState
     * @return 填充的布局
     */
    protected abstract View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    /**
     * 初始化数据
     */
    protected void initData() {
        isLoadData = true;
    }

    /**
     * 初始化监听器
     */
    protected void initListener() {
    }

}
