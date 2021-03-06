package com.liqingfeng.DailyNews.detail.gankio;

import android.content.Context;
import android.graphics.Bitmap;

import com.bumptech.glide.DrawableTypeRequest;
import com.liqingfeng.sdk.base.IBaseModel;
import com.liqingfeng.sdk.base.IBasePresenter;
import com.liqingfeng.sdk.base.IBaseView;


/**
 * Created by lonlife on 2018/1/13.
 */

public interface ImageDetailContract {
    interface Model extends IBaseModel {
        DrawableTypeRequest<String> getBitmap(String imageUrl);
    }

    interface View extends IBaseView {
        Context getContext();

        void showBitmap(Bitmap bitmap);

        void showErrorBitmap();

        void showProgressBar();

        void hideProgressBar();

        void showSaveSucess();


    }

    abstract class Presenter extends IBasePresenter<Model, View> {
        abstract void loadBitmap(String url);
    }

}
