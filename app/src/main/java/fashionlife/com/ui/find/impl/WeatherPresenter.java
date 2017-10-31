package fashionlife.com.ui.find.impl;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.bigkoo.pickerview.OptionsPickerView;

import java.util.ArrayList;
import java.util.List;

import fashionlife.com.base.component.BasePresenter;
import fashionlife.com.ui.find.data.SupportWeatherCityBean;
import fashionlife.com.ui.find.data.WeatherBean;
import fashionlife.com.ui.find.model.IWallpaper;
import fashionlife.com.ui.find.model.IWeatherModel;
import fashionlife.com.ui.find.model.WallpaperModel;
import fashionlife.com.ui.find.model.WeatherModel;
import fashionlife.com.util.ToastHelper;
import fashionlife.com.util.Utils;

/**
 * @author: lovexujh
 * @time: 2017/10/24
 * @descripition:
 */

public class WeatherPresenter extends BasePresenter<WeatherContract.View> implements IWeatherModel, WeatherContract.Presenter, IWallpaper {

    private WallpaperModel mWallpaperModel;
    private WeatherModel mWeatherModel;

    public WeatherPresenter(WeatherContract.View view) {
        super(view);
        mWeatherModel = new WeatherModel(this);
        mWallpaperModel = new WallpaperModel(this);
    }

    @Override
    public void updateView(List<WeatherBean.ResultBean> resultBeen) {
        if (mView != null && !Utils.isEmpty(resultBeen)) {
//            //解决MOBAPI坑人的当前不返还白天天气，只能从外围取出来
            WeatherBean.ResultBean resultBean = resultBeen.get(0);
            mView.updateCurDayView(resultBean);
//
//            String weather = resultBean.getWeather();
//            //哎，MOB 的天气 不怎么样 到了晚上就查询不到上午的天气了。。。
//            String temperature = resultBean.getTemperature().replace("°", "").replace("C", "").replace("℃", "").trim();
            List<WeatherBean.ResultBean.FutureBean> future = resultBean.getFuture();
//            String temperature1 = future.get(0).getTemperature();
//            future.get(0).setDayTime(weather);
//            future.get(0).setTemperature(temperature + "/" + temperature1);
            mView.updateFutureView(future);
        }
    }

    @Override
    public void updateLocation(String city, String district) {
        if (mView != null) {
            mView.updateLocationView(city, district);
        }
    }

    @Override
    public void locationFailed() {
        if(mView != null){
            mView.checkLocationPermission();
        }
    }

    @Override
    public void initCityJsonData(SupportWeatherCityBean supportWeatherCityBean) {
        initJsonData(supportWeatherCityBean.getResult());
    }

    @Override
    public void queryWeather() {
        mWeatherModel.queryWeather();
    }

    @Override
    public void queryWeather(String city, String district) {

    }

    @Override
    public void downloadWallpaper() {
        if (mWallpaperModel != null){
            mWallpaperModel.queryWallpaperUrl();
        }
    }

    @Override
    public void downloadImgSucceed(String imgName) {
       if(mView != null){
           mView.downloadWallpaperSucceed(imgName);
       }
    }


    private void initJsonData(ArrayList<SupportWeatherCityBean.ResultBean> jsonBean) {//解析数据

        if (jsonBean == null) {
            ToastHelper.showToast("省三级数据为空");
        }

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
//        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            options1Items.add(jsonBean.get(i).getProvince());

            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCity().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCity().get(c).getCity();
                CityList.add(CityName);//添加城市
                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCity().get(c).getCity() == null
                        || jsonBean.get(i).getCity().get(c).getDistrict().size() == 0) {
                    City_AreaList.add("");
                } else {

                    for (int d = 0; d < jsonBean.get(i).getCity().get(c).getDistrict().size(); d++) {//该城市对应地区所有数据
                        String AreaName = jsonBean.get(i).getCity().get(c).getDistrict().get(d).getDistrict();

                        City_AreaList.add(AreaName);//添加该城市所有地区数据
                    }
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }


    }




    private ArrayList<String> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();




    public void ShowPickerView(final Context context) {// 弹出选择器

        OptionsPickerView pvOptions = new OptionsPickerView.Builder(context, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1) +
                        options2Items.get(options1).get(options2) +
                        options3Items.get(options1).get(options2).get(options3);

//                Toast.makeText(context, tx, Toast.LENGTH_SHORT).show();
                String province = options1Items.get(options1);
                String city = options2Items.get(options1).get(options2);
                mWeatherModel.queryWeather(province, city);
                if(mView != null){
                    mView.updateLocationView(province, city);
                }
            }
        })

                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }


}
