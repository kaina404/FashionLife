package fashionlife.com.ui.home.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;

import fashionlife.com.R;
import fashionlife.com.base.component.BaseFragment;
import fashionlife.com.ui.home.adapter.HomeAdapter;
import fashionlife.com.ui.home.data.WXNewsTitleBean;
import fashionlife.com.ui.home.impl.HomeWXContract;
import fashionlife.com.ui.home.impl.HomeWXPresenter;
import xu.viewpagerflextitle.ViewPagerTitle;

/**
 * Created by lovexujh on 2017/10/9
 */

public class HomeFragment extends BaseFragment<HomeWXPresenter> implements HomeWXContract.View {


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
        mPresenter.queryWXNewsTitle();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewPagerTitle = (ViewPagerTitle) view.findViewById(R.id.view_pager_title);
        mViewPager = (ViewPager)view.findViewById(R.id.view_pager);
    }


    @Override
    public void onResume() {
        super.onResume();
//        ImageLoadHelper.getFLImageLoader().init(getActivity()).loadImage(mIV, "https://www.baidu.com/img/bd_logo1.png");
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
        if(mAdapter == null){
            mAdapter = new HomeAdapter(getFragmentManager(), beanList);
        }
        mViewPager.setAdapter(mAdapter);
        mViewPagerTitle.initData(titles, mViewPager, 0);
    }

    @Override
    public void onTitleEmpty() {

    }
}
