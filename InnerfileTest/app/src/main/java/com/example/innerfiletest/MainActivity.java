package com.example.innerfiletest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnmemorylight,btnmemorylightread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnmemorylight = (Button)findViewById(R.id.btnmemorylight);
        btnmemorylightread = (Button)findViewById(R.id.btnmemorylightread);

        btnmemorylight.setOnClickListener(this);
        btnmemorylightread.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //내부 메모리에 파일을 쓰기
            case R.id.btnmemorylight: fileInnerwrite(); break;
            //내부 메모리에 파일을 읽기
            case R.id.btnmemorylightread: fileInnerread(); break;
        }

    }


    //모듈화 관리-파일쓰기
    public void fileInnerwrite() {
        try{
            FileOutputStream fos = openFileOutput("file.txt",Context.MODE_PRIVATE);
            String str = "쿡북 안드로이드";
            fos.write(str.getBytes());
            fos.close();
            toastDisplay("file.txt가 잘 생성됨");
        }catch (IOException e){

        }

    }

    //모듈화 관리-파일읽기
    public void fileInnerread() {
        FileInputStream fis = null;
        try {
            fis = openFileInput("file.txt");
            byte[] data=new byte[fis.available()];
            fis.read(data);
            toastDisplay(new String(data));
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    //toastmessage
    public void toastDisplay(String s) {
        Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();
    }



}
