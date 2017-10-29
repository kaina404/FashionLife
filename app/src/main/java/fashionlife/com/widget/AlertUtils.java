package fashionlife.com.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import fashionlife.com.manager.ActivityManager;

/**
 * @author: lovexujh
 * @time: 2017/10/29
 * @descripition:
 */

public class AlertUtils {


    private static AlertDialog.Builder mAlertDialogBuilder;
    private static String mMsg;
    private static String mOk;
    private static String mCancel;
    private static DialogInterface.OnClickListener mOkListener;

    public static void showMessage(Context context, String message, String ok, String cancel, DialogInterface.OnClickListener okListener) {
        try {

            if (message == null) {
                return;
            }

            if (message.equals(mMsg) || mOkListener == okListener) {
                return;
            }

            mAlertDialogBuilder = new AlertDialog.Builder(ActivityManager.getInstance().getTopActivity());
            mAlertDialogBuilder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    mAlertDialogBuilder = null;
                    mOkListener = null;
                    mMsg = "";
                    mOk = "";
                    mCancel = "";
                }
            });
            mAlertDialogBuilder.setMessage(message)
                    .setPositiveButton(ok, okListener)
                    .setNegativeButton(cancel, null)
                    .create();

            mMsg = message;
            mOk = ok;
            mCancel = cancel;
            mOkListener = okListener;
            mAlertDialogBuilder.show();


        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void showMessage(Context context, String msg) {
        showMessage(context, msg, "", "", null);
    }

}
