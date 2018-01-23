package com.liqingfeng.DailyNews.main;


import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatDelegate;
import android.view.Gravity;
import android.view.MenuItem;

import com.liqingfeng.DailyNews.R;
import com.liqingfeng.DailyNews.about.AboutActivity;
import com.liqingfeng.DailyNews.common.AppApplication;
import com.liqingfeng.DailyNews.common.constant.Constant;
import com.liqingfeng.DailyNews.common.ui.BaseActivity;
import com.liqingfeng.DailyNews.common.ui.BaseFragmentAdapter;
import com.liqingfeng.DailyNews.common.util.BottomNavigationViewHelper;
import com.liqingfeng.DailyNews.common.util.SPUtils;
import com.liqingfeng.DailyNews.common.util.ToastUtil;
import com.liqingfeng.DailyNews.hot.ZHHotActivity;
import com.liqingfeng.DailyNews.main.gankio.GankioFragment;
import com.liqingfeng.DailyNews.main.home.HomeFragment;
import com.liqingfeng.DailyNews.main.movie.hot.HotMovieFragment;
import com.liqingfeng.DailyNews.main.personal.PersonalFragment;
import com.liqingfeng.DailyNews.setting.SettingActivity;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/3/21 7:47
 * @DESC: 主页面模块 Activity
 * @VERSION: V1.0
 */
public class MainActivity extends BaseActivity implements HomeFragment.OnDrawerLayoutOpenListener {
    @BindView(R.id.vp_main)
    ViewPager vpMain;
    @BindView(R.id.bniv_bar)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;
    @BindView(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;

    private List<Fragment> mFragments = new ArrayList<>(4);

    private long mLastPressBackTime;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData(Bundle saveInstanceState) {
        super.initData(saveInstanceState);
        if (saveInstanceState == null) {
            mFragments.add(0, HomeFragment.newInstance());
            mFragments.add(1, GankioFragment.newInstance());
            mFragments.add(2, HotMovieFragment.newInstance());
            mFragments.add(3, PersonalFragment.newInstance());
        } else {
            //这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题
            // 这里我们需要拿到mFragments的引用,也可以通过getSupportFragmentManager.getFragments()
            // 自行进行判断查找(效率更高些),用下面的方法查找更方便些
            mFragments.add(0, findFragment(HomeFragment.class));
            mFragments.add(1, findFragment(GankioFragment.class));
            mFragments.add(2, findFragment(HotMovieFragment.class));
            mFragments.add(3, findFragment(PersonalFragment.class));
        }
    }

    @Override
    protected void initView(Bundle saveInstanceState) {
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setSelected(true);
        vpMain.setAdapter(new BaseFragmentAdapter(getSupportFragmentManager(), mFragments));
    }


    @Override
    protected void initListener() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView
                .OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_zh_hot:
                        startActivity(new Intent(mActivity, ZHHotActivity.class));
                        break;
                    case R.id.nav_change_theme:
                        changeUiTheme();
                        break;
                    case R.id.nav_setting:
                        startActivity(new Intent(mActivity, SettingActivity.class));
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

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bottom_menu_item_home:
                        vpMain.setCurrentItem(0);
                        break;
                    case R.id.bottom_menu_item_gankio:
                        vpMain.setCurrentItem(1);
                        break;
                    case R.id.bottom_menu_item_movie:
                        vpMain.setCurrentItem(2);
                        break;
                    case R.id.bottom_menu_item_personal:
                        vpMain.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void onBackPressedSupport() {
        if (mDrawerLayout.isDrawerOpen(Gravity.START)) {
            mDrawerLayout.closeDrawer(Gravity.START);
            return;
        }
        long currentPressBackTime = System.currentTimeMillis();
        if (currentPressBackTime - mLastPressBackTime > 1500) {
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
        recreate();
        if (mDrawerLayout.isDrawerOpen(Gravity.START)) {
            mDrawerLayout.closeDrawer(Gravity.START);
            return;
        }
    }

    //初始化Drawlayout
    private void initDrawerLayout() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(mActivity, mDrawerLayout,
                mToolBar, 0, 0);
        toggle.syncState();
        mDrawerLayout.addDrawerListener(toggle);
    }


}
