package com.liqingfeng.DailyNews.main.movie.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liqingfeng.DailyNews.R;
import com.liqingfeng.DailyNews.bean.douban.movie.SubjectsBean;
import com.liqingfeng.DailyNews.common.AppApplication;
import com.liqingfeng.DailyNews.common.util.GlideUtils;

import java.util.List;

/**
 * Created by lonlife on 2018/1/9.
 */

public class TopMovieAdapter extends BaseQuickAdapter<SubjectsBean, BaseViewHolder> {

    public TopMovieAdapter() {
        this(null);
    }

    public TopMovieAdapter(@Nullable List<SubjectsBean> data) {
        super(R.layout.item_top_movie, data);
        openLoadAnimation(BaseQuickAdapter.SCALEIN);
        isFirstOnly(false);

        setEnableLoadMore(true);

    }

    @Override
    protected void convert(BaseViewHolder helper, SubjectsBean item) {
        helper.setText(R.id.tv_top_moive_name, item.getTitle());
        helper.setText(R.id.tv_top_moive_rate, String.valueOf(item.getRating().getAverage()));
        GlideUtils.loadImage(AppApplication.getInstance(), (ImageView) helper.getView(R.id
                .iv_top_moive_photo), item.getImages().getLarge(), R
                .mipmap.img_default_movie);

    }
}
