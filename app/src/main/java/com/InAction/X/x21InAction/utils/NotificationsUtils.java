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

import com.InAction.X.x21InAction.R;
import com.InAction.X.x21InAction._main.view.MainActivity;
import com.InAction.X.x21InAction.tasks.model.Task;

public class NotificationsUtils {

    public static final String ID_NOTIFICATION_CHANNEL = "id-notification-channel";
    public static final String NAME_NOTIFICATION_CHANNEL = "name-notification-channel";
    private Context context;

    public NotificationsUtils(Context context) {

        this.context = context;
    }

    public void notifyTimeFor(Task task) {

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel notificationChannel = new NotificationChannel(ID_NOTIFICATION_CHANNEL, NAME_NOTIFICATION_CHANNEL, NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        Notification notification = getTaskNotification(task.getHabitName(), task.getName());

        notificationManager.notify(1000, notification);
    }

    private Notification getTaskNotification(String habitName, String name) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, ID_NOTIFICATION_CHANNEL)
                .setSmallIcon(R.drawable.ic_stat_ac_unit)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_stat_bubble_chart))
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
                .setSmallIcon(R.drawable.ic_stat_ac_unit)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_stat_bubble_chart))
                .setContentTitle("Start programming : " + habitName)
                .setContentText("now you can start finishing tasks")
                .setStyle(new NotificationCompat.BigTextStyle().setBigContentTitle("Start programming : " + habitName))
                .setStyle(new NotificationCompat.BigTextStyle().bigText("now you can start finishing tasks\nclick for to see what tasks you have"))
                .setContentIntent(PendingIntent.getActivity(context, 77, new Intent(context, MainActivity.class), PendingIntent.FLAG_CANCEL_CURRENT))
                .setAutoCancel(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        }

        return builder.build();
    }

    private Notification getCountingFinishNotification(String habitName) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, ID_NOTIFICATION_CHANNEL)
                .setSmallIcon(R.drawable.ic_stat_ac_unit)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_stat_bubble_chart))
                .setContentTitle("End programming : " + habitName)
                .setContentText("You finish 21 day of programming ")
                .setStyle(new NotificationCompat.BigTextStyle().bigText("You finish 21 day of programming"))
                .setContentIntent(PendingIntent.getActivity(context, 66, new Intent(context, MainActivity.class), PendingIntent.FLAG_CANCEL_CURRENT))
                .setAutoCancel(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        }

        return builder.build();
    }

    public void notifyCountingEnd(String habitName) {

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel notificationChannel = new NotificationChannel(ID_NOTIFICATION_CHANNEL, NAME_NOTIFICATION_CHANNEL, NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        Notification notification = getCountingFinishNotification(habitName);

        notificationManager.notify(1000, notification);
    }
}
