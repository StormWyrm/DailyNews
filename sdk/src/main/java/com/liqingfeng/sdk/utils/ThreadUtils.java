package com.liqingfeng.sdk.utils;

import android.os.Handler;
import android.os.Looper;

/**
 * @AUTHER:       李青峰
 * @EMAIL:        1021690791@qq.com
 * @PHONE:        18045142956
 * @DATE:         2017/3/22 22:23
 * @DESC:         操作线程的工具类
 * @VERSION:      V1.0
 */
public class ThreadUtils {
    private static Handler handler = new Handler(Looper.getMainLooper());

    /**
     * 在子线程中执行任务
     * */
    public static void runOnThread(Runnable runnable){
        new Thread(runnable).start();
    }
    /**
     * 在主线程中执行任务
     * */
    public static void runOnUiThread(Runnable runnable){
        handler.post(runnable);
    }
    /**
     * 在主线程中延迟执行任务
     * */
    public static void runOnUiThreadDelayed(Runnable runnable,long delayMillis){
        handler.postDelayed(runnable,delayMillis);
    }
}
