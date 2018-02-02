package com.liqingfeng.sdk.utils;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.liqingfeng.sdk.R;


/**
 * Created by Horrarndoo on 2017/8/31.
 * <p>
 * 对话框工具类, 提供常用对话框显示, 使用support.v7包内的AlertDialog样式
 */
public class DialogUtils {

    public static Dialog createProgressDialog(Context context) {
        return createProgressDialog(context, "Loadding...", true);
    }

    public static Dialog createProgressDialog(Context context, String message, boolean needCancle) {
        ProgressDialog dialog = new ProgressDialog(context);
        dialog.setMessage(message);
        dialog.setCancelable(needCancle);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }


    public static Dialog showCommonDialog(Context context, String title, String message,
                                          DialogInterface.OnClickListener
                                                  positiveListener, DialogInterface.OnClickListener
                                                  negativeListener) {
        return showCommonDialog(context, title, message, context.getString(R.string.dialog_positive),
                context.getString(R.string.dialog_negative), positiveListener, negativeListener);
    }

    public static Dialog showCommonDialog(Context context, String title, String message, String positiveText,
                                          String negativeText, DialogInterface.OnClickListener
                                                  positiveListener, DialogInterface.OnClickListener
                                                  negativeListener) {
        return new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton(positiveText, positiveListener)
                .setNegativeButton(negativeText, negativeListener)
                .show();
    }


    public static Dialog showConfirmDialog(Context context, String message,
                                           DialogInterface.OnClickListener listener) {
        return showConfirmDialog(context, message, context.getString(R.string.dialog_positive),
                listener);
    }

    public static Dialog showConfirmDialog(Context context, String message, String positiveText,
                                           DialogInterface.OnClickListener listener) {
        return new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton(positiveText, listener)
                .show();
    }
}
