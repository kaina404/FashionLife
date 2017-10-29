package fashionlife.com.widget;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * @author: lovexujh
 * @time: 2017/10/29
 * @descripition:
 */

public class AlertUtils {

    public static void showMessageOKCancel(Activity activity, String message, String ok, String cancel, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(activity)
                .setMessage(message)
                .setPositiveButton(ok, okListener)
                .setNegativeButton(cancel, null)
                .create()
                .show();
    }

}
