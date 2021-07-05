package com.iatjt.whib;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ReminderDAO {

    @Query("SELECT * FROM reminder")
    List<Reminder> getAll();

    @Query("SELECT * FROM reminder WHERE uuid=:uuid ")
    Reminder GetReminder(int uuid);

    @Insert
    Long InsertReminder(Reminder reminder);

    @Delete
    void DeleteReminder(Reminder reminder);

}
