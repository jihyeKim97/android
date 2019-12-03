package com.example.example_mydatecalender;
//데이타
public class MonthItem {
    private int dayValue;

    public MonthItem() {
    }

    public MonthItem(int dayValue) {
        this.dayValue = dayValue;
    }

    public int getDayValue() {
        return dayValue;
    }

    public void setDayValue(int dayValue) {
        this.dayValue = dayValue;
    }
}
