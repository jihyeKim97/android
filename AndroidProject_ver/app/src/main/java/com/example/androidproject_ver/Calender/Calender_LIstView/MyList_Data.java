package com.example.androidproject_ver.Calender.Calender_LIstView;

public class MyList_Data {

    private String date;
    private String coinrdo;
    private String coinedttxt;
    private String coinfilter;
    private String edtwhere;

    public MyList_Data(String date, String coinrdo, String coinedttxt, String coinfilter, String edtwhere) {
        this.date = date;
        this.coinrdo = coinrdo;
        this.coinedttxt = coinedttxt;
        this.coinfilter = coinfilter;
        this.edtwhere = edtwhere;
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

    public String getEdtwhere() { return edtwhere; }

    public void setEdtwhere(String edtwhere) { this.edtwhere = edtwhere; }

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
