package com.myfashionlife.ui.common;

import com.myfashionlife.R;
import com.myfashionlife.common.BottomTabEntity;
import com.myfashionlife.util.Utils;
import com.myfashionlife.widget.BottomTabWidget;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lovexujh
 * @time: 2017/10/27
 * @descripition:
 */

public class BottomTabData  {

    private BottomTabWidget mBottomView;

    public static BottomTabData create(){
        BottomTabData mBottomTabData = new BottomTabData();
        return mBottomTabData;
    }

    public BottomTabData setBottomView(BottomTabWidget bottomView) {
        mBottomView = bottomView;
        return this;
    }

    public void initView(){
        //int checkedIconRes, int noCheckIconRes, int text, int checkedTxtColor, int noCheckTxtColor
        List<BottomTabEntity> bottomTabEntities = new ArrayList<>();
        int checkedColor = Utils.getColor(R.color.c_2e77fb);
        int noCheckColor = Utils.getColor(R.color.c_000000);
        BottomTabEntity home = new BottomTabEntity(R.mipmap.home_checked, R.mipmap.home_no_checked,
                "首页", checkedColor, noCheckColor);

        BottomTabEntity weather = new BottomTabEntity(R.mipmap.weather_checked, R.mipmap.weather_no_check,
                "天气", checkedColor, noCheckColor);

        BottomTabEntity tool = new BottomTabEntity(R.mipmap.tool_checked, R.mipmap.tool_no_check,
                "工具", checkedColor, noCheckColor);

        bottomTabEntities.add(home);
        bottomTabEntities.add(weather);
        bottomTabEntities.add(tool);

        mBottomView.updateView(bottomTabEntities);


    }

}
