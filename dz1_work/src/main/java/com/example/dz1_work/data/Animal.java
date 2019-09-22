package com.example.dz1_work.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "animal_table")
public class Animal {
    @PrimaryKey()
    @NonNull
    public String name;
    public String description;

    public Animal(String name, String description) {
        this.name = name;
        this.description = description;
    }

}
