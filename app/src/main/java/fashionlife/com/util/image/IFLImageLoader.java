package fashionlife.com.util.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by lovexujh on 2017/10/11
 */

public interface IFLImageLoader {

    IFLImageLoader loadImage(ImageView view, String path);

    IFLImageLoader loadImage(ImageView view, String path, IImageLoaderCallBack iImageLoaderCallBack);

    IFLImageLoader loadImage(ImageView view, File file);

    IFLImageLoader loadImage(ImageView view, int drawable);

    IFLImageLoader setOptions(ImageOptions imageOptions);

    IFLImageLoader clearMemoryCache();

    IFLImageLoader clearLocalCache();

    IFLImageLoader init(Context context);

    interface IImageLoaderCallBack {
        void onLoadCompleted(Bitmap bitmap);

        void onLoadFailed();
    }
}
