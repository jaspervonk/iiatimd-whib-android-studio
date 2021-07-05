package com.iatjt.whib;

import android.os.Looper;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GetUserTask implements Runnable {

    private AppDatabase db;
    private List<User> user;
    private WelcomeActivity callback;

    public GetUserTask(AppDatabase db, WelcomeActivity wa) {
        this.db = db;
        this.callback = wa;
    }

    @Override
    public void run() {
        // Get all users from DAO
        this.user = db.userDAO().getAll();

        Log.d("GetUserTask", "" + this.user.size());

        // Check if there is a user and return results to WelcomeActivity
        if(this.user.size() == 0){
            User dummyUser = new User("dummy", "dummy");
            this.callback.userFoundCallback(false, dummyUser);
        } else {
            this.callback.userFoundCallback(true, this.user.get(0));
        }




    }
}
