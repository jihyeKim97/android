package com.example.example_10_1_rdonewpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    RadioButton rdo2, rdo3;
    Button btnOpen;
    RadioGroup rdoG;
    int selectnum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOpen = findViewById(R.id.btnOpen);
        rdo2 = findViewById(R.id.rdo2);
        rdo3 = findViewById(R.id.rdo3);
        rdoG = findViewById(R.id.rdoG);
        rdoG.setOnCheckedChangeListener(this);
        btnOpen.setOnClickListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rdo2:
                selectnum = 1;
                break;
            case R.id.rdo3:
                selectnum = 2;
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (selectnum) {
            case 1:
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
                break;
            case 2:
                Intent intent1 = new Intent(MainActivity.this, ThirdActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
