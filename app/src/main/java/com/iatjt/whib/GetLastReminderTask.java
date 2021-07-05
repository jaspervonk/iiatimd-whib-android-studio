package com.iatjt.whib;

import android.view.View;
import android.widget.TextView;

public class GetLastReminderTask implements Runnable {

    AppDatabase db;
    Reminder reminder;
    public int reminderId;

    public GetLastReminderTask(AppDatabase db) {
        this.db = db;
    }

    @Override
    public void run() {
        reminder = db.reminderDAO().GetLastReminder();
        reminderId = reminder.getUuid();
    }
}
