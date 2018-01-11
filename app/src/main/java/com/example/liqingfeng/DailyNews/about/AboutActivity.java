package com.example.liqingfeng.DailyNews.about;

import android.os.Bundle;

import com.example.liqingfeng.DailyNews.R;
import com.example.liqingfeng.DailyNews.common.ui.BaseActivity;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/4/4 23:39
 * @DESC: About界面---Activity
 * @VERSION: V1.0
 */
public class AboutActivity extends BaseActivity {
    private AboutFragment mAboutFragment;

    @Override
    protected int getViewId() {
        return R.layout.activity_about;
    }

    @Override
    protected void initView(Bundle saveInstanceState) {
        addToolBar(getString(R.string.main_nav_about), true);

        mAboutFragment = (AboutFragment) getSupportFragmentManager().findFragmentById(R.id.fl_container);

        if (mAboutFragment == null) {
            mAboutFragment = new AboutFragment();

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_container, mAboutFragment)
                    .commit();
        }

        new AboutPresenter(mActivity, mAboutFragment);
    }

}
