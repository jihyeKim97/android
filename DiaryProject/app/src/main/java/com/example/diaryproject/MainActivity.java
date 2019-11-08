package com.example.diaryproject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DatePicker.OnDateChangedListener {

    Button btnSave;
    EditText edtText;
    DatePicker datePicker;
    String fileName;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSave = (Button)findViewById(R.id.btnSave);
        edtText = (EditText)findViewById(R.id.edtText);
        datePicker = (DatePicker)findViewById(R.id.datePicker);

        //1.현재 날짜 가져오기
        pickcurrentDate();
        //2.날짜 선택을 하면 이벤트 처리
        datePicker.setOnDateChangedListener(this);
        //3.일기장을 저장하는 기능 이벤트
        btnSave.setOnClickListener(this);

    }
    //1.현재 날짜 가져오기
    public void pickcurrentDate() {
       Calendar calendar= Calendar.getInstance();
       int year = calendar.get(Calendar.YEAR);
       int month = calendar.get(Calendar.MONTH);
       int day = calendar.get(Calendar.DAY_OF_MONTH);
        datePicker.init(year,month,day,null);
        fileName = String.valueOf(year)+"_"+String.valueOf(month)+"_"+String.valueOf(day)+".txt";
    }

    //2.날짜 선택을 하면 이벤트 처리
    @Override
    public void onDateChanged(DatePicker datePicker, int year, int month, int day) {
        try {
            fileName = String.valueOf(year)+"_"+String.valueOf(month+1)+"_"+String.valueOf(day)+".txt";
            FileInputStream fis =null;
            fis =openFileInput(fileName);
            byte[] readDiary = new byte[fis.available()];
            fis.read(readDiary);
            edtText.setText(new String(readDiary));
            btnSave.setText("수정");
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
            toastDisplay(fileName+"저장할 일기가 존재 하지 않습니다.");
            btnSave.setText("저장");
            edtText.setText("");
        }
    }

    //3.일기장을 저장하는 기능 이벤트
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSave: saveDiary(); break;
        }
    }

    //일기를 저장하는 함수
    private void saveDiary() {
        try {
            FileOutputStream fos =openFileOutput(fileName, Context.MODE_PRIVATE);
            String diaryData = edtText.getText().toString();
            if(diaryData.trim().length()>=1){
                fos.write(diaryData.getBytes());
                fos.close();
                toastDisplay(fileName+"저장완료!");
            }else{
                toastDisplay("내용이 없습니다.");
            }
            fos.close();
           /* if(btnSave.getText().toString().equals("수정")){
                btnSave.setText("저장");
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //토스트 메세지
    private void toastDisplay(String s) {
        Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();
    }

}
