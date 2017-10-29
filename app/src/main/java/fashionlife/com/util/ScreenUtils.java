package fashionlife.com.util;

import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

import fashionlife.com.base.component.BaseApplication;

/**
 * Created by lovexujh on 2017/10/16
 */

public class ScreenUtils {

    public static int getScreenWidth() {
        WindowManager manager = (WindowManager) BaseApplication.getInstance().getApplicationContext()
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getWidth();
    }

    public static int getScreenHeight() {
        WindowManager manager = (WindowManager) BaseApplication.getInstance().getApplicationContext()
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getHeight();
    }

    public static int pxToDp(float px) {
        final float scale = BaseApplication.getInstance().getApplicationContext().getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    public static int dpToPx(float dp)
    {
        final float scale = BaseApplication.getInstance().getApplicationContext().getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static int spToPx(float sp)
    {
        final float scale = BaseApplication.getInstance().getApplicationContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * scale + 0.5f);
    }

}
