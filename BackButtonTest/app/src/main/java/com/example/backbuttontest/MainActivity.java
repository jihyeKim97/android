package com.example.backbuttontest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private long backButtonTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        long gapTime = currentTime - backButtonTime;
        if (gapTime >= 0 && gapTime <= 2000) {
            super.onBackPressed();
        }else {
            backButtonTime = currentTime;
            Toast.makeText(getApplicationContext(),"2초이내 두번 누르면 나가기",Toast.LENGTH_LONG).show();
        }
    }
}
