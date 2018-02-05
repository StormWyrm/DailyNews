package com.liqingfeng.DailyNews;


import android.support.v7.app.AppCompatDelegate;


import com.liqingfeng.DailyNews.constant.SPConstant;
import com.liqingfeng.DailyNews.util.SPUtils;
import com.liqingfeng.sdk.global.GlobalApplication;
import com.mob.MobSDK;
import com.tencent.bugly.crashreport.CrashReport;


/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/3/28 10:10
 * @DESC: 默认的Application
 * @VERSION: V1.0
 */
public class AppApplication extends GlobalApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        //Bugly Crash上报
        CrashReport.initCrashReport(getApplicationContext(), "c39db1a0c9", false);

        MobSDK.init(this);

        if((Boolean) SPUtils.get(mContext, SPConstant.UI_MODE_NIGHT,false)){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

}
