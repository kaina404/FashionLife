package fashionlife.com.util.image;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by lovexujh on 2017/10/11
 */

public class FLImageLoader implements IFLImageLoader {

    private static FLImageLoader sFLImageLoader;
    private static volatile IFLImageLoader sLoader;

    private FLImageLoader() {

    }

    public static FLImageLoader getFLImageLoader() {
        if (sFLImageLoader == null) {
            synchronized (FLImageLoader.class) {
                if (sFLImageLoader == null) {
                    sFLImageLoader = new FLImageLoader();
                }
            }
        }
        return sFLImageLoader;
    }

    public void init(@NonNull IFLImageLoader loader) {
        if (sLoader == null) {
            sLoader = loader;
        } else {
            throw new IllegalArgumentException("error ImageLoader frameWork !");
        }
    }


    @Override
    public IFLImageLoader loadImage(ImageView ImageView, String path) {
        return sLoader.loadImage(ImageView, path);
    }

    @Override
    public IFLImageLoader loadImage(ImageView ImageView, String path, IImageLoaderCallBack iImageLoaderCallBack) {
        return sLoader.loadImage(ImageView, path, iImageLoaderCallBack);
    }

    @Override
    public IFLImageLoader loadImage(ImageView ImageView, File file) {
        return sLoader.loadImage(ImageView, file);
    }

    @Override
    public IFLImageLoader loadImage(ImageView ImageView, int drawable) {
        return sLoader.loadImage(ImageView, drawable);
    }

    @Override
    public IFLImageLoader setOptions(ImageOptions imageOptions) {
        return sLoader.setOptions(imageOptions);
    }

    @Override
    public IFLImageLoader clearMemoryCache() {
        return sLoader.clearMemoryCache();
    }

    @Override
    public IFLImageLoader clearLocalCache() {
        return sLoader.clearLocalCache();
    }

    @Override
    public IFLImageLoader init(Context context) {
        return sLoader.init(context);
    }
}
