package com.myfashionlife.manager;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.Fragment;

import com.myfashionlife.R;
import com.myfashionlife.util.ResourceUtils;
import com.myfashionlife.widget.AlertUtils;

/**
 * @author Created by lovexujh on 2017/10/11
 */

public class PermissionMangerHelper {

    private static final int REQUEST_CODE = 600;
    public static final int PERMISSION_LOCATION_OK = 100;
    public static final int PERMISSION_FILE_OK = 200;


    public static void init(Activity activity) {
        checkAllPermission(activity);
    }

    public static String[] getPermissions() {
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
    public static void checkPermission(final Activity activity, final String permission) {
        if (!activity.shouldShowRequestPermissionRationale(permission) && !hasPermission(activity, permission)) {
            AlertUtils.showMessage(ResourceUtils.getString(R.string.you_shold_access_permission),
                    ResourceUtils.getString(R.string.yes), ResourceUtils.getString(R.string.abandon), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            activity.requestPermissions(new String[]{permission}, REQUEST_CODE);
                        }
                    });
        } else if (activity.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
            // 申请一个（或多个）权限，并提供用于回调返回的获取码（用户定义)
            activity.requestPermissions(new String[]{permission}, REQUEST_CODE);
        }
    }


    /**
     * 判断是否需要改权限
     *
     * @param fragment
     * @param permission
     */
    @TargetApi(23)
    public static void checkPermission(final Fragment fragment, final String permission, final int requestCode) {
        if (!fragment.shouldShowRequestPermissionRationale(permission) && !hasPermission(fragment, permission)) {
            AlertUtils.showMessage(ResourceUtils.getString(R.string.you_shold_access_permission),
                    ResourceUtils.getString(R.string.yes), ResourceUtils.getString(R.string.abandon), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            fragment.requestPermissions(new String[]{permission}, requestCode);
                        }
                    });
        } else {
            fragment.requestPermissions(new String[]{permission}, requestCode);
        }
    }

    /**
     * @param fragment
     * @return true  已经授权
     */
    @TargetApi(23)
    public static boolean needRequestLocationPermission(final Fragment fragment) {
        if (!fragment.shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION) ||
                !fragment.shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
            if (!hasPermission(
                    fragment,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                AlertUtils.showMessage(ResourceUtils.getString(R.string.you_shold_access_permission),
                        ResourceUtils.getString(R.string.yes), ResourceUtils.getString(R.string.abandon), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                fragment.requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                                        Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_LOCATION_OK);
                            }
                        });
            }

        } else if (fragment.getActivity().checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                fragment.getActivity().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            AlertUtils.showMessage(fragment.getActivity(), "查看天气需要定位哦~~", "查看天气", "不要天气", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    fragment.requestPermissions(new String[]{
                                    Manifest.permission.ACCESS_COARSE_LOCATION,
                                    Manifest.permission.ACCESS_FINE_LOCATION,},
                            PermissionMangerHelper.PERMISSION_LOCATION_OK);
                }
            });
            return true;
        }
        return false;
    }

    @TargetApi(Build.VERSION_CODES.M)
    private static boolean hasPermission(Fragment fragment, String... permissions) {
        for (int i = 0; i < permissions.length; i++) {
            if (fragment.getActivity().checkSelfPermission(permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @TargetApi(Build.VERSION_CODES.M)
    private static boolean hasPermission(Activity activity, String... permissions) {
        for (int i = 0; i < permissions.length; i++) {
            if (activity.checkSelfPermission(permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }
}
