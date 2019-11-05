package com.example.example_6_3_tabhost;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TabActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TabHost;

public class MainActivity extends TabActivity {
    ImageView imgDog,imgCat,imgRabbit,imgPony;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabHost tabHost = getTabHost();

        imgDog=(ImageView)findViewById(R.id.imgDog);
        imgCat=(ImageView)findViewById(R.id.imgCat);
        imgRabbit=(ImageView)findViewById(R.id.imgRabbit);
        imgPony=(ImageView)findViewById(R.id.imgPony);

        imgDog.setImageResource(R.drawable.bbang);
        imgCat.setImageResource(R.drawable.fanda);
        imgRabbit.setImageResource(R.drawable.hhhha);
        imgPony.setImageResource(R.drawable.brid);

        TabHost.TabSpec imaViewDog = tabHost.newTabSpec("Dog").setIndicator("강아지");
        imaViewDog.setContent(R.id.imgDog);
        tabHost.addTab(imaViewDog);


        TabHost.TabSpec imaViewCat = tabHost.newTabSpec("Cat").setIndicator("고양이");
        imaViewCat.setContent(R.id.imgCat);
        tabHost.addTab(imaViewCat);


        TabHost.TabSpec imaViewRabbit = tabHost.newTabSpec("Rabbit").setIndicator("토끼");
        imaViewRabbit.setContent(R.id.imgRabbit);
        tabHost.addTab(imaViewRabbit);


        TabHost.TabSpec imaViewPony = tabHost.newTabSpec("Pony").setIndicator("말");
        imaViewPony.setContent(R.id.imgPony);
        tabHost.addTab(imaViewPony);


        tabHost.setCurrentTab(0);


    }
}
