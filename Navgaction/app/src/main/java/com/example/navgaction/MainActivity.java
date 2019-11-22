package com.example.navgaction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {

    DrawerLayout mainDrawerLAyOut;
    Button btnClick,btnClose;
    LinearLayout drawerMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainDrawerLAyOut = findViewById(R.id.mainDrawerLAyOut);
        btnClick = findViewById(R.id.btnClick);
        btnClose = findViewById(R.id.btnClose);
        drawerMenu = findViewById(R.id.drawerMenu);

        btnClick.setOnClickListener(this);
        btnClose.setOnClickListener(this);
        drawerMenu.setOnTouchListener(this);
        mainDrawerLAyOut.setDrawerListener(listener);
    }
    DrawerLayout.DrawerListener listener = new DrawerLayout.DrawerListener(){

        //슬라이딩을 시작했을 때 이벤트 발생
        @Override
        public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
            Toast.makeText(getApplicationContext(),"start sliding",Toast.LENGTH_LONG).show();
        }

        //메뉴가 열렸을 때 이벤트 발생
        @Override
        public void onDrawerOpened(@NonNull View drawerView) {

        }

        //메뉴를 닫았을 때 이벤트 발생
        @Override
        public void onDrawerClosed(@NonNull View drawerView) {

            Toast.makeText(getApplicationContext(),"closed menu",Toast.LENGTH_LONG).show();
        }

        //메뉴바의 상태가 바뀌었을 때 이벤트 발생
        @Override
        public void onDrawerStateChanged(int newState) {

        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnClick:
                mainDrawerLAyOut.openDrawer(drawerMenu);
                break;
            case R.id.btnClose:
                mainDrawerLAyOut.closeDrawer(drawerMenu);
                break;
        }//end of switch
    }//end of onClick

    //drawerMenu를 터치했을때 발생하고자 하는 이벤트 처리
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        return true;
    }//end of onTouch
}
