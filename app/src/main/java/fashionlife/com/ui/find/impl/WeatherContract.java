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
        void updateFutureView(List<WeatherBean.ResultBean.FutureBean> future);

        /**
         * 更新当前的天气状况，例如：
         * 东南风 18 级
         * 中毒污染
         * @param resultBean
         */
        void updateCurDayView(WeatherBean.ResultBean resultBean);
        void onError();

        /**
         * 更新当前位置
         * @param city 城市
         * @param district 区
         */
        void updateLocationView(String city, String district);
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
