package com.example.whib;

import java.util.Date;

public class Reminder {

    private String title;
    private String description;
    private String date;

    public Reminder(String title, String description, String date) {
        this.title = title;
        this.description = description;
        this.date = date;
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
}
