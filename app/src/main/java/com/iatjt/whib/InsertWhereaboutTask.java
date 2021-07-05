package com.iatjt.whib;

public class InsertWhereaboutTask implements Runnable {

    AppDatabase db;
    Whereabout whereabout;

    public InsertWhereaboutTask(AppDatabase db, Whereabout whereabout) {
        this.db = db;
        this.whereabout = whereabout;
    }
    @Override
    public void run() {
        db.whereaboutDAO().InsertWhereabout(this.whereabout);
    }
}
