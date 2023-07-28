package com.example.myhomepaylist.simple;

import java.io.Serializable;

public class Payment implements Serializable {
    private int id;
    private String title;
    private int periodId;

    public int getPeriodId() {
        return periodId;
    }

    public void setPeriodId(int periodId) {
        this.periodId = periodId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Payment() {

    }

    public Payment(int id, String title, int periodId) {
        this.id = id;
        this.title = title;
        this.periodId = periodId;
    }
}
