package com.example.customlistviewstringtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<MyCustomDAO> list=new ArrayList<MyCustomDAO>();
    private  MyCustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        listInsertData();

       adapter= new MyCustomAdapter(getApplicationContext(),R.layout.list_item_data,list);
       listView.setAdapter(adapter);

    }

    private void listInsertData() {
        for(int i =0;i<30;i++){
            final int index = i;
            list.add(new MyCustomDAO("김 완두네 "+i));
        }

    }
}
