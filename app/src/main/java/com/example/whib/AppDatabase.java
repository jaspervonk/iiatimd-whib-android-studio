package com.example.whib;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Reminder.class}, version = 7)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ReminderDAO reminderDAO();

    private static AppDatabase instance;

    static synchronized AppDatabase getInstance(Context context) {
        if(instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static AppDatabase create(final Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "whib").fallbackToDestructiveMigration().build();
    }
}
