package com.example.whib;

import android.icu.text.UFormat;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Entity
public class Reminder {

    @PrimaryKey(autoGenerate = true)
    public int uuid;

    @ColumnInfo
    private String title;

    @ColumnInfo
    private String description;

    @ColumnInfo
    private String date;

    @ColumnInfo
    private String time;

    @ColumnInfo
    private boolean repeat;

    public Reminder(String title, String description, String date, String time, boolean repeat) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.time = time;
        this.repeat = repeat;
    }

    public int getUuid() {
        return this.uuid;
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

    public String getTime() {
        return this.time;
    }

    public boolean getRepeat() {
        return this.repeat;
    }

    public int setUuid(int uuid){
        this.uuid = uuid;
        return this.uuid;
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

    public String setTime(String time) {
        this.time = time;
        return this.time;
    }

    public boolean setRepeat(boolean repeat) {
        this.repeat = repeat;
        return this.repeat;
    }

}
