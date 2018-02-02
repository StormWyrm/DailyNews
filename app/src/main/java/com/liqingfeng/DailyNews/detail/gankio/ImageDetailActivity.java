package com.liqingfeng.DailyNews.detail.gankio;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.bm.library.PhotoView;
import com.liqingfeng.DailyNews.R;
import com.liqingfeng.DailyNews.constant.BundleKeyConstant;
import com.liqingfeng.DailyNews.util.SnackBarUtil;
import com.liqingfeng.sdk.base.IBasePresenter;
import com.liqingfeng.sdk.base.activity.BaseMvpActivity;
import com.liqingfeng.sdk.utils.StatusBarUtils;

import butterknife.BindView;


public class ImageDetailActivity
        extends BaseMvpActivity<ImageDetailContract.Model, ImageDetailContract.Presenter>
        implements ImageDetailContract.View {

    @BindView(R.id.pv_pic)
    PhotoView pvPic;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.pb_pic)
    ProgressBar pbPic;
    @BindView(R.id.fab_save)
    FloatingActionButton fabSave;

    private String mImageUrl;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gankio_welfare_detail;
    }

    @Override
    protected void initData(Bundle saveInstanceState) {
        super.initData(saveInstanceState);
        Bundle extras = getIntent().getExtras();

        if(extras != null){
            mImageUrl =  extras.getString(BundleKeyConstant.BUNDLE_KEY_IMAGE_DETAIL_URL);
        }

        mPresenter.loadBitmap(mImageUrl);
    }

    @Override
    protected void initView(Bundle saveInstanceState) {
        super.initView(saveInstanceState);

        pvPic.enable();
        addToolBar("",true);
        mToolBar.setNavigationIcon(R.drawable.ic_vector_arrow_back_white);
        StatusBarUtils.setBarColor(mActivity, Color.BLACK);
    }

    @NonNull
    @Override
    public IBasePresenter initPresenter() {
        return new ImageDetailPresenter();
    }

    @Override
    public Context getContext() {
        return mActivity;
    }

    @Override
    public void showBitmap(Bitmap bitmap) {
        pvPic.setImageBitmap(bitmap);
    }

    @Override
    public void showErrorBitmap() {
        pvPic.setImageResource(R.mipmap.img_default_meizi);
    }

    @Override
    public void showProgressBar() {
        pbPic.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        pbPic.setVisibility(View.GONE);
    }

    @Override
    public void showSaveSucess() {
        SnackBarUtil.showMessage(getWindow().getDecorView().getRootView(),"保存成功");
    }

}
