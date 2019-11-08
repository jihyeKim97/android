package com.example.example_7_6_animal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {

    Button btnShow;
    ImageView img;
    RadioButton rdoDog, rdoCat, rdoRabbit;
    View dialogView;
    int imageAddress;
    String animal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnShow = (Button) findViewById(R.id.btnShow);

        rdoDog = (RadioButton) findViewById(R.id.rdoDog);
        rdoCat = (RadioButton) findViewById(R.id.rdoCat);
        rdoRabbit = (RadioButton) findViewById(R.id.rdoRabbit);

        rdoDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageAddress = R.drawable.bbang;
                animal = "멍멍이";
            }
        });

        rdoCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageAddress = R.drawable.brid;
                animal = "냥냥이";
            }
        });

        rdoRabbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageAddress = R.drawable.fanda;
                animal = "토끼";
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //인스턴스로 변환
                dialogView = View.inflate(MainActivity.this, R.layout.dialogview, null);
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle(animal);
                dialog.setView(dialogView);
                //R.layout.dialogview에 있는 이미지뷰를 가져옴
                img = (ImageView) dialogView.findViewById(R.id.img);
                img.setImageResource(imageAddress);
                dialog.show();
            }

        });


    }
}
