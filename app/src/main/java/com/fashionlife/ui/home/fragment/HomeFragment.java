package com.fashionlife.ui.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.fashionlife.R;
import com.fashionlife.base.component.BaseFragment;
import com.fashionlife.ui.home.adapter.HomeAdapter;
import com.fashionlife.ui.home.data.WXNewsTitleBean;
import com.fashionlife.ui.home.impl.HomeWXContract;
import com.fashionlife.ui.home.impl.HomeWXPresenter;
import com.fashionlife.util.Utils;
import com.fashionlife.widget.viewpagerflex.ViewPagerTitle;

import java.util.List;

/**
 * Created by lovexujh on 2017/10/9
 */

public class HomeFragment extends BaseFragment<HomeWXPresenter> implements HomeWXContract.View, SwipeRefreshLayout.OnRefreshListener {


    private ViewPagerTitle mViewPagerTitle;
    private ViewPager mViewPager;
    private HomeAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewPagerTitle = (ViewPagerTitle) view.findViewById(R.id.view_pager_title);
        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
        mPresenter.queryWXNewsTitle();
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            if (mAdapter == null || mAdapter.getCount() == 0) {
                Utils.handlerNoNet(getContext());
            }
        }
    }

    @Override
    public HomeWXPresenter attachPresenter() {
        return new HomeWXPresenter(this);
    }

    @Override
    public void refreshTitle(List<WXNewsTitleBean.ResultBean> beanList) {
        String[] titles = new String[beanList.size()];
        for (int i = 0; i < beanList.size(); i++) {
            titles[i] = beanList.get(i).getName();
        }
        if (mAdapter == null) {
            mAdapter = new HomeAdapter(getFragmentManager(), beanList);
        }
        mViewPager.setAdapter(mAdapter);
        mViewPagerTitle.initData(titles, mViewPager, 0);
    }

    @Override
    public void onTitleEmpty() {
    }

    @Override
    public void onRefresh() {
        mPresenter.queryWXNewsTitle();
    }
}
