package com.example.example_8_diary;

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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePicker.OnDateChangedListener, View.OnClickListener {

    Button btnEdit,btnSave,btnEnd;
    EditText edtText;
    DatePicker datePicker;
    String fileName;
    int year,month,day;

    @RequiresApi(api = Build.VERSION_CODES.O)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSave = (Button)findViewById(R.id.btnSave);
        btnEdit = (Button)findViewById(R.id.btnEdit);
        btnEnd = (Button)findViewById(R.id.btnEnd);
        edtText = (EditText)findViewById(R.id.edtText);
        datePicker = (DatePicker)findViewById(R.id.datePicker);

       //--------------------------------------------------------------

        //1.현재 날짜 가져오기
        bringCurrentDate();
        fileName = String.valueOf(year)+"_"+String.valueOf(month+1)+"_"+String.valueOf(day)+".txt";
        FileInputStream fis = null;
        try {

            fis = openFileInput(fileName);
            byte[] readData = new byte[fis.available()];
            fis.read(readData);
            edtText.setText(new String(readData));
            btnSave.setClickable(false);
            btnSave.setVisibility(View.INVISIBLE);
            btnEdit.setVisibility(View.VISIBLE);
            btnEdit.setClickable(true);
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
            edtText.setText("");
            edtText.setHint("없음");
            btnSave.setClickable(true);
            btnSave.setVisibility(View.VISIBLE);
            btnEdit.setVisibility(View.INVISIBLE);
            btnEdit.setClickable(false);
            Toast.makeText(MainActivity.this, "파일이 없습니다",Toast.LENGTH_SHORT).show();
        }

        //2.날짜 선택을 하면 이벤트 처리
        datePicker.setOnDateChangedListener(this);

        //3.저장하기
        btnSave.setOnClickListener(this);

        //4.수정하기
        btnEdit.setOnClickListener(this);

        //5.끝내기
        btnEnd.setOnClickListener(this);

    }
    //1.현재 날짜 가져오기
    public void bringCurrentDate() {
        Calendar calendar= Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        datePicker.init(year,month,day,null);
        fileName = String.valueOf(year)+"_"+String.valueOf(month+1)+"_"+String.valueOf(day)+".txt";
    }


    //2.날짜 선택을 하면 이벤트 처리
    @Override
    public void onDateChanged(DatePicker datePicker, int year, int month, int day) {
        try {
        fileName = String.valueOf(year)+"_"+String.valueOf(month+1)+"_"+String.valueOf(day)+".txt";
        FileInputStream fis = null;
        fis = openFileInput(fileName);
        byte[] readData = new byte[fis.available()];
        fis.read(readData);
        edtText.setText(new String(readData));
        btnSave.setClickable(false);
        btnSave.setVisibility(View.INVISIBLE);
        btnEdit.setVisibility(View.VISIBLE);
        btnEdit.setClickable(true);
        fis.close();
        } catch (Exception e) {
            e.printStackTrace();
            edtText.setText("");
            edtText.setHint("없음");
            btnSave.setClickable(true);
            btnSave.setVisibility(View.VISIBLE);
            btnEdit.setVisibility(View.INVISIBLE);
            btnEdit.setClickable(false);
            Toast.makeText(MainActivity.this, "파일이 없습니다",Toast.LENGTH_SHORT).show();
        }
    }

    //버튼들의 이벤트 처리
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSave:fileSave();break;
            case R.id.btnEdit:fileEdit();break;
            case R.id.btnEnd:finish();break;
        }
    }
    //수정하기
    public void fileEdit() {
        try {
            FileOutputStream fos= openFileOutput(fileName, Context.MODE_PRIVATE);
            String diartData = edtText.getText().toString();
            if(diartData.trim().length()>=1){
                fos.write(diartData.getBytes());
                fos.close();
                Toast.makeText(MainActivity.this,fileName+"수정을 완료 했어요!",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.this,"내용이 없습니다.",Toast.LENGTH_SHORT).show();
            }
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //저장하기
    public void fileSave() {
        try {
            FileOutputStream fos= openFileOutput(fileName, Context.MODE_PRIVATE);
            String diartData = edtText.getText().toString();
            if(diartData.trim().length()>=1){
                fos.write(diartData.getBytes());
                fos.close();
                Toast.makeText(MainActivity.this,fileName+"저장을 완료 했어요!",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.this,"내용이 없습니다.",Toast.LENGTH_SHORT).show();
            }
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
