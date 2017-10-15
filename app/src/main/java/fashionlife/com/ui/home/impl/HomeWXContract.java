package fashionlife.com.ui.home.impl;

import java.util.List;

import fashionlife.com.base.BaseView;
import fashionlife.com.base.component.IPresenter;
import fashionlife.com.ui.home.data.WXNewsTitleBean;

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
