package com.myfashionlife.ui.home.impl;

import java.util.List;

import com.myfashionlife.base.BaseView;
import com.myfashionlife.base.component.IPresenter;
import com.myfashionlife.ui.home.data.WXNewsTitleBean;

/**
 * 首页微信精选
 * Created by lovexujh on 2017/10/14
 */

public interface HomeWXContract {

    interface View extends BaseView {

        void refreshTitle(List<WXNewsTitleBean.ResultBean> beanList);

        void onTitleEmpty();
    }

    interface Presenter extends IPresenter<View> {
        void queryWXNews();

        /**
         * 查询title
         */
        void queryWXNewsTitle();
    }
}
