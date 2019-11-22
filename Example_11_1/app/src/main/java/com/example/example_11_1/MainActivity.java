package com.example.example_11_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    int[] imageList = {R.drawable.mov01, R.drawable.mov02, R.drawable.mov03, R.drawable.mov04, R.drawable.mov05, R.drawable.mov06, R.drawable.mov07, R.drawable.mov08, R.drawable.mov09, R.drawable.mov10};
    ArrayList<PosterModel> list = new ArrayList<PosterModel>();
    String[] movieNameList = {"써니","완득이","괴물","라디오스타","비열한거리","왕의남자","아일랜드","웰컴투동막골","헬보이","백투더퓨쳐"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("그리드뷰영화포스터");
        gridView = findViewById(R.id.gridView);

        //데이터를 넣는 작업
        for(int i= 0;i<imageList.length;i++){
            list.add(new PosterModel(imageList[i],movieNameList[i]));
        }

        //어뎁터를 만들고 그리드뷰에 셋팅
        PosterAdapter posterAdapter = new PosterAdapter(MainActivity.this, list ,R.layout.gridlayout);
        gridView.setAdapter(posterAdapter);



    }

}
