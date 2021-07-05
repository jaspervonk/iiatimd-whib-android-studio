package com.iatjt.whib;

import android.content.Context;


public class UpdateReminderTask implements Runnable {

    AppDatabase db;
    int uuid;
    String date;

    public UpdateReminderTask(AppDatabase db, int uuid, String date) {
        this.db = db;
        this.uuid = uuid;
        this.date = date;
    }

    @Override
    public void run() {
        Reminder reminder = db.reminderDAO().GetReminder(this.uuid);
        reminder.setDate(date);

        db.reminderDAO().UpdateReminder(reminder);
    }
}
