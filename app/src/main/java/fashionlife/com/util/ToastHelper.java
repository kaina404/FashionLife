package fashionlife.com.util;

import android.widget.Toast;

import fashionlife.com.base.component.BaseApplication;

/**
 * @author: lovexujh
 * @time: 2017/10/25
 * @descripition:
 */

public class ToastHelper {

    public static void showToast(String msg) {
        Toast.makeText(BaseApplication.getInstance().getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

}
