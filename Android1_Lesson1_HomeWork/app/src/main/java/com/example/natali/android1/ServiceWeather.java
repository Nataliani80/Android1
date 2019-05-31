package com.example.natali.android1;

import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;


public class ServiceWeather extends IntentService {

    private int messageID  = 0;
    public static final String CITY_NAME = "cityName";

    public ServiceWeather() {
        super("ServiceWeather");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String cityName = intent.getExtras().getString(CITY_NAME);
        makeNote("onHandleIntentBefore");

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        makeNote("onHandleIntentAfter in the city " + cityName);
    }

    @Override
    public void onCreate() {
        makeNote("onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(@Nullable final Intent intent, int flags, int startId) {
        makeNote("onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        makeNote("onDestroy");
        super.onDestroy();
    }

    private void makeNote(String message) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Main service notification")
                .setContentText(message);
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(resultPendingIntent);
        NotificationManager notificationManager = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            String channelId = "Your_channel_id";
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Weather channel", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
            builder.setChannelId(channelId);
        }
        notificationManager.notify(messageID++, builder.build());
    }
}
