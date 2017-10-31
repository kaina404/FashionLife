package com.fashionlife.app;

import com.fashionlife.manager.ActivityManager;
import com.fashionlife.util.Utils;

/**
 * Created by lovexujh on 2017/9/19
 */

public class AppUtils {

    public static final String COMMON = "fashionlife";

    public static final String APP_CACHE_DIRNAME = "fashionlife";

    public static void exitAPP() {
        ActivityManager.getInstance().finishAllActivity();
    }

    public static String getMobWXImgURL(String thumbnails) {
        if (Utils.isEmpty(thumbnails)) {
            return null;
        }
        return thumbnails.split("$")[0];
    }

    public static String getWXNewsKey(String cid, int page) {
        return COMMON + cid + "AND" + page;
    }
}
