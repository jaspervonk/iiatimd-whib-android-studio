package com.iatjt.whib;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey
    @NonNull
    public String token;

    @ColumnInfo
    private String name;


    public User(String token, String name){
        this.token = token;
        this.name = name;
    }

    public String getToken(){
        return this.token;
    }
    public String getName(){
        return this.name;
    }


    public String setToken(String token){
        this.token = token;
        return this.token;
    }
    public String setName(String name){
        this.name = name;
        return this.name;
    }
}
