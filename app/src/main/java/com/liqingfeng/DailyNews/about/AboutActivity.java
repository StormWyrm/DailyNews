package com.liqingfeng.DailyNews.about;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;

import com.liqingfeng.DailyNews.R;
import com.liqingfeng.sdk.base.activity.BaseActivity;

import butterknife.BindView;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/4/4 23:39
 * @DESC: About界面---Activity
 * @VERSION: V1.0
 */
public class AboutActivity extends BaseActivity {

    @BindView(R.id.tv_version_code)
    TextView tvVersionCode;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void initView(Bundle saveInstanceState) {
        super.initView(saveInstanceState);
        addToolBar(getString(R.string.main_nav_about), true);
        getVersionName();
    }

    public void getVersionName() {

        String versionName = "";
        try {
            PackageManager manager = mActivity.getPackageManager();
            PackageInfo info = manager.getPackageInfo(mActivity.getPackageName(), 0);
            versionName = mActivity.getString(R.string.about_app_version) + info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            versionName = mActivity.getString(R.string.about_app_version_no_find);
        } finally {
            tvVersionCode.setText(versionName);
        }

    }


}
