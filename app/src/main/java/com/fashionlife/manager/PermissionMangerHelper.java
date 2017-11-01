package com.fashionlife.manager;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v4.app.Fragment;

import com.fashionlife.common.CommonConstant;
import com.fashionlife.widget.AlertUtils;

/**
 * @author Created by lovexujh on 2017/10/11
 */

public class PermissionMangerHelper {

    private static final int REQUEST_CODE = 600;

    public static void init(Activity activity) {
        // TODO: 2017/10/12  关于权限的要封装起来
        checkAllPermission(activity);
    }

    public static String[] getPermissions(){
        return new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};
    }

    @TargetApi(23)
    public static void checkAllPermission(Activity activity) {
        String[] permissions = getPermissions();
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

    /**
     *
     * @param fragment
     * @return true  已经授权
     */
    @TargetApi(23)
    public static boolean needRequestLocationPermission(final Fragment fragment) {

        if (fragment.getActivity().checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                fragment.getActivity().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            AlertUtils.showMessage(fragment.getActivity(), "查看天气需要定位哦~~", "查看天气", "不要天气", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    fragment.requestPermissions(new String[]{
                                    Manifest.permission.ACCESS_COARSE_LOCATION,
                                    Manifest.permission.ACCESS_FINE_LOCATION,},
                            CommonConstant.PERMISSION_OK);
                }
            });
            return true;
        }
        return false;
    }
}
