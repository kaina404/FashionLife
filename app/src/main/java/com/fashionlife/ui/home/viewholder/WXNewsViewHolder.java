package com.fashionlife.ui.home.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fashionlife.R;
import com.fashionlife.app.AppUtils;
import com.fashionlife.base.viewholder.BaseViewHolder;
import com.fashionlife.ui.home.data.WXNewsBean;
import com.fashionlife.util.Utils;
import com.fashionlife.util.image.ImageLoadHelper;

/**
 * Created by lovexujh on 2017/10/15
 */

public class WXNewsViewHolder extends BaseViewHolder<WXNewsBean.ResultBean.ListBean> {
    private TextView mTvTitle;
    private ImageView mIv;

    public WXNewsViewHolder(Context context) {
        super(context);
    }

    @Override
    protected void initView(View itemView) {
        mTvTitle = (TextView) findViewById(R.id.item_wx_news_tv_title);
        mIv = (ImageView) findViewById(R.id.item_wx_news_iv);
    }

    @Override
    protected int getItemViewLayoutId() {
        return R.layout.item_wx_news_layout;
    }

    @Override
    public void setData(WXNewsBean.ResultBean.ListBean bean, int position, View convertView) {
        mTvTitle.setText(bean.getTitle());
        String url = AppUtils.getMobWXImgURL(bean.getThumbnails());
        if(Utils.isEmpty(url)){
            mIv.setVisibility(View.GONE);
        }else {
            mIv.setVisibility(View.VISIBLE);
            ImageLoadHelper.getFLImageLoader().init(mContext).loadImage(mIv, url);
        }

    }
}
