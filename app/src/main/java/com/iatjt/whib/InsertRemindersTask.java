package com.iatjt.whib;

public class InsertRemindersTask implements Runnable{

    AppDatabase db;
    Reminder[] reminders;

    public InsertRemindersTask(AppDatabase db, Reminder[] reminders) {
        this.db = db;
        this.reminders = reminders;
    }
    @Override
    public void run() {
        for(int i = 0; i < reminders.length; i++) {
            db.reminderDAO().InsertReminder(this.reminders[i]);
        }
    }
}
