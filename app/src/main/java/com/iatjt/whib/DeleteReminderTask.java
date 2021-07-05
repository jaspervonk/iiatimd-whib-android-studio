package com.iatjt.whib;

public class DeleteReminderTask implements Runnable {

    AppDatabase db;
    int uuid;

    public DeleteReminderTask(AppDatabase db, int uuid) {
        this.db = db;
        this.uuid = uuid;
    }

    @Override
    public void run() {
        // Get reminder
        Reminder reminder = db.reminderDAO().GetReminder(this.uuid);
        db.reminderDAO().DeleteReminder(reminder);
    }
}
