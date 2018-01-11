package com.example.liqingfeng.DailyNews.splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.liqingfeng.DailyNews.R;
import com.example.liqingfeng.DailyNews.common.ui.BaseActivity;
import com.example.liqingfeng.DailyNews.main.MainActivity;

public class SplashActivity extends BaseActivity {
    private ImageView mIvSplash;
    private TextView mTvName;
    private TextView mTvVersion;
    private AlphaAnimation animation;

    @Override
    protected void initView(Bundle saveInstanceState) {
        mIvSplash = (ImageView) findViewById(R.id.iv_splash);
        mTvName = (TextView) findViewById(R.id.tv_name);
        mTvVersion = (TextView) findViewById(R.id.tv_version);
    }

    @Override
    protected int getViewId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initData(Bundle saveInstanceState) {
        animation = new AlphaAnimation(0, 1);
        animation.setDuration(3000);
        mIvSplash.setAnimation(animation);
        mTvName.setAnimation(animation);
        animation.start();

    }

    @Override
    protected void initListener() {
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(mActivity, MainActivity.class));
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
