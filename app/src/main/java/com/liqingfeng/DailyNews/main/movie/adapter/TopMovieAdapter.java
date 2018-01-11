package com.liqingfeng.DailyNews.main.movie.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liqingfeng.DailyNews.R;
import com.liqingfeng.DailyNews.bean.douban.movie.SubjectsBean;

import java.util.List;

/**
 * Created by lonlife on 2018/1/9.
 */

public class TopMovieAdapter extends BaseQuickAdapter<SubjectsBean,BaseViewHolder> {

    public TopMovieAdapter() {
        super(R.layout.item_top_movie);
    }

    public TopMovieAdapter(@Nullable List<SubjectsBean> data) {
        super(R.layout.item_top_movie, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SubjectsBean item) {

    }
}
