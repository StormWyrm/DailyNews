package com.liqingfeng.DailyNews.main.home.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liqingfeng.DailyNews.R;
import com.liqingfeng.DailyNews.bean.douban.news.DoubanNewsItemBean;
import com.liqingfeng.DailyNews.bean.guoke.GuokeNewsItemBean;
import com.liqingfeng.DailyNews.common.AppApplication;
import com.liqingfeng.DailyNews.common.util.NetworkImageUtil;

import java.util.List;

/**
 * Created by lonlife on 2018/1/26.
 */

public class DoubanNewsAdapter extends BaseMultiItemQuickAdapter<DoubanNewsItemBean, BaseViewHolder> {

    public DoubanNewsAdapter() {
        super(null);
    }

    public DoubanNewsAdapter(List<DoubanNewsItemBean> data) {
        super(data);

        openLoadAnimation();

        setEnableLoadMore(true);

        addItemType(DoubanNewsItemBean.DOUBAN_NEW_NOMAL,R.layout.item_main_recycler_text_detail_image);
        addItemType(DoubanNewsItemBean.DOUBAN_NEW_NO_IMAGE,R.layout.item_main_recycler_text_detail_no_image);
    }

    @Override
    protected void convert(BaseViewHolder helper, DoubanNewsItemBean item) {
        helper.setText(R.id.tv_title,item.getTitle());
        helper.setText(R.id.tv_content,item.getAbstractX());

        if(item.getItemType() == GuokeNewsItemBean.GUOKE_NEWS_NORMAL){
            NetworkImageUtil.loadNetImageDependNet(AppApplication.getInstance()
                    , (ImageView) helper.getView(R.id.iv_cover), item.getShare_pic_url());
        }
    }
}
