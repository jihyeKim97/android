package com.example.intent_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SubActivity extends AppCompatActivity implements View.OnClickListener {

    int sum;
    int number1, number2;
    Button btnAgain;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        btnAgain = findViewById(R.id.btnAgain);
        btnAgain.setOnClickListener(this);

        //getIntent속에 값이 들어있음
        //메인에서 보낸 인텐트를 받는다!
        Intent intent =getIntent();
        //데이터를 가져올때!!
         number1 = intent.getIntExtra("NO1",0);
         number2 = intent.getIntExtra("NO2",0);
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        sum = number1+number2;
        intent.putExtra("sum",sum);
        //인텐트를 메인에게 다시 보낸다!!
        setResult(1001,intent);
        //자기를 죽이면서 원위치로 돌아간다
        finish();

    }
}
