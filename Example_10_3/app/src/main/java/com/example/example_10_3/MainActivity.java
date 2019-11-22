package com.example.example_10_3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    EditText Number1,Number2;
    RadioGroup rdoG;
    RadioButton rdoPlus,rdoSub,rdoMul,rdoDiv;
    Button btnCal;
    int saveCalValue;
    int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Number1 = findViewById(R.id.Number1);
        Number2 = findViewById(R.id.Number2);
        rdoG = findViewById(R.id.rdoG);
        rdoPlus = findViewById(R.id.rdoPlus);
        rdoSub = findViewById(R.id.rdoSub);
        rdoMul = findViewById(R.id.rdoMul);
        rdoDiv = findViewById(R.id.rdoDiv);
        btnCal = findViewById(R.id.btnCal);

        rdoG.setOnCheckedChangeListener(this);
        btnCal.setOnClickListener(this);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rdoPlus:
                saveCalValue = 1;
                break;
            case R.id.rdoSub:
                saveCalValue = 2;
                break;
            case R.id.rdoMul:
                saveCalValue = 3;
                break;
            case R.id.rdoDiv:
                saveCalValue = 4;
                break;
        }
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(MainActivity.this,SecondActivity.class);
        intent.putExtra("calValue", saveCalValue);
        intent.putExtra("num1",Integer.parseInt(Number1.getText().toString().trim()));
        intent.putExtra("num2",Integer.parseInt(Number2.getText().toString().trim()));
        startActivityForResult(intent,10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 10 && resultCode == 11){
            result = data.getIntExtra("result",0);
            Toast.makeText(getApplication(),"결과는"+result,Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplication(),"없음",Toast.LENGTH_LONG).show();
        }

    }
}
