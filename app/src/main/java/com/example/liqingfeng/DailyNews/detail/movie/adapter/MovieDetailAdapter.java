package com.example.liqingfeng.DailyNews.detail.movie.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.TextureView;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.liqingfeng.DailyNews.R;
import com.example.liqingfeng.DailyNews.bean.douban.movie.PersonBean;
import com.example.liqingfeng.DailyNews.common.AppApplication;
import com.example.liqingfeng.DailyNews.common.util.GlideUtils;

import java.util.List;

/**
 * Created by lonlife on 2018/1/9.
 */

public class MovieDetailAdapter extends BaseQuickAdapter<PersonBean, BaseViewHolder> {

    public MovieDetailAdapter(@Nullable List<PersonBean> data) {
        super(R.layout.item_movie_detail, data);
    }

    public MovieDetailAdapter() {
        super(R.layout.item_movie_detail);
    }

    @Override
    protected void convert(BaseViewHolder helper, PersonBean item) {
        helper.setText(R.id.tv_person_name, item.getName());

        if(TextUtils.isEmpty(item.getType())){
            if(getParentPosition(item) == 1){
                helper.setText(R.id.tv_person_type, "导演");
            }else{
                helper.setText(R.id.tv_person_type, "演员");
            }
        }else{
            helper.setText(R.id.tv_person_type, item.getType());
        }

        GlideUtils.loadImage(AppApplication.getInstance(),
                (ImageView) helper.getView(R.id.iv_person_photo)
                , item.getAvatars().getLarge());
    }
}
