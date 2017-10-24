package fashionlife.com.ui.find.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.List;

import fashionlife.com.R;
import fashionlife.com.base.component.BaseFragment;
import fashionlife.com.ui.find.data.WeatherBean;
import fashionlife.com.ui.find.impl.WeatherContract;
import fashionlife.com.ui.find.impl.WeatherPresenter;
import fashionlife.com.util.Utils;
import fashionlife.com.widget.WeatherView;

/**
 * @author: lovexujh
 * @time: 2017/10/22
 * @descripition: 天气
 */

public class WeatherFragment extends BaseFragment<WeatherPresenter> implements WeatherContract.View {


    private WeatherView mWeatherView;

    @Override
    protected WeatherPresenter attachPresenter() {
        return new WeatherPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_weather;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mWeatherView = (WeatherView) view.findViewById(R.id.weather_view);
        mPresenter.queryWeather();
    }

    @Override
    public void updateView(List<WeatherBean.ResultBean.FutureBean> future) {
        if (!Utils.isEmpty(future) && mWeatherView != null) {
            mWeatherView.updateView(future);
        }
    }

    @Override
    public void onError() {

    }
}
