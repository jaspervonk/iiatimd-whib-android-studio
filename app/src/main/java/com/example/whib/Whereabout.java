package com.example.whib;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Whereabout {

    @PrimaryKey
    private int uuid;

    @ColumnInfo
    private String title;

    @ColumnInfo
    private int part;

    @ColumnInfo
    private String note;

    @ColumnInfo
    private String source;

    @ColumnInfo
    private String progress;

    @ColumnInfo
    private String kind;

    public Whereabout (int uuid, String title, int part, String note, String source, String progress, String kind){
        this.uuid = uuid;
        this.title = title;
        this.part = part;
        this.note = note;
        this.source = source;
        this.progress = progress;
        this.kind = kind;
    }

    public int getUuid(){
        return this.uuid;
    }
    public String getTitle(){
        return this.title;
    }
    public int getPart(){
        return this.part;
    }
    public String getNote(){
        return this.note;
    }
    public String getSource(){
        return this.source;
    }
    public String getProgress() {
        return this.progress;
    }
    public String getKind(){
        return this.kind;
    }

    public String setTitle(String title){
        this.title = title;
        return this.title;
    }
    public int setPart(int part){
        this.part = part;
        return this.part;
    }
    public String setNote(String note){
        this.note = note;
        return this.note;
    }
    public String setSource(String source){
        this.source = source;
        return this.source;
    }
    public String setProgress(String progress){
        this.progress = progress;
        return this.progress;
    }
    public String setKind(String kind){
        this.kind = kind;
        return this.kind;
    }
}
