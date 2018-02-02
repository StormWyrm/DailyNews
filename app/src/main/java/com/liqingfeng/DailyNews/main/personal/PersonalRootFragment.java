package com.liqingfeng.DailyNews.main.personal;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.liqingfeng.DailyNews.R;
import com.liqingfeng.DailyNews.main.personal.tab.PersonalFragment;
import com.liqingfeng.sdk.base.fragment.BaseFragment;

/**
 * Created by lonlife on 2018/1/25.
 */

public class PersonalRootFragment extends BaseFragment {

    public static PersonalRootFragment newInstance() {
        Bundle args = new Bundle();

        PersonalRootFragment fragment = new PersonalRootFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_container;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

        //懒加载
        if(findChildFragment(PersonalFragment.class) == null){
            loadRootFragment(R.id.fl_container,PersonalFragment.newInstance());
        }
    }
}
