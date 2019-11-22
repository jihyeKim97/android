package com.example.gridviewtest;

import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private GridView gridView;
    private ArrayList<MyData> list = new ArrayList<>();
    private MyGridAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("영화 포스터");

        gridView = findViewById(R.id.gridView);

        inputData();
        adapter = new MyGridAdapter(this, R.layout.image_view, list);
        gridView.setAdapter(adapter);
    }

    private void inputData() {
        Integer[] posterId = {R.drawable.mov01, R.drawable.mov02,R.drawable.mov03,
                R.drawable.mov04, R.drawable.mov05, R.drawable.mov06,R.drawable.mov07,
                R.drawable.mov08, R.drawable.mov09, R.drawable.mov10};
        for(int i = 0; i < 30; i++){
            list.add(new MyData(posterId[(int)(Math.random()*(10))]));
        }
    }
}