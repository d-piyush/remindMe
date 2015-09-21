package com.mycompany.remindme;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.content.WakefulBroadcastReceiver;

import java.util.Calendar;

/**
 * Created by piyush on 19/9/15.
 */
public class AlarmReceiver extends WakefulBroadcastReceiver {

    // The app's AlarmManager, which provides access to the system alarm services.
    private AlarmManager alarmMgr;
    // The pending intent that is triggered when the alarm fires.
    private PendingIntent alarmIntent;


    @Override
    public void onReceive(Context context, Intent intent) {

        Intent service = new Intent(context, AlarmSchedulingService.class);
        startWakefulService(context, service);


    }

    public void setAlarm(Context context) {
        alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        calendar.set(Calendar.HOUR_OF_DAY, MainActivity.hour);
        calendar.set(Calendar.MINUTE, MainActivity.minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.YEAR, MainActivity.year);
        calendar.set(Calendar.MONTH, MainActivity.month);
        calendar.set(Calendar.DATE, MainActivity.day);

        alarmMgr.set(AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(), alarmIntent);

        /*
        Enable {@code BootReceiver} to automatically restart the alarm when the
        device is rebooted.
        */

        ComponentName receiver = new ComponentName(context, BootReceiver.class);
        PackageManager pm = context.getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);

    }
}
