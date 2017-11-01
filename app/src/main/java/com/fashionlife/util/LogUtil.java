package com.fashionlife.util;

import android.util.Log;

import com.fashionlife.BuildConfig;

/**
 * @author
 * Created by lovexujh on 2017/10/9
 */

public class LogUtil {
    private static final String Tag = "FashionLife";
    private static final boolean DEBUG = BuildConfig.DEBUG;

    public static void d(String msg, Object... params) {
        if (DEBUG) {
            android.util.Log.d(Tag, String.format(msg, params));
        }
    }

    public static void d(Object obj, String params) {
        if (DEBUG) {
            if (obj instanceof String) {
                Log.d((String) obj, params);
            } else {
                android.util.Log.d(obj.getClass().getSimpleName(), params);

            }
        }
    }

    public static void e(Object obj, String params) {
        if (DEBUG) {
            if (obj instanceof String) {
                Log.e((String) obj, params);
            } else {
                android.util.Log.e(obj.getClass().getSimpleName(), params);

            }
        }
    }
}
