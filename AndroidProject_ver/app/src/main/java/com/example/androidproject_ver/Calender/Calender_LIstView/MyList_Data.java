package com.example.androidproject_ver.Calender.Calender_LIstView;

public class MyList_Data {

    private String date;
    private String coinrdo;
    private String coinedttxt;
    private String coinfilter;
    private int image;

    public MyList_Data(String date, String coinrdo, String coinedttxt, String coinfilter, int image) {
        this.date = date;
        this.coinrdo = coinrdo;
        this.coinedttxt = coinedttxt;
        this.coinfilter = coinfilter;
        this.image = image;
    }

    //리스트나 데이타형식에 맞게
    public MyList_Data(String coinrdo, String coinfilter, String coinedttxt) {
        this.coinrdo = coinrdo;
        this.coinfilter = coinfilter;
        this.coinedttxt = coinedttxt;
    }
//db에 들어갈꺼
    public MyList_Data(String date, String coinrdo, String coinfilter, String coinedttxt) {
        this.date = date;
        this.coinrdo = coinrdo;
        this.coinfilter = coinfilter;
        this.coinedttxt = coinedttxt;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCoinrdo() {
        return coinrdo;
    }

    public void setCoinrdo(String coinrdo) {
        this.coinrdo = coinrdo;
    }

    public String getCoinedttxt() {
        return coinedttxt;
    }

    public void setCoinedttxt(String coinedttxt) {
        this.coinedttxt = coinedttxt;
    }

    public String getCoinfilter() {
        return coinfilter;
    }

    public void setCoinfilter(String coinfilter) {
        this.coinfilter = coinfilter;
    }
}
