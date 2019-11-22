package com.example.intent_activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnClick;
    EditText edtText1, edtText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnClick = findViewById(R.id.btnClick);
        edtText1 = findViewById(R.id.edtText1);
        edtText2 = findViewById(R.id.edtText2);

        btnClick.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //인센트를 부름(명시적 인텐트==값전달 안함)
        Intent intent = new Intent(MainActivity.this, SubActivity.class);

        //인센트를 부름(명시적 인텐트==값전달함)
        //NO1 == 조금한프로토콜
        //데이터를 보낼때!
        intent.putExtra("NO1", Integer.parseInt(edtText1.getText().toString().trim()));
        intent.putExtra("NO2", Integer.parseInt(edtText2.getText().toString().trim()));
        //requerCode
        startActivityForResult(intent, 1000);
        //startActivity(intent);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent returnIntent) {
        super.onActivityResult(requestCode, resultCode, returnIntent);
        if(requestCode == 1000 &&resultCode == 1001){
        int sum = returnIntent.getIntExtra("sum",0);
            Toast.makeText(getApplicationContext(),"합계"+sum,Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(),"nononnononononnon!!",Toast.LENGTH_LONG).show();
        }
    }
}
