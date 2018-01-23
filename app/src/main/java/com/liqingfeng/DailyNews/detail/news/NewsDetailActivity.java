package com.liqingfeng.DailyNews.detail.news;

import android.os.Bundle;

import com.liqingfeng.DailyNews.R;
import com.liqingfeng.DailyNews.common.ui.BaseActivity;
import com.liqingfeng.DailyNews.common.util.StatusBarUtils;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/3/31 19:52
 * @DESC: 消息详情界面
 * @VERSION: V1.0
 */
public class NewsDetailActivity extends BaseActivity {

    private NewsDetailFragment mDetailFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.layout_container;
    }

    @Override
    protected void initView(Bundle saveInstanceState) {
        StatusBarUtils.setTransparent(this);
        mDetailFragment = (NewsDetailFragment) getSupportFragmentManager().findFragmentById(R.id.fl_container);


        if (mDetailFragment == null) {
            mDetailFragment = new NewsDetailFragment();

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_container, mDetailFragment)
                    .commit();
        }



        new NewsDetailPresenter(mActivity, mDetailFragment);

    }

}
