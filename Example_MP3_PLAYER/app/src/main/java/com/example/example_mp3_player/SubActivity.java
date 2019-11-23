package com.example.example_mp3_player;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import static com.example.example_mp3_player.MainActivity.myDBHelper;
import static com.example.example_mp3_player.MainActivity.sqlDB;

public class SubActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton btMyList;
    ListView myListView;

    private ArrayList<MyData> list = new ArrayList<>();
    private MyWishAdapter myWishAdapterdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wishlistview);
        setTitle("[ # MY PLAY LIST ]");

        btMyList = findViewById(R.id.btMyList);
        myListView = findViewById(R.id.myListView);
        insertListData();
        btMyList.setOnClickListener(this);
        myWishAdapterdapter = new MyWishAdapter(list,R.layout.wish_listview,this);
        myListView.setAdapter(myWishAdapterdapter);

        myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                myDBHelper = new MyDBHelper(parent.getContext());
                sqlDB = myDBHelper.getWritableDatabase();
                sqlDB.execSQL("DELETE FROM musicTBL WHERE malbum ='" + list.get(position).getAblumId() + "';");
                sqlDB.close();
                insertListData();
                myWishAdapterdapter.notifyDataSetChanged();
                return false;
            }
        });

    }

    public void insertListData() {
        myDBHelper = new MyDBHelper(this);
        sqlDB = myDBHelper.getReadableDatabase();
        Cursor cursor;

        cursor = sqlDB.rawQuery("SELECT * FROM musicTBL", null);
        list.removeAll(list);
        while(cursor.moveToNext()) {
            String albumName = cursor.getString(0);
            String singerName = cursor.getString(1);

            list.add(new MyData(albumName, singerName));
        }
        cursor.close();
        sqlDB.close();
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        setResult(1001,intent);
        //자기를 죽이면서 원위치로 돌아간다
        finish();
    }
}
