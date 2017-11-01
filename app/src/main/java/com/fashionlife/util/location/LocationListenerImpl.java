package com.fashionlife.util.location;


import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.fashionlife.util.LogUtil;

/**
 * @author: lovexujh
 * @time: 2017/10/25
 * @descripition:
 * BDAbstractLocationListener为7.2版本新增的Abstract类型的监听接口，原有BDLocationListener接口暂时同步保留。具体介绍请参考后文中的说明
 */

public class LocationListenerImpl extends BDAbstractLocationListener {

    @Override
    public void onReceiveLocation(BDLocation location) {
        //获取定位结果
        location.getTime();    //获取定位时间
        location.getLocationID();    //获取定位唯一ID，v7.2版本新增，用于排查定位问题
        location.getLocType();    //获取定位类型
        location.getLatitude();    //获取纬度信息
        location.getLongitude();    //获取经度信息
        location.getRadius();    //获取定位精准度
        location.getAddrStr();    //获取地址信息
        location.getCountry();    //获取国家信息
        location.getCountryCode();    //获取国家码
        location.getCity();    //获取城市信息
        location.getCityCode();    //获取城市码
        location.getDistrict();    //获取区县信息
        location.getStreet();    //获取街道信息
        location.getStreetNumber();    //获取街道码
        location.getLocationDescribe();    //获取当前位置描述信息
        location.getPoiList();    //获取当前位置周边POI信息

        location.getBuildingID();    //室内精准定位下，获取楼宇ID
        location.getBuildingName();    //室内精准定位下，获取楼宇名称
        location.getFloor();    //室内精准定位下，获取当前位置所处的楼层信息


        if (mLocationResultListener != null) {
            mLocationResultListener.onLocationResult(location.getCity(), location.getDistrict(), location.getStreet());
        }


        if (location.getLocType() == BDLocation.TypeGpsLocation) {

            //当前为GPS定位结果，可获取以下信息
            location.getSpeed();    //获取当前速度，单位：公里每小时
            location.getSatelliteNumber();    //获取当前卫星数
            location.getAltitude();    //获取海拔高度信息，单位米
            location.getDirection();    //获取方向信息，单位度

            LogUtil.d(this, "当前为GPS定位结果");

        } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {

            //当前为网络定位结果，可获取以下信息
            location.getOperators();    //获取运营商信息

            LogUtil.d(this, "当前为网络定位结果-TypeNetWorkLocation");

        } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {

            //当前为网络定位结果
            LogUtil.d(this, "当前为网络定位结果-TypeOffLineLocation");

        } else if (location.getLocType() == BDLocation.TypeServerError) {

            //当前网络定位失败
            //可将定位唯一ID、IMEI、定位失败时间反馈至loc-bugs@baidu.com
            LogUtil.d(this, "当前网络定位失败");

        } else if (location.getLocType() == BDLocation.TypeNetWorkException) {

            //当前网络不通
            LogUtil.d(this, "当前网络不通");

        } else if (location.getLocType() == BDLocation.TypeCriteriaException) {

            //当前缺少定位依据，可能是用户没有授权，建议弹出提示框让用户开启权限
            //可进一步参考onLocDiagnosticMessage中的错误返回码
            LogUtil.d(this, "当前缺少定位依据，可能是用户没有授权，建议弹出提示框让用户开启权限");
            /*AlertUtils.showMessage(null, ResourceUtils.getString(R.string.need_location_persmisson),
                    ResourceUtils.getString(R.string.yes),
                    ResourceUtils.getString(R.string.abandon),
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // 转到手机设置界面，用户设置GPS
                            try{
                                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                BaseApplication.getInstance().getApplicationContext().startActivity(intent); // 设置完成后返回到原来的界面
                            }catch (Exception e){
                                e.printStackTrace();
                            }

                        }
                    });*/

        } else {
            LogUtil.d(this, "百度地图SDK，未知错误, onReceiveLocation LocType :" + location.getLocType());
        }
    }

    /**
     * 回调定位诊断信息，开发者可以根据相关信息解决定位遇到的一些问题
     * 自动回调，相同的diagnosticType只会回调一次
     *
     * @param locType           当前定位类型
     * @param diagnosticType    诊断类型（1~9）
     * @param diagnosticMessage 具体的诊断信息释义
     */
    @Override
    public void onLocDiagnosticMessage(int locType, int diagnosticType, String diagnosticMessage) {

        if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_BETTER_OPEN_GPS) {

            //建议打开GPS
            LogUtil.d(this, "建议打开GPS");

        } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_BETTER_OPEN_WIFI) {

            //建议打开wifi，不必连接，这样有助于提高网络定位精度！
            LogUtil.d(this, "建议打开wifi，不必连接，这样有助于提高网络定位精度！");

        } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_CHECK_LOC_PERMISSION) {

            //定位权限受限，建议提示用户授予APP定位权限！
            LogUtil.d(this, "定位权限受限，建议提示用户授予APP定位权限！");

        } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_CHECK_NET) {

            //网络异常造成定位失败，建议用户确认网络状态是否异常！
            LogUtil.d(this, "网络异常造成定位失败，建议用户确认网络状态是否异常！");

        } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_CLOSE_FLYMODE) {

            //手机飞行模式造成定位失败，建议用户关闭飞行模式后再重试定位！
            LogUtil.d(this, "手机飞行模式造成定位失败，建议用户关闭飞行模式后再重试定位！");

        } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_INSERT_SIMCARD_OR_OPEN_WIFI) {

            //无法获取任何定位依据，建议用户打开wifi或者插入sim卡重试！
            LogUtil.d(this, "无法获取任何定位依据，建议用户打开wifi或者插入sim卡重试！");

        } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_NEED_OPEN_PHONE_LOC_SWITCH) {

            //无法获取有效定位依据，建议用户打开手机设置里的定位开关后重试！
            LogUtil.d(this, "无法获取有效定位依据，建议用户打开手机设置里的定位开关后重试！");

        } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_SERVER_FAIL) {

            //百度定位服务端定位失败
            //建议反馈location.getLocationID()和大体定位时间到loc-bugs@baidu.com
            LogUtil.d(this, "百度定位服务端定位失败 建议反馈location.getLocationID()和大体定位时间到loc-bugs@baidu.com");

        } else if (diagnosticType == LocationClient.LOC_DIAGNOSTIC_TYPE_FAIL_UNKNOWN) {

            //无法获取有效定位依据，但无法确定具体原因
            //建议检查是否有安全软件屏蔽相关定位权限
            //或调用LocationClient.restart()重新启动后重试！
            LogUtil.d(this, "无法获取有效定位依据，但无法确定具体原因;建议检查是否有安全软件屏蔽相关定位权限;或调用LocationClient.restart()重新启动后重试！");

        } else {
            LogUtil.d(this, "百度地图SDK，未知错误, onLocDiagnosticMessage diagnosticMessage = " + diagnosticMessage);
        }

    }

    public interface LocationResultListener {
        void onLocationResult(String city, String district, String Street);

        void onLocationResultString(String result);
    }

    private LocationResultListener mLocationResultListener;

    public void setLocationResultListener(LocationResultListener locationResultListener) {
        mLocationResultListener = locationResultListener;
    }


    @Override
    public void onConnectHotSpotMessage(String connectWifiMac, int hotSpotState) {
        super.onConnectHotSpotMessage(connectWifiMac, hotSpotState);
        LogUtil.d(this, "onConnectHotSpotMessage->  connectWifiMac = " + connectWifiMac + "--" + "hotSpotState = " + hotSpotState);
    }
}
