package com.myfashionlife.test;

import android.content.Context;

import java.util.List;

import com.myfashionlife.base.adapter.FLBaseAdapter;

/**
 * Created by lovexujh on 2017/10/12
 */

public class TestAdapter extends FLBaseAdapter<TestBean.ResultBean, TestViewHolder> {

    public TestAdapter(Context context, List<TestBean.ResultBean> beans) {
        super(context, beans);
    }

    @Override
    protected TestViewHolder getBaseViewHolder() {
        return new TestViewHolder(mContext);
    }

}
