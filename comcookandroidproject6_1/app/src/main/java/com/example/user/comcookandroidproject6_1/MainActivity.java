package com.example.user.comcookandroidproject6_1;

import android.graphics.Color;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {
    Chronometer chronometer;
    FrameLayout frame;
    RadioGroup rdoGroup;
    RadioButton rdoHour, rdoDay;
    CalendarView calendarView;
    TimePicker timePicker;
    TextView Year, Month, Day,Hour, Minute, Message;
    String strYear, strMonth, strDay, strHour, strMinute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("시간예약(수정)");

        chronometer=(Chronometer)findViewById(R.id.chronometer);
        frame=(FrameLayout)findViewById(R.id.frame);

        rdoGroup = (RadioGroup)findViewById(R.id.rdoGroup);
        rdoHour = (RadioButton)findViewById(R.id.rdoHour);
        rdoDay = (RadioButton)findViewById(R.id.rdoDay);

        calendarView = (CalendarView)findViewById(R.id.calendarView);
        timePicker = (TimePicker)findViewById(R.id.timePicker);

        Year = (TextView)findViewById(R.id.Year);
        Month = (TextView)findViewById(R.id.Month);
        Day = (TextView)findViewById(R.id.Day);
        Hour = (TextView)findViewById(R.id.Hour);
        Minute = (TextView)findViewById(R.id.Minute);
        Message = (TextView)findViewById(R.id.Message);

        rdoGroup.setVisibility(View.INVISIBLE);
        calendarView.setVisibility(View.INVISIBLE);
        timePicker.setVisibility(View.INVISIBLE);

        chronometer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                rdoGroup.setVisibility(View.VISIBLE);
            }
        });

        rdoHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarView.setVisibility(View.INVISIBLE);
                timePicker.setVisibility(View.VISIBLE);
            }
        });
        rdoDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarView.setVisibility(View.VISIBLE);
                timePicker.setVisibility(View.INVISIBLE);
            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NotNull CalendarView view, int year, int month, int dayOfMonth) {
                strYear=String.valueOf(year);
                strMonth=String.valueOf(month);
                strDay = String.valueOf(dayOfMonth);
            }
        });
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                strHour = String.valueOf(hourOfDay);
                strMinute = String.valueOf(minute);
            }
        });
        Year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rdoGroup.setVisibility(View.INVISIBLE);
                calendarView.setVisibility(View.INVISIBLE);
                timePicker.setVisibility(View.INVISIBLE);

                chronometer.stop();
                Year.setText(strYear+"년 ");
                Month.setText(strMonth+1+"월");
                Day.setText(strDay+"일");
                Hour.setText(strHour+"시");
                Minute.setText(strMinute + "분");
                Message.setText("예약완료");


            }
        });
    }
}
