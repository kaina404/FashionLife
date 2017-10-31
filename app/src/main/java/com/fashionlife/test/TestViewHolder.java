package com.fashionlife.test;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.fashionlife.R;
import com.fashionlife.base.viewholder.BaseViewHolder;

/**
 * Created by lovexujh on 2017/10/12
 */

public class TestViewHolder extends BaseViewHolder<TestBean.ResultBean> {

    private TextView mTextView;
    private TextView mTextView2;

    public TestViewHolder(Context context) {
        super(context);
        initView();
    }


    private void initView() {
//        mTextView = (TextView) mItemView.findViewById(R.id.textView);
//        mTextView2 = (TextView) mItemView.findViewById(R.id.textView2);
    }

    @Override
    protected int getItemViewLayoutId() {
        return R.layout.test_item_layout;
    }

    @Override
    public void setData(TestBean.ResultBean bean, int position, View convertView) {
        mTextView.setText(bean.getName());
        mTextView2.setText(bean.getCid());
    }
}
