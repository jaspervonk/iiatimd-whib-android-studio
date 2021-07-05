package com.iatjt.whib;

import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class GetRemindersTask implements Runnable {
    private RecyclerView.Adapter recyclerViewAdapter;

    AppDatabase db;
    List<Reminder> reminders;
    RecyclerView recyclerView;

    public GetRemindersTask(AppDatabase db, RecyclerView recyclerView) {
        this.db = db;
        this.recyclerView = recyclerView;
    }

    @Override
    public void run() {
        // Get all reminders from DAO
        this.reminders = db.reminderDAO().getAll();

        // Make recyclerViewAdapter using the database Data
        Collections.reverse(this.reminders);
        recyclerViewAdapter = new ReminderAdapter(this.reminders);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}
