package com.liqingfeng.DailyNews.detail.gankio;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.liqingfeng.sdk.base.IBaseModel;


/**
 * Created by lonlife on 2018/1/13.
 */

public class ImageDetailPresenter extends ImageDetailContract.Presenter {
    @Override
    public IBaseModel getModel() {
        return new ImageDetailModel();
    }

    @Override
    void loadBitmap(String url) {
        if (mView == null || mModel == null)
            return;

        DrawableTypeRequest<String> drawableTypeRequest = mModel.getBitmap(url);

        loadImage(drawableTypeRequest);

    }

    private void loadImage(DrawableTypeRequest<String> drawableTypeRequest) {
        drawableTypeRequest.asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onStart() {
                super.onStart();
                mView.showProgressBar();
            }

            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                mView.hideProgressBar();
                mView.showBitmap(resource);
            }

            @Override
            public void onLoadFailed(Exception e, Drawable errorDrawable) {
                super.onLoadFailed(e, errorDrawable);
                mView.hideProgressBar();
                mView.showErrorBitmap();
            }
        });
    }


}
