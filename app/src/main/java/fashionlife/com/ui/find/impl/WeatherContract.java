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
         * 查询当前位置天气
         */
        void queryWeather();

        /**
         * 查询指定位置的天气
         * @param city 城市名（url编码）  市 合肥市
         * @param district 当前城市的所属省份 如：北京-通州、江苏-通州（url编码）
         */
        void queryWeather(String city, String district);
    }
}
