package com.liqingfeng.DailyNews.main.gankio.adapter;

import android.app.Application;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liqingfeng.DailyNews.R;
import com.liqingfeng.DailyNews.bean.gankio.GankIoWelfareItemBean;
import com.liqingfeng.DailyNews.common.AppApplication;
import com.liqingfeng.DailyNews.common.util.GlideUtils;

import java.util.List;

/**
 * Created by lonlife on 2018/1/11.
 */

public class GankioWelfareAdapter extends BaseQuickAdapter<GankIoWelfareItemBean, BaseViewHolder> {

    public GankioWelfareAdapter(@Nullable List<GankIoWelfareItemBean> data) {
        super(R.layout.item_gankio_welfare, data);
        init();

    }

    public GankioWelfareAdapter() {
        super(R.layout.item_gankio_welfare);
        init();
    }


    private void init() {
        setEnableLoadMore(true);
        openLoadAnimation();
    }

    @Override
    protected void convert(BaseViewHolder helper, GankIoWelfareItemBean item) {
        GlideUtils.loadImage(AppApplication.getInstance(),
                (ImageView) helper.getView(R.id.iv_item_image),
                item.getUrl(),
                R.mipmap.img_default_meizi);
    }
}
