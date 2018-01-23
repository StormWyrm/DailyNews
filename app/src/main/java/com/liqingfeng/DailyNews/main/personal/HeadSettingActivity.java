package com.liqingfeng.DailyNews.main.personal;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.liqingfeng.DailyNews.R;
import com.liqingfeng.DailyNews.bean.RxEventHeadBean;
import com.liqingfeng.DailyNews.common.constant.HeadConstant;
import com.liqingfeng.DailyNews.common.constant.RxBusCodeCanstant;
import com.liqingfeng.DailyNews.common.rxbus.RxBus;
import com.liqingfeng.DailyNews.common.ui.BaseActivity;
import com.liqingfeng.DailyNews.main.personal.widget.ClipViewLayout;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Horrarndoo on 2017/9/25.
 * <p>
 * 设置头像Activity
 */

public class HeadSettingActivity extends BaseActivity {
    @BindView(R.id.cvl_rect)
    ClipViewLayout cvlRect;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RxBus.get().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unRegister(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_head_setting;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        addToolBar("选取头像", true);
        cvlRect.setImageSrc(getIntent().getData());
    }

    @OnClick({R.id.tv_cancel, R.id.tv_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                finish();
                break;
            case R.id.tv_ok:
                Observable.create(new ObservableOnSubscribe<Uri>() {

                    @Override
                    public void subscribe(ObservableEmitter<Uri> e) throws Exception {
                        e.onNext(generateUri());
                        e.onComplete();
                    }
                }).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Uri>() {
                            @Override
                            public void accept(Uri uri) throws Exception {
                                RxEventHeadBean rxEventHeadBean = new RxEventHeadBean(uri);
                                RxBus.get().send(RxBusCodeCanstant.RX_BUS_CODE_HEAD_IMAGE_URI, rxEventHeadBean);
                                finish();
                            }
                        });
                break;
        }
    }


    /**
     * 生成Uri
     */
    private Uri generateUri() {
        //调用返回剪切图
        Bitmap zoomedCropBitmap;
        zoomedCropBitmap = cvlRect.clip();
        Uri mSaveUri = Uri.fromFile(new File(getCacheDir(), HeadConstant.HEAD_IMAGE_NAME + ".jpg"));
        if (mSaveUri != null) {
            OutputStream outputStream = null;
            try {
                outputStream = getContentResolver().openOutputStream(mSaveUri);
                if (outputStream != null) {
                    zoomedCropBitmap.compress(Bitmap.CompressFormat.JPEG, 90, outputStream);
                }
            } catch (IOException ex) {
                Log.e("android", "Cannot open file: " + mSaveUri, ex);
            } finally {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return mSaveUri;
    }
}
