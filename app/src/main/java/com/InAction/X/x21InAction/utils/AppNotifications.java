package com.InAction.X.x21InAction.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.InAction.X.x21InAction._main.view.MainActivity;
import com.InAction.X.x21InAction.tasks.model.Task;

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


    public void notifyCountingStart(String habitName) {

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel notificationChannel = new NotificationChannel(ID_NOTIFICATION_CHANNEL, NAME_NOTIFICATION_CHANNEL, NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        Notification notification = getCountingStartNotification(habitName);

        notificationManager.notify(2000, notification);
    }

    private Notification getCountingStartNotification(String habitName) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, ID_NOTIFICATION_CHANNEL)
                .setSmallIcon(android.support.v4.R.drawable.notify_panel_notification_icon_bg)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), android.support.v4.R.drawable.notification_action_background))
                .setContentTitle("Start programming : " + habitName)
                .setContentText("now you can start finishing tasks\nclick for to see what tasks you have")
                .setStyle(new NotificationCompat.BigTextStyle().setBigContentTitle("Start programming : " + habitName))
                .setStyle(new NotificationCompat.BigTextStyle().bigText("now you can start finishing tasks\nclick for to see what tasks you have"))
                .setContentIntent(PendingIntent.getActivity(context, 32323, new Intent(context, MainActivity.class), PendingIntent.FLAG_CANCEL_CURRENT))
                .setAutoCancel(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        }

        return builder.build();
    }
}
