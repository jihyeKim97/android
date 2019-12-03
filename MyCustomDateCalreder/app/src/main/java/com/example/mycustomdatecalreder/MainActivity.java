package com.example.mycustomdatecalreder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnPrevious,btnNext;
    private GridView gvCalender;
    private TextView tvYearMonth;
    private MonthAdapter monthAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);
        gvCalender = findViewById(R.id.gvCalender);
        tvYearMonth = findViewById(R.id.tvYearMonth);

        //1.어뎁터를 생성
        monthAdapter = new MonthAdapter(this);
        gvCalender.setAdapter(monthAdapter);

        btnNext.setOnClickListener(this);
        btnPrevious.setOnClickListener(this);

        setYearMonth();

        gvCalender.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MonthItem curItem = (MonthItem)monthAdapter.getItem(position);
                int day = curItem.getDayValue();
                String message = monthAdapter.curYear+"년"+monthAdapter.curMonth+"월"+day+"일";
                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnNext:
                monthAdapter.setNextMonth();
                monthAdapter.notifyDataSetChanged();
                setYearMonth();
                break;
            case R.id.btnPrevious:
                monthAdapter.setPreviousMonth();
                monthAdapter.notifyDataSetChanged();
                setYearMonth();
                break;
        }
    }

    // 월 표시 텍스트 설정
    private void setYearMonth() {
        String yearMonth = monthAdapter.curYear+"년"+(monthAdapter.curMonth+1) + "월";
        tvYearMonth.setText(yearMonth);
    }
}
