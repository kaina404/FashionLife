package fashionlife.com.widget;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;

import fashionlife.com.util.Utils;

/**
 * @author: lovexujh
 * @time: 2017/10/29
 * @descripition:
 */

public class ProgressDialogHepler {

    private static ProgressDialog mProgressDialog;
    private static Context mContext;

    public static void show(Context context, String title, String msg) {
        if (context == null || mContext == context) {
            return;
        }
        if (context instanceof Activity) {
            if (!Utils.isActivityRunning((Activity) context)) {
                return;
            }
        }
        mContext = context;
        mProgressDialog = ProgressDialog.show(context, title, msg);
    }

    public static void dismiss() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
        mContext = null;
    }

}
