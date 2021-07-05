package com.iatjt.whib;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import static android.content.Context.ALARM_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

public class InsertReminderTask implements Runnable {

    AppDatabase db;
    Reminder reminder;
    Context context;

    public InsertReminderTask(AppDatabase db, Reminder reminder) {
        this.db = db;
        this.reminder = reminder;

    }
    @Override
    public void run() {
        db.reminderDAO().InsertReminder(this.reminder);
        Log.d("GEEEEEEEEF", "" + this.reminder.getUuid());
    }

}
