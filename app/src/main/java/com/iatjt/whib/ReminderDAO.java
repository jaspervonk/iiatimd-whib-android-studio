package com.iatjt.whib;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ReminderDAO {

    @Query("SELECT * FROM reminder")
    List<Reminder> getAll();

    @Query("SELECT * FROM reminder WHERE uuid=:uuid ")
    Reminder GetReminder(int uuid);

    @Query("SELECT * FROM reminder WHERE uuid=(SELECT max(uuid) FROM reminder)")
    Reminder GetLastReminder();

    @Insert
    Long InsertReminder(Reminder reminder);

    @Update
    void UpdateReminder(Reminder reminder);

    @Delete
    void DeleteReminder(Reminder reminder);

}
