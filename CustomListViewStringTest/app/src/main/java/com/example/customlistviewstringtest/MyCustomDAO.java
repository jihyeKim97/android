package com.example.customlistviewstringtest;


import android.content.Context;
import android.view.LayoutInflater;

import java.util.ArrayList;

public class MyCustomDAO {
    private String stringData;

    public MyCustomDAO() {
    }

    public MyCustomDAO(String stringData) {
        this.stringData = stringData;
    }

    public String getStringData() {
        return stringData;
    }

    public void setStringData(String stringData) {
        this.stringData = stringData;
    }
}
