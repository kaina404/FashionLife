package fashionlife.com.ui.find.model;

import java.util.List;

import fashionlife.com.ui.find.data.WeatherBean;

/**
 * @author: lovexujh
 * @time: 2017/10/24
 * @descripition:
 */

public interface IWeatherModel {
    void updateView(List<WeatherBean.ResultBean> resultBeen);
    void updateLocation(String city, String district);
}
