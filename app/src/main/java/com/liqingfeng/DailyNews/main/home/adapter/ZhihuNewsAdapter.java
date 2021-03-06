package com.liqingfeng.DailyNews.main.home.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liqingfeng.DailyNews.R;
import com.liqingfeng.DailyNews.bean.zhihu.ZhihuNewsItemBean;
import com.liqingfeng.DailyNews.AppApplication;
import com.liqingfeng.DailyNews.util.GlideUtils;


import java.util.List;

/**
 * Created by lonlife on 2018/1/26.
 */

public class ZhihuNewsAdapter extends BaseMultiItemQuickAdapter<ZhihuNewsItemBean, BaseViewHolder> {

    public ZhihuNewsAdapter() {
        super(null);
    }

    public ZhihuNewsAdapter(@Nullable List<ZhihuNewsItemBean> data) {
        super(data);
        openLoadAnimation();

        setEnableLoadMore(true);

        addItemType(ZhihuNewsItemBean.ZHIHU_NEWS_NORMAL,R.layout.item_main_recycler_text_and_image);
        addItemType(ZhihuNewsItemBean.ZHIHU_NEWS_NO_IMAGE,R.layout.item_main_recycler_only_text);
    }

    @Override
    protected void convert(BaseViewHolder helper, ZhihuNewsItemBean item) {

        helper.setText(R.id.tv_title,item.getTitle());
        if(item.getItemType() == ZhihuNewsItemBean.ZHIHU_NEWS_NORMAL){
            GlideUtils.loadImage(AppApplication.getInstance(),
                    (ImageView) helper.getView(R.id.iv_cover),
                    item.getImages().get(0));
        }

    }
}
