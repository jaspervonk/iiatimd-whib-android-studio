package com.iatjt.whib;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class ReminderBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
//        int uuid = intent.getIntExtra("uuid");
        Intent onClickIntent = new Intent(context, RemindersActivity.class);
        String title = intent.getStringExtra("title");
        String description = intent.getStringExtra("description");
        Log.d("received", "message received at " + System.currentTimeMillis() + title);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, "notifyWhib")
                .setContentTitle(title)
                .setContentText(description)
                .setSmallIcon(R.mipmap.ic_logo)
                .setAutoCancel(true)
                .setContentIntent(PendingIntent.getActivity(
                        context,
                        0,
                        onClickIntent,
                        0
                ))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        notificationManager.notify(200, notificationBuilder.build());

        String checkBox = intent.getStringExtra("checkBox");
        Log.d("CHECKBOX", "" + checkBox);
        int reminderId = intent.getIntExtra("reminderId", 0);

        Log.d("checkbox" , "" + checkBox);
        Log.d("USERID" , "" + reminderId);
        AppDatabase db = AppDatabase.getInstance(context);
        new Thread(new DeleteReminderTask(db, reminderId)).start();
//
//        if(checkBox.equals("false")) {
//        }

    }
}
