package com.liqingfeng.DailyNews.detail.gankio;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.liqingfeng.DailyNews.AppApplication;

/**
 * Created by lonlife on 2018/1/13.
 */

public class ImageDetailModel implements ImageDetailContract.Model {
    @Override
    public DrawableTypeRequest<String> getBitmap(String imageUrl) {
        return Glide.with(AppApplication.getInstance()).load(imageUrl);
    }
}
