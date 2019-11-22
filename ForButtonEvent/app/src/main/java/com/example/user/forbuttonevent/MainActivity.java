package com.example.user.forbuttonevent;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
   //1.변수 선언 : 반드시 xml화면의 위젯의 아이디와 일치시킬것
    Button btnNate,btnHoney, btnGallery, btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //2.화면객체를 찾아서 가져온다(형변환)
        setContentView(R.layout.activity_main);
        btnNate = (Button)findViewById(R.id.btnNate);
        btnHoney = (Button)findViewById(R.id.btnHoney);
        btnGallery = (Button)findViewById(R.id.btnGallery);
        btnExit = (Button)findViewById(R.id.btnExit);

        //3.btnNate이벤트를 건다
        btnNate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //인텐트 기술
                Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("http://m.nate.com"));
                startActivity(intent);
            }
        });
        //3.btnHoney이벤트를 건다
        btnHoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //인텐트 기술
                Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("tel:/010-8484-5881"));
                startActivity(intent);
            }
        });
        //3.btnGallery이벤트를 건다
        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //인텐트 기술
                Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("content://media/internal/images/media"));
                startActivity(intent);
            }
        });
        //3.btnExit이벤트를 건다
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
