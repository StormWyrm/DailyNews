package com.liqingfeng.DailyNews.common.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.liqingfeng.DailyNews.common.framework.OnRecyclerViewClickListener;

import java.util.List;

/**
 * @AUTHER: 李青峰
 * @EMAIL: 1021690791@qq.com
 * @PHONE: 18045142956
 * @DATE: 2017/3/30 16:01
 * @DESC: 自定义封装RecyclerView的Adapter, 可以选择是否配置头尾布局
 * @VERSION: V1.0
 */

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_NORMAL = 1;
    private static final int TYPE_FOOTER = 2;

    public View headerView;
    public View footerView;

    private Context mContext;
    private List<T> mDatas;
    private OnRecyclerViewClickListener mListener;

    private int mLayoutId;


    public BaseRecyclerAdapter(Context context, List<T> datas, int layoutId) {
        this(context, datas, layoutId, null, null);
    }


    public BaseRecyclerAdapter(Context context, List<T> datas, int layoutId, View headerView, View footerView) {
        this.mContext = context;
        this.mLayoutId = layoutId;
        this.mDatas = datas;
        this.headerView = headerView;
        this.headerView = headerView;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            return new BaseViewHolder(headerView);
        }
        if (viewType == TYPE_FOOTER) {
            return new BaseViewHolder(footerView);
        }

        return new BaseViewHolder(mContext, parent, mLayoutId);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        if (getItemViewType(position) == TYPE_NORMAL) {
            if (headerView != null) {
                convert(holder, mDatas.get(position - 1));
                initListener(holder, position - 1);
            } else {
                convert(holder, mDatas.get(position));
                initListener(holder, position);
            }
        }

    }

    //根据头尾布局的情况获得item的数量
    @Override
    public int getItemCount() {
        if (mDatas != null && mDatas.size() != 0) {
            int size = mDatas.size();

            if (headerView != null) {
                size++;
            }
            if (footerView != null) {
                size++;
            }
            return size;

        } else {
            return 0;
        }
    }

    //根据位置设置Item的ViewType
    @Override
    public int getItemViewType(int position) {
        if (headerView != null) {
            if (position == 0) {
                return TYPE_HEADER;
            }
        }
        if (footerView != null) {
            if (position == getItemCount() - 1) {
                return TYPE_FOOTER;
            }
        }

        return TYPE_NORMAL;

    }

    //配置点击事件
    private void initListener(final BaseViewHolder holder, final int position) {
        if (mListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClick(holder.itemView, position);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return mListener.onItemLongClick(holder.itemView, position);
                }
            });
        }
    }

    /**
     * 更新每一个ItemView的数据
     *
     * @param holder ViewHolder
     * @param t      当前位置数据
     */
    public abstract void convert(BaseViewHolder holder, T t);

    /**
     * 给ItemView设置监听器
     *
     * @param listener
     */
    public void setOnRecyclerViewClickListener(OnRecyclerViewClickListener listener) {
        this.mListener = listener;
    }


}

