package com.myfashionlife.manager;

import android.app.Activity;

import com.myfashionlife.base.component.BaseActivity;

/**
 * Created by lovexujh on 2017/10/9
 */

public class ActivityRecord {

    private final Class<? extends Activity> activityClz;
    private final String title;

    public ActivityRecord(String title, Class<? extends BaseActivity> clz) {
        this.title = title;
        this.activityClz = clz;
    }

    public Class<? extends Activity> getActivityClz() {
        return activityClz;
    }

    public String getTitle() {
        return title;
    }
}
