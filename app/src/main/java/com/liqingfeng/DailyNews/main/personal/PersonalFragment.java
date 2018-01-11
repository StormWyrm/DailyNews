package com.liqingfeng.DailyNews.main.personal;

import android.os.Bundle;

import com.liqingfeng.DailyNews.R;
import com.liqingfeng.DailyNews.common.ui.BaseFragment;


public class PersonalFragment extends BaseFragment {

    public PersonalFragment() {

    }

    public static PersonalFragment newInstance() {
        PersonalFragment fragment = new PersonalFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getViewId() {
        return R.layout.fragment_personal;
    }
}
