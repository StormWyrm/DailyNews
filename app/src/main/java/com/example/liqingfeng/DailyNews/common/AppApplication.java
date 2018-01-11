package com.example.liqingfeng.DailyNews.common;

import android.app.Activity;
import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import com.example.liqingfeng.DailyNews.common.util.Constant;
import com.example.liqingfeng.DailyNews.common.util.SPUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/3/28 10:10
 * @DESC: 默认的Application
 * @VERSION: V1.0
 */
public class AppApplication extends Application {
    private static AppApplication mContext;
    private static List<Activity> mActivityList;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = (AppApplication) getApplicationContext();
        mActivityList = new ArrayList<>();
        if((Boolean) SPUtils.get(mContext, Constant.Config.UI_MODE_NIGHT,false)){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    public static AppApplication getInstance() {
        return mContext;
    }

    public synchronized void addActivity(Activity activity) {
        mActivityList.add(activity);
    }

    public synchronized void removeActivity(Activity activity) {
        mActivityList.remove(activity);
    }

    //离开App
    public void exit() {
        for (Activity activity : mActivityList) {
            activity.finish();
        }
        System.exit(0);
    }

}
