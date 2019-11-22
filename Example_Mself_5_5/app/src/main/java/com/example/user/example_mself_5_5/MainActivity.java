package com.example.user.example_mself_5_5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnAdd,btnSub,btnMul,btnDiv;
    EditText edttxt1,edttxt2;
    TextView txtView;
    String num1,num2;
    Double result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd=(Button)findViewById(R.id.btnAdd);
        btnSub=(Button)findViewById(R.id.btnSub);
        btnMul=(Button)findViewById(R.id.btnMul);
        btnDiv=(Button)findViewById(R.id.btnDiv);

        edttxt1=(EditText)findViewById(R.id.edttxt1);
        edttxt2=(EditText)findViewById(R.id.edttxt2);

        txtView=(TextView)findViewById(R.id.txtView);

        //숫자 버튼 이벤트(on click)
        final Button[] numButtons = new Button[10];
        final Integer[] number = {R.id.btn0,R.id.btn1,R.id.btn2,R.id.btn3,R.id.btn4,R.id.btn5,R.id.btn6,R.id.btn7,R.id.btn8,R.id.btn9};
        for(int i=0;i<number.length;i++){
            final int index =i;
            numButtons[index]=findViewById(number[index]);
            numButtons[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(edttxt1.isFocused()){
                        String oldValue=numButtons[index].getText().toString();
                        edttxt1.setText(oldValue+numButtons[index].getText().toString());
                    }else if(edttxt2.isFocused()){
                        String oldValue=numButtons[index].getText().toString();
                        edttxt2.setText(oldValue+numButtons[index].getText().toString());
                    }else {
                        Toast.makeText(getApplicationContext(), "포커스오류", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edttxt1.getText().toString().equals("")||edttxt2.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "빈칸없이 작성", Toast.LENGTH_SHORT).show();
                }else {
                    num1 = edttxt1.getText().toString();
                    num2 = edttxt2.getText().toString();
                    result = Double.parseDouble(num1) +Double.parseDouble(num2);
                    txtView.setText("계산 결과 : " + result.toString());
                }
            }
        });
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edttxt1.getText().toString().equals("")||edttxt2.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "빈칸없이 작성", Toast.LENGTH_SHORT).show();
                }else {
                    num1 = edttxt1.getText().toString();
                    num2 = edttxt2.getText().toString();
                    result = Double.parseDouble(num1) -Double.parseDouble(num2);
                    txtView.setText("계산 결과 : " + result.toString());
                }
            }
        });
        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edttxt1.getText().toString().equals("")||edttxt2.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "빈칸없이 작성", Toast.LENGTH_SHORT).show();
                }else {
                    num1 = edttxt1.getText().toString();
                    num2 = edttxt2.getText().toString();
                    result = (Double.parseDouble(num1)) *(Double.parseDouble(num2));
                    txtView.setText("계산 결과 : " + result.toString());
                }
            }
        });
        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edttxt1.getText().toString().equals("")||edttxt2.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "빈칸없이 작성", Toast.LENGTH_SHORT).show();
                }else {
                    num1 = edttxt1.getText().toString();
                    num2 = edttxt2.getText().toString();
                    result = (Double.parseDouble(num1)) /(Double.parseDouble(num2));
                    txtView.setText("계산 결과 : " + result.toString());
                }
            }
        });

    }
}
