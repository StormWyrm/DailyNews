package com.liqingfeng.DailyNews.main.personal.tab;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.liqingfeng.DailyNews.common.ui.IBaseModel;
import com.liqingfeng.DailyNews.common.util.FileUtils;
import com.liqingfeng.DailyNews.common.util.MD5Utils;

import java.io.File;

/**
 * Created by lonlife on 2018/1/17.
 */

public class PersonalPresenter extends PersonalContract.Presenter {
    //请求相机
    private static final int REQUEST_CAMERA = 100;
    //请求相册
    private static final int REQUEST_PHOTO = 101;
    //请求访问外部存储
    private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 103;
    //请求写入外部存储
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 104;

    private File tempFile;

    @Override
    public IBaseModel getModel() {
        return new PersonalModel();
    }

    @Override
    public void btnHeadClicked() {
        mView.showPopupView();
    }

    @Override
    public void btnCameraClicked() {
        //创建拍照存储的图片文件
        tempFile = new File(FileUtils.checkDirPath(Environment.getExternalStorageDirectory()
                .getPath() + "/image/"), MD5Utils.getMD5("daily_news_head_image") + System
                .currentTimeMillis() + ".jpg");
        //权限判断
        if (ContextCompat.checkSelfPermission(mView.getActivity(), Manifest.permission
                .WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(mView.getActivity(), new String[]{Manifest
                    .permission.WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
        } else {
            //跳转到调用系统相机
            mView.gotoSystemCamera(tempFile, REQUEST_CAMERA);
        }
        if (mView.popupIsShowing())
            mView.dismissPopupView();
    }

    @Override
    public void btnPhotoClicked() {
        mView.gotoSystemPhoto(REQUEST_PHOTO);
        if (mView.popupIsShowing())
            mView.dismissPopupView();
    }

    @Override
    public void btnCancelClicked() {
        if (mView.popupIsShowing())
            mView.dismissPopupView();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                if (mView != null)
                    mView.gotoSystemCamera(tempFile, REQUEST_CAMERA);
            }
        } else if (requestCode == READ_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                if (mView != null)
                    mView.gotoSystemPhoto(REQUEST_PHOTO);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case REQUEST_CAMERA: //调用系统相机返回
                if (resultCode == Activity.RESULT_OK) {
                    mView.gotoHeadSettingActivity(Uri.fromFile(tempFile));
                }
                break;
            case REQUEST_PHOTO:  //调用系统相册返回
                if (resultCode == Activity.RESULT_OK) {
                    Uri uri = intent.getData();
                    mView.gotoHeadSettingActivity(uri);
                }
                break;
        }
    }
}
