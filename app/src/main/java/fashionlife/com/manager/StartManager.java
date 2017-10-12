package fashionlife.com.manager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import fashionlife.com.base.component.BaseApplication;

/**
 * Created by lovexujh on 2017/10/9
 */

public class StartManager {

    public static void startActivity(String activityId, Context context, Intent intent) {
        if (activityId == null) {
            return;
        }
        ActivityRecord activityRecord = ActivityManager.getInstance().getActivityRecord(activityId);
        if (activityRecord != null) {
            Class<? extends Activity> activityClz = activityRecord.getActivityClz();
            if (activityClz == null) {
                return;
            }
            if (context == null) {
                context = BaseApplication.getInstance();
            }
            if (intent == null) {
                intent = new Intent();
            }
            intent.setClass(context, activityClz);
            try {
                context.startActivity(intent);
            } catch (Exception e) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        }
    }
}
