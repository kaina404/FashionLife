package com.fashionlife.base.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.fashionlife.base.data.BaseBean;
import com.fashionlife.base.viewholder.BaseViewHolder;

import java.util.List;

/**
 * Created by lovexujh on 2017/10/12
 */

public abstract class FLBaseAdapter<T extends BaseBean,V extends BaseViewHolder<T>> extends BaseAdapter {

    private List<T> mBeans;
    protected Context mContext;

    public FLBaseAdapter(Context context, List<T> beans) {
        this.mContext = context;
        this.mBeans = beans;
    }

    @Override
    public int getCount() {
        if(mBeans == null){
            return 0;
        }else {
            return mBeans.size();
        }
    }

    @Override
    public T getItem(int position) {
        return mBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        V baseViewHolder;
        if (convertView == null) {
            baseViewHolder = getBaseViewHolder();
            convertView = baseViewHolder.getItemView();
            convertView.setTag(baseViewHolder);
        } else {
            baseViewHolder = (V) convertView.getTag();
        }
        baseViewHolder.setData(getItem(position), position, convertView);
        return convertView;
    }

    protected abstract V getBaseViewHolder();

//    public void setItemData(List<T> beans) {
//        if(this.mBeans == null){
//            this.mBeans = new ArrayList<>();
//        }
////        this.mBeans.clear();
////        this.mBeans.addAll(beans);
//        notifyDataSetChanged();
//    }
}
