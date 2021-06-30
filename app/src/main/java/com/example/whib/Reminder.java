package com.example.whib;

import java.util.Date;

public class Reminder {

    private String title;
    private String description;
    private Date date;

    public Reminder(String title, String description, Date date) {
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

    public Date getDate() {
        return this.date;
    }
}
