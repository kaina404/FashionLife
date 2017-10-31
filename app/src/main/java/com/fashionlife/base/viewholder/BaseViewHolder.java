package com.fashionlife.base.viewholder;

import android.content.Context;
import android.view.View;

import com.fashionlife.base.data.BaseBean;

/**
 * Created by lovexujh on 2017/10/12
 */

public abstract class BaseViewHolder<T extends BaseBean> {

    protected Context mContext;
    protected View mItemView;

    public BaseViewHolder(Context context) {
        this.mContext = context;
        mItemView = View.inflate(mContext, getItemViewLayoutId(), null);
        if(mItemView == null){
            throw new IllegalArgumentException("error layout id");
        }
        initView(mItemView);
    }

    protected void initView(View itemView) {

    }

    public View findViewById(int id){
        return mItemView.findViewById(id);
    }


    public View getItemView() {
        return mItemView;
    }

    protected abstract int getItemViewLayoutId();

    public abstract void setData(T bean, int position, View convertView);
}
