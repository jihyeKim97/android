package com.example.fragmentviewtest_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.fregmentviewtest.Fragment1_Activity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnMenu1, btnMenu2, btnMenu3, btnMenu4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMenu1 = findViewById(R.id.btnMenu1);
        btnMenu2 = findViewById(R.id.btnMenu2);
        btnMenu3 = findViewById(R.id.btnMenu3);
        btnMenu4 = findViewById(R.id.btnMenu4);


        btnMenu1.setOnClickListener(this);
        btnMenu2.setOnClickListener(this);
        btnMenu3.setOnClickListener(this);
        btnMenu4.setOnClickListener(this);

        btnMenu1.callOnClick();
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment fragmentActivity = null;
        switch (v.getId()){
            case R.id.btnMenu1: fragmentActivity= new Fragment1_Activity(); break;
            case R.id.btnMenu2: fragmentActivity= new Fragment2_Activity();break;
            case R.id.btnMenu3: fragmentActivity= new Fragment3_Activity();break;
            case R.id.btnMenu4: fragmentActivity= new Fragment4_Activity();break;
        }
        //바꿔주다
        fragmentTransaction.replace(R.id.frameLayOut, fragmentActivity);
        fragmentTransaction.commit();
    }
}
