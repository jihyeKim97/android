package com.example.user.example_6_2_viewpplreper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {

    Button btnStart, btnStop;
    ViewFlipper viewFlipper1;
    ImageView img1,img2,img3,img4,img5,img6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
        img1 = (ImageView)findViewById(R.id.img1);
        img2 = (ImageView)findViewById(R.id.img2);
        img3 = (ImageView)findViewById(R.id.img3);
        img4 = (ImageView)findViewById(R.id.img4);
        img5 = (ImageView)findViewById(R.id.img5);
        img6 = (ImageView)findViewById(R.id.img6);
        viewFlipper1 = (ViewFlipper) findViewById(R.id.viewFlipper1);

        img1.setImageResource(R.drawable.gihun);
        img2.setImageResource(R.drawable.goja);
        img3.setImageResource(R.drawable.gomin);
        img4.setImageResource(R.drawable.eeee);
        img5.setImageResource(R.drawable.hib);
        img6.setImageResource(R.drawable.ne);

        btnStart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                viewFlipper1.startFlipping();
                viewFlipper1.setFlipInterval(1400);
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                viewFlipper1.stopFlipping();
            }
        });



    }
}
