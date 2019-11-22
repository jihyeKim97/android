package com.example.example_10_3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    int result;
    Button brnAgain;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        brnAgain = findViewById(R.id.brnAgain);

        brnAgain.setOnClickListener(this);

        Intent intent = getIntent();
        int calValue = intent.getIntExtra("calValue", 0);
        int num1 = intent.getIntExtra("num1", 0);
        int num2 = intent.getIntExtra("num2", 0);

        switch (calValue){
            case 1:
                result = num1+num2;
                break;
            case 2:
                result = num1-num2;
                break;
            case 3:
                result = num1*num2;
                break;
            case 4:
                result = num1/num2;
                break;
        }

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(SecondActivity.this,MainActivity.class);
        intent.putExtra("result",result);
        setResult(11, intent);
        finish();
    }
}
