package fashionlife.com.util.location;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import fashionlife.com.base.component.BaseApplication;

/**
 * @author: lovexujh
 * @time: 2017/10/25
 * @descripition:
 */

public class LocationHelper {

    public LocationClient mLocationClient = null;

    private static LocationHelper instance;

    public LocationListenerImpl mLocationListener = new LocationListenerImpl();
    ;

    private LocationHelper() {
    }

    public static LocationHelper getInstance() {
        if (instance == null) {
            synchronized (LocationHelper.class) {
                if (instance == null) {
                    instance = new LocationHelper();
                }
            }
        }
        return instance;
    }

    public LocationHelper init() {
        if (mLocationClient == null) {
            mLocationClient = new LocationClient(BaseApplication.getInstance().getApplicationContext());
            //声明LocationClient类
            mLocationClient.registerLocationListener(mLocationListener);
            //注册监听函数
        }
        return this;
    }


    private void initLocation(int span) {

        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备

        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系

//        int span = 5000;
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        if (span >= 1000) {
            option.setScanSpan(span);
        }

        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要

        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps

        option.setLocationNotify(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果

        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”

        option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到

        option.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死

//        option.setIgnoreCacheException(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集

        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要

//        option.setWifiValidTime(5*60*1000);
        //可选，7.2版本新增能力，如果您设置了这个接口，首次启动定位时，会先判断当前WiFi是否超出有效期，超出有效期的话，会先重新扫描WiFi，然后再定位

        mLocationClient.setLocOption(option);
    }

    public void locationClientStart() {
        mLocationClient.start();
    }

    public void locationClientStop() {
        if (mLocationClient != null) {
            mLocationClient.stop();
        }
    }


    public void locationClientRestart() {
        if (mLocationClient != null) {
            mLocationClient.restart();
        }
    }

    public void requestLocationOnce() {
        if (mLocationClient != null) {
            if (mLocationClient.isStarted()) {
                mLocationClient.restart();
            } else {
                initLocation(0);
                mLocationClient.start();
            }

//            mLocationClient.requestLocation();
        }
    }

    public void requestHotSpotState() {
        if (mLocationClient != null) {
            mLocationClient.requestHotSpotState();
        }
    }


    public LocationListenerImpl getLocationListener() {
        return mLocationListener;
    }

}
