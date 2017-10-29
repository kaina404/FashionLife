package fashionlife.com.base.component;

import android.app.Application;

import fashionlife.com.util.image.ImageLoadHelper;
import fashionlife.com.util.image.GlideImageLoaderFrameWork;

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
        ImageLoadHelper.getFLImageLoader().init(new GlideImageLoaderFrameWork());
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
