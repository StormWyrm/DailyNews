package com.liqingfeng.DailyNews.setting;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.liqingfeng.DailyNews.R;
import com.liqingfeng.DailyNews.constant.SPConstant;
import com.liqingfeng.DailyNews.util.SPUtils;
import com.liqingfeng.DailyNews.util.ToastUtil;
import com.liqingfeng.DailyNews.setting.widget.SettingItemView;
import com.liqingfeng.sdk.base.activity.BaseActivity;

import butterknife.BindView;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/4/4 22:55
 * @DESC: Setting界面---Activity
 * @VERSION: V1.0
 */
public class SettingActivity extends BaseActivity {
    @BindView(R.id.sv_browser)
    SettingItemView svBrowser;
    @BindView(R.id.sv_clear_cache)
    SettingItemView svClearCache;
    @BindView(R.id.sv_update)
    SettingItemView svUpdate;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView(Bundle saveInstanceState) {
        addToolBar(getString(R.string.setting), true);
    }

    @Override
    protected void initListener() {
        svBrowser.setOnCheckedChangeListener(new SettingItemView.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(boolean isChecked) {
                SPUtils.put(mActivity, SPConstant.WAY_OF_BROWSER, isChecked);
            }
        });
        svClearCache.setOnClickListener(new SettingItemView.OnClickListener() {
            @Override
            public void onClick() {
                Glide.get(mActivity).clearDiskCache();
            }
        });
        svUpdate.setOnClickListener(new SettingItemView.OnClickListener() {
            @Override
            public void onClick() {
                ToastUtil.shortMessage(mActivity, "检查更新");
            }
        });
    }

}

