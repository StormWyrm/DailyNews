package com.liqingfeng.DailyNews.setting;


import android.os.Bundle;

import com.liqingfeng.DailyNews.R;
import com.liqingfeng.DailyNews.common.ui.BaseFragment;
import com.liqingfeng.DailyNews.setting.widget.SettingItemView;

import butterknife.BindView;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/4/25 17:28
 * @DESC: Setting界面--Fragment
 * @VERSION: V1.0
 */
public class SettingFragment extends BaseFragment implements SettingContract.View {
    @BindView(R.id.sv_image)
    SettingItemView svImage;
    @BindView(R.id.sv_browser)
    SettingItemView svBrowser;
    @BindView(R.id.sv_clear_cache)
    SettingItemView svClearCache;
    @BindView(R.id.sv_update)
    SettingItemView svUpdate;

    private SettingContract.Presenter mPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_setting;
    }

    @Override
    protected void initListener() {
        svImage.setOnCheckedChangeListener(new SettingItemView.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(boolean isChecked) {
                mPresenter.saveImageSetting(isChecked);
            }
        });
        svBrowser.setOnCheckedChangeListener(new SettingItemView.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(boolean isChecked) {
                mPresenter.saveBrowserSetting(isChecked);
            }
        });
        svClearCache.setOnClickListener(new SettingItemView.OnClickListener() {
            @Override
            public void onClick() {
                mPresenter.clearCache();
            }
        });
        svUpdate.setOnClickListener(new SettingItemView.OnClickListener() {
            @Override
            public void onClick() {
                mPresenter.update();
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mPresenter.initImageMode();
        mPresenter.initBrowserMode();
    }

    @Override
    public void setPresenter(SettingContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void setImageMode(boolean isChecked) {
        svImage.setChecked(isChecked);
    }

    @Override
    public void setBrowserMode(boolean isChecked) {
        svBrowser.setChecked(isChecked);
    }


}
