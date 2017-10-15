package fashionlife.com.ui.home.impl;

import fashionlife.com.base.BaseView;
import fashionlife.com.base.component.IPresenter;
import fashionlife.com.ui.home.data.WXNewsBean;

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
