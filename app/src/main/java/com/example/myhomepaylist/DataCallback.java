package com.example.myhomepaylist;

import com.example.myhomepaylist.simple.Period;

import java.util.ArrayList;

public interface DataCallback {
    void onDataLoaded(ArrayList<Period> periods);
}