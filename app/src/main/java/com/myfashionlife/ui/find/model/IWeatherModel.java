package com.myfashionlife.ui.find.model;

import java.util.List;

import com.myfashionlife.ui.find.data.SupportWeatherCityBean;
import com.myfashionlife.ui.find.data.WeatherBean;

/**
 * @author: lovexujh
 * @time: 2017/10/24
 * @descripition:
 */

public interface IWeatherModel {
    void updateView(List<WeatherBean.ResultBean> resultBeen);
    void updateLocation(String city, String district);

    void locationFailed();

    void initCityJsonData(SupportWeatherCityBean supportWeatherCityBean);
}
