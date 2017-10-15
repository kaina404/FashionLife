package fashionlife.com.app;

import fashionlife.com.manager.ActivityManager;
import fashionlife.com.util.Tool;

/**
 * Created by lovexujh on 2017/9/19
 */

public class AppUtils {

    public static void exitAPP() {
        ActivityManager.getInstance().finishAllActivity();
    }

    public static String getMobWXImgURL(String thumbnails) {
        if(Tool.isEmpty(thumbnails)){
            return null;
        }
        // TODO: 2017/10/15
        return thumbnails.split("$")[0];
    }
}
