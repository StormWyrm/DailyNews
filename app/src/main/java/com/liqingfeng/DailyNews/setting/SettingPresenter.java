package com.liqingfeng.DailyNews.setting;


import com.bumptech.glide.Glide;
import com.liqingfeng.DailyNews.R;
import com.liqingfeng.DailyNews.common.ui.BaseActivity;
import com.liqingfeng.DailyNews.common.constant.Constant;
import com.liqingfeng.DailyNews.common.util.SPUtils;
import com.liqingfeng.DailyNews.common.util.ThreadUtils;
import com.liqingfeng.DailyNews.common.util.ToastUtil;
import com.liqingfeng.DailyNews.main.home.cache.DoubanCache;
import com.liqingfeng.DailyNews.main.home.cache.GuokeCache;
import com.liqingfeng.DailyNews.main.home.cache.GuokeHotCache;
import com.liqingfeng.DailyNews.main.home.cache.ZhihuCache;
import com.liqingfeng.DailyNews.main.home.cache.ZhihuHotCache;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/4/25 17:29
 * @DESC: Setting界面---mvp persenter
 * @VERSION: V1.0
 */
public class SettingPresenter implements SettingContract.Presenter {
    private BaseActivity mActivity;
    private SettingContract.View mSettingView;

    public SettingPresenter(BaseActivity activity, SettingContract.View view) {
        this.mActivity = activity;
        this.mSettingView = view;
        this.mSettingView.setPresenter(this);
    }


    @Override
    public void initImageMode() {
        Boolean ischecked = (Boolean) SPUtils.get(mActivity, Constant.Config.WAY_OF_IMAGE_SHOW,
                false);
        mSettingView.setImageMode(ischecked);
    }

    @Override
    public void initBrowserMode() {
        Boolean ischecked = (Boolean) SPUtils.get(mActivity, Constant.Config.WAY_OF_BROWSER,
                true);
        mSettingView.setBrowserMode(ischecked);
    }

    @Override
    public void saveImageSetting(boolean isChecked) {
        SPUtils.put(mActivity, Constant.Config.WAY_OF_IMAGE_SHOW, isChecked);
    }

    @Override
    public void saveBrowserSetting(boolean isChecked) {
        SPUtils.put(mActivity, Constant.Config.WAY_OF_BROWSER, isChecked);
    }

    @Override
    public void clearCache() {
        ThreadUtils.runOnThread(new Runnable() {
            @Override
            public void run() {
                Glide.get(mActivity).clearDiskCache();
                new ZhihuCache().clearCache();
                new ZhihuHotCache().clearCache();
                new GuokeCache().clearCache();
                new GuokeHotCache().clearCache();
                new DoubanCache().clearCache();
                ThreadUtils.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.shortMessage(mActivity, mActivity.getString(R.string
                                .setting_other_clear_message));
                    }
                });
            }
        });
    }

    @Override
    public void update() {
        ToastUtil.shortMessage(mActivity, "检查更新");
    }

}
