package com.liqingfeng.DailyNews.main.gankio.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.liqingfeng.DailyNews.R;
import com.liqingfeng.DailyNews.bean.gankio.GankIoDayItemBean;

import java.util.List;

/**
 * Created by lonlife on 2018/1/13.
 */

public class GankioDayAdapter extends BaseMultiItemQuickAdapter<GankIoDayItemBean,BaseViewHolder> {
    private int mAndroidIndex = 0;
    private int mIOSIndex = 0;

    //GankIo没有返回ImageList，这里使用固定的图片资源模拟刷新item效果
    private int[] mResAndroid = {
            R.mipmap.gank_io_day_item_android1,
            R.mipmap.gank_io_day_item_android2,
            R.mipmap.gank_io_day_item_android3,
            R.mipmap.gank_io_day_item_android4,
            R.mipmap.gank_io_day_item_android5,
            R.mipmap.gank_io_day_item_android6};

    private int[] mResIOS = {
            R.mipmap.gank_io_day_item_ios1,
            R.mipmap.gank_io_day_item_ios2,
            R.mipmap.gank_io_day_item_ios3};

    public GankioDayAdapter() {
        this(null);
    }

    public GankioDayAdapter(List<GankIoDayItemBean> data) {
        super(data);
        init();
    }

    private void init() {
        openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);

        addItemType(GankIoDayItemBean.GANK_IO_DAY_ITEM_DAY_NORMAL, R.layout.item_gankio_day_nomal);
        addItemType(GankIoDayItemBean.GANK_IO_DAY_ITEM_DAY_REFESH, R.layout.item_gankio_day_refresh);
    }

    //刷新某一项数据
    public void onRefreshItem(int position,GankIoDayItemBean item){
        if(item.getType().equals("Android")){
            mAndroidIndex ++;
        }else{
            mIOSIndex ++;
        }
        setData(position,item);
    }

    @Override
    protected void convert(BaseViewHolder helper, GankIoDayItemBean item) {
        //设置标题
        helper.setText(R.id.tv_type_item_title, item.getType());
        helper.setText(R.id.tv_item_title, item.getDesc());

        //设置内容
        switch (item.getType()) {
            case "福利":
                helper.setImageResource(R.id.iv_type_item_title, R.drawable
                        .ic_vector_title_welfare);
                Glide.with(mContext).load(item.getUrl()).crossFade().into((ImageView) helper
                        .getView(R.id.iv_item_title));
                break;
            case "Android":
                helper.setImageResource(R.id.iv_type_item_title, R.drawable
                        .ic_vector_title_android);
                helper.setImageResource(R.id.iv_item_title, mResAndroid[mAndroidIndex % 6]);
                break;
            case "iOS":
                helper.setImageResource(R.id.iv_type_item_title, R.drawable.ic_vector_title_ios);
                helper.setImageResource(R.id.iv_item_title, mResIOS[mIOSIndex % 3]);
                break;
            case "前端":
                helper.setImageResource(R.id.iv_type_item_title, R.drawable.ic_vector_title_front);
                helper.setImageResource(R.id.iv_item_title, R.mipmap.gank_io_day_item_web);
                break;
            case "休息视频":
                helper.setImageResource(R.id.iv_type_item_title, R.drawable.ic_vector_title_video);
                helper.setImageResource(R.id.iv_item_title, R.mipmap.gank_io_day_item_video);
                break;
            case "瞎推荐":
                helper.setImageResource(R.id.iv_type_item_title, R.drawable.ic_vector_item_tuijian);
                break;
            case "拓展资源":
                helper.setImageResource(R.id.iv_type_item_title, R.drawable.ic_vector_item_tuozhan);
                break;
            case "App":
                helper.setImageResource(R.id.iv_type_item_title, R.drawable.ic_vector_item_app);
                break;
        }

        //添加child点击
        switch (helper.getItemViewType()){
            case GankIoDayItemBean.GANK_IO_DAY_ITEM_DAY_NORMAL:
                helper.addOnClickListener(R.id.ll_more);
                break;
            case GankIoDayItemBean.GANK_IO_DAY_ITEM_DAY_REFESH:
                helper.addOnClickListener(R.id.ll_more).addOnClickListener(R.id.ll_refesh);
                break;
        }
    }
}
