package fashionlife.com.base.component;

import android.app.Application;

import fashionlife.com.util.image.FLImageLoader;
import fashionlife.com.util.image.GlideImageLoaderFrameWork;

/**
 * Created by lovexujh on 2017/9/19
 */

public class BaseApplication extends Application {

    private static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        FLImageLoader.getFLImageLoader().init(new GlideImageLoaderFrameWork());
    }

    public static BaseApplication getInstance(){
        return instance;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
    
}
