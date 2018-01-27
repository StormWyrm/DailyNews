package com.liqingfeng.DailyNews.common.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.liqingfeng.DailyNews.R;

import java.io.File;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/3/28 20:07
 * @DESC: Glide的封装类
 * @VERSION: V1.0
 */

public class GlideUtils {
    /**
     * 将对应url的图片加载到ImageView之上
     *
     * @param context
     * @param imageView
     * @param imagePath
     */
    public static void loadImage(Context context, ImageView imageView, String imagePath) {
        Glide.with(context)
                .load(imagePath)
                .asBitmap()
                .placeholder(R.drawable.ic_vector_load_default)
                .error(R.drawable.ic_vector_load_default)
                .centerCrop()
                .into(imageView);

    }

    /**
     * 将对应url的图片加载到ImageView之上
     *
     * @param context
     * @param imageView
     * @param imagePath
     */
    public static void loadImage(Context context, ImageView imageView, String imagePath,int resourceId) {
        Glide.with(context)
                .load(imagePath)
                .asBitmap()
                .placeholder(resourceId)
                .error(resourceId)
                .centerCrop()
                .into(imageView);

    }

    //加载占位图
    public static void loadPlaceHolder(Context context, ImageView imageView,int imageId) {
        Glide.with(context)
                .load(imageId)
                .asBitmap()
                .centerCrop()
                .into(imageView);
    }

    /**
     * 显示网络虚化图片
     *
     * @param context   context
     * @param imgUrl    图片url
     * @param imageView 要显示的imageview
     */
    public static void displayBlurImg(Context context, final String imgUrl, ImageView imageView) {
        // "23":模糊度；"4":图片缩放4倍后再进行模糊
        Glide.with(context)
                .load(imgUrl)
                .placeholder(R.drawable.stackblur_default)
                .error(R.drawable.stackblur_default)
                .crossFade(300)
                .bitmapTransform(new BlurTransformation(context, 23, 4))
                .into(imageView);
    }

    /**
     * 显示本地虚化图片
     *
     * @param context   context
     * @param file      本地图片file
     * @param imageView 要显示的imageview
     */
    public static void displayBlurImg(Context context, File file, ImageView imageView) {
        // "23":模糊度；"4":图片缩放4倍后再进行模糊
        Glide.with(context)
                .load(file)
                .error(R.drawable.stackblur_default)
                .placeholder(R.drawable.stackblur_default)
                .crossFade(300)
                .bitmapTransform(new BlurTransformation(context, 23, 4))
                .into(imageView);
    }

    /**
     * 显示资源虚化图片
     *
     * @param context    context
     * @param resourceId 图片资源id
     * @param imageView  要显示的imageview
     */
    public static void displayBlurImg(Context context, Integer resourceId, ImageView imageView) {
        // "23":模糊度；"4":图片缩放4倍后再进行模糊
        Glide.with(context)
                .load(resourceId)
                .error(R.drawable.stackblur_default)
                .placeholder(R.drawable.stackblur_default)
                .crossFade(300)
                .bitmapTransform(new BlurTransformation(context, 23, 4))
                .into(imageView);
    }
}
