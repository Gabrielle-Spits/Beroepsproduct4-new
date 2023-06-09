package com.example.beroepsproduct4.Bluetooth;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.example.beroepsproduct4.MainActivity;
import com.example.beroepsproduct4.R;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Tasks {
    Context getApplicationContext;
    NotificationManager notificationManager;

    /**
     * @param getApplicationContext to get this:
     *                              getApplicationContext()
     * @param notificationManager   to get this:
     *                              (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
     */
    public Tasks(Context getApplicationContext, NotificationManager notificationManager) {
        this.getApplicationContext = getApplicationContext;
        this.notificationManager = notificationManager;
    }

    @SuppressLint("ObsoleteSdkInt")
    public void sendNotification(String message, String title) {
        Intent intent = new Intent(getApplicationContext, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext, 0, intent,
                PendingIntent.FLAG_IMMUTABLE);

        String channelId = "some_channel_id";
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(getApplicationContext, channelId)
                        .setSmallIcon(R.mipmap.ic_launcher_round)
                        .setContentTitle(title)
                        .setContentText(message)
                        .setAutoCancel(true)
                        .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

        NotificationManager notificationManager = this.notificationManager;

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(channel);
        }

        assert notificationManager != null;
        notificationManager.notify(0, notificationBuilder.build());
    }
}
