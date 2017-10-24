package fashionlife.com.ui.find.impl;

import java.util.List;

import fashionlife.com.base.BaseView;
import fashionlife.com.base.component.IPresenter;
import fashionlife.com.ui.find.data.WeatherBean;

/**
 * @author: lovexujh
 * @time: 2017/10/24
 * @descripition:
 */

public interface WeatherContract {

    interface View extends BaseView {
        /**
         * 刷新天气view
         * @param future
         */
        void updateView(List<WeatherBean.ResultBean.FutureBean> future);
        void onError();
    }

    interface Presenter extends IPresenter<WeatherContract.View> {
        /**
         * 查询天气
         */
        void queryWeather();
    }
}
