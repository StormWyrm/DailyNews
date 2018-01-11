package com.liqingfeng.DailyNews.about;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AlertDialog;

import com.liqingfeng.DailyNews.R;
import com.liqingfeng.DailyNews.common.ui.BaseActivity;
import com.liqingfeng.DailyNews.common.util.ToastUtil;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/4/25 11:08
 * @DESC: About界面---Mvp Presenter实现
 * @VERSION: V1.0
 */

public class AboutPresenter implements AboutContract.Presenter {
    private BaseActivity mActivity;
    private AboutContract.View mAboutView;

    public AboutPresenter(BaseActivity context, AboutContract.View view) {
        this.mActivity = context;
        this.mAboutView = view;
        mAboutView.setPresenter(this);
    }

    @Override
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
            mAboutView.setCurVersion(versionName);
        }

    }

    @Override
    public void comment() {
        mActivity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github" +
                ".com/StormWyrm/DailyNews")));
    }

    @Override
    public void aboutAuthor() {
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

    @Override
    public void followMyGithub() {
        mActivity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github" +
                ".com/StormWyrm")));
    }

    @Override
    public void followMyZhihu() {
        mActivity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.zhihu" +
                ".com/people/liqingfeng.love")));
    }

    @Override
    public void feedback() {
        Intent data = new Intent(Intent.ACTION_SENDTO);
        data.setData(Uri.parse("mailto:qingfeng-love@qq.com"));
        mActivity.startActivity(data);
    }
}
