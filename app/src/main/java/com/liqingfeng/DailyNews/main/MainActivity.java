package com.liqingfeng.DailyNews.main;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatDelegate;
import android.view.Gravity;
import android.view.MenuItem;

import com.liqingfeng.DailyNews.R;
import com.liqingfeng.DailyNews.about.AboutActivity;
import com.liqingfeng.DailyNews.common.AppApplication;
import com.liqingfeng.DailyNews.common.ui.BaseActivity;
import com.liqingfeng.DailyNews.common.ui.BaseFragment;
import com.liqingfeng.DailyNews.common.util.BottomNavigationViewHelper;
import com.liqingfeng.DailyNews.common.constant.Constant;
import com.liqingfeng.DailyNews.common.util.SPUtils;
import com.liqingfeng.DailyNews.common.util.ToastUtil;
import com.liqingfeng.DailyNews.main.gankio.GankioFragment;
import com.liqingfeng.DailyNews.main.home.HomeFragment;
import com.liqingfeng.DailyNews.hot.ZHHotActivity;
import com.liqingfeng.DailyNews.main.movie.hot.HotMovieFragment;
import com.liqingfeng.DailyNews.main.personal.PersonalFragment;
import com.liqingfeng.DailyNews.setting.SettingActivity;
import com.umeng.analytics.MobclickAgent;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/3/21 7:47
 * @DESC: 主页面模块 Activity
 * @VERSION: V1.0
 */
public class MainActivity extends BaseActivity implements HomeFragment.OnDrawerLayoutOpenListener {
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private BottomNavigationView bottomNavigationView;
    private BaseFragment[] mFragments = new BaseFragment[4];

    private long mLastPressBackTime;

    @Override
    protected int getViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle saveInstanceState) {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mDrawerLayout.setDrawerElevation(0.6f);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bniv_bar);
        if(saveInstanceState == null){
            mFragments[0] = HomeFragment.newInstance();
            mFragments[1] = GankioFragment.newInstance();
            mFragments[2] = HotMovieFragment.newInstance();
            mFragments[3] = PersonalFragment.newInstance();
        }else{
            mFragments[0] = (BaseFragment) getSupportFragmentManager()
                    .findFragmentByTag(HomeFragment.class.getSimpleName());
            mFragments[1] = (BaseFragment) getSupportFragmentManager()
                    .findFragmentByTag(GankioFragment.class.getSimpleName());
            mFragments[2] = (BaseFragment) getSupportFragmentManager()
                    .findFragmentByTag(HotMovieFragment.class.getSimpleName());
            mFragments[3] = (BaseFragment) getSupportFragmentManager()
                    .findFragmentByTag(PersonalFragment.class.getSimpleName());
        }
    }

    //初始化Drawlayout
    private void initDrawerLayout() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(mActivity, mDrawerLayout,
                mToolBar, 0, 0);
        toggle.syncState();
        mDrawerLayout.addDrawerListener(toggle);
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
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setSelectedItemId(0);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_container,mFragments[0],mFragments[0].getClass().getSimpleName())
                .commit();
        bottomNavigationView.setSelected(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bottom_menu_item_home:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fl_container,mFragments[0],mFragments[0].getClass().getSimpleName())
                                .commit();
                        break;
                    case R.id.bottom_menu_item_gankio:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fl_container,mFragments[1],mFragments[1].getClass().getSimpleName())
                                .commit();
                        break;
                    case R.id.bottom_menu_item_movie:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fl_container,mFragments[2],mFragments[2].getClass().getSimpleName())
                                .commit();
                        break;
                    case R.id.bottom_menu_item_personal:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fl_container,mFragments[3],mFragments[3].getClass().getSimpleName())
                                .commit();
                        break;
                }
                return true;
            }
        });
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

    //双击退出应用
    @Override
    public void onBackPressed() {
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
        super.onBackPressed();
    }

    @Override
    public void onOpen() {
        mDrawerLayout.openDrawer(Gravity.START);
    }
}
