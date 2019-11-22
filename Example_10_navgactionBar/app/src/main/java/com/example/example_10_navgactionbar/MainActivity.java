package com.example.example_10_navgactionbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {
    Button btnMenu, btnGallery, btnSlid,btnClose,btnClick;
    DrawerLayout mainDrawerLAyOut;
    LinearLayout drawerMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainDrawerLAyOut = findViewById(R.id.mainDrawerLAyOut);
        btnMenu = findViewById(R.id.btnMenu);
        btnGallery = findViewById(R.id.btnGallery);
        btnSlid = findViewById(R.id.btnSlid);
        btnClick = findViewById(R.id.btnClick);
        btnClose = findViewById(R.id.btnClose);
        drawerMenu = findViewById(R.id.drawerMenu);


        mainDrawerLAyOut.setDrawerListener(listener);
        drawerMenu.setOnTouchListener(this);
        btnMenu.setOnClickListener(this);
        btnGallery.setOnClickListener(this);
        btnSlid.setOnClickListener(this);
        btnClick.setOnClickListener(this);
        btnClose.setOnClickListener(this);

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
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment fragmentActivity = null;
        Toast.makeText(getApplicationContext(),"dddd",Toast.LENGTH_LONG).show();
        switch (v.getId()){
            case R.id.btnClick: mainDrawerLAyOut.openDrawer(drawerMenu);return;
            case R.id.btnClose: mainDrawerLAyOut.closeDrawer(drawerMenu);return;
            case R.id.btnMenu: fragmentActivity= new Fragment1_Activity();/*mainDrawerLAyOut.closeDrawer(drawerMenu);*/break;
            case R.id.btnGallery: fragmentActivity= new Fragment2_Activity();/*mainDrawerLAyOut.closeDrawer(drawerMenu);*/break;
            case R.id.btnSlid: fragmentActivity= new Fragment3_Activity();/*mainDrawerLAyOut.closeDrawer(drawerMenu);*/break;

        }
        //바꿔주다
        fragmentTransaction.replace(R.id.frameLayOut, fragmentActivity);
        fragmentTransaction.commit();
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }
}
