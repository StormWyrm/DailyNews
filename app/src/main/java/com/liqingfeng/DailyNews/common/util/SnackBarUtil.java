package com.liqingfeng.DailyNews.common.util;

import android.support.design.widget.Snackbar;
import android.view.View;

import com.liqingfeng.DailyNews.R;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/5/16 15:45
 * @DESC: SnackBar工具类
 * @VERSION: V1.0
 */

public class SnackBarUtil {

    public static void showMessage(View container, String message) {
        Snackbar snackbar = Snackbar.make(container, message, Snackbar.LENGTH_SHORT);
        View view = snackbar.getView();
        view.setBackgroundColor(view.getContext().getResources().getColor(R.color.colorPrimary));
        snackbar.show();
    }

    public static void showMessage(View container, String message, String todoTitle, final Runnable todo) {

        Snackbar snackbar = Snackbar.make(container, message, Snackbar.LENGTH_SHORT);
        View view = snackbar.getView();
        view.setBackgroundColor(view.getContext().getResources().getColor(R.color.windowBackground));

        snackbar.setAction(todoTitle, new View
                .OnClickListener() {
            @Override
            public void onClick(View v) {
                todo.run();
            }
        }).show();
    }
}
