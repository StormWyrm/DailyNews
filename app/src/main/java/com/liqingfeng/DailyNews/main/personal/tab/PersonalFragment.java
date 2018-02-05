package com.liqingfeng.DailyNews.main.personal.tab;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.liqingfeng.DailyNews.R;
import com.liqingfeng.DailyNews.bean.RxEventHeadBean;
import com.liqingfeng.DailyNews.constant.HeadConstant;
import com.liqingfeng.DailyNews.constant.RxBusCodeCanstant;
import com.liqingfeng.sdk.base.IBasePresenter;
import com.liqingfeng.sdk.base.fragment.BaseMvpFragment;
import com.liqingfeng.sdk.rxbus.RxBus;
import com.liqingfeng.sdk.rxbus.Subscribe;
import com.liqingfeng.sdk.utils.AppUtils;
import com.liqingfeng.sdk.utils.FileUtils;
import com.liqingfeng.DailyNews.main.HeadSettingActivity;
import com.liqingfeng.sdk.widgets.PersonalPopupWindow;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;


public class PersonalFragment
        extends BaseMvpFragment<PersonalContract.Model, PersonalContract.Presenter>
        implements PersonalContract.View {

    @BindView(R.id.profile_image)
    CircleImageView profileImage;
    @BindView(R.id.tv_setting)
    TextView tvSetting;


    private PersonalPopupWindow personalPopupWindow;
    public PersonalFragment() {

    }

    public static PersonalFragment newInstance() {
        PersonalFragment fragment = new PersonalFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RxBus.get().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBus.get().unRegister(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_personal;
    }



    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        initPopupView();
        initHeadImage();
    }

    @Override
    protected void initListener() {
        super.initListener();
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.btnHeadClicked();
            }
        });

        tvSetting.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mPresenter.onSettingClick();
            }
        });
    }

    @NonNull
    @Override
    public IBasePresenter initPresenter() {
        return new PersonalPresenter();
    }

    @Override
    public void initPopupView() {
        personalPopupWindow = new PersonalPopupWindow(mActivity);
        personalPopupWindow.setOnItemClickListener(new PersonalPopupWindow.OnItemClickListener() {
            @Override
            public void onCaremaClicked() {
                mPresenter.btnCameraClicked();
            }

            @Override
            public void onPhotoClicked() {
                mPresenter.btnPhotoClicked();
            }

            @Override
            public void onCancelClicked() {
                mPresenter.btnCancelClicked();
            }
        });
    }

    @Override
    public void showHead(Bitmap bitmap) {
        profileImage.setImageBitmap(bitmap);
    }

    @Override
    public void showPopupView() {
        personalPopupWindow.showAtLocation(getView(), Gravity.BOTTOM | Gravity.LEFT,0,0);
    }

    @Override
    public void dismissPopupView() {
        personalPopupWindow.dismiss();
    }

    @Override
    public boolean popupIsShowing() {
        return personalPopupWindow.isShowing();
    }

    @Override
    public void gotoHeadSettingActivity(Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(mActivity,HeadSettingActivity.class);
        intent.setData(uri);
        startActivity(intent);
    }

    @Override
    public void gotoSystemPhoto(int requestCode) {
        //跳转到调用系统图库
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media
                .EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "请选择图片"), requestCode);
    }

    @Override
    public void gotoSystemCamera(File tempFile, int requestCode) {
        //跳转到调用系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //设置7.0中共享文件，分享路径定义在xml/file_paths.xml
            //            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            //            Uri contentUri = FileProvider.getUriForFile(mActivity, BuildConfig.APPLICATION_ID + "" +
            //                    ".fileProvider", tempFile);
            //            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
            ContentValues contentValues = new ContentValues(1);
            contentValues.put(MediaStore.Images.Media.DATA, tempFile.getAbsolutePath());
            Uri uri = mActivity.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        }
        startActivityForResult(intent, requestCode);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        mPresenter.onActivityResult(requestCode, resultCode, intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mPresenter.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * RxBus接收图片Uri
     *
     * @param
     */
    @Subscribe(code = RxBusCodeCanstant.RX_BUS_CODE_HEAD_IMAGE_URI)
    public void rxBusEvent(RxEventHeadBean bean) {
        Uri uri = bean.getUri();
        if (uri == null) {
            return;
        }
        String cropImagePath = FileUtils.getRealFilePathFromUri(AppUtils.getContext(), uri);
        Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);
        if (bitMap != null)
            profileImage.setImageBitmap(bitMap);
    }

    private void initHeadImage(){
        Uri mSaveUri = Uri.fromFile(new File(mActivity.getCacheDir(), HeadConstant.HEAD_IMAGE_NAME + ".jpg"));
        String cropImagePath = FileUtils.getRealFilePathFromUri(AppUtils.getContext(), mSaveUri);
        Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);
        if (bitMap != null)
            profileImage.setImageBitmap(bitMap);

    }

}
