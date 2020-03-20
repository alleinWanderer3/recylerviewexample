package com.hack.fragmentsinteractiondemo;

public class User {
    public String name;
    public int id;
    public boolean isMale;
    public boolean selected = false;

    public User(String name, int id, boolean isMale) {
        this.name = name;
        this.id = id;
        this.isMale = isMale;
    }
}
