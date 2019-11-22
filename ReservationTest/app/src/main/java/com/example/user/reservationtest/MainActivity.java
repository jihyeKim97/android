package com.example.user.reservationtest;

        import android.graphics.Color;
        import android.os.SystemClock;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.CalendarView;
        import android.widget.Chronometer;
        import android.widget.RadioButton;
        import android.widget.TextView;
        import android.widget.TimePicker;

        import org.jetbrains.annotations.NotNull;

        import java.time.Month;

public class MainActivity extends AppCompatActivity {

    Chronometer chronometer;
    Button btnStart, btnEnd;
    RadioButton rdoCalendar, rdoTime;
    CalendarView calendarView;
    TimePicker timePicker;
    TextView tvYear, tvMonth, tvDay,tvHour, tvMinute, tvMessage;
    String strYear, strMonth, strDay, strhour, strMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("시간예약");


        chronometer = (Chronometer)findViewById(R.id.chronometer);

        btnStart = (Button)findViewById(R.id.btnStart);
        btnEnd = (Button)findViewById(R.id.btnEnd);

        rdoCalendar = (RadioButton)findViewById(R.id.rdoCalendar);
        rdoTime = (RadioButton)findViewById(R.id.rdoTime);

        calendarView = (CalendarView)findViewById(R.id.calendarView);
        timePicker = (TimePicker)findViewById(R.id.timePicker);

        tvYear = (TextView)findViewById(R.id.tvYear);
        tvMonth = (TextView)findViewById(R.id.tvMonth);
        tvDay = (TextView)findViewById(R.id.tvDay);
        tvHour = (TextView)findViewById(R.id.tvHour);
        tvMinute = (TextView)findViewById(R.id.tvMinute);
        tvMessage = (TextView)findViewById(R.id.tvMessage);

        calendarView.setVisibility(View.INVISIBLE);
        timePicker.setVisibility(View.INVISIBLE);
        //1. 라디오 버튼의 켈린더 뷰 를 보여주기 기능
        rdoCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarView.setVisibility(View.VISIBLE);
                timePicker.setVisibility(View.INVISIBLE);
            }
        });
        rdoTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarView.setVisibility(View.INVISIBLE);
                timePicker.setVisibility(View.VISIBLE);
            }
        });
        //2.예약시작을 누르면 크로노미터가 작동함
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //elapsedRealtime()=long type
                //값을 초기화 시킨다
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                chronometer.setTextColor(Color.RED);
            }
        });
        //3. 컬렌더뷰를 선택했을때 현재의 년,월,일 값을  저장한다
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
                strhour = String.valueOf(hourOfDay);
                strMinute = String.valueOf(minute);
            }
        });

        //4.예약완료를 했을 때 처리하는 기능
        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //크로노 미터 정지
                chronometer.stop();
                tvYear.setText(strYear+"년 ");
                tvMonth.setText(strMonth+1+"월");
                tvDay.setText(strDay+"일");
                tvHour.setText(strhour+"시");
                tvMinute.setText(strMinute + "분");
                tvMessage.setText("예약완료");

            }
        });
    }
}
