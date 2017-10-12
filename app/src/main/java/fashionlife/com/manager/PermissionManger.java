package fashionlife.com.manager;

import android.Manifest;
import android.app.Activity;
import android.support.v4.app.ActivityCompat;

/**
 * Created by lovexujh on 2017/10/11
 */

public class PermissionManger {

    public static void init(Activity activity){
        // TODO: 2017/10/12  关于权限的要封装起来
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.INTERNET}, 1111);
    }
}
