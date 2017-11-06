package com.myfashionlife.ui.home.temp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.myfashionlife.R;


/**
 * Created by lovexujh on 2017/10/16
 */

public class MyViewHolder extends RecyclerView.ViewHolder {
    public View mItemView;
    public TextView mTv;
    public ImageView mIv;


    public MyViewHolder(View itemView) {
        super(itemView);
        mItemView = itemView;
        mTv = (TextView) itemView.findViewById(R.id.item_wx_news_tv_title);
        mIv = (ImageView) itemView.findViewById(R.id.item_wx_news_iv);

    }

}
