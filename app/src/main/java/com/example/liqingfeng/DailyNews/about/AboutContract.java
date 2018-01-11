package com.example.liqingfeng.DailyNews.about;

import com.example.liqingfeng.DailyNews.common.ui.BasePresenter;
import com.example.liqingfeng.DailyNews.common.ui.BaseView;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/4/25 11:07
 * @DESC: About界面---Mvp接口
 * @VERSION: V1.0
 */

public class AboutContract {

    public interface Presenter extends BasePresenter {
        void getVersionName();

        void comment();

        void aboutAuthor();

        void followMyGithub();

        void followMyZhihu();

        void feedback();
    }

    public interface View extends BaseView<Presenter> {

        void setCurVersion(String versionName);
    }
}
