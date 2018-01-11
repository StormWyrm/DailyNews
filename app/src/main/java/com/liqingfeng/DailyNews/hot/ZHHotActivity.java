package com.liqingfeng.DailyNews.hot;

import android.os.Bundle;

import com.liqingfeng.DailyNews.R;
import com.liqingfeng.DailyNews.common.ui.BaseActivity;


/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/4/4 23:49
 * @DESC: 知乎热搜界面---Activity
 * @VERSION: V1.0
 */
public class ZHHotActivity extends BaseActivity {
    private ZHHotFragment mZHHhotFragment;


    @Override
    protected int getViewId() {
        return R.layout.activity_zh_hot;
    }

    @Override
    protected void initView(Bundle saveInstanceState) {
        addToolBar(getString(R.string.main_nav_zhhot), true);

        mZHHhotFragment = (ZHHotFragment) getSupportFragmentManager().findFragmentById(R.id.fl);

        if (mZHHhotFragment == null) {
            mZHHhotFragment = new ZHHotFragment();

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl, mZHHhotFragment)
                    .commit();
        }

        new ZHHotPresenter(this, mZHHhotFragment);
    }

}
