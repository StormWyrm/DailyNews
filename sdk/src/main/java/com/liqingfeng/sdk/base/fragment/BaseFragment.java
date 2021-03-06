package com.liqingfeng.sdk.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liqingfeng.sdk.base.activity.BaseActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/4/25 12:02
 * @DESC: Fragment的基类
 * @VERSION: V1.0
 */

public abstract class BaseFragment extends SupportFragment {
    protected Context mContext;
    protected BaseActivity mActivity;
    protected View mRootView;
    private Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        mActivity = (BaseActivity) context;
        context.getApplicationContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutId(),container,false);
        unbinder = ButterKnife.bind(this, mRootView);
        return mRootView;
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData(savedInstanceState);
        initListener();
        initView(savedInstanceState);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(unbinder != null){
            unbinder.unbind();
        }
    }

    @Override
    public boolean onBackPressedSupport() {
        if (getFragmentManager().getBackStackEntryCount() > 1) {
            //如果当前存在fragment>1，当前fragment出栈
            pop();
        } else {
            //已经退栈到root fragment，交由Activity处理回退事件
            return false;
        }
        return true;
    }

    /**
     * 返回Fragment的布局文件
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化监听器
     */
    protected void initListener() {
    }

    /**
     * 初始化数据
     */
    protected void initData(Bundle savedInstanceState) {
    }

    /**
     * 负责视图和数据的绑定
     * @param savedInstanceState
     */
    protected  void initView(Bundle savedInstanceState){

    }



}
