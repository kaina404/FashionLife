package fashionlife.com.ui.find.impl;

import java.util.List;

import fashionlife.com.base.component.BasePresenter;
import fashionlife.com.ui.find.data.WeatherBean;
import fashionlife.com.ui.find.model.IWeatherModel;
import fashionlife.com.ui.find.model.WeatherModel;

/**
 * @author: lovexujh
 * @time: 2017/10/24
 * @descripition:
 */

public class WeatherPresenter extends BasePresenter<WeatherContract.View> implements IWeatherModel, WeatherContract.Presenter {

    private WeatherModel mWeatherModel;

    public WeatherPresenter(WeatherContract.View view) {
        super(view);
        mWeatherModel = new WeatherModel(this);
    }

    @Override
    public void updateView(List<WeatherBean.ResultBean.FutureBean> future) {
        if (mView != null) {
            mView.updateView(future);
        }
    }

    @Override
    public void queryWeather() {
        mWeatherModel.queryWeather();
    }

    @Override
    public void queryWeather(String city, String district) {

    }
}
