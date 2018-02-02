package com.liqingfeng.DailyNews.main.movie.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liqingfeng.DailyNews.R;
import com.liqingfeng.DailyNews.bean.douban.movie.SubjectsBean;
import com.liqingfeng.DailyNews.util.GlideUtils;

import java.util.List;

/**
 * Created by lonlife on 2018/1/3.
 */

public class HotMovieAdapter extends BaseQuickAdapter<SubjectsBean,BaseViewHolder>{


    public HotMovieAdapter(){
        this(null);
    }

    public HotMovieAdapter(@Nullable List<SubjectsBean> data) {
        super(R.layout.item_hot_movie, data);
        openLoadAnimation(BaseQuickAdapter.SCALEIN);
        isFirstOnly(false);
    }


    @Override
    protected void convert(BaseViewHolder holder, SubjectsBean item) {
        ImageView imageView = holder.getView(R.id.iv_movie);
        GlideUtils.loadImage(mContext,imageView,item.getImages().getMedium(),R.mipmap.img_default_movie);

        holder.setText(R.id.tv_movie_name,item.getTitle());

        String directors = mContext.getResources().getString(R.string.movie_hot_movie_director);
        holder.setText(R.id.tv_movie_director,String.format(directors,item.getDirectorsString()));

        String actors = mContext.getResources().
                getString(R.string.movie_hot_movie_starring,item.getActorsString());
        holder.setText(R.id.tv_movie_starring,actors);

        String type = mContext.getResources().
                getString(R.string.movie_hot_movie_type,item.getGenresString());
        holder.setText(R.id.tv_movie_type,type);

        String score = mContext.getResources().
                getString(R.string.movie_hot_movie_score,String.valueOf(item.getRating().getAverage()));
        holder.setText(R.id.tv_movie_score,score);
    }

}
