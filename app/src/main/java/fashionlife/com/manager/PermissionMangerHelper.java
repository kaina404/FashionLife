package fashionlife.com.manager;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageManager;

/**
 * @author Created by lovexujh on 2017/10/11
 */

public class PermissionMangerHelper {

    private static final int REQUEST_CODE = 600;

    public static void init(Activity activity) {
        // TODO: 2017/10/12  关于权限的要封装起来
        checkAllPermission(activity);
    }

    @TargetApi(23)
    public static void checkAllPermission(Activity activity) {
        String[] permissions = new String[]{
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};
        for (int i = 0; i < permissions.length; i++) {
            checkPermission(activity, permissions[i]);
        }
    }


    @TargetApi(23)
    public static void checkPermission(Activity activity, String permission) {
        if (activity.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
            // 申请一个（或多个）权限，并提供用于回调返回的获取码（用户定义)
            activity.requestPermissions(new String[]{permission}, REQUEST_CODE);
        }
    }
}
