package com.example.implcitintenttest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnDial, btnopen, btnGoogleMap, btnGoogleSearch, btnPic, btnSms,btnExit;

    //생명주기
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //MainActivity에서 수많은 오류를 찾을수 있음(=Regex)
        Log.d("MainActivity","onCreate()"/*+savedInstanceState*/);

        btnDial = findViewById(R.id.btnDial);
        btnopen = findViewById(R.id.btnopen);
        btnGoogleMap = findViewById(R.id.btnGoogleMap);
        btnGoogleSearch = findViewById(R.id.btnGoogleSearch);
        btnPic = findViewById(R.id.btnPic);
        btnSms = findViewById(R.id.btnSms);
        btnExit = findViewById(R.id.btnExit);

        btnDial.setOnClickListener(this);
        btnopen.setOnClickListener(this);
        btnGoogleMap.setOnClickListener(this);
        btnGoogleSearch.setOnClickListener(this);
        btnPic.setOnClickListener(this);
        btnSms.setOnClickListener(this);
        btnExit.setOnClickListener(this);

    }

    @Override
    protected void onRestart() {
        Log.d("MainActivity","onRestart()");
        super.onRestart();
    }

    @Override
    protected void onStart() {
        Log.d("MainActivity","onStart()");
        super.onStart();
    }


    @Override
    protected void onResume() {
        Log.d("MainActivity","onResume()");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("MainActivity","onPause()");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("MainActivity","onStop()");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("MainActivity","onDestroy()");
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        Uri uri = null;
        Intent intent = null;

        switch (v.getId()) {
            case R.id.btnDial:
                uri = Uri.parse("tel:010-1234-5678");
                intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
                break;
            case R.id.btnopen:
                uri = Uri.parse("http://www.naver.com");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case R.id.btnGoogleMap:
                uri = Uri.parse("http://maps.google.com/maps?q="+37.562128+"," +127.035181);
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            case R.id.btnGoogleSearch:
                intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY,"신방빌딩");
                startActivity(intent);
                break;
            case R.id.btnPic:
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
                break;
            case R.id.btnSms:
                intent = new Intent(Intent.ACTION_SENDTO);
                intent.putExtra("sms_body","담배좀 그만펴");
                intent.setData(Uri.parse("smsto:"+Uri.encode("010-6761-2714")));
                startActivity(intent);
                break;
            case R.id.btnExit:
                finish();
                break;
        }
    }

}
