package com.example.whib;

import android.util.Log;

public class InsertReminderTask implements Runnable {

    AppDatabase db;
    Reminder reminder;

    public InsertReminderTask(AppDatabase db, Reminder reminder) {
        this.db = db;
        this.reminder = reminder;
    }
    @Override
    public void run() {
        db.reminderDAO().InsertReminder(this.reminder);
    }
}
