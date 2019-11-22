package com.example.example_mp3_player;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SubActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton btMyList;
    ListView myListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wishlistview);
        setTitle("[ # MY PLAY LIST ]");

        btMyList = findViewById(R.id.btMyList);
        myListView = findViewById(R.id.myListView);

        btMyList.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        setResult(1001,intent);
        //자기를 죽이면서 원위치로 돌아간다
        finish();
    }
}
