package com.iatjt.whib;

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
        String title = intent.getStringExtra("title");
        String description = intent.getStringExtra("description");
        Log.d("received", "message received at " + System.currentTimeMillis() + title);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, "notifyWhib")
                .setContentTitle(title)
                .setContentText(description)
                .setSmallIcon(R.mipmap.ic_logo)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        notificationManager.notify(200, notificationBuilder.build());

//        AppDatabase db = AppDatabase.getInstance(context);
//        new Thread(new InsertReminderTask(db, newReminder).start();

    }
}
