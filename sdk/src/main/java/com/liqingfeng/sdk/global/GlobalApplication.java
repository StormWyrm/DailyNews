package com.liqingfeng.sdk.global;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * Created by lonlife on 2018/1/31.
 */

public class GlobalApplication extends Application {
    protected static GlobalApplication mApp;
    protected static Context mContext;
    protected static Handler mHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        mContext = getApplicationContext();
        mHandler = new Handler();
    }


    public static synchronized GlobalApplication getInstance() {
        return mApp;
    }

    /**
     * 获取上下文对象
     *
     * @return context
     */
    public static Context getContext() {
        return mContext;
    }

    /**
     * 获取全局handler
     *
     * @return 全局handler
     */
    public static Handler getHandler() {
        return mHandler;
    }

}
