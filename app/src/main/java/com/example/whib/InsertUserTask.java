package com.example.whib;

public class InsertUserTask implements Runnable {

    AppDatabase db;
    User user;

    public InsertUserTask(AppDatabase db, User user) {
        this.db = db;
        this.user = user;
    }

    @Override
    public void run() {
        db.userDAO().InsertUser(this.user);
    }
}
