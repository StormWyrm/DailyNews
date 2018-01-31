package com.liqingfeng.DailyNews.main;


import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatDelegate;
import android.view.Gravity;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.liqingfeng.DailyNews.R;
import com.liqingfeng.DailyNews.about.AboutActivity;
import com.liqingfeng.DailyNews.browser.BrowserActivity;
import com.liqingfeng.DailyNews.common.AppApplication;
import com.liqingfeng.DailyNews.common.constant.BundleKeyConstant;
import com.liqingfeng.DailyNews.common.constant.Constant;
import com.liqingfeng.DailyNews.common.ui.BaseActivity;
import com.liqingfeng.DailyNews.common.ui.BaseFragment;
import com.liqingfeng.DailyNews.common.util.SPUtils;
import com.liqingfeng.DailyNews.common.util.ToastUtil;
import com.liqingfeng.DailyNews.main.gankio.GankioRootFragment;
import com.liqingfeng.DailyNews.main.home.HomeFragment;
import com.liqingfeng.DailyNews.main.home.HomeRootFragment;
import com.liqingfeng.DailyNews.main.movie.MovieRootFragment;
import com.liqingfeng.DailyNews.main.personal.PersonalRootFragment;
import com.umeng.analytics.MobclickAgent;

import butterknife.BindView;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/3/21 7:47
 * @DESC: 主页面模块 Activity
 * @VERSION: V1.0
 */
public class MainActivity extends BaseActivity
        implements HomeFragment.OnDrawerLayoutOpenListener {
    public static final int TIME_OF_BACK = 1500;

    @BindView(R.id.bniv_bar)
    BottomNavigationViewEx bottomNavigationView;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;
    @BindView(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;

    private BaseFragment[] mFragments = new BaseFragment[4];

    private long mLastPressBackTime;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData(Bundle saveInstanceState) {
        super.initData(saveInstanceState);
        BaseFragment firstFragment = findFragment(HomeRootFragment.class);
        if (firstFragment == null) {
            mFragments[0] = HomeRootFragment.newInstance();
            mFragments[1] = GankioRootFragment.newInstance();
            mFragments[2] = MovieRootFragment.newInstance();
            mFragments[3] = PersonalRootFragment.newInstance();

            // 加载多个同级根Fragment,类似Wechat, QQ主页的场景
            loadMultipleRootFragment(R.id.fl_container, 0,
                    mFragments[0],
                    mFragments[1],
                    mFragments[2],
                    mFragments[3]);

        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题

            // 这里我们需要拿到mFragments的引用
            mFragments[0] = firstFragment;
            mFragments[1] = findFragment(GankioRootFragment.class);
            mFragments[2] = findFragment(MovieRootFragment.class);
            mFragments[3] = findFragment(PersonalRootFragment.class);
        }
    }

    @Override
    protected void initListener() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView
                .OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_project_repository:
                        Bundle extra = new Bundle();
                        extra.putString(BundleKeyConstant.BUNDLE_KEY_BROWSER_URL,
                                "https://github.com/StormWyrm/DailyNews");
                        startActivity(BrowserActivity.class,extra);
                        break;
                    case R.id.nav_project_qrcode:
                        startActivity(QrcodeActivity.class);
                        break;
                    case R.id.nav_share_project:

                        break;
                    case R.id.nav_change_theme:
                        changeUiTheme();
                        break;

                    case R.id.nav_about:
                        startActivity(new Intent(mActivity, AboutActivity.class));
                        break;

                    case R.id.nav_exit:
                        MobclickAgent.onKillProcess(mActivity);
                        AppApplication.getInstance().exit();
                        break;
                }
                mDrawerLayout.closeDrawer(Gravity.START);
                return true;
            }
        });

        //支持3个以上fragment,关闭切换动画
        bottomNavigationView.enableAnimation(true);
        bottomNavigationView.enableShiftingMode(false);
        bottomNavigationView.enableItemShiftingMode(false);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bottom_menu_item_home:
                        showHideFragment(mFragments[0]);
                        break;

                    case R.id.bottom_menu_item_gankio:
                        showHideFragment(mFragments[1]);
                        break;

                    case R.id.bottom_menu_item_movie:
                        showHideFragment(mFragments[2]);
                        break;

                    case R.id.bottom_menu_item_personal:
                        showHideFragment(mFragments[3]);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void onBackPressedSupport() {
        //侧边栏关闭
        if (mDrawerLayout.isDrawerOpen(Gravity.START)) {
            mDrawerLayout.closeDrawer(Gravity.START);
            return;
        }

        //多个fragment返回栈
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            pop();
            return;
        }

        //双击推出
        long currentPressBackTime = System.currentTimeMillis();
        if (currentPressBackTime - mLastPressBackTime > TIME_OF_BACK) {
            mLastPressBackTime = currentPressBackTime;
            ToastUtil.shortMessage(mActivity, getString(R.string.main_exit_app_message));
            return;
        }
        super.onBackPressedSupport();
    }

    @Override
    public void onOpen() {
        mDrawerLayout.openDrawer(Gravity.START);
    }


    //实现夜间模式切换
    private void changeUiTheme() {
        int mode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if (mode == Configuration.UI_MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            SPUtils.put(mActivity, Constant.Config.UI_MODE_NIGHT, false);
        } else if (mode == Configuration.UI_MODE_NIGHT_NO) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            SPUtils.put(mActivity, Constant.Config.UI_MODE_NIGHT, true);
        }


        if (mDrawerLayout.isDrawerOpen(Gravity.START)) {
            mDrawerLayout.closeDrawer(Gravity.START);
        }

        reload();
    }

}
