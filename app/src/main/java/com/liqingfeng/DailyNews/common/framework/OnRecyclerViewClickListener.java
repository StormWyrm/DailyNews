package com.liqingfeng.DailyNews.common.framework;


import android.view.View;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/3/28 11:26
 * @DESC: RecyclerView的点击事件的监听
 * @VERSION: V1.0
 */

public abstract class OnRecyclerViewClickListener {

    /**
     * Item的点击事件
     *
     * @param itemView
     * @param position
     */
    public abstract void onItemClick(View itemView, int position);

    /**
     * Item的长按事件
     *
     * @param itemView
     * @param position
     * @return
     */
    public boolean onItemLongClick(View itemView, int position) {
        return false;
    }
}
