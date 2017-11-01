package com.fashionlife.manager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.fashionlife.base.component.BaseApplication;
import com.fashionlife.common.ActivityId;
import com.fashionlife.common.IntentKeys;

/**
 * Created by lovexujh on 2017/10/9
 */

public class StartManager {

    private static final int START_WEB = 1;


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

    public static void startWeb( String url, Context context){
        startWeb(url, null, context);
    }

    public static void startWeb(String url, Intent intent, Context context) {
        if (intent == null) {
            intent = new Intent();
        }
        intent.putExtra(IntentKeys.URL, url);
        StartManager.startActivity(ActivityId.WEB_VIEW_ACTIVITY, context, intent);
    }

}
