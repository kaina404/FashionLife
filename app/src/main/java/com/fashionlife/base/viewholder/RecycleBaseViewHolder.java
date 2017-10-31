package com.fashionlife.base.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fashionlife.base.impl.OnRecycleViewItemClickListener;

/**
 * Created by lovexujh on 2017/10/17
 */

public class RecycleBaseViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mViews;//用来缓存itemView中的控件
    protected Context mContext;
    private View mItemView;
    protected ViewGroup mViewGroup;
    private int mPosition;
    private OnRecycleViewItemClickListener mOnItemClickListener;

    public RecycleBaseViewHolder(Context context, View itemView, ViewGroup viewGroup) {
        super(itemView);
        this.mContext = context;
        this.mItemView = itemView;
        this.mViewGroup = viewGroup;
        this.mViews = new SparseArray<>();
    }

    public static RecycleBaseViewHolder create(Context context, ViewGroup parent, int layoutId) {
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        RecycleBaseViewHolder holder = new RecycleBaseViewHolder(context, itemView, parent);
        return holder;
    }


    public <T extends View> T findViewById(int viewId) {

        View view = mViews.get(viewId);

        if (view == null) {
            view = mItemView.findViewById(viewId);
            mViews.put(viewId, view);
        }

        return (T) view;
    }

    public RecycleBaseViewHolder setText(int viewId, String text) {
        TextView tv = findViewById(viewId);
        tv.setText(text);
        return this;
    }

    public RecycleBaseViewHolder setImageResource(int viewId, int resId) {
        ImageView view = findViewById(viewId);
        view.setImageResource(resId);
        return this;
    }

    public RecycleBaseViewHolder setPosition(int position) {
        mPosition = position;
        return this;
    }

    public RecycleBaseViewHolder setOnClickListener(View.OnClickListener listener, int viewId) {
        View view = findViewById(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    public RecycleBaseViewHolder setOnItemClickListener(OnRecycleViewItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
        if (mOnItemClickListener != null) {
            mItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(mItemView, mPosition);
                    }
                }
            });
        }
        return this;
    }


}
