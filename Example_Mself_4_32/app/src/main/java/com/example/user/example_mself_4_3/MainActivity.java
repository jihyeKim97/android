package com.example.user.example_mself_4_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edit1, edit2;
    Button btnAdd, btnSub, btnMul, btnDiv, btnRem;
    TextView textResult;
    String num1, num2;
    Double result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("초간단계산기(수정)");

        edit1 = (EditText)findViewById(R.id.edit1);
        edit2 = (EditText)findViewById(R.id.edit2);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnSub = (Button) findViewById(R.id.btnSub);
        btnMul = (Button) findViewById(R.id.btnMul);
        btnDiv = (Button) findViewById(R.id.btnDiv);
        btnRem = (Button) findViewById(R.id.btnRem);

        textResult = (TextView) findViewById(R.id.textResult);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edit1.getText().toString().equals("")||edit2.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "빈칸없이 작성", Toast.LENGTH_SHORT).show();
                }else{
                    num1 = edit1.getText().toString();
                    num2 = edit2.getText().toString();
                    result = Double.parseDouble(num1) + Double.parseDouble(num2);

                    textResult.setText("계산 결과 : " + Math.round(result*100)/100.0);
                }
            }
        });
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edit1.getText().toString().equals("")||edit2.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "빈칸없이 작성", Toast.LENGTH_SHORT).show();
                }else{
                    num1 = edit1.getText().toString();
                    num2 = edit2.getText().toString();
                    result = Double.parseDouble(num1) - Double.parseDouble(num2);
                    textResult.setText("계산 결과 : " + Math.round(result*100)/100.0);
                }
            }
        });
        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edit1.getText().toString().equals("")||edit2.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "빈칸없이 작성", Toast.LENGTH_SHORT).show();
                }else{
                    num1 = edit1.getText().toString();
                    num2 = edit2.getText().toString();
                    result = (Double.parseDouble(num1)) * (Double.parseDouble(num2));
                    textResult.setText("계산 결과 : " + Math.round(result*100)/100.0);
                }
            }
        });

        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edit1.getText().toString().equals("")||edit2.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "빈칸없이 작성", Toast.LENGTH_SHORT).show();
                }else if(edit1.getText().toString().equals("0")||edit2.getText().toString().equals("0")){
                    Toast.makeText(getApplicationContext(), "0으로는 못나눕니다", Toast.LENGTH_SHORT).show();
                }else{
                    num1 = edit1.getText().toString();
                    num2 = edit2.getText().toString();
                    result = (Double.parseDouble(num1)) / (Double.parseDouble(num2));
                    textResult.setText("계산 결과 : " + Math.round(result*100)/100.0);
                }
            }
        });
        btnRem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edit1.getText().toString().equals("")||edit2.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "빈칸없이 작성", Toast.LENGTH_SHORT).show();
                }else{
                    num1 = edit1.getText().toString();
                    num2 = edit2.getText().toString();
                    result = (Double.parseDouble(num1)) % (Double.parseDouble(num2));
                    textResult.setText("계산 결과 : " + Math.round(result*100)/100.0);
                }
            }
        });
    }
}
