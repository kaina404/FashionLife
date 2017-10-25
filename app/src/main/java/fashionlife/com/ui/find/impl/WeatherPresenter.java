package fashionlife.com.ui.find.impl;

import java.util.List;

import fashionlife.com.base.component.BasePresenter;
import fashionlife.com.ui.find.data.WeatherBean;
import fashionlife.com.ui.find.model.IWeatherModel;
import fashionlife.com.ui.find.model.WeatherModel;
import fashionlife.com.util.Utils;

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
    public void updateView(List<WeatherBean.ResultBean> resultBeen) {
        if (mView != null && !Utils.isEmpty(resultBeen)) {
            //解决MOBAPI坑人的当前不返还白天天气，只能从外围取出来
            WeatherBean.ResultBean resultBean = resultBeen.get(0);
            mView.updateCurDayView(resultBean);

            String weather = resultBean.getWeather();
            //哎，无奈的格式化
            String temperature = resultBean.getTemperature().replace("°", "").replace("C", "").replace("℃", "").trim();
            List<WeatherBean.ResultBean.FutureBean> future = resultBean.getFuture();
            String temperature1 = future.get(0).getTemperature();
            future.get(0).setDayTime(weather);
            future.get(0).setTemperature(temperature + "/" + temperature1);
            mView.updateFutureView(future);
        }
    }

    @Override
    public void updateLocation(String city, String district) {
        if(mView != null){
            mView.updateLocationView(city, district);
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
