package com.fashionlife.ui.home.impl;

import com.fashionlife.base.BaseView;
import com.fashionlife.base.component.IPresenter;
import com.fashionlife.ui.home.data.WXNewsBean;

/**
 * Created by lovexujh on 2017/10/15
 */

public interface WXNewsContract {
    interface View extends BaseView{
        void onError(String msg);

        void onRefresh(WXNewsBean.ResultBean result);
    }
    interface Presenter extends IPresenter<WXNewsContract.View>{
        void queryNews();
    }
}
