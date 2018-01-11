package com.example.liqingfeng.DailyNews.common.ui;


import android.app.Activity;
import android.graphics.Color;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.liqingfeng.DailyNews.R;
import com.example.liqingfeng.DailyNews.common.AppApplication;
import com.umeng.analytics.MobclickAgent;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/3/21 7:37
 * @DESC: 所有Activity的基类
 * @VERSION: V1.0
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected BaseActivity mActivity;
    protected Toolbar mToolBar;
    protected ActionBar mActionBar;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        initTransparentStatusBar();
        setContentView(getViewId());
        unbinder = ButterKnife.bind(this);
        mActivity = this;
        initView(savedInstanceState);
        initData(savedInstanceState);
        initListener();
        AppApplication.getInstance().addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
        AppApplication.getInstance().removeActivity(this);
    }

    protected abstract int getViewId();


    /**
     * 初始化数据
     *
     * @param saveInstanceState
     */
    protected void initData(Bundle saveInstanceState) {
    }

    /**
     * 初始化监听器
     */
    protected void initListener() {
    }

    /**
     * 完成数据和试图的绑定
     *
     * @param saveInstanceState
     */
    protected void initView(Bundle saveInstanceState) {
    }

    /**
     * 添加ToolBar
     *
     * @param title      ToolBar的标题
     * @param isBackable 是否支持返回按钮
     */
    protected void addToolBar(String title, boolean isBackable) {
        setSupportActionBar(mToolBar = (Toolbar) findViewById(R.id.toolBar));

        mActionBar = getSupportActionBar();
        if (!TextUtils.isEmpty(title)) {
            mActionBar.setTitle(title);
        } else {
            mActionBar.setTitle(R.string.app_name);
        }
        if (isBackable) {
            mActionBar.setDisplayHomeAsUpEnabled(true);
            //设置返回键点击功能
            mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }

    }


    //兼容android4.4以上状态栏透明


}
