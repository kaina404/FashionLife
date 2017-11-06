package com.myfashionlife.ui.home.impl;

import java.util.List;

import com.myfashionlife.base.component.BasePresenter;
import com.myfashionlife.ui.home.data.WXNewsTitleBean;
import com.myfashionlife.ui.home.model.HomeWXNewsModel;
import com.myfashionlife.ui.home.model.HomeWXNewsTitleImpl;

/**
 * Created by lovexujh on 2017/10/14
 */

public class HomeWXPresenter extends BasePresenter<HomeWXContract.View> implements HomeWXContract.Presenter, HomeWXNewsTitleImpl {

    private HomeWXNewsModel homeWXNewsModel;

    public HomeWXPresenter(HomeWXContract.View view) {
        super(view);
        homeWXNewsModel = new HomeWXNewsModel(this);
    }


    @Override
    public void queryWXNews() {

    }

    @Override
    public void queryWXNewsTitle() {
        homeWXNewsModel.queryTitle();
    }

    @Override
    public void refreshTitle(List<WXNewsTitleBean.ResultBean> beanList) {
        if (mView != null) {
            mView.refreshTitle(beanList);
        }
    }

    @Override
    public void onTitleEmpty() {
        if (mView != null) {
            mView.onTitleEmpty();
        }
    }
}
