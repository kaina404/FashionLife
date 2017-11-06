package com.myfashionlife.base.adapter;

import android.content.Context;
import android.view.ViewGroup;

import java.util.List;

import com.myfashionlife.base.impl.MultiItemRecycle;
import com.myfashionlife.base.viewholder.RecycleBaseViewHolder;

/**
 * 多itemType
 * Created by lovexujh on 2017/10/17
 */

public abstract class MultiItemRecycleAdapter<T> extends RecycleBaseAdapter<RecycleBaseViewHolder>   {


    private MultiItemRecycle<T> mMultiItemRecycle;

    public MultiItemRecycleAdapter(Context context, int layoutId, List<RecycleBaseViewHolder> data, MultiItemRecycle<T> multiItemRecycle) {
        super(context, 0, data);
        if(multiItemRecycle == null){
            throw new IllegalArgumentException("you must be implements MultiItemRecycle !");
        }
        mMultiItemRecycle = multiItemRecycle;
    }


    @Override
    public int getItemViewType(int position) {
        return mMultiItemRecycle.getItemViewType(position, (T) mData.get(position));
    }

    @Override
    public RecycleBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return RecycleBaseViewHolder.create(mContext, parent, mMultiItemRecycle.getItemLayoutId(viewType));
    }

}
