package com.example.whib;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ReminderDAO {

    @Query("SELECT * FROM reminder")
    List<Reminder> getAll();

    @Insert
    void InsertReminder(Reminder reminder);



    @Delete
    void delete(Reminder reminder);

}
