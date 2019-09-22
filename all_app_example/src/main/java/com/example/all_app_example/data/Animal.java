package com.example.all_app_example.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "animal_table")
public class Animal {
    @PrimaryKey
    @NonNull
    public String name;
    public String description;

    public Animal(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
