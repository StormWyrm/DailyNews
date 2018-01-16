package com.liqingfeng.DailyNews.common.util;

import android.content.Context;
import android.widget.ImageView;

import com.liqingfeng.DailyNews.R;
import com.liqingfeng.DailyNews.common.constant.Constant;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/4/6 18:32
 * @DESC: 加载网络图片的工具类
 * @VERSION: V1.0
 */

public class NetworkImageUtil {

    /**
     * 根据网络状态以及加载图片
     *
     * @param context
     * @param imageView
     * @param imageUrl
     */
    public static void loadNetImageDependNet(Context context, ImageView imageView, String
            imageUrl) {

        if (NetworkUtil.isWifi(context)) {
            GlideUtils.loadImage(context, imageView, imageUrl);
        } else {
            //判断是否允许非WIFT网络加载图片
            if (!(Boolean) SPUtils.get(context, Constant.Config.WAY_OF_IMAGE_SHOW, false)) {
                GlideUtils.loadImage(context, imageView, imageUrl);
            } else {

                GlideUtils.loadPlaceHolder(context, imageView, R.drawable.icon_load_default);
            }
        }

    }

    /**
     * 根据网络状态以及加载图片
     *
     * @param context
     * @param imageView
     * @param imageUrl
     */
    public static void loadNetImageDependNet(Context context, ImageView imageView, String
            imageUrl, int resourceId) {

        if (NetworkUtil.isWifi(context)) {
            GlideUtils.loadImage(context, imageView, imageUrl,resourceId);
        } else {
            //判断是否允许非WIFT网络加载图片
            if (!(Boolean) SPUtils.get(context, Constant.Config.WAY_OF_IMAGE_SHOW, false)) {

            } else {
                GlideUtils.loadPlaceHolder(context, imageView, resourceId);
            }
        }

    }
}
