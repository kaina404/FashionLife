package com.myfashionlife.base.server;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.myfashionlife.R;
import com.myfashionlife.base.component.BaseApplication;
import com.myfashionlife.ui.common.ContainerActivity;

/**
 * @author: lovexujh
 * @time: 2017/11/6
 * @descripition:
 */

public class NotificationService {

    /**
     *
     * @param temperature
     * @param weather
     * @param pollutionIndexContent
     * @param pollutionIndex
     * @param location
     * @param refreshTime
     */
    public static void updateWeatherNotification(String temperature, String weather,
                                                 String pollutionIndexContent,
                                                 String pollutionIndex, String location,
                                                 String refreshTime){



    }


    public static void start(MyService service) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(BaseApplication.getInstance().getApplicationContext())
                        .setSmallIcon(R.mipmap.duoyun)
                        .setContentTitle("My notification")
                        .setPriority(Notification.DEFAULT_VIBRATE)
                        .setAutoCancel(false)
                        .setContentText("Hello World!");
        Intent resultIntent = new Intent(BaseApplication.getInstance().getApplicationContext(), ContainerActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(BaseApplication.getInstance().getApplicationContext());
        stackBuilder.addParentStack(ContainerActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent( 0,  PendingIntent.FLAG_UPDATE_CURRENT  );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager notificationManagerCompat = (NotificationManager) BaseApplication.getInstance().getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

        service.startForeground(1, mBuilder.build());
    }

}
