package fashionlife.com.ui.find.model;

import fashionlife.com.base.data.BaseModel;
import fashionlife.com.manager.NetManager;
import fashionlife.com.net.INetCall;
import fashionlife.com.net.NetId;
import fashionlife.com.ui.find.data.SupportWeatherCityBean;
import fashionlife.com.ui.find.data.WeatherBean;
import fashionlife.com.util.JsonHelper;
import fashionlife.com.util.LogUtil;
import fashionlife.com.util.Utils;
import fashionlife.com.util.location.LocationHelper;
import fashionlife.com.util.location.LocationListenerImpl;

/**
 * @author: lovexujh
 * @time: 2017/10/24
 * @descripition:
 */

public class WeatherModel extends BaseModel<IWeatherModel> implements INetCall, LocationListenerImpl.LocationResultListener {

    private static final int NEED_REPLACE_LENGTH = 3;
    private SupportWeatherCityBean mSupportWeatherCityBean;

    public WeatherModel(IWeatherModel iWeatherModel) {
        super(iWeatherModel);
        LocationHelper.getInstance().getLocationListener().setLocationResultListener(this);
        NetManager.queryWeatherSupportCity(this);
    }

    public void queryWeather() {
        LocationHelper.getInstance().requestLocationOnce();
    }

    @Override
    public void onResponse(int requestId, String response) {
        switch (requestId) {
            case NetId.QUERY_WEATHER:
                handlerQueryWeather(response);
                break;
            case NetId.QUERY_SUPPORT_CITY_WEATHER:
                handlerSupportCity(response);
                break;
        }
    }

    private void handlerSupportCity(String response) {
        mSupportWeatherCityBean = JsonHelper.parseObject(response, SupportWeatherCityBean.class);
    }

    private void handlerQueryWeather(String response) {
        WeatherBean weatherBean = JsonHelper.parseObject(response, WeatherBean.class);
        if (mModel != null) {
            mModel.updateView(weatherBean.getResult().get(0).getFuture());
        }
    }

    @Override
    public void onFailure(int requestId, String errObj) {

    }

    @Override
    public void onLocationResult(String city, String district, String Street) {
        LogUtil.d(this, "city = " + city + ",  district = " + district);
        if (!Utils.isEmpty(city) && !Utils.isEmpty(district)) {
            if (city.length() >= NEED_REPLACE_LENGTH) {
                //上海市-》上海
                city = city.replace("市", "");
            }
            if (district.length() >= NEED_REPLACE_LENGTH) {
                district = district.replace("市", "");
            }
            NetManager.queryWeatherTmp(this, city, district);
        }
    }

    @Override
    public void onLocationResultString(String result) {

    }
}
