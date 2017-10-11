package fashionlife.com.util.image;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import java.io.File;

/**
 * Glide图片加载框架
 * Created by lovexujh on 2017/10/11
 */

public class GlideImageLoaderFrameWork implements IFLImageLoader {


    private RequestManager requestManager;

    @Override
    public IFLImageLoader loadImage(ImageView ImageView, String path) {
        requestManager.load(path).into(ImageView);
        return this;
    }

    @Override
    public IFLImageLoader loadImage(ImageView ImageView, String path, IImageLoaderCallBack iImageLoaderCallBack) {
        return this;
    }

    @Override
    public IFLImageLoader loadImage(ImageView ImageView, File file) {
        requestManager.load(file).into(ImageView);
        return this;
    }

    @Override
    public IFLImageLoader loadImage(ImageView ImageView, int drawable) {
        requestManager.load(drawable).into(ImageView);
        return this;
    }

    @Override
    public IFLImageLoader setOptions(ImageOptions imageOptions) {
        return this;
    }

    @Override
    public IFLImageLoader clearMemoryCache() {
        return this;
    }

    @Override
    public IFLImageLoader clearLocalCache() {
        return this;
    }

    @Override
    public IFLImageLoader init(Context context) {
         requestManager = Glide.with(context);
        return this;
    }
}
