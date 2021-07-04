package com.example.whib;

import android.view.View;

public class DeleteWhereaboutTask implements Runnable{

        AppDatabase db;
        int uuid;

        public DeleteWhereaboutTask(AppDatabase db, int uuid) {
            this.db = db;
            this.uuid = uuid;
        }

        @Override
        public void run() {
            // Get whereabout
            Whereabout whereabout = db.whereaboutDAO().GetWhereabout(this.uuid);
            db.whereaboutDAO().DeleteWhereabout(whereabout);
        }
}
