package com.mycompany.remindme;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Created by piyush on 19/9/15.
 */
public class AlarmSchedulingService extends IntentService {

    public static final String Tag = "Reminder";
    public static final int Notification_id = 1;
    NotificationCompat.Builder builder;
    private NotificationManager mNotificationManager;

    public AlarmSchedulingService() {
        super("SchedulingService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        sendNotification(MainActivity.descText);
        Log.i(Tag, "Reminder!");
        AlarmReceiver.completeWakefulIntent(intent);

    }

    //send notification of reminder

    private void sendNotification(String Msg) {
        mNotificationManager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_launcher)
                        .setContentTitle(MainActivity.titleText)
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText(Msg))
                        .setContentText(Msg)
                        .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        mBuilder.setSound(alarmSound);

        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(Notification_id, mBuilder.build());


    }
}
