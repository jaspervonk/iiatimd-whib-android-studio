package com.example.whib;

import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        Log.d("poep", "" + this.reminders.size());

        // Make recyclerViewAdapter using the database Data
        recyclerViewAdapter = new ReminderAdapter(this.reminders);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    public List<Reminder> getReminders(){
        return this.reminders;
    }
}
