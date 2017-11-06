package com.myfashionlife.ui.home.adapter;

import android.content.Context;

import java.util.List;

import com.myfashionlife.base.adapter.FLBaseAdapter;
import com.myfashionlife.ui.home.data.WXNewsBean;
import com.myfashionlife.ui.home.viewholder.WXNewsViewHolder;

/**
 * Created by lovexujh on 2017/10/15
 */

public class WXNewsAdapter extends FLBaseAdapter<WXNewsBean.ResultBean.ListBean, WXNewsViewHolder> {


    public WXNewsAdapter(Context context, List<WXNewsBean.ResultBean.ListBean> beans) {
        super(context, beans);
    }

    @Override
    protected WXNewsViewHolder getBaseViewHolder() {
        return new WXNewsViewHolder(mContext);
    }

}
