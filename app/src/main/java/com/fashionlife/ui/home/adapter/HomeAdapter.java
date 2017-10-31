package com.fashionlife.ui.home.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fashionlife.app.APPConstant;
import com.fashionlife.ui.home.data.WXNewsTitleBean;
import com.fashionlife.ui.home.fragment.WXNewsFragment;

/**
 * Created by lovexujh on 2017/10/15
 */

public class HomeAdapter extends FragmentStatePagerAdapter {

    private List<WXNewsTitleBean.ResultBean> mBeanList;
    private int mFragmentSize;
    private Map<String, WXNewsFragment> mFragmentMap = new HashMap<>();

    public HomeAdapter(FragmentManager fm,  List<WXNewsTitleBean.ResultBean> beanList) {
        super(fm);
        mBeanList = beanList;
        mFragmentSize = mBeanList.size();
    }


    @Override
    public Fragment getItem(int position) {
        Fragment fragment = mFragmentMap.get(position);
        if (fragment == null) {
            fragment = new WXNewsFragment();
            Bundle bundle = new Bundle();
            bundle.putString(APPConstant.WX_NEWS_CID, mBeanList.get(position).getCid());
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return mFragmentSize;
    }
}
