package com.fashionlife.ui.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.fashionlife.R;
import com.fashionlife.base.adapter.RecycleBaseAdapter;
import com.fashionlife.base.impl.OnRecycleViewItemClickListener;
import com.fashionlife.base.viewholder.RecycleBaseViewHolder;
import com.fashionlife.common.ActivityId;
import com.fashionlife.common.IntentKeys;
import com.fashionlife.manager.StartManager;
import com.fashionlife.ui.home.data.WXNewsBean;
import com.fashionlife.util.ScreenUtils;

import java.util.List;
import java.util.Random;

/**
 * @author
 * Created by lovexujh on 2017/10/17
 */

public class WXNewsNewAdapter extends RecycleBaseAdapter<WXNewsBean.ResultBean.ListBean> implements OnRecycleViewItemClickListener {

    private int mImgWidth;
    private int mScreenWidth;
    private Random random = new Random();

    public WXNewsNewAdapter(Context context, List<WXNewsBean.ResultBean.ListBean> data) {
        super(context, R.layout.item_wxnews_layout, data);
        mScreenWidth = ScreenUtils.getScreenWidth();
        mImgWidth = mScreenWidth / 2;
    }

    @Override
    public void setItemData(RecycleBaseViewHolder holder, WXNewsBean.ResultBean.ListBean bean, int position) {
        holder.setOnItemClickListener(this);
        int imageHeight = bean.getImageHeight();
        // TODO: 2017/10/17  图片位移的问题待解决
        if (imageHeight <= 0) {
            imageHeight = getRandomHeight();
            bean.setImageHeight(imageHeight);
        }
        Glide.with(mContext).load(bean.getThumbnails()).placeholder(R.mipmap.loading)
                .override(mImgWidth, imageHeight).centerCrop().override(mImgWidth, imageHeight)
                .into((ImageView) holder.findViewById(R.id.item_wx_news_iv));
        holder.setText(R.id.item_wx_news_tv_title, bean.getSubTitle());

    }

    public int getRandomHeight() {
        int max = 620;
        int min = 400;
        int s = random.nextInt(max) % (max - min + 1) + min;
        return s;
    }

    @Override
    public void onItemClick(View itemView, int position) {
        WXNewsBean.ResultBean.ListBean listBean = mData.get(position);
        Intent intent = new Intent();
        intent.putExtra(IntentKeys.URL, listBean.getSourceUrl());
        StartManager.startActivity(ActivityId.WEB_VIEW_ACTIVITY, mContext, intent);
    }
}
