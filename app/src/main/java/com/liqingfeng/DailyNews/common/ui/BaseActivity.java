package com.liqingfeng.DailyNews.common.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;

import com.liqingfeng.DailyNews.R;
import com.liqingfeng.DailyNews.common.AppApplication;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;


/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/3/21 7:37
 * @DESC: 所有Activity的基类
 * @VERSION: V1.0
 */

public abstract class BaseActivity  extends SupportActivity {
    protected BaseActivity mActivity;
    protected Toolbar mToolBar;
    protected ActionBar mActionBar;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        initTransparentStatusBar();
//        StatusBarUtils.setTransparent(this);
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        mActivity = this;
        initData(savedInstanceState);
        initView(savedInstanceState);
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

    protected abstract int getLayoutId();


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
            mActionBar.setTitle("");
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

    /**
     * [页面跳转]
     *
     * @param clz 要跳转的Activity
     */
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(this, clz));
    }

    /**
     * [页面跳转]
     *
     * @param clz    要跳转的Activity
     * @param intent intent
     */
    public void startActivity(Class<?> clz, Intent intent) {
        intent.setClass(this, clz);
        startActivity(intent);
    }

    /**
     * [携带数据的页面跳转]
     *
     * @param clz    要跳转的Activity
     * @param bundle bundel数据
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * [含有Bundle通过Class打开编辑界面]
     *
     * @param clz         要跳转的Activity
     * @param bundle      bundel数据
     * @param requestCode requestCode
     */
    public void startNewActivityForResult(Class<?> clz, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

}
