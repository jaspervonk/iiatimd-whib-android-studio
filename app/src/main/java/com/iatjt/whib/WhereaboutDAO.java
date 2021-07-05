package com.iatjt.whib;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WhereaboutDAO {

    @Query("SELECT * FROM whereabout")
    List<Whereabout> getAll();

    @Query("SELECT * FROM whereabout WHERE uuid=:uuid ")
    Whereabout GetWhereabout(int uuid);

    @Insert
    Long InsertWhereabout(Whereabout whereabout);

    @Update
    void UpdateWhereabout(Whereabout whereabout);

    @Delete
    void DeleteWhereabout(Whereabout whereabout);

}
