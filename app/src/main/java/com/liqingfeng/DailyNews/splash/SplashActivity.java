package com.liqingfeng.DailyNews.splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.liqingfeng.DailyNews.R;
import com.liqingfeng.DailyNews.common.ui.BaseActivity;
import com.liqingfeng.DailyNews.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity {
    @BindView(R.id.iv_splash)
    ImageView mIvSplash;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_version)
    TextView mTvVersion;

    private AlphaAnimation animation;


    @Override
    protected int getLayoutId() {
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
