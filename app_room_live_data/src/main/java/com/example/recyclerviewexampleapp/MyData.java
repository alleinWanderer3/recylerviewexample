package com.example.recyclerviewexampleapp;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class MyData {

    public String name, surname;
    public int year;
    public int type;
    @PrimaryKey(autoGenerate = true)
    public int id;

    public MyData(String name, String surname, int year, int type) {
        this.name = name;
        this.surname = surname;
        this.year = year;
        this.type = type;

    }
    @Ignore
    public MyData(String name, String surname, int year, int type, int id) {
        this.name = name;
        this.surname = surname;
        this.year = year;
        this.type = type;
        this.id = id;
    }
}