package com.liqingfeng.DailyNews.setting;

import android.os.Bundle;

import com.liqingfeng.DailyNews.R;
import com.liqingfeng.DailyNews.common.ui.BaseActivity;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/4/4 22:55
 * @DESC: Setting界面---Activity
 * @VERSION: V1.0
 */
public class SettingActivity extends BaseActivity {
    private SettingFragment mSetttingFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView(Bundle saveInstanceState) {
        addToolBar(getString(R.string.setting), true);

        mSetttingFragment = (SettingFragment) getSupportFragmentManager().findFragmentById(R.id.fl_container);

        if (mSetttingFragment == null) {
            mSetttingFragment = new SettingFragment();

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_container, mSetttingFragment)
                    .commit();
        }


        new SettingPresenter(mActivity, mSetttingFragment);
    }

}

