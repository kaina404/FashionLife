package fashionlife.com.ui.find.model;

import fashionlife.com.base.data.BaseModel;
import fashionlife.com.manager.NetManager;
import fashionlife.com.net.INetCall;
import fashionlife.com.ui.find.data.WeatherBean;
import fashionlife.com.util.JsonHelper;

/**
 * @author: lovexujh
 * @time: 2017/10/24
 * @descripition:
 */

public class WeatherModel extends BaseModel<IWeatherModel> implements INetCall {

    public WeatherModel(IWeatherModel iWeatherModel) {
        super(iWeatherModel);
    }

    public void queryWeather() {
        NetManager.queryWeatherTmp(this);
    }

    @Override
    public void onResponse(int requestId, String response) {
        WeatherBean weatherBean = JsonHelper.parseObject(response, WeatherBean.class);
        if (mModel != null) {
            mModel.updateView(weatherBean.getResult().get(0).getFuture());
        }
    }

    @Override
    public void onFailure(int requestId, String errObj) {

    }
}
