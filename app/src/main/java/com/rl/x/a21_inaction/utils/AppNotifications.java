package com.rl.x.a21_inaction.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.rl.x.a21_inaction._main.view.MainActivity;
import com.rl.x.a21_inaction.tasks.model.Task;

public class AppNotifications {

    public static final String ID_NOTIFICATION_CHANNEL = "id-notification-channel";
    public static final String NAME_NOTIFICATION_CHANNEL = "name-notification-channel";
    private Context context;

    public AppNotifications(Context context) {

        this.context = context;
    }

    public void notifyWith(Task task) {

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel notificationChannel = new NotificationChannel(ID_NOTIFICATION_CHANNEL, NAME_NOTIFICATION_CHANNEL, NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        Notification notification = getNotification(task.getHabitName(), task.getName());

        notificationManager.notify(1000, notification);
    }

    private Notification getNotification(String habitName, String name) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, ID_NOTIFICATION_CHANNEL)
                .setSmallIcon(android.support.v4.R.drawable.notify_panel_notification_icon_bg)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), android.support.v4.R.drawable.notification_action_background))
                .setContentTitle("habit to program : " + habitName)
                .setContentText("its time for : " + name)
                .setStyle(new NotificationCompat.BigTextStyle().setBigContentTitle(" You are to program : " + habitName))
                .setStyle(new NotificationCompat.BigTextStyle().bigText("its time for : " + name))
                .setContentIntent(PendingIntent.getActivity(context, 2121, new Intent(context, MainActivity.class), PendingIntent.FLAG_CANCEL_CURRENT))
                .setAutoCancel(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        }

        return builder.build();
    }
}
