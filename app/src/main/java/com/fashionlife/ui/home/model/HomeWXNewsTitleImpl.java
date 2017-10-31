package com.fashionlife.ui.home.model;

import java.util.List;

import com.fashionlife.ui.home.data.WXNewsTitleBean;

/**
 * Created by lovexujh on 2017/10/14
 */

public interface HomeWXNewsTitleImpl {

    void refreshTitle(List<WXNewsTitleBean.ResultBean> beanList);
    void onTitleEmpty();
}
