package com.liqingfeng.DailyNews.about;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.liqingfeng.DailyNews.R;
import com.liqingfeng.DailyNews.common.ui.BaseActivity;
import com.liqingfeng.DailyNews.common.util.ToastUtil;
import com.liqingfeng.DailyNews.setting.widget.SettingItemView;

import butterknife.BindView;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/4/4 23:39
 * @DESC: About界面---Activity
 * @VERSION: V1.0
 */
public class AboutActivity extends BaseActivity {
    @BindView(R.id.sv_version)
    SettingItemView svVersion;
    @BindView(R.id.sv_star)
    SettingItemView svStar;
    @BindView(R.id.sv_author)
    SettingItemView svAuthor;
    @BindView(R.id.sv_github)
    SettingItemView svGithub;
    @BindView(R.id.sv_zhihu)
    SettingItemView svZhihu;
    @BindView(R.id.sv_feedback)
    SettingItemView svFeedback;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void initView(Bundle saveInstanceState) {
        super.initView(saveInstanceState);
        addToolBar(getString(R.string.main_nav_about),true);
        getVersionName();
    }

    @Override
    protected void initListener() {
        svStar.setOnClickListener(new SettingItemView.OnClickListener() {
            @Override
            public void onClick() {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github" +
                        ".com/StormWyrm/DailyNews")));

            }
        });

        svAuthor.setOnClickListener(new SettingItemView.OnClickListener() {
            @Override
            public void onClick() {
                AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);

                builder.setTitle(R.string.about_author_dialog_title)
                        .setPositiveButton(R.string.about_author_dialog_positive, new
                                DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        ToastUtil.shortMessage(mActivity, mActivity.getString(R.string
                                                .about_author_dialog_message));
                                    }
                                })
                        .setNegativeButton(R.string.about_author_dialog_negative, new
                                DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                        ToastUtil.shortMessage(mActivity, mActivity.getString(R.string
                                                .about_author_dialog_message));
                                    }
                                }).show();
            }
        });

        svGithub.setOnClickListener(new SettingItemView.OnClickListener() {
            @Override
            public void onClick() {
                mActivity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github" +
                        ".com/StormWyrm")));
            }
        });

        svZhihu.setOnClickListener(new SettingItemView.OnClickListener() {
            @Override
            public void onClick() {
                mActivity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.zhihu" +
                        ".com/people/liqingfeng.love")));
            }
        });

        svFeedback.setOnClickListener(new SettingItemView.OnClickListener() {
            @Override
            public void onClick() {
                Intent data = new Intent(Intent.ACTION_SENDTO);
                data.setData(Uri.parse("mailto:qingfeng-love@qq.com"));
                mActivity.startActivity(data);
            }
        });

    }

    public void getVersionName() {

        String versionName = "";
        try {
            PackageManager manager = mActivity.getPackageManager();
            PackageInfo info = manager.getPackageInfo(mActivity.getPackageName(), 0);
            versionName = mActivity.getString(R.string.about_app_version) + info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            versionName = mActivity.getString(R.string.about_app_version_no_find);
        } finally {
            svVersion.setTitle(versionName);
        }

    }

}
