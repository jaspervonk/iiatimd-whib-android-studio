package com.iatjt.whib;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.text.SimpleDateFormat;

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
        int reminderId = intent.getIntExtra("reminderId", 0);
        long previousTime = intent.getLongExtra("previousTime", 0);
        long weekMilliseconds = 7 * 86400000;
        long nextTime = previousTime + weekMilliseconds;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String date = formatter.format(nextTime);

        AppDatabase db = AppDatabase.getInstance(context);

        Log.d("REMINDER ID BEFORE", "" + reminderId);
        if(checkBox.equals("false")) {
            new Thread(new DeleteReminderTask(db, reminderId)).start();
        } else {
            Log.d("REMINDER ID AFTER", "" + reminderId);
            // Change reminder date and time to next alarm
            new Thread(new UpdateReminderTask(db, reminderId, date)).start();
        }

    }
}
