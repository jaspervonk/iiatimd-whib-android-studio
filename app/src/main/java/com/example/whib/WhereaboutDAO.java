package com.example.whib;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WhereaboutDAO {

    @Query("SELECT * FROM whereabout")
    List<Whereabout> getAll();

    @Insert
    Long InsertWhereabout(Whereabout whereabout);

    @Delete
    void delete(Whereabout whereabout);

}
