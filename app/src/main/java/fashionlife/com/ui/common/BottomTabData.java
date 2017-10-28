package fashionlife.com.ui.common;

import java.util.ArrayList;
import java.util.List;

import fashionlife.com.R;
import fashionlife.com.common.BottomTabEntity;
import fashionlife.com.net.INetCall;
import fashionlife.com.net.NetRequest;
import fashionlife.com.util.LogUtil;
import fashionlife.com.util.Utils;
import fashionlife.com.widget.BottomTabWidget;

/**
 * @author: lovexujh
 * @time: 2017/10/27
 * @descripition:
 */

public class BottomTabData implements INetCall {

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
        // TODO: 2017/10/27  TEST
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

        queryBottomTab();

    }

    public void queryBottomTab(){
        // TODO: 2017/10/27  https://github.com/xujianhui404/FashionLifeConsole/blob/master/testjson
        String url = "https://github.com/xujianhui404/FashionLifeConsole/blob/master/testjson";
        //int requestId, Object params, String url, String method, MediaType mediaType, INetCall netCall
        NetRequest.http(100, null, url, NetRequest.Method.GET, NetRequest.JSON_TYPE, this);
    }


    @Override
    public void onResponse(int requestId, String response) {
        LogUtil.d(this, "返回结果是："+response);
    }

    @Override
    public void onFailure(int requestId, String errObj) {

    }
}
