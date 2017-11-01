package com.fashionlife.base.component;

import android.app.Application;
import android.content.Intent;

import com.fashionlife.UncaughtExceptionHandlerHelper;
import com.fashionlife.base.server.MyService;
import com.fashionlife.util.image.GlideImageLoaderFrameWork;
import com.fashionlife.util.image.ImageLoadHelper;

/**
 * @author
 * Created by lovexujh on 2017/9/19
 */

public class BaseApplication extends Application {

    private static BaseApplication instance;
    private boolean mWifi;
    private boolean mMobile;
    private boolean mConnected;
    private boolean mEnablaWifi;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initData();
    }

    private void initData() {
        ImageLoadHelper.getFLImageLoader().init(new GlideImageLoaderFrameWork());
        startMyService();
        UncaughtExceptionHandlerHelper uncaughtExceptionHandlerHelper = new UncaughtExceptionHandlerHelper();
        Thread.setDefaultUncaughtExceptionHandler(uncaughtExceptionHandlerHelper);
    }

    private void startMyService() {
        Intent intentService = new Intent(this, MyService.class);
        startService(intentService);
    }

    public static BaseApplication getInstance(){
        return instance;
    }
    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public void setWifi(boolean wifi) {
        mWifi = wifi;
    }

    public void setMobile(boolean mobile) {
        mMobile = mobile;
    }

    public void setConnected(boolean connected) {
        mConnected = connected;
    }

    public void setEnablaWifi(boolean enablaWifi) {
        mEnablaWifi = enablaWifi;
    }
}
