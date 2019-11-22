package com.example.example_10_1_rdonewpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
  Button btnAgain;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        btnAgain = findViewById(R.id.btnAgain);
        btnAgain.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        setResult(1001,intent);
        //자기를 죽이면서 원위치로 돌아간다
        finish();
    }
}
