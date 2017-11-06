package com.myfashionlife.ui.find.model;

import com.myfashionlife.base.data.BaseModel;
import com.myfashionlife.common.SpConstant;
import com.myfashionlife.common.ThreadHelper;
import com.myfashionlife.manager.NetManager;
import com.myfashionlife.net.INetCall;
import com.myfashionlife.net.NetId;
import com.myfashionlife.ui.find.data.SupportWeatherCityBean;
import com.myfashionlife.ui.find.data.WeatherBean;
import com.myfashionlife.util.JsonHelper;
import com.myfashionlife.util.LogUtil;
import com.myfashionlife.util.SpUtils;
import com.myfashionlife.util.Utils;
import com.myfashionlife.util.location.LocationHelper;
import com.myfashionlife.util.location.LocationListenerImpl;
import com.myfashionlife.widget.AlertUtils;

import java.util.List;

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
    private static final String NO_FIND_CITY_WEATHER_CODE = "20402";
    private ThreadHelper mThreadHepler;
    private SupportWeatherCityBean mSupportWeatherCityBean;
    private boolean mNeedQuery = true;
    private ObservableEmitter<SupportWeatherCityBean> mSupportWeatherCityBeanObservableEmitter;
    private ObservableEmitter<CityEntity> mCityEntityObservableEmitter;
    private int mQueryOnce = 0;
    private String mLastQueryCity;
    private String mLastQueryDistrict;

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

                                    //由于MOBAPI的入参数据格式较百度地图返回的位置比较十分不准确，故这里不再精确到县，
                                    // 只是确定下MOBAPI中是否支持该城市。
                                    //其实向这样的无用北鼻无奈的选择，我是拒绝的。
                                    if (entity.city.contains(province) || entity.city.contains(city)) {
                                        //说明是 市
                                        entity.city = city;
                                        entity.district = city;
                                    }
                                    return entity;
                                }
                            }

                        }

                        return entity;
                    }
                }).observeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<CityEntity>() {
            @Override
            public void accept(CityEntity cityEntity) throws Exception {
                mLastQueryCity = cityEntity.city;
                mLastQueryDistrict = cityEntity.district;
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
        mQueryOnce = 0;
        if (Utils.isEmpty(mLastQueryCity) || Utils.isEmpty(mLastQueryDistrict)) {
            querySupportCity();
            mThreadHepler.executorRunnable(new Runnable() {
                @Override
                public void run() {
                    LocationHelper.getInstance().init().requestLocationOnce();
                }
            });
        } else {
            queryWeather(mLastQueryCity, mLastQueryDistrict);
        }

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
        if (mSupportWeatherCityBean != null && mModel != null) {
            mModel.initCityJsonData(mSupportWeatherCityBean);
        }
    }


    private void handlerQueryWeather(String response) {
        WeatherBean weatherBean = JsonHelper.parseObject(response, WeatherBean.class);
        if (mModel != null) {
            if (NO_FIND_CITY_WEATHER_CODE.equals(weatherBean.getRetCode())) {
                if (mQueryOnce <= 0) {
                    NetManager.queryWeatherTmp(this, mLastQueryCity, mLastQueryCity);
                    mQueryOnce++;
                } else {
                    AlertUtils.showMessage(null, "没有查询到当前城市天气哦~~");
                }
            } else {
//                AlertUtils.showMessage(null, "开启新一天的" + weatherBean.getResult().get(0).getDistrct() + "天气哦");
                mModel.updateView(weatherBean.getResult());
            }

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
        } else {
            if (mModel != null) {
                mModel.locationFailed();
            }
        }
    }

    @Override
    public void onLocationResultString(String result) {

    }

    public void queryWeather(String province, String city) {
        mLastQueryCity = province;
        mLastQueryDistrict = city;
        NetManager.queryWeatherTmp(this, province, city);
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
