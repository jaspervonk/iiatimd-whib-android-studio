package com.iatjt.whib;

import android.os.Looper;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class GetWhereaboutsTask implements Runnable {

    private AppDatabase db;
    private RecyclerView recyclerView;

    public List<Whereabout> whereabouts;
    public WhereaboutAdapter recyclerViewAdapter;


    public GetWhereaboutsTask(AppDatabase db, RecyclerView recyclerView) {
        this.db = db;
        this.recyclerView = recyclerView;
    }

    @Override
    public void run() {
        Looper.prepare();

        // Get all reminders from DAO
        this.whereabouts = db.whereaboutDAO().getAll();
        Log.d("whereaboutTask", "" + this.whereabouts.size());

        // Make recyclerViewAdapter using the database Data
        Collections.reverse(this.whereabouts);
        recyclerViewAdapter = new WhereaboutAdapter(this.whereabouts);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}
