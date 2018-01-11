package com.liqingfeng.DailyNews.common.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/3/29 10:31
 * @DESC: ViewHolder的封装类
 * @VERSION: V1.0
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;

    public BaseViewHolder(Context context, ViewGroup parent, int layoutId) {
        super(LayoutInflater.from(context).inflate(layoutId, parent,
                false));
        this.mViews = new SparseArray<View>();
    }

    public BaseViewHolder(View itemView) {
        super(itemView);
        this.mViews = new SparseArray<View>();
    }

    /**
     * 通过viewId获取控件
     *
     * @param viewId View控件id
     * @return View控件
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

}
