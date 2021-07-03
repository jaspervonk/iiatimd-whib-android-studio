package com.example.whib;

public class InsertWhereaboutsTask implements Runnable {

    AppDatabase db;
    Whereabout[] whereabouts;

    public InsertWhereaboutsTask(AppDatabase db, Whereabout[] whereabouts) {
        this.db = db;
        this.whereabouts = whereabouts;
    }
    @Override
    public void run() {
        for(int i = 0; i < whereabouts.length; i++) {
            db.whereaboutDAO().InsertWhereabout(this.whereabouts[i]);
        }
    }
}
