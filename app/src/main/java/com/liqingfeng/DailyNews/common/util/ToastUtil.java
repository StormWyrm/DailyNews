package com.liqingfeng.DailyNews.common.util;

import android.content.Context;
import android.widget.Toast;

import com.liqingfeng.DailyNews.common.AppApplication;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/3/21 8:25
 * @DESC: Toast的封装工具类
 * @VERSION: V1.0
 */
public class ToastUtil {
    private static Toast sToast;

    public static void shortMessage(Context context, String title) {
        createToast();
        sToast.setDuration(Toast.LENGTH_SHORT);
        sToast.setText(title);
        sToast.show();
    }

    public static void longMessage(Context context, String title) {
        createToast();
        sToast.setDuration(Toast.LENGTH_LONG);
        sToast.setText(title);
        sToast.show();
    }

    private static void createToast() {
        if (sToast == null) {
            sToast = Toast.makeText(AppApplication.getInstance(), "", Toast.LENGTH_SHORT);
        }
    }
}
