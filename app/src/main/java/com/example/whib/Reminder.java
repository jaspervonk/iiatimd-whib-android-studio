package com.example.whib;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Reminder {

    @PrimaryKey
    private int uid;

    @ColumnInfo
    private String title;

    @ColumnInfo
    private String description;

    @ColumnInfo
    private String date;

    public Reminder(int uid, String title, String description, String date) {
        this.uid = uid;
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public int getUid() {
        return this.uid;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public String getDate() {
        return this.date;
    }

    public String setTitle(String title) {
        this.title = title;
        return this.title;
    }

    public String setDescription(String description) {
        this.description = description;
        return this.description;
    }

    public String setDate(String date) {
        this.date = date;
        return this.date;
    }
}
