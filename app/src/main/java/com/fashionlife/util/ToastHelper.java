package com.fashionlife.util;

import android.widget.Toast;

import com.fashionlife.BuildConfig;
import com.fashionlife.base.component.BaseApplication;

/**
 * @author: lovexujh
 * @time: 2017/10/25
 * @descripition:
 */

public class ToastHelper {

    public static void showDebugToast(String msg) {
        if (BuildConfig.DEBUG) {
            Toast.makeText(BaseApplication.getInstance().getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        }
    }

}
