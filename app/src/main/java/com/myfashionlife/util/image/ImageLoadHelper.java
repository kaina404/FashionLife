package com.myfashionlife.util.image;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

import java.io.File;

/**
 * @author
 * Created by lovexujh on 2017/10/11
 */

public class ImageLoadHelper implements IFLImageLoader {

    private static ImageLoadHelper sFLImageLoader;
    private static volatile IFLImageLoader sLoader;

    private ImageLoadHelper() {

    }

    public static ImageLoadHelper getFLImageLoader() {
        if (sFLImageLoader == null) {
            synchronized (ImageLoadHelper.class) {
                if (sFLImageLoader == null) {
                    sFLImageLoader = new ImageLoadHelper();
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

    @Override
    public IFLImageLoader init(Activity activity) {
        return sLoader.init(activity);
    }

    @Override
    public IFLImageLoader init(Fragment fragment) {
        return sLoader.init(fragment);
    }

    @Override
    public IFLImageLoader init(FragmentActivity fragmentActivity) {
        return sLoader.init(fragmentActivity);
    }
}
