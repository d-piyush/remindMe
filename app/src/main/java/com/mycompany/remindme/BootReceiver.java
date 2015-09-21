package com.mycompany.remindme;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by piyush on 19/9/15.
 */

public class BootReceiver extends BroadcastReceiver {
    AlarmReceiver alarm = new AlarmReceiver();

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            alarm.setAlarm(context);
        }
    }
}
