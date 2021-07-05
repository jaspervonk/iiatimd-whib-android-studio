package com.iatjt.whib;

import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GetWhereaboutsTask implements Runnable {
    private RecyclerView.Adapter recyclerViewAdapter;

    AppDatabase db;
    List<Whereabout> whereabouts;
    RecyclerView recyclerView;

    public GetWhereaboutsTask(AppDatabase db, RecyclerView recyclerView) {
        this.db = db;
        this.recyclerView = recyclerView;
    }

    @Override
    public void run() {
        // Get all reminders from DAO
        this.whereabouts = db.whereaboutDAO().getAll();
        Log.d("whereaboutTask", "" + this.whereabouts.size());

        // Make recyclerViewAdapter using the database Data
        recyclerViewAdapter = new WhereaboutAdapter(this.whereabouts);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}
