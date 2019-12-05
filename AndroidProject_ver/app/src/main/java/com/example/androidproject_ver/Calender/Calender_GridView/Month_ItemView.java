package com.example.androidproject_ver.Calender.Calender_GridView;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

//부분화면
public class Month_ItemView extends AppCompatTextView {
    private MonthItem item;

    public Month_ItemView(Context context) {
        super(context);
        init();
    }

    public Month_ItemView(Context context, AttributeSet attrs, MonthItem item) {
        super(context, attrs);
        init();
    }

    private void init() {
        setBackgroundColor(Color.GRAY);
    }

    public MonthItem getItem() {
        return item;
    }

    public void setItem(MonthItem item) {
        this.item = item;

        int day = item.getDayValue();
        if (day != 0) {
            setText(String.valueOf(day));
        } else {
            setText("");
        }

    }


}

