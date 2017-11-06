package com.myfashionlife.manager;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.myfashionlife.base.component.BaseActivity;
import com.myfashionlife.common.ActivityId;
import com.myfashionlife.test.TestActivity;
import com.myfashionlife.ui.ZoomImageActivity;
import com.myfashionlife.ui.common.ContainerActivity;
import com.myfashionlife.ui.web.WebViewActivity;

/**
 * Created by lovexujh on 2017/10/9
 */

public class ActivityManager {

    private static List<BaseActivity> activityList;
    private static ActivityManager instance;
    private Map<String, ActivityRecord> activityRecordMap;


    public static ActivityManager getInstance() {
        if (instance == null) {
            synchronized (ActivityManager.class) {
                if (instance == null) {
                    instance = new ActivityManager();
                    activityList = new ArrayList<>();
                }
            }
        }
        return instance;
    }

    public ActivityManager() {
        activityRecordMap = new HashMap<>();
        initData();
    }

    private void initData() {
        activityRecordMap.put(ActivityId.Container_Activity, new ActivityRecord("", ContainerActivity.class));
        activityRecordMap.put(ActivityId.Test_Activity, new ActivityRecord("", TestActivity.class));
        activityRecordMap.put(ActivityId.WEB_VIEW_ACTIVITY, new ActivityRecord("", WebViewActivity.class));
        activityRecordMap.put(ActivityId.ZOOM_IMAGE_ACTIVITY, new ActivityRecord("", ZoomImageActivity.class));
    }

    /**
     * 当父Activity创建完毕后，调用
     *
     * @param activity
     */
    public void addActivity(BaseActivity activity) {
        if (activity == null) {
            return;
        }
        synchronized (activityList) {
            activityList.add(activity);
        }
    }

    public void removeActivity(BaseActivity activity) {
        if (activity == null) {
            return;
        }
        synchronized (activityList) {
            activityList.remove(activity);
        }
    }

    public BaseActivity getTopActivity() {
        if (activityList.isEmpty()) {
            return null;
        }
        return activityList.get(activityList.size() - 1);
    }

    public void finishActivity(BaseActivity activity) {
        if (activity != null) {
            synchronized (activityList) {
                activityList.remove(activity);
                activity.finish();
            }
        }
    }

    public void finishActivity(Class<?> cls) {
        for (BaseActivity activity : activityList) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
                return;
            }
        }
    }

    public void finishAllActivity() {
        synchronized (ActivityManager.class) {
            Iterator<BaseActivity> iterator = activityList.iterator();
            while (iterator.hasNext()) {
                iterator.next().finish();
            }
        }
    }

    public ActivityRecord getActivityRecord(String activityId) {
        if (activityRecordMap == null) {
            return null;
        } else {
            return activityRecordMap.get(activityId);
        }
    }
}
