package com.example.whib;

import android.util.Log;

public class GetReminderTask implements Runnable {

    AppDatabase db;

    public GetReminderTask(AppDatabase db) {
        this.db = db;
    }

    @Override
    public void run() {
        String title = db.reminderDAO().getAll().get(0).getTitle();
        Log.d("getReminderTask", title);
    }
}
