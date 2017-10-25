package fashionlife.com.ui.find.model;

import java.util.List;

import fashionlife.com.base.data.BaseModel;
import fashionlife.com.common.SpConstant;
import fashionlife.com.common.ThreadHelper;
import fashionlife.com.manager.NetManager;
import fashionlife.com.net.INetCall;
import fashionlife.com.net.NetId;
import fashionlife.com.ui.find.data.SupportWeatherCityBean;
import fashionlife.com.ui.find.data.WeatherBean;
import fashionlife.com.util.JsonHelper;
import fashionlife.com.util.LogUtil;
import fashionlife.com.util.SpUtils;
import fashionlife.com.util.Utils;
import fashionlife.com.util.location.LocationHelper;
import fashionlife.com.util.location.LocationListenerImpl;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author: lovexujh
 * @time: 2017/10/24
 * @descripition:
 */

public class WeatherModel extends BaseModel<IWeatherModel> implements INetCall, LocationListenerImpl.LocationResultListener {

    private static final int NEED_REPLACE_LENGTH = 3;
    private ThreadHelper mThreadHepler;
    private SupportWeatherCityBean mSupportWeatherCityBean;
    private boolean mNeedQuery = true;
    private ObservableEmitter<SupportWeatherCityBean> mSupportWeatherCityBeanObservableEmitter;
    private ObservableEmitter<CityEntity> mCityEntityObservableEmitter;

    public WeatherModel(IWeatherModel iWeatherModel) {
        super(iWeatherModel);
        LocationHelper.getInstance().getLocationListener().setLocationResultListener(this);
        initRxData();
        mThreadHepler = new ThreadHelper();
    }

    private void initRxData() {
        Observable<SupportWeatherCityBean> weatherCityBeanObservable = Observable.create(new ObservableOnSubscribe<SupportWeatherCityBean>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<SupportWeatherCityBean> supportWeatherCityBeanObservableEmitter) throws Exception {
                WeatherModel.this.mSupportWeatherCityBeanObservableEmitter = supportWeatherCityBeanObservableEmitter;
            }
        });

        Observable<CityEntity> cityEntityObservable = Observable.create(new ObservableOnSubscribe<CityEntity>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<CityEntity> weatherBeanObservableEmitter) throws Exception {
                WeatherModel.this.mCityEntityObservableEmitter = weatherBeanObservableEmitter;
            }
        });

        Observable.zip(weatherCityBeanObservable, cityEntityObservable,
                new BiFunction<SupportWeatherCityBean, CityEntity, CityEntity>() {
                    @Override
                    public CityEntity apply(
                            @NonNull SupportWeatherCityBean supportWeatherCityBean,
                            @NonNull CityEntity entity) throws Exception {
                        if (entity != null && supportWeatherCityBean != null) {
                            boolean needBreak = false;

                            List<SupportWeatherCityBean.ResultBean> result = supportWeatherCityBean.getResult();
                            for (int i = 0; i < result.size(); i++) {
                                SupportWeatherCityBean.ResultBean resultBean = result.get(i);
                                //省级名称
                                String province = resultBean.getProvince();

                                List<SupportWeatherCityBean.ResultBean.CityBean> cityBeans = resultBean.getCity();
                                for (int j = 0; j < cityBeans.size(); j++) {
                                    SupportWeatherCityBean.ResultBean.CityBean cityBean = cityBeans.get(j);
                                    //市级名称
                                    String city = cityBean.getCity();

                                    // TODO: 2017/10/25  由于MOBAPI的入参数据格式较百度地图返回的位置比较十分不准确，故这里不再精确到县，
                                    // 只是确定下MOBAPI中是否支持该城市。
                                    //其实向这样的无用北鼻无奈的选择，我是拒绝的。
                                    if (entity.city.contains(province) || entity.city.contains(city)) {
                                        //说明是 市
                                        entity.city = city;
                                        entity.district = city;
                                    }
                                    return entity;

//                                    List<SupportWeatherCityBean.ResultBean.CityBean.DistrictBean> districts = cityBean.getDistrict();
//                                    for (int k = 0; k < districts.size(); k++) {
//                                        //县级名称
//                                        String district = districts.get(k).getDistrict();
//
//                                    }
                                }
                            }

                        }

                        return entity;
                    }
                }).observeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<CityEntity>() {
            @Override
            public void accept(CityEntity cityEntity) throws Exception {
                NetManager.queryWeatherTmp(WeatherModel.this, cityEntity.city, cityEntity.district);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                LogUtil.d(WeatherModel.this, throwable.getMessage());
            }
        });
    }

    /**
     * 查询支持的城市只需要每次打开APP查询一次
     */
    private void querySupportCity() {
        SpUtils spUtils = new SpUtils(SpConstant.QUERY_WEATHER_SUPPORT_CITY);
        String cache = spUtils.getString(SpConstant.QUERY_WEATHER_SUPPORT_CITY, "");
        if (Utils.isEmpty(cache) || mNeedQuery) {
            NetManager.queryWeatherSupportCity(this);
        } else {
            onResponse(NetId.QUERY_SUPPORT_CITY_WEATHER, cache);
        }
    }

    public void queryWeather() {
        querySupportCity();
        mThreadHepler.executorRunnable(new Runnable() {
            @Override
            public void run() {
                LocationHelper.getInstance().init().requestLocationOnce();
            }
        });
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//            }
//        }).start();
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

    private void handlerSupportCity(final String response) {
        mSupportWeatherCityBean = JsonHelper.parseObject(response, SupportWeatherCityBean.class);
        mNeedQuery = mSupportWeatherCityBean == null;
        mSupportWeatherCityBeanObservableEmitter.onNext(mSupportWeatherCityBean);
//        mSupportWeatherCityBeanObservableEmitter.onComplete();
    }


    private void handlerQueryWeather(String response) {
        WeatherBean weatherBean = JsonHelper.parseObject(response, WeatherBean.class);
        if (mModel != null) {
            mModel.updateView(weatherBean.getResult());
        }
    }

    @Override
    public void onFailure(int requestId, String errObj) {
        switch (requestId) {
            case NetId.QUERY_SUPPORT_CITY_WEATHER:
                mSupportWeatherCityBeanObservableEmitter.onError(new Throwable("查询支持的城市出错"));
                break;
            case NetId.QUERY_WEATHER:
                mCityEntityObservableEmitter.onError(new Throwable("查询天气出错"));
                break;
        }
    }

    @Override
    public void onLocationResult(String city, String district, String Street) {
        LogUtil.d(this, "city = " + city + ",  district = " + district);
        if (!Utils.isEmpty(city) && !Utils.isEmpty(district)) {

            if (mModel != null) {
                mModel.updateLocation(city, district);
            }

            if (city.length() >= NEED_REPLACE_LENGTH) {
                //上海市-》上海
                city = city.replace("市", "");
            }
            if (district.length() >= NEED_REPLACE_LENGTH) {
                district = district.replace("市", "").replace("区", "");
            }

            mCityEntityObservableEmitter.onNext(new CityEntity(city, district));

//            mCityEntityObservableEmitter.onComplete();
        }
    }

    @Override
    public void onLocationResultString(String result) {

    }

    private class CityEntity {
        public String city;
        public String district;

        public CityEntity(String city, String district) {
            this.city = city;
            this.district = district;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

    }
}
