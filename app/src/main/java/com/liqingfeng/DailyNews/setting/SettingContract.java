package com.liqingfeng.DailyNews.setting;

import com.liqingfeng.DailyNews.common.ui.BasePresenter;
import com.liqingfeng.DailyNews.common.ui.BaseView;


/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/4/25 17:27
 * @DESC: Setting界面---mvp接口管理
 * @VERSION: V1.0
 */
public interface SettingContract {

    interface Presenter extends BasePresenter {
        void initImageMode();

        void initBrowserMode();

        void saveImageSetting(boolean isChecked);

        void saveBrowserSetting(boolean isChecked);

        void clearCache();

        void update();
    }

    interface View extends BaseView<SettingContract.Presenter> {

        void setImageMode(boolean isChecked);

        void setBrowserMode(boolean isChecked);
    }
}
