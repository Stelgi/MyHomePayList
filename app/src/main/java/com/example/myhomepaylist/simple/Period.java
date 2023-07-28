package com.example.myhomepaylist.simple;

import java.io.Serializable;

public class Period implements Serializable {
    private String title;
    private int id;

    public Period() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Period(String title, int id) {
        this.title = title;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
