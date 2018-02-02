package com.liqingfeng.DailyNews.splash;

import android.Manifest;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.liqingfeng.DailyNews.R;
import com.liqingfeng.DailyNews.AppApplication;
import com.liqingfeng.sdk.base.activity.BaseActivity;
import com.liqingfeng.sdk.helper.RxHelper;
import com.liqingfeng.DailyNews.util.ToastUtil;
import com.liqingfeng.DailyNews.main.MainActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class SplashActivity extends BaseActivity {
    @BindView(R.id.iv_splash)
    ImageView mIvSplash;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_version)
    TextView mTvVersion;
    @BindView(R.id.tv_time_down)
    TextView tvTimeDown;

    private boolean mIsCancle;
    private int mTime = 3;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initData(Bundle saveInstanceState) {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.CAMERA,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean granted) throws Exception {
                        if (!granted) {
                            ToastUtil.shortMessage(AppApplication.getInstance(), "App未能获取全部需要的相关权限，部分功能可能不能正常使用");
                        }
                        initCountDown();
                    }
                });
    }

    @Override
    public void onBackPressedSupport() {
        super.onBackPressedSupport();
        mIsCancle = true;
        setIsTransAnim(false);//关闭动画
        finish();
    }

    @OnClick(R.id.tv_time_down)
    public void skip() {
        mIsCancle = true;
        startActivity(MainActivity.class);
        finish();
    }

    private void initCountDown() {
        Observable.interval(1, TimeUnit.SECONDS)
                .take(3)
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(Long aLong) throws Exception {
                        return mTime - aLong;
                    }
                }).compose(RxHelper.<Long>rxSchedulerHelper())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long value) {
                        String s = "跳过:"+String.valueOf(value);
                        if (tvTimeDown != null)
                            tvTimeDown.setText(TextUtils.isEmpty(s) ? "" : s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        if (!mIsCancle) {
                            startActivity(MainActivity.class);
                            finish();
                        }
                    }
                });
    }
}
