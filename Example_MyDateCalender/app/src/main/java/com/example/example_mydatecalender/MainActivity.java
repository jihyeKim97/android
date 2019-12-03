package com.example.example_mydatecalender;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    private TextView btnPrevious,btnNext;
    private GridView gvCalender;
    private TextView tvYearMonth;
    private MonthAdapter monthAdapter;
    private String add;


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
        gvCalender.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                View dialogView = View.inflate(MainActivity.this, R.layout.dialog, null);
                final EditText edtMemo = dialogView.findViewById(R.id.edtMemo);
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setView(dialogView);
                dialog.setPositiveButton("내 맘속에 저장 ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            Toast.makeText(getApplicationContext(), edtMemo.getText().toString(), Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "n-n", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                dialog.setNegativeButton("안해안해!", null);
                dialog.show();
                return true;
            }
        });
        gvCalender.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MonthItem curItem = (MonthItem)monthAdapter.getItem(position);
                if(curItem.getDayValue() !=0){
                    int day = curItem.getDayValue();
                    String message = monthAdapter.curYear+"년"+(monthAdapter.curMonth+1)+"월"+day+"일";
                    Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"돌아가",Toast.LENGTH_LONG).show();
                }
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

