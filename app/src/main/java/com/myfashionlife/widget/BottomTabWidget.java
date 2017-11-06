package com.myfashionlife.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.myfashionlife.R;
import com.myfashionlife.common.BottomTabEntity;
import com.myfashionlife.util.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lovexujh
 * @time: 2017/10/27
 * @descripition:
 */

public class BottomTabWidget extends LinearLayout implements View.OnClickListener {


    private List<BottomTabEntity> mBottomTabEntities;
    private OnCheckedListener mOnCheckedListener;
    private List<BottomBean> mBottomBeans;
    private int mLastIndex = -1;

    public BottomTabWidget(Context context) {
        this(context, null);
    }

    public BottomTabWidget(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomTabWidget(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mBottomTabEntities = new ArrayList<>();
        setOrientation(HORIZONTAL);
    }

    public void updateView(List<BottomTabEntity> bottomTabEntities) {
        this.mBottomTabEntities = bottomTabEntities;
        mBottomBeans = new ArrayList<>();
        removeAllViews();
        LayoutParams lp = new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1);
        for (int i = 0; i < mBottomTabEntities.size(); i++) {
            final View itemView = View.inflate(getContext(), R.layout.bottom_item_layout, null);
            ImageView ivChecked = (ImageView) itemView.findViewById(R.id.iv_checked);
            ImageView ivNoCheck = (ImageView) itemView.findViewById(R.id.iv_no_check);
            TextView textView = (TextView) itemView.findViewById(R.id.tv);

            BottomTabEntity entity = mBottomTabEntities.get(i);

            if (Utils.isEmpty(entity.getCheckedIconUrl())) {
                ivChecked.setImageResource(entity.getCheckedIconRes());
            } else {
                Glide.with(getContext()).load(entity.getCheckedIconUrl()).into(ivChecked).
                        onLoadFailed(null, getResources().getDrawable(entity.getCheckedIconRes()));
            }


            if (Utils.isEmpty(entity.getNoCheckIconUrl())) {
                ivNoCheck.setImageResource(entity.getNoCheckIconRes());
            } else {
                Glide.with(getContext()).load(entity.getCheckedIconUrl()).into(ivNoCheck).
                        onLoadFailed(null, getResources().getDrawable(entity.getNoCheckIconRes()));
            }


            textView.setText(entity.getText());
            textView.setTextColor(entity.getNoCheckTxtColor());
            itemView.setTag(i);

            itemView.setOnClickListener(this);
            addView(itemView, lp);
            mBottomBeans.add(new BottomBean(itemView, ivChecked, ivNoCheck, textView));

        }
    }


    public void setOnCheckedListener(OnCheckedListener onCheckedListener) {
        mOnCheckedListener = onCheckedListener;
    }


    @Override
    public void onClick(View v) {
        Object tag = v.getTag();
        if (tag instanceof Integer) {
            int index = (int) tag;
            setCheck(index, true);

        }
    }

    public void setCheck(int index, boolean needListener) {
        if (index == mLastIndex || index >= mBottomBeans.size() || index >= mBottomTabEntities.size()) {
            return;
        }
        for (int i = 0; i < mBottomBeans.size(); i++) {
            BottomBean bottomBean = mBottomBeans.get(i);
            if (index == i) {
                bottomBean.ivChecked.setVisibility(VISIBLE);
                bottomBean.ivNoCheck.setVisibility(GONE);
                bottomBean.textView.setTextColor(mBottomTabEntities.get(i).getCheckedTxtColor());
            } else {
                bottomBean.ivChecked.setVisibility(GONE);
                bottomBean.ivNoCheck.setVisibility(VISIBLE);
                bottomBean.textView.setTextColor(mBottomTabEntities.get(i).getNoCheckTxtColor());
            }
        }
        mLastIndex = index;
        if (needListener) {
            if (mOnCheckedListener != null) {
                mOnCheckedListener.onChecked(index);
            }
        }
    }


    public interface OnCheckedListener {
        void onChecked(int index);
    }


    private class BottomBean {
        public View itemView;
        public ImageView ivChecked;
        public ImageView ivNoCheck;
        public TextView textView;

        public BottomBean(View itemView, ImageView ivChecked, ImageView ivNoCheck, TextView textView) {
            this.itemView = itemView;
            this.ivChecked = ivChecked;
            this.ivNoCheck = ivNoCheck;
            this.textView = textView;
        }
    }


}
