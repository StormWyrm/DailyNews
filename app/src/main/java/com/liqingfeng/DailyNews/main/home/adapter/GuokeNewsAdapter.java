package com.liqingfeng.DailyNews.main.home.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liqingfeng.DailyNews.R;
import com.liqingfeng.DailyNews.bean.guoke.GuokeNewsItemBean;
import com.liqingfeng.DailyNews.AppApplication;
import com.liqingfeng.DailyNews.util.GlideUtils;


import java.util.List;

/**
 * Created by lonlife on 2018/1/26.
 */

public class GuokeNewsAdapter extends BaseMultiItemQuickAdapter<GuokeNewsItemBean, BaseViewHolder> {

    public GuokeNewsAdapter() {
        this(null);
    }

    public GuokeNewsAdapter(@Nullable List<GuokeNewsItemBean> data) {
        super(data);
        openLoadAnimation();

        addItemType(GuokeNewsItemBean.GUOKE_NEWS_NORMAL,R.layout.item_main_recycler_text_detail_image);
        addItemType(GuokeNewsItemBean.GUOKE_NEWS_NO_IMAGE,R.layout.item_main_recycler_text_detail_no_image);
    }


    @Override
    protected void convert(BaseViewHolder helper, GuokeNewsItemBean item) {
        helper.setText(R.id.tv_title,item.getTitle());
        helper.setText(R.id.tv_content,item.getSummary());

        if(item.getItemType() == GuokeNewsItemBean.GUOKE_NEWS_NORMAL){
            GlideUtils.loadImage(AppApplication.getInstance()
                    , (ImageView) helper.getView(R.id.iv_cover), item.getHeadline_img());
        }
    }
}
