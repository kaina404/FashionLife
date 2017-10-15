package fashionlife.com.app;

import fashionlife.com.manager.ActivityManager;
import fashionlife.com.util.Tool;

/**
 * Created by lovexujh on 2017/9/19
 */

public class AppUtils {

    public static final String APP_CACHE_DIRNAME = "fashionlife";

    public static void exitAPP() {
        ActivityManager.getInstance().finishAllActivity();
    }

    public static String getMobWXImgURL(String thumbnails) {
        if (Tool.isEmpty(thumbnails)) {
            return null;
        }
        return thumbnails.split("$")[0];
    }
}
