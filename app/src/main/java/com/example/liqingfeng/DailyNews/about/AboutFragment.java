package com.example.liqingfeng.DailyNews.about;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.liqingfeng.DailyNews.R;
import com.example.liqingfeng.DailyNews.common.ui.BaseFragment;
import com.example.liqingfeng.DailyNews.setting.widget.SettingItemView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/4/25 10:57
 * @DESC: About界面---Fragment
 * @VERSION: V1.0
 */
public class AboutFragment extends BaseFragment implements AboutContract.View {
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

    private AboutContract.Presenter mPresenter;

    @Override
    public int getViewId() {
        return R.layout.fragment_about;
    }

    @Override
    protected void initData() {
        //设置版本号
        mPresenter.getVersionName();
    }

    @Override
    protected void initListener() {
        svStar.setOnClickListener(new SettingItemView.OnClickListener() {
            @Override
            public void onClick() {
                mPresenter.comment();

            }
        });

        svAuthor.setOnClickListener(new SettingItemView.OnClickListener() {
            @Override
            public void onClick() {
                mPresenter.aboutAuthor();
            }
        });

        svGithub.setOnClickListener(new SettingItemView.OnClickListener() {
            @Override
            public void onClick() {
                mPresenter.followMyGithub();
            }
        });

        svZhihu.setOnClickListener(new SettingItemView.OnClickListener() {
            @Override
            public void onClick() {
                mPresenter.followMyZhihu();
            }
        });

        svFeedback.setOnClickListener(new SettingItemView.OnClickListener() {
            @Override
            public void onClick() {
                mPresenter.feedback();
            }
        });

    }


    @Override
    public void setPresenter(AboutContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void setCurVersion(String versionName) {
        svVersion.setTitle(versionName);
    }

}
