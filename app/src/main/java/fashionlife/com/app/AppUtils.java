package fashionlife.com.app;

import fashionlife.com.manager.ActivityManager;

/**
 * Created by lovexujh on 2017/9/19
 */

public class AppUtils {

    public static void exitAPP() {
        ActivityManager.getInstance().finishAllActivity();
    }
}
